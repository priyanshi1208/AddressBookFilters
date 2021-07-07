package com.addresbook.services;
import com.addresbook.entity.Person;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class PersonServiceDb {
    JDBCConnection jdbcConnection=new JDBCConnection();
    AddressBookServiceDb addressBookServiceDb=new AddressBookServiceDb();
    public void addPersonDb(Person person,String addressBookName) {
        Connection connection = jdbcConnection.getConnection();
        try {
            int id = addressBookServiceDb.insertIntoSpecificAddressBook(addressBookName);
            connection.prepareStatement(String.format
                    ("Insert into person where addressBookId=%d,firstName=%s,lastName=%s,city=%s,state=%s," +
                    "email=%s,zip=%s,phoneNumber=%s",id,person.getFirst_name(),person.getLast_name(),person.getCity(),
                            person.getState(),person.getEmail(),person.getZip(),person.getPhone_number())).executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void editPersonDb(String personName, String columnName, String editedDetail) {
        Connection connection= jdbcConnection.getConnection();
        try {
            connection.prepareStatement(String.format
                    ("Update table person set %s=%s where firstName=%d",columnName,editedDetail,personName)).executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void showCityDataDb(String city) {
        Connection connection= jdbcConnection.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("Select * from person where city=?");
            preparedStatement.setString(1,city);
            ResultSet resultSet=preparedStatement.executeQuery();
            while(resultSet.next()){
                System.out.println(resultSet.getString("firstName")+
                        resultSet.getString("lastName")+
                        resultSet.getString("city")+
                        resultSet.getString("state")+
                        resultSet.getString("email")+
                        resultSet.getString("zip")+
                        resultSet.getString("phoneNumber"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
