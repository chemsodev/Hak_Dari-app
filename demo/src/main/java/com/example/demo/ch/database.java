/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.ch;

import java.sql.Connection;
import java.sql.DriverManager;
public class database {
    
    public static Connection connectDb(){
        
        try{

            Class.forName("com.mysql.jdbc.Driver");

            return DriverManager.getConnection("jdbc:mysql://localhost/employee", "root", "");
        }catch(Exception e){e.printStackTrace();}
        return null;
    }
    
}
