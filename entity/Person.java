package com.addresbook.entity;

public class Person {
    private String first_name,last_name,city,state,email;
    private String zip;
    private String phone_number;

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    @Override
    public String toString() {
        return String.format(first_name+" "+last_name+" "+city+" "+state+" "+email+" "+zip+" "+phone_number);
    }

    public Person(String first_name, String last_name, String city, String state, String email, String zip, String phone_number) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.city = city;
        this.state = state;
        this.email = email;
        this.zip = zip;
        this.phone_number = phone_number;
    }
}
