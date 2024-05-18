package com.example.demo.transaction;

import com.example.demo.Database;
import com.example.demo.alerts.Alerts;
import com.example.demo.user.User;
import javafx.scene.control.Alert;

import java.sql.*;
import java.time.LocalDate;

public class TransactionManagement {


    public static void addTransaction(User user, Transaction transaction) {
        Alerts alerts = new Alerts();
        if(user.getRole().getTransactionManager()) {

            String query = "insert into Transaction (Type,DateTransaction,Prix,Frais,MethodePaiement,Statut,Note,Id_Client,Id_Propriete) values(?,?,?,?,?,?,?,?,?)";

            try (Connection connection = Database.connect()) {
                assert connection != null;
                try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {

                    preparedStatement.setString(1, transaction.getType());
                    preparedStatement.setDate(2, Date.valueOf(transaction.getDate()));
                    preparedStatement.setDouble(3, transaction.getPrix());
                    preparedStatement.setDouble(4, transaction.getFrais());
                    preparedStatement.setString(5, transaction.getMethodePaiement());
                    preparedStatement.setString(6, transaction.getStatut());
                    preparedStatement.setString(7, transaction.getNote());
                    preparedStatement.setInt(8, transaction.getId_Client());
                    preparedStatement.setInt(9, transaction.getId_Propriete());


                    int rowsAffected = preparedStatement.executeUpdate();
                    if (rowsAffected > 0) {
                        alerts.showAlertSuccessfuly("Added","Transaction");
                    } else {
                        alerts.showAlertFailedTo("Add","Transaction");
                    }
                }
            }catch (SQLException e) {
                    throw new RuntimeException(e);
            }

        }else{
            alerts.showAlertPermissionError("Add","Transaction");
        }
    }

    public static void updateTransaction() {}

    public static void deleteTransaction(User user, int id) {
        Alerts alerts = new Alerts();
        if(user.getRole().getTransactionManager()) {
            String query = "delete from Transaction where id = ?";

            try (Connection connection = Database.connect()) {
                assert connection != null;
                try (PreparedStatement statement = connection.prepareStatement(query)) {

                    statement.setString(1, String.valueOf(id));
                    int numRowsAffected = statement.executeUpdate();

                    if (numRowsAffected > 0) {
                        alerts.showAlertSuccessfuly("Delete","Transaction");
                    } else {
                        alerts.showAlertFailedTo("Delete","Transaction");
                    }
                }
            }
            catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }else{
            alerts.showAlertPermissionError("Delete","Transaction");
        }
    }

}
