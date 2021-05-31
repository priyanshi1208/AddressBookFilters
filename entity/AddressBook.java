package com.addresbook.entity;

import java.util.List;

public class AddressBook {
    public List<Person> addressbook;
    private String id;
    private String addressBookName;

    public AddressBook(String id, String addressBookName, List<Person> addressbook) {
        this.id = id;
        this.addressBookName = addressBookName;
        this.addressbook = addressbook;
    }
    public String toString(){
        return String.format(id+" "+addressBookName+" "+addressbook);
    }
}
