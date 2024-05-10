package com.example.demo.transaction;

import com.example.demo.Database;
import com.example.demo.user.User;
import javafx.scene.control.Alert;

import java.sql.*;
import java.time.LocalDate;

public class TransactionManagement {


    public static void addTransaction(User user, Transaction transaction) {
        if(user.getRole().getTransactionManager()) {
            LocalDate currentDate = LocalDate.now();
            String query = "insert into Transaction (Type,DateTransaction,Prix,Frais,MethodePaiement,Statut,Note,Id_Client,Id_Propriete) values(?,?,?,?,?,?,?,?,?)";

            try (Connection connection = Database.connect()) {
                assert connection != null;
                try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {

                    preparedStatement.setString(1, transaction.getType());
                    preparedStatement.setDate(2, Date.valueOf(currentDate));
                    preparedStatement.setDouble(3, transaction.getPrix());
                    preparedStatement.setDouble(4, transaction.getFrais());
                    preparedStatement.setString(5, transaction.getMethodePaiement());
                    preparedStatement.setString(6, transaction.getStatut());
                    preparedStatement.setString(7, transaction.getNote());
                    preparedStatement.setInt(8, transaction.getId_Client());
                    preparedStatement.setInt(9, transaction.getId_Propriete());


                    int rowsAffected = preparedStatement.executeUpdate();
                    if (rowsAffected > 0) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Result Message");
                    alert.setHeaderText(null);
                    alert.setContentText("transaction added successfully.");
                    alert.showAndWait();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Failed to add transaction.");
                    alert.showAndWait();
                }
        }
            } catch (SQLIntegrityConstraintViolationException e){

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error Message");
        alert.setHeaderText(null);
        alert.setContentText("cannot delete this real estate because it's in an existing transaction  .");
        alert.showAndWait();
        } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        else{
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
