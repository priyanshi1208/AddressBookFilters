package com.addresbook.services;

import com.addresbook.entity.AddressBook;
import com.addresbook.entity.Person;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class AddressBookService implements IAddressBookService{
    public static Map<String,AddressBook> allAddressbook=new HashMap<>();
    public static String activeAddressBook=null;

    @Override
    public boolean createAddressBook(String addressBookName) {
        String addressbookId= UUID.randomUUID().toString();
        List<Person> personList=new ArrayList<>();
        AddressBook addressbook=new AddressBook(addressbookId,addressBookName,personList);
        allAddressbook.put(addressBookName,addressbook);
        return true;
    }

    @Override
    public Map<String, AddressBook> getAllAddressBook() {
        return this.allAddressbook;
    }

    @Override
    public boolean openAddressBook(String addressBookName) {
        this.activeAddressBook=addressBookName;
        System.out.println("Current AddressBook is:"+addressBookName);
        return true;
    }

    @Override
    public boolean closeAddressBook() {
        this.activeAddressBook=null;
        System.out.println("Exit from Addressbook");
        return true;
    }
}
