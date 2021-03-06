package com.addresbook.controller;

import com.addresbook.entity.AddressBook;
import com.addresbook.services.AddressBookService;
import com.addresbook.services.AddressBookServiceDb;
import com.addresbook.services.IAddressBookService;

import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

public class AddressBookController {
    Scanner scanner = new Scanner(System.in);
    IAddressBookService bookService =new AddressBookService();
    AddressBookServiceDb bookServiceDb=new AddressBookServiceDb();

    public void getAllAddressBook() {
        Map<String, AddressBook> allAddressBook = this.bookService.getAllAddressBook();
        bookServiceDb.retrieveAllAddressBookDb();
        System.out.println(allAddressBook);
    }
    public void createAddressBook() {
        System.out.println("Enter AddressBook Name \n");
        String addressBookName = scanner.nextLine();
        bookService.createAddressBook(addressBookName);
        bookServiceDb.insertAddressbookInDb(addressBookName);
    }
    public boolean openAddressBook() {
        Map<String, AddressBook> allAddressBook = this.bookService.getAllAddressBook();
        System.out.println(allAddressBook.keySet());
        System.out.println("Select One AddressBook !!");
        String addressBook = scanner.nextLine();
        return this.bookService.openAddressBook(addressBook);
    }
    public boolean closeAddressBook() {
        return this.bookService.closeAddressBook();
    }

}
