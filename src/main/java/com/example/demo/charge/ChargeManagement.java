package com.example.demo.charge;

import com.example.demo.Database;
import com.example.demo.user.User;
import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ChargeManagement {

    public static void createCharge(Charge charge, User user) {
        if(user.getRole().getChargeManager()) {
            String query = "INSERT INTO charge (Title, Description, Total) VALUES (?,?,?)";

            try (Connection connection = Database.connect()) {
                assert connection != null;
                try (PreparedStatement statement = connection.prepareStatement(query)) {

                    statement.setString(1, charge.getTitle());
                    statement.setString(2, charge.getDescription() );
                    statement.setString(3, String.valueOf(charge.getTotal()));


                    int numRowsAffected = statement.executeUpdate();

                    if (numRowsAffected > 0) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Result Message");
                        alert.setHeaderText(null);
                        alert.setContentText("Charge added successfully.");
                        alert.showAndWait();
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error Message");
                        alert.setHeaderText(null);
                        alert.setContentText("Failed to add Charge.");
                        alert.showAndWait();
                    }
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Permision Error");
            alert.setHeaderText(null);
            alert.setContentText("You don't have permission to add a Charge.");
            alert.showAndWait();
        }
    }

    public static void deleteClient(String id,User user){
        if(user.getRole().getChargeManager()) {
            String query = "delete from charge where charge_id = ?";

            try (Connection connection = Database.connect()) {
                assert connection != null;
                try (PreparedStatement statement = connection.prepareStatement(query)) {

                    statement.setString(1, id);
                    int numRowsAffected = statement.executeUpdate();

                    if (numRowsAffected > 0) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Result Message");
                        alert.setHeaderText(null);
                        alert.setContentText("Charge deleted successfully.");
                        alert.showAndWait();
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error Message");
                        alert.setHeaderText(null);
                        alert.setContentText("Failed to delete Charge.");
                        alert.showAndWait();
                    }
                }
            }
            catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Permision Error");
            alert.setHeaderText(null);
            alert.setContentText("You don't have permission to delete a Charge.");
            alert.showAndWait();
        }
    }


}
