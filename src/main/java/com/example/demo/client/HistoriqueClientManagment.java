package com.example.demo.client;

import com.example.demo.Database;
import com.example.demo.user.User;

import java.sql.*;

public class HistoriqueClientManagment {

    public static void addUpdateHistorique(User user,String msg,Client client){

        String query = "INSERT INTO HistoriqueClient (Description,userId,ClientId) VALUES (?, ?, ?)";
        try (Connection connection = Database.connect()) {
            assert connection != null;
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {

                preparedStatement.setString(1, msg);
                preparedStatement.setInt(2, user.getId());
                preparedStatement.setInt(3, client.getId());

                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Historique added Successfully");
                } else {
                    System.err.println("Failed to add to Historique");
                }
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static void addAddHistorique(User user, String msg, Client client) {

        String subQuery = "SELECT Id FROM Client WHERE Nom=? AND Prenom=? AND Email=?";

        String query = "INSERT INTO HistoriqueClient (Description, userId, ClientId) VALUES (?, ?, ?)";

        try (Connection connection = Database.connect()) {
            assert connection != null;

            // Step 1: Retrieve the Client ID
            int clientId = -1;
            try (PreparedStatement subPreparedStatement = connection.prepareStatement(subQuery)) {
                subPreparedStatement.setString(1, client.getNom());
                subPreparedStatement.setString(2, client.getPrenom());
                subPreparedStatement.setString(3, client.getEmail());

                try (ResultSet resultSet = subPreparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        clientId = resultSet.getInt("Id");
                    } else {
                        System.err.println("Client not found");
                        return;
                    }
                }
            }

            // Step 2: Insert into HistoriqueClient using the retrieved Client ID
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, msg);
                preparedStatement.setInt(2, user.getId());
                preparedStatement.setInt(3, clientId);

                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Historique added successfully");
                } else {
                    System.err.println("Failed to add to Historique");
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static void addDeleteHistorique(User user,String msg){

        String query = "INSERT INTO HistoriqueClient (Description,userId) VALUES (?, ?)";
        try (Connection connection = Database.connect()) {
            assert connection != null;
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {

                preparedStatement.setString(1, msg);
                preparedStatement.setInt(2, user.getId());

                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Historique added Successfully");
                } else {
                    System.err.println("Failed to add to Historique");
                }
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static void addAddRealEstateHistorique(User user, String msg, int id) {


        String query = "INSERT INTO HistoriqueClient (Description,userId,ClientId) VALUES (?, ?, ?)";
        try (Connection connection = Database.connect()) {
            assert connection != null;
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {

                preparedStatement.setString(1, msg);
                preparedStatement.setInt(2, user.getId());
                preparedStatement.setInt(3, id);

                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Historique added Successfully");
                } else {
                    System.err.println("Failed to add to Historique");
                }
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
