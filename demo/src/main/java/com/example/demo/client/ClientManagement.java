package com.example.demo.client;

import com.example.demo.Database;
import com.example.demo.user.User;
import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ClientManagement {

    public static void createClient(Client client, User user) {
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
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Result Message");
                        alert.setHeaderText(null);
                        alert.setContentText("Client added successfully.");
                        alert.showAndWait();
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error Message");
                        alert.setHeaderText(null);
                        alert.setContentText("Failed to add client.");
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
            alert.setContentText("You don't have permission to add a client.");
            alert.showAndWait();
        }
    }

}
