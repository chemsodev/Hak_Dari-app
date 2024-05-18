package com.example.demo.charge;

import com.example.demo.Database;
import com.example.demo.alerts.Alerts;
import com.example.demo.user.User;
import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ChargeManagement {

    public static void createCharge(Charge charge, User user) {
        Alerts alerts = new Alerts();
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
                        alerts.showAlertSuccessfuly("Added","Charge");
                    }
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }else{
            alerts.showAlertPermissionError("Add","Charge");
        }
    }

    public static void deleteClient(String id,User user){
        Alerts alerts = new Alerts();
        if(user.getRole().getChargeManager()) {
            String query = "delete from charge where charge_id = ?";

            try (Connection connection = Database.connect()) {
                assert connection != null;
                try (PreparedStatement statement = connection.prepareStatement(query)) {

                    statement.setString(1, id);
                    int numRowsAffected = statement.executeUpdate();

                    if (numRowsAffected > 0) {
                        alerts.showAlertSuccessfuly("Deleted","Charge");
                    } else {
                        alerts.showAlertFailedTo("Delete","Charge");
                    }
                }
            }
            catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }else{
            alerts.showAlertPermissionError("Delete","Charge");
        }
    }


}
