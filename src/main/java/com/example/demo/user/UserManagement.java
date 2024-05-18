package com.example.demo.user;

import com.example.demo.BoardController;
import com.example.demo.Database;
import com.example.demo.alerts.Alerts;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Optional;

import static com.example.demo.BoardController.*;

public class UserManagement {
    //Create user
    public static void createUser(User superUser,User user){
        Alerts alerts = new Alerts();
        if(superUser.getRole().getUserManager()) {
            String query = "INSERT INTO users (username, password, userManag, clientManag, realEstateManag, transactionManag, chargeManag) VALUES (?, ?, ?, ?, ?, ?, ?)";

            try (Connection connection = Database.connect()) {
                assert connection != null;
                try (PreparedStatement statement = connection.prepareStatement(query)) {

                    statement.setString(1, user.getUsername());
                    statement.setString(2, user.getPassword());
                    if (user.getRole().getUserManager()) {
                        statement.setString(3, String.valueOf(1));
                    } else {
                        statement.setString(3, String.valueOf(0));
                    }
                    if (user.getRole().getClientManager()) {
                        statement.setString(4, String.valueOf(1));
                    } else {
                        statement.setString(4, String.valueOf(0));
                    }
                    if (user.getRole().getRealEstateManager()) {
                        statement.setString(5, String.valueOf(1));
                    } else {
                        statement.setString(5, String.valueOf(0));
                    }
                    if (user.getRole().getTransactionManager()) {
                        statement.setString(6, String.valueOf(1));
                    } else {
                        statement.setString(6, String.valueOf(0));
                    }
                    if (user.getRole().getChargeManager()) {
                        statement.setString(7, String.valueOf(1));
                    } else {
                        statement.setString(7, String.valueOf(0));
                    }
                    int numRowsAffected = statement.executeUpdate();
                    if (numRowsAffected > 0) {
                        alerts.showAlertSuccessfuly("Added","User");
                    } else {
                        alerts.showAlertFailedTo("Add","User");
                    }
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void updateUser(User user){
        Alerts alerts = new Alerts();
        String query = "UPDATE users SET username=?, password=?, userManag=?, clientManag=?, realEstateManag=?, transactionManag=?, chargeManag=?  where id = ?";
        try (Connection connection = Database.connect()) {
            assert connection != null;
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, user.getUsername());
                statement.setString(2, user.getPassword());
                if (user.getRole().getUserManager()){statement.setString(3, String.valueOf(1));}
                else{statement.setString(3, String.valueOf(0));}
                if (user.getRole().getClientManager()){statement.setString(4, String.valueOf(1));}
                else{statement.setString(4, String.valueOf(0));}
                if (user.getRole().getRealEstateManager()){statement.setString(5, String.valueOf(1));}
                else{statement.setString(5, String.valueOf(0));}
                if (user.getRole().getTransactionManager()){statement.setString(6, String.valueOf(1));}
                else{statement.setString(6, String.valueOf(0));}
                if (user.getRole().getChargeManager()){statement.setString(7, String.valueOf(1));}
                else{statement.setString(7, String.valueOf(0));}
                statement.setString(8, String.valueOf(user.getId()));
                int numRowsAffected = statement.executeUpdate();

                if (numRowsAffected > 0) {
                    alerts.showAlertSuccessfuly("Updated","User");
                } else {
                    alerts.showAlertFailedTo("Update","User");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void deleteUser(User user, int id) throws SQLException {
        Alerts alerts = new Alerts();
        if(user.getRole().getUserManager()) {
            if(user.getId() == id){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("Delete User Failed");
                alert.showAndWait();
            }else{
                String query = "delete from users where id = ?";
                try (Connection connection = Database.connect()) {
                    assert connection != null;
                    try (PreparedStatement statement = connection.prepareStatement(query)) {

                        statement.setString(1, String.valueOf(id));
                        int numRowsAffected = statement.executeUpdate();

                        if (numRowsAffected > 0) {
                            alerts.showAlertSuccessfuly("Deleted","User");
                        } else {
                            alerts.showAlertFailedTo("Delete","User");
                        }
                    }
                }
                catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

}
