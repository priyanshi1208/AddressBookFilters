package com.addresbook.controller;
import com.addresbook.entity.Person;
import com.addresbook.services.AddressBookService;
import com.addresbook.services.IPersonService;
import com.addresbook.services.PersonService;

import java.util.*;
public class PersonController {

    public static String firstname;
    Scanner scanner=new Scanner(System.in);
    IPersonService personService=new PersonService();
    public void addPerson() {
        String activeBook=AddressBookService.activeAddressBook;
        do{
            System.out.println("Enter Person FirstName");
             firstname=scanner.next();
        }while(!personService.equals(AddressBookService.allAddressbook.get(activeBook).addressbook));
        System.out.println("Enter Person LastName \n");
        String lastName = scanner.next();
        System.out.println("Enter Person City \n");
        String city = scanner.next();

        System.out.println("Enter Person State \n");
        String state = scanner.next();
        System.out.println("Enter Person Email \n");
        String email = scanner.next();
        System.out.println("Enter Person Zip \n");
        String zip = scanner.next();
        System.out.println("Enter Person Phone Number \n");
        String phoneNumber = scanner.next();

        Person person = new Person(firstname,lastName, city, state,email,zip,phoneNumber);
        this.personService.addPerson(person);
    }
    public void editPerson(){
        System.out.println("Enter Name of the person to edit details");
        String personName=scanner.next();
        System.out.println("Enter Column number you want to edit");
        System.out.println("1:Firstname, 2:LatName, 3:City, 4:State, 5:Zip, 6:Phone-Number");
        int columnNumber=scanner.nextInt();
        System.out.println("Enter new detail");
        String editedDetail=scanner.next();
        PersonService personService=new PersonService();
        personService.editPerson(personName, columnNumber,editedDetail);
    }
    public void deletePerson(){

    }
    public void showCityStateData(){
        System.out.println("Choose if you want to Select 1:City-Wise,  2:State-Wise");
        int selection=scanner.nextInt();
        switch(selection){
            case 1:{
                System.out.println("Enter name of the city");
                String city=scanner.next();
                personService.showCityData(city);
                break;
            }
            case 2:{
                System.out.println("Enter name of the state");
                String state=scanner.next();
                personService.showStateData(state);
                break;
            }
        }
    }
    public void getAllPersons() {
        List<Person> allPersons = this.personService.getAllPersons();
        System.out.println(allPersons);
    }
}
