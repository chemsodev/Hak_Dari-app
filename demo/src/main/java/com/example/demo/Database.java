
package com.example.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

    public static Connection connect(){

        try {

            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Connecting to database...");
            return DriverManager.getConnection("jdbc:mysql://mysql-11303ae7-poodb.a.aivencloud.com:11410/dbusers", "avnadmin", "AVNS_yEC1BwhAN6l7KmBlD8n");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Could not load MySQL driver");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Could not connect to database");
        }
        return null;
    }

}