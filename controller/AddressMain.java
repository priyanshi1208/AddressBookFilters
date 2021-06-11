package com.addresbook.controller;


import com.addresbook.services.IReadWriteOperations;
import com.addresbook.services.OutputType;
import com.addresbook.services.PersonService;
import com.addresbook.services.ReadWriteOperations;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class AddressMain {
    public static boolean abort = false;
    private Scanner scanner = new Scanner(System.in);
    PersonController personController=new PersonController();
    AddressBookController bookController=new AddressBookController();
    PersonService personService=new PersonService();
    IReadWriteOperations readWriteOpeartions=new ReadWriteOperations();

    public static void main(String[] args) throws IOException {

        AddressMain addressBookMain = new AddressMain();
        while (true) {
            addressBookMain.addressBookCRUD();
        }
    }
    public void addressBookCRUD() throws IOException {
        abort = false;
        int i = this.bookInputs();
        switch (i) {
            case 1:
                this.bookController.createAddressBook();
                break;

            case 2:
                this.bookController.getAllAddressBook();
                break;

            case 3:
                if (this.bookController.openAddressBook()) {
                    while (!abort) this.personCRUD();
                }
            default:
                break;
        }
    }
    public void personCRUD() throws IOException {
        int i = this.personInputs();
        switch (i) {
            case 1:
                this.personController.addPerson();
                break;

            case 2:
                this.personController.getAllPersons();
                break;

            case 3:
                this.bookController.closeAddressBook();
                abort = true;
                this.addressBookCRUD();
            case 4:
                this.personController.editPerson();
                break;
            case 5:
                this.personController.showCityStateData();
                break;
            case 6:
                this.personService.nameSort();
                break;
            case 7:
                this.personController.sort();
                break;
            default:
                break;
        }
    }
    public int personInputs() {
        System.out.println(
                "1.Add Person in currrent addressbook \n" +
                        "2.Print Person detail of current addressbook \n" +
                        "3.Close current AddressBook \n"+
                        "4.Edit person details\n"+
                        "5.Show person city/state vise\n"+
                        "6.Sort By Name\n"+
                        "7.Sort Person by city/state/zip\n"+
                        "8.Print Addressbook into a File\n"
        );
        return scanner.nextInt();
    }


    public int bookInputs() {
        System.out.println(
                "1.Create a new AddressBook \n" +
                        "2.show All AddressBook \n" +
                        "3.Open a specified AddressBook \n"

        );
        return scanner.nextInt();
    }
}
