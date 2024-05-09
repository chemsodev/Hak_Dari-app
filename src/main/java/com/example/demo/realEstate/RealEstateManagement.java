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

    public static void createRealEstate(RealEstate realEstate,User user) {
        if(user.getRole().getRealEstateManager()) {
            LocalDate currentDate = LocalDate.now();
            String query = "insert into RealEstate (Title,Description,Price,Area,Address,Type,Date_Creation,id_Owner) VALUES (?,?,?,?,?,?,?,?)";

            try (Connection connection = Database.connect()) {
                assert connection != null;
                try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {

                    preparedStatement.setString(1, realEstate.getTitle());
                    preparedStatement.setString(2, realEstate.getDescription());
                    preparedStatement.setDouble(3, realEstate.getPrice());
                    preparedStatement.setDouble(4, realEstate.getArea());
                    preparedStatement.setString(5, realEstate.getAddress());
                    preparedStatement.setString(6,realEstate.getType());
                    preparedStatement.setDate(7, Date.valueOf(currentDate));
                    preparedStatement.setInt(8, realEstate.getOwnerId());

                    int rowsAffected = preparedStatement.executeUpdate();

                     if (rowsAffected > 0) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Result Message");
                        alert.setHeaderText(null);
                        alert.setContentText("real_estate added successfully.");
                        alert.showAndWait();
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error Message");
                        alert.setHeaderText(null);
                        alert.setContentText("Failed to add real_estate.");
                        alert.showAndWait();
                    }

                }
            } catch (SQLException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Error during data insertion: " + e.getMessage());
                alert.showAndWait();
            }
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Permission");
            alert.setHeaderText("Permission denied");
            alert.setContentText("You do not have permission to add Real Estate.");
            alert.showAndWait();
        }
    }

    public static void updateRealEstate(RealEstate realEstate,User user) {

    }

    public static void deleteRealEstate(RealEstate realEstate,User user) {

    }

}
