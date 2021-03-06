package com.addresbook.controller;
import com.addresbook.entity.Person;
import com.addresbook.services.*;

import java.io.IOException;
import java.util.*;
public class PersonController {

    public static String firstname;
    Scanner scanner=new Scanner(System.in);
    IPersonService personService=new PersonService();
    PersonServiceDb personServiceDb=new PersonServiceDb();
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
        this.personServiceDb.addPersonDb(person,activeBook);
    }
    public void editPerson(){
        System.out.println("Enter Name of the person to edit details");
        String personName=scanner.next();
        System.out.println("Enter Column number you want to edit");
        System.out.println("1:Firstname, 2:LatName, 3:City, 4:State, 5:Email, 6:Zip, 7:Phone-Number");
        int columnNumber=scanner.nextInt();
        System.out.println("Write corresponding column name");
        String columnName=scanner.next();
        System.out.println("Enter new detail");
        String editedDetail=scanner.next();
        PersonService personService=new PersonService();
        personService.editPerson(personName, columnNumber,editedDetail);
        personServiceDb.editPersonDb(personName,columnName,editedDetail);
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
                personServiceDb.showCityDataDb(city);
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
    public void sort(){
        System.out.println("Select the way to sort the details\n"+
                "1.City\n"+
                "2.State\n"+
                "3.Zip");
        String option=scanner.next();
        personService.sort(option);
    }
    public void getAllPersons() throws IOException {
        System.out.println("Choose where to print Person data\n "+
                "1.Console\n"+
                "2.File\n"+
                "3.CSV\n"+
                "4.Json");
        int options = scanner.nextInt();
        System.out.println("Enter the file name you want to read data from");
        String filename=scanner.next();
        personService.writeToFile(options,filename);
    }
    public void readFromFile() throws IOException {
        System.out.println("Choose from where to read Person data\n "+
                "1.TextFile\n"+
                "2.CSV\n"+
                "3.Json");
        int options = scanner.nextInt();
        System.out.println("Enter the file name you want to read data from");
        String filename=scanner.next();
        personService.readFromFile(options,filename);
    }

}
