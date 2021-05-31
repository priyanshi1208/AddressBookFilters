package com.addresbook.services;

import com.addresbook.controller.PersonController;
import com.addresbook.entity.Person;

import java.util.List;

public class PersonService implements IPersonService {
    @Override
    public boolean addPerson(Person person) {
        String activeBook=AddressBookService.activeAddressBook;
        return AddressBookService.allAddressbook.get(activeBook).addressbook.add(person);
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
        AddressBookService.allAddressbook.get(activeBook).addressbook.forEach(p->{
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
    public List<Person> getAllPersons() {
        String activeBook=AddressBookService.activeAddressBook;
        return AddressBookService.allAddressbook.get(activeBook).addressbook;
    }
}
