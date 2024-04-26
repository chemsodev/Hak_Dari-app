
package com.example.demo;

import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

    public static Connection connect(){

        try {

            System.out.println("Connecting to database...");
            return DriverManager.getConnection("jdbc:mysql://mysql-11303ae7-poodb.a.aivencloud.com:11410/dbusers", "avnadmin", "AVNS_yEC1BwhAN6l7KmBlD8n");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Could not connect to database");
            //Alert if Connection failed
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Connection to MySQL failed \n Please Check Your Internet Connection");
            alert.showAndWait();
        }
        return null;
    }

}