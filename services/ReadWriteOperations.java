package com.addresbook.services;

import com.addresbook.entity.Person;
import com.google.gson.Gson;
import com.opencsv.CSVWriter;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static com.addresbook.services.AddressBookService.allAddressbook;

public class ReadWriteOperations {
    Path fileLocation = Paths.get("C:\\Users\\ayuanshi\\Documents\\AddressBooks");


    public void printAddressbookIntoFile(String filename,OutputType outputType) throws IOException {
        if(outputType.equals(OutputType.FileInputOutput))
           printInTextFile(filename);
        if(outputType.equals(OutputType.CSVInputOutput))
            printInCsvFile(filename);
        if(outputType.equals(OutputType.JsonInputOutput))
            printInJsonFile(filename);
    }
    public void printInTextFile(String filename) throws IOException {
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
    public void printInCsvFile(String filename) throws IOException {
        Path csvfile = Files.createFile(Path.of(fileLocation + "/" +filename+ ".csv"));
        BufferedWriter writer = Files.newBufferedWriter(csvfile);
        StatefulBeanToCsv<Person> beanToCsv = new StatefulBeanToCsvBuilder<Person>(writer)
                .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER).build();
        String activeBook=AddressBookService.activeAddressBook;
        List<Person> personlist = allAddressbook.get(activeBook).addressbook;
        personlist.forEach(System.out::println);
            try {
                beanToCsv.write(personlist);
            } catch (CsvDataTypeMismatchException | CsvRequiredFieldEmptyException e) {
                e.printStackTrace();
            }
        System.out.println("File created");
    }
    public void printInJsonFile(String filename)throws IOException{
        Path JsonFile=Files.createFile(Path.of(fileLocation + "/" + filename + ".json"));
        Gson gson=new Gson();
        FileWriter writer = new FileWriter(String.valueOf(JsonFile));
        String activeBook=AddressBookService.activeAddressBook;
        List<Person> personlist = allAddressbook.get(activeBook).addressbook;
        String json = gson.toJson(personlist);
        writer.write(json);
        writer.close();

    }
    public  void ReadFile(String filename,OutputType outputType) throws IOException {
        if(outputType.equals(OutputType.FileInputOutput))
           ReadFromTextFile(filename);
        if(outputType.equals(OutputType.CSVInputOutput))
           ReadFrmCsvFile(filename);
        if(outputType.equals(OutputType.JsonInputOutput))
            ReadFromJsonFile(filename);
    }
    public void ReadFromTextFile(String filename) throws IOException {
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
    public void ReadFrmCsvFile(String filename) throws IOException {
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
    public void ReadFromJsonFile(String filename) throws IOException{
        Path JsonFile=Files.createFile(Path.of(fileLocation + "/" + filename + ".json"));
        Gson gson=new Gson();
        Person[] people;
        try (BufferedReader reader = Files.newBufferedReader(JsonFile)) {
            people = gson.fromJson(reader, Person[].class);
        }
        List<Person> personList = Arrays.asList(people);
        for(Person person:personList){
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
