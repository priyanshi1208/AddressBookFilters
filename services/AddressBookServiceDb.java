package com.addresbook.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AddressBookServiceDb {
    JDBCConnection jdbcConnection=new JDBCConnection();
    public void insertAddressbookInDb(String addressBookName) {
        Connection connection = jdbcConnection.getConnection();
        PreparedStatement preparedStatement= null;
        try {
            preparedStatement = connection.prepareStatement("Insert into addressBook (name)values(?)");
            preparedStatement.setString(1,addressBookName);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void retrieveAllAddressBookDb() {
        Connection connection=jdbcConnection.getConnection();
        ResultSet resultSet = null;
        try {
            resultSet = connection.prepareStatement("Select * from addressbook").executeQuery();
            while (resultSet.next()){
                System.out.println(resultSet.getString("name"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public int insertIntoSpecificAddressBook(String addressBook) {
        Connection connection=jdbcConnection.getConnection();
        try {
            PreparedStatement preparedStatement=connection.prepareStatement("select id from addressBook where id=?");
            preparedStatement.setString(1,addressBook);
            ResultSet resultSet=preparedStatement.executeQuery();
            return resultSet.getInt("id");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }
}
