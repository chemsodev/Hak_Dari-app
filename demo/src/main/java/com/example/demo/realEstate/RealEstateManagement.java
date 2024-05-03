package com.example.demo.realEstate;

import com.example.demo.Database;
import com.example.demo.user.User;
import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

public class RealEstateManagement {

    public void createRealEstate(RealEstate realEstate,User user,int id_Owner) {
        if(user.getRole().getRealEstateManager()) {
            LocalDate currentDate = LocalDate.now();
            String query = "insert into RealEstate (Title,Description,Price,Area,Address,Type,Date_Creation,id_Owner) values(?,?,?,?,?,?,?,?,?)";

            try (Connection connection = Database.connect();
                 PreparedStatement preparedStatement = connection.prepareStatement(query)) {

                preparedStatement.setString(1, realEstate.getTitle());
                preparedStatement.setString(2, realEstate.getDescription());
                preparedStatement.setDouble(3, realEstate.getPrice());
                preparedStatement.setDouble(4, realEstate.getArea());
                preparedStatement.setString(5, realEstate.getAddress());
                preparedStatement.setInt(6,realEstate.getType());
                preparedStatement.setDate(7, Date.valueOf(currentDate));
                preparedStatement.setInt(8, id_Owner);


                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Data inserted successfully!");
                } else {
                    System.out.println("Failed to insert data.");
                }

            } catch (SQLException e) {
                System.err.println("Error during data insertion: " + e.getMessage());
            }
        }else{
            System.out.println("You do not have permission to add Real Estate.");
            //Alert
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Permission");
            alert.setHeaderText("Permission denied");
            alert.setContentText("You do not have permission to add Real Estate.");
            alert.showAndWait();
        }
    }

    public int updateRealEstate(RealEstate realEstate,User user) {
        return 0;
    }

    public int deleteRealEstate(RealEstate realEstate,User user) {
        return 0;
    }
}
