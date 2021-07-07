package com.addresbook.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCConnection {
    private String url="jdbc:mysql://localhost:3306/addressBook";
    private String userName="root";
    private String password="bunny1234";
    public Connection getConnection(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(url,userName,password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return  null;
    }
}
