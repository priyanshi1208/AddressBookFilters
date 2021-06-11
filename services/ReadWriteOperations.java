package com.addresbook.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static com.addresbook.services.AddressBookService.allAddressbook;

public class ReadWriteOperations implements IReadWriteOperations {
    Path fileLocation = Paths.get("C:\\Users\\ayuanshi\\Documents\\AddressBooks");

    @Override
    public void printAddressbookIntoFile(String filename,OutputType outputType) throws IOException {
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

    @Override
    public void ReadFile(String filename) throws IOException {
        BufferedReader reader=Files.newBufferedReader(Path.of((fileLocation + "/" + filename + ".txt")));
        Files.lines(Path.of(fileLocation + "/" + filename + ".txt")).forEach(lines-> {
            try {
                reader.readLine();
                System.out.println(lines);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
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
