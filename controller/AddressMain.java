package com.addresbook.controller;


import com.addresbook.services.PersonService;

import java.util.ArrayList;
import java.util.Scanner;

public class AddressMain {
    public static boolean abort = false;
    private Scanner scanner = new Scanner(System.in);
    PersonController personController=new PersonController();
    AddressBookController bookController=new AddressBookController();
    PersonService personService=new PersonService();

    public static void main(String[] args) {

        AddressMain addressBookMain = new AddressMain();
        while (true) {
            addressBookMain.addressBookCRUD();
        }
    }
    public void addressBookCRUD() {
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
    public void personCRUD() {
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
                        "2.Show Person detail in current addressbook \n" +
                        "3.Close current AddressBook \n"+
                        "4.Edit person details\n"+
                        "5.Show person city/state vise\n"+
                        "6.Sort By Name\n"+
                        "7.Sort Person by city/state/zip"
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
