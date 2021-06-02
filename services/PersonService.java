package com.addresbook.services;

import com.addresbook.controller.PersonController;
import com.addresbook.entity.AddressBook;
import com.addresbook.entity.Person;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static com.addresbook.services.AddressBookService.allAddressbook;

public class PersonService implements IPersonService {


    @Override
    public boolean addPerson(Person person) {
        String activeBook=AddressBookService.activeAddressBook;
        return allAddressbook.get(activeBook).addressbook.add(person);
    }

    @Override
    public boolean equals(List<Person> addressbook) {
        if(addressbook.isEmpty())
            return true;
        else{
            for (Person p : addressbook) {
                if (p.getFirst_name().equals(PersonController.firstname)) {
                    System.out.println("Person already exists!");
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public void editPerson(String personName,int columnNumber,String editedDetail) {
        String activeBook=AddressBookService.activeAddressBook;
        allAddressbook.get(activeBook).addressbook.forEach(p->{
            if(p.getFirst_name().equalsIgnoreCase(personName)){
                switch(columnNumber){
                    case 1:{
                        p.setFirst_name(editedDetail);
                        break;
                    }
                    case 2:{
                        p.setLast_name(editedDetail);
                        break;
                    }
                    case 3:{
                        p.setCity(editedDetail);
                        break;
                    }
                    case 4:{
                        p.setState(editedDetail);
                        break;
                    }
                    case 5:{
                        p.setEmail(editedDetail);
                        break;
                    }
                    case 6: {
                        p.setZip(editedDetail);
                        break;
                    }
                    case 7:{
                        p.setPhone_number(editedDetail);
                        break;
                    }
                }
            }
        });

    }

    @Override
    public boolean deletePerson(Person person) {
        return true;
    }
    @Override
    public void showCityData(String city){
        System.out.println("Records in city "+city+":");
        long count = 0;
        for (AddressBook a : allAddressbook.values()) {
             count=a.addressbook.stream().filter(Person -> Person.getCity().equalsIgnoreCase(city)).count();
            a.addressbook.stream().filter(Person->Person.getCity().equalsIgnoreCase(city)).forEach(System.out::println);
        }
        System.out.println("There Are "+count+" Records in the addressbook");
    }

    @Override
    public void showStateData(String state){
        System.out.println("Records in city "+state+":");
        long count = 0;
        for (AddressBook a : allAddressbook.values()) {
            count=a.addressbook.stream().filter(Person -> Person.getCity().equalsIgnoreCase(state)).count();
            a.addressbook.stream().filter(Person->Person.getState().equalsIgnoreCase(state)).forEach(System.out::println);
        }
        System.out.println("There Are "+count+" Records in the addressbook");
    }
    public void sorting(Comparator<Person> compare){
        for(AddressBook a:allAddressbook.values()){
            a.addressbook.stream().sorted(compare).collect(Collectors.toList()).forEach(System.out::println);
            //a.addressbook.forEach(System.out::println);
        }
    }
    @Override
    public void nameSort(){
            Comparator<Person> compareName=Comparator.comparing(Person::getFirst_name);
            sorting(compareName);
    }
    @Override
    public void sort(String options){
            switch(options) {
                case "1": {
                    Comparator<Person> compareCity=Comparator.comparing(Person::getCity).thenComparing(Person::getFirst_name);
                    sorting(compareCity);
                    break;
                }
                case "2":{
                    Comparator<Person> compareState=Comparator.comparing(Person::getState).thenComparing(Person::getFirst_name);
                    sorting(compareState);
                    break;
                }
                case "3":{
                    Comparator<Person> compareZip=Comparator.comparing(Person::getZip).thenComparing(Person::getFirst_name);
                    sorting(compareZip);
                    break;
                }
            }
    }

    @Override
    public List<Person> getAllPersons() {
        String activeBook=AddressBookService.activeAddressBook;
        return allAddressbook.get(activeBook).addressbook;
    }
}
