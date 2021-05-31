package com.addresbook.services;

import com.addresbook.entity.AddressBook;
import com.addresbook.entity.Person;

import java.util.*;

public interface IAddressBookService {
    boolean createAddressBook(String addressBookName);
    Map<String, AddressBook> getAllAddressBook();
    boolean openAddressBook(String addressBookName);
    boolean closeAddressBook();
}
