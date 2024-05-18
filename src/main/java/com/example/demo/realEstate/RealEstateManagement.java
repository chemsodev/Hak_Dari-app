package com.example.demo.realEstate;

import com.example.demo.Database;
import com.example.demo.alerts.Alerts;
import com.example.demo.user.User;
import javafx.scene.control.Alert;

import java.sql.*;
import java.time.LocalDate;

public class RealEstateManagement {

    public static void createRealEstate(RealEstate realEstate,User user) {
        Alerts alerts = new Alerts();
        if(user.getRole().getRealEstateManager()) {
            LocalDate currentDate = LocalDate.now();
            String query = "insert into RealEstate (Title,Description,Price,Area,Address,Type,Date_Creation,id_Owner) VALUES (?,?,?,?,?,?,?,?)";

            try (Connection connection = Database.connect()) {
                assert connection != null;
                try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {

                    preparedStatement.setString(1, realEstate.getTitle());
                    preparedStatement.setString(2, realEstate.getDescription());
                    preparedStatement.setDouble(3, realEstate.getPrice());
                    preparedStatement.setDouble(4, realEstate.getArea());
                    preparedStatement.setString(5, realEstate.getAddress());
                    preparedStatement.setString(6,realEstate.getType());
                    preparedStatement.setDate(7, Date.valueOf(currentDate));
                    preparedStatement.setInt(8, realEstate.getOwnerId());

                    int rowsAffected = preparedStatement.executeUpdate();

                     if (rowsAffected > 0) {
                        alerts.showAlertSuccessfuly("Added","RealEstate");
                    } else {
                         alerts.showAlertFailedTo("Add","RealEstate");
                    }

                }
            } catch (SQLException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Error during data insertion: " + e.getMessage());
                alert.showAndWait();
            }
        }else{
            alerts.showAlertPermissionError("RealEstate","Add");
        }
    }

    public static void updateRealEstate(RealEstate realEstate,User user) {
        Alerts alerts = new Alerts();
        if(user.getRole().getRealEstateManager()) {
            LocalDate currentDate = LocalDate.now();
            String query = "UPDATE RealEstate SET Title=?,Description=?,Price=?,Area=?,Address=?,Type=?, id_Owner=? WHERE Id=? ";

            try (Connection connection = Database.connect()) {
                assert connection != null;
                try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {

                    preparedStatement.setString(1, realEstate.getTitle());
                    preparedStatement.setString(2, realEstate.getDescription());
                    preparedStatement.setDouble(3, realEstate.getPrice());
                    preparedStatement.setDouble(4, realEstate.getArea());
                    preparedStatement.setString(5, realEstate.getAddress());
                    preparedStatement.setString(6,realEstate.getType());
                    preparedStatement.setInt(7, realEstate.getOwnerId());
                    preparedStatement.setInt(8,realEstate.getId());

                    int rowsAffected = preparedStatement.executeUpdate();

                    if (rowsAffected > 0) {
                        alerts.showAlertSuccessfuly("Added","RealEstate");
                    } else {
                        alerts.showAlertFailedTo("Update","RealEstate");
                    }

                }
            } catch (SQLException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Error during data insertion: " + e.getMessage());
                alert.showAndWait();
            }
        }else{
            alerts.showAlertPermissionError("RealEstate","Update");
        }
    }

    public static void deleteRealEstate(int id,User user) {
        Alerts alerts = new Alerts();
        if(user.getRole().getRealEstateManager()) {
            String query = "delete from RealEstate where id = ?";

            try (Connection connection = Database.connect()) {
                assert connection != null;
                try (PreparedStatement statement = connection.prepareStatement(query)) {

                    statement.setString(1, String.valueOf(id));
                    int numRowsAffected = statement.executeUpdate();

                    if (numRowsAffected > 0) {
                        alerts.showAlertSuccessfuly("Added","RealEstate");
                    } else {
                        alerts.showAlertFailedTo("Delete","RealEstate");
                    }
                }
            }catch (SQLIntegrityConstraintViolationException e){

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("cannot delete this real estate because it's in an existing transaction  .");
                alert.showAndWait();
            }
            catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }else{
            alerts.showAlertPermissionError("RealEstate","Delete");
        }
    }
}