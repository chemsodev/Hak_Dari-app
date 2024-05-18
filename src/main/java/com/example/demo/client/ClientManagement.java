package com.example.demo.client;

import com.example.demo.Database;
import com.example.demo.alerts.Alerts;
import com.example.demo.user.User;
import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

public class ClientManagement {

    public static void createClient(Client client, User user) {
        Alerts alerts = new Alerts();
        if(user.getRole().getClientManager()) {
            String query = "INSERT INTO Client (Nom, Prenom, Email, Password, Phone) VALUES (?, ?, ?, ?, ?)";

            try (Connection connection = Database.connect()) {
                assert connection != null;
                try (PreparedStatement statement = connection.prepareStatement(query)) {

                    statement.setString(1, client.getNom());
                    statement.setString(2, client.getPrenom());
                    statement.setString(3, client.getEmail());
                    statement.setString(4, null);
                    statement.setString(5, client.getPhone());

                    int numRowsAffected = statement.executeUpdate();

                    if (numRowsAffected > 0) {
                        alerts.showAlertSuccessfuly("Added","Client");
                    } else {
                        alerts.showAlertFailedTo("Add","Client");
                    }
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }else{
            alerts.showAlertPermissionError("Client","Add");
        }
    }

    public static void updateClient(User user,Client client) {
        Alerts alerts = new Alerts();
        if(user.getRole().getClientManager()) {
            String query = "UPDATE Client SET Nom=? , Prenom=? , Email=? , Phone=?  where id = ?";

            try (Connection connection = Database.connect()) {
                assert connection != null;
                try (PreparedStatement statement = connection.prepareStatement(query)) {
                    statement.setString(1,client.getNom());
                    statement.setString(2,client.getPrenom());
                    statement.setString(3,client.getEmail());
                    statement.setString(4,client.getPhone());
                    statement.setString(5, String.valueOf(client.getId()));
                    int numRowsAffected = statement.executeUpdate();

                    if (numRowsAffected > 0) {
                        alerts.showAlertSuccessfuly("Updated","Client");
                    } else {
                        alerts.showAlertFailedTo("Update","Client");
                    }
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }else{
            alerts.showAlertPermissionError("Client","Update");
        }
    }

    public static void deleteClient(int id,User user){
        Alerts alerts = new Alerts();
        if(user.getRole().getClientManager()) {
            String query = "delete from Client where id = ?";

            try (Connection connection = Database.connect()) {
                assert connection != null;
                try (PreparedStatement statement = connection.prepareStatement(query)) {

                    statement.setString(1, String.valueOf(id));
                    int numRowsAffected = statement.executeUpdate();

                    if (numRowsAffected > 0) {
                        alerts.showAlertSuccessfuly("Deleted","Client");
                    } else {
                        alerts.showAlertFailedTo("Delete","Client");
                    }
                }
            }catch (SQLIntegrityConstraintViolationException e){

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("cannot delete this client because he have an existing real estate  .");
                alert.showAndWait();
            }
            catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }else{
            alerts.showAlertPermissionError("Client","Delete");
        }
    }

}
