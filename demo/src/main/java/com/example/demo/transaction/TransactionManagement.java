package com.example.demo.transaction;

import com.example.demo.Database;
import com.example.demo.user.User;
import javafx.scene.control.Alert;

import java.sql.*;
import java.time.LocalDate;

public class TransactionManagement {


    public void addTransaction(User user, Transaction transaction, int IdPropriete, int IdClient) {
        if(user.getRole().getTransactionManager()) {
            LocalDate currentDate = LocalDate.now();
            String query = "insert into Transaction (Type,DateTransaction,Prix,Frais,MethodePaiement,Statut,Note,Id_Client,Id_Propriete) values(?,?,?,?,?,?,?,?,?)";

            try (Connection connection = Database.connect();
                 PreparedStatement preparedStatement = connection.prepareStatement(query)) {

                preparedStatement.setString(1, transaction.getType());
                preparedStatement.setDate(2, Date.valueOf(currentDate));
                preparedStatement.setDouble(3, transaction.getPrix());
                preparedStatement.setDouble(4, transaction.getFrais());
                preparedStatement.setString(5, transaction.getMethodePaiement());
                preparedStatement.setString(6, transaction.getStatut());
                preparedStatement.setString(7, transaction.getNote());
                preparedStatement.setInt(8, IdClient);
                preparedStatement.setInt(9, IdPropriete);


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
            System.out.println("You do not have permission to add transaction.");
            //Alert
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Permission");
            alert.setHeaderText("Permission denied");
            alert.setContentText("You do not have permission to add transaction.");
            alert.showAndWait();
        }
    }

}
