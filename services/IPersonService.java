package com.addresbook.services;

import com.addresbook.entity.Person;

import java.util.Comparator;
import java.util.List;

public interface IPersonService {
    boolean addPerson(Person person);
    void editPerson(String personName,int columnNumber,String editedDetails);
    boolean deletePerson(Person person);
    List<Person> getAllPersons();

    boolean equals(List<Person> addressbook);

    void showCityData(String city);
    void nameSort();

    void showStateData(String state);

    void sort(String option);
}
