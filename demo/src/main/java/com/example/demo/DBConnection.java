package com.example.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    public static final Connection connection;
    static{
        try {
            connection = DriverManager.getConnection("jdbc:mysql://mysql-11303ae7-poodb.a.aivencloud.com:11410/dbusers", "avnadmin", "AVNS_yEC1BwhAN6l7KmBlD8n");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}