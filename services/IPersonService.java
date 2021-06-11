package com.addresbook.services;

import com.addresbook.entity.Person;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;

public interface IPersonService {
    boolean addPerson(Person person);
    void editPerson(String personName,int columnNumber,String editedDetails);
    boolean deletePerson(Person person);
    List<Person> getAllPersons();
    void readFromFile(int options, String filename) throws IOException;
    boolean equals(List<Person> addressbook);

    void showCityData(String city);
    void nameSort();

    void showStateData(String state);

    void sort(String option);


}
