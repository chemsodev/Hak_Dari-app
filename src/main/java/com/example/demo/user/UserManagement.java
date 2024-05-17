package com.example.demo.user;

import com.example.demo.BoardController;
import com.example.demo.Database;
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
    public static void createUser(User user){
        String query = "INSERT INTO users (username, password, userManag, clientManag, realEstateManag, transactionManag, chargeManag) VALUES (?, ?, ?, ?, ?, ?, ?)";

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
                int numRowsAffected = statement.executeUpdate();
                if (numRowsAffected > 0) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Result Message");
                    alert.setHeaderText(null);
                    alert.setContentText("User added successfully.");
                    alert.showAndWait();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Failed to add User.");
                    alert.showAndWait();
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void updateUser(User user){
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
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Result Message");
                    alert.setHeaderText(null);
                    alert.setContentText("User updated successfully.");
                    alert.showAndWait();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Failed to update client.");
                    alert.showAndWait();
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //Te9der tbedel les parametre chof wsh ysa3dk chemsou
    //rigl
    public static void deleteUser(User user, int id) throws SQLException {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirm deleting User");
            alert.setHeaderText("Notice:");
        if(user.getId()==id) {alert.setContentText("you are trying to delete the user account that is now logged, u will be logged out after deleting it.\nConfirm account deleting");}
         else{alert.setContentText("Confirm User deleting.");}
        Optional<ButtonType> result =alert.showAndWait();
         if(result.get() == ButtonType.OK){
            String query = "delete from users where id = ?";
            try (Connection connection = Database.connect()) {
                assert connection != null;
                try (PreparedStatement statement = connection.prepareStatement(query)) {

                    statement.setString(1, String.valueOf(id));
                    int numRowsAffected = statement.executeUpdate();

                    if (numRowsAffected > 0) {
                        alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Result Message");
                        alert.setHeaderText(null);
                        alert.setContentText("User deleted successfully.");
                        alert.showAndWait();
                    } else {
                        Alert alert1= new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error Message");
                        alert.setHeaderText(null);
                        alert.setContentText("Failed to delete User.");
                        alert.showAndWait();
                    }
                }
            }
            catch (SQLException e) {
                throw new RuntimeException(e);
            }
         }
    }


}
