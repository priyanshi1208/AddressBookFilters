package com.addresbook.services;

import com.addresbook.entity.Person;
import com.opencsv.CSVWriter;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;

import static com.addresbook.services.AddressBookService.allAddressbook;

public class ReadWriteOperations {
    Path fileLocation = Paths.get("C:\\Users\\ayuanshi\\Documents\\AddressBooks");


    public void printAddressbookIntoFile(String filename,OutputType outputType) throws IOException {
        if(outputType.equals(OutputType.FileInputOutput)){
           printInTextFile(filename,OutputType.FileInputOutput);
        }
        if(outputType.equals(OutputType.CSVInputOutput)){
            printInCsvFile(filename,OutputType.CSVInputOutput);
        }
    }
    public void printInTextFile(String filename,OutputType outputType) throws IOException {
        Path file = Files.createFile(Path.of(fileLocation + "/" +filename+ ".txt"));
        StringBuffer buffer = new StringBuffer();
        String activeBook = AddressBookService.activeAddressBook;
        allAddressbook.get(activeBook).addressbook.forEach(Person -> {
            String person = Person.toString().concat("\n");
            buffer.append(person);
            try {
                Files.write(file, buffer.toString().getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        System.out.println("There are "+CountEntries(filename)+" entries in the addressbook");
    }
    public void printInCsvFile(String filename,OutputType outputType) throws IOException {
        Path csvfile = Files.createFile(Path.of(fileLocation + "/" +filename+ ".csv"));
        BufferedWriter writer = Files.newBufferedWriter(csvfile);
        StatefulBeanToCsv<String> beanToCsv = new StatefulBeanToCsvBuilder<String>(writer)
                .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER).build();
        String activeBook=AddressBookService.activeAddressBook;
        allAddressbook.get(activeBook).addressbook.forEach(Person->{
            try {
                beanToCsv.write(Person.toString());
            } catch (CsvDataTypeMismatchException e) {
                e.printStackTrace();
            } catch (CsvRequiredFieldEmptyException e) {
                e.printStackTrace();
            }
        });
    }


    public  void ReadFile(String filename,OutputType outputType) throws IOException {
        if(outputType.equals(OutputType.FileInputOutput)) {
           ReadFromTextFile(filename,OutputType.FileInputOutput);
        }
        if(outputType.equals(OutputType.CSVInputOutput)){
           ReadFrmCsvFile(filename,OutputType.CSVInputOutput);
        }
    }
    public void ReadFromTextFile(String filename,OutputType outputType) throws IOException {
        BufferedReader reader = Files.newBufferedReader(Path.of((fileLocation + "/" + filename + ".txt")));
        Files.lines(Path.of(fileLocation + "/" + filename + ".txt")).forEach(lines -> {
            try {
                reader.readLine();
                System.out.println(lines);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
    public void ReadFrmCsvFile(String filename,OutputType outputType) throws IOException {
        BufferedReader reader = Files.newBufferedReader(Path.of(fileLocation + "/" + filename + ".csv"));
        CsvToBean<Person> csvToBean=new CsvToBeanBuilder<Person>(reader)
                .withType(Person.class).withIgnoreLeadingWhiteSpace(true).build();
        Iterator<Person> iteartor=csvToBean.iterator();
        while(iteartor.hasNext()){
            Person person= iteartor.next();
            System.out.println(person.getFirst_name());
            System.out.println(person.getLast_name());
            System.out.println(person.getCity());
            System.out.println(person.getState());
            System.out.println(person.getEmail());
            System.out.println(person.getZip());
            System.out.println(person.getPhone_number());
            System.out.println();
        }
    }



    public long CountEntries(String filename) {
        long personData=0;
        try {
            personData  = Files.lines(Path.of(fileLocation + "/" + filename + ".txt")).count();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return personData;
    }

}
