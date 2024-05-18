package com.example.demo.appointment;

import com.example.demo.Database;
import com.example.demo.alerts.Alerts;
import com.example.demo.user.User;
import javafx.scene.control.Alert;

import java.sql.*;

public class AppointmentManagement {

    public static void addAppointment(User user, Appointment appointment) {
        Alerts alerts = new Alerts();
        if(user.getRole().getClientManager() || true) {
            String query = "INSERT INTO Appointment (description, appointment_date, client_fullname, client_phone, realEstate_id,user_id) VALUES (?, ?, ?, ?, ?, ?)";

            try (Connection connection = Database.connect()) {
                assert connection != null;
                try (PreparedStatement statement = connection.prepareStatement(query)) {

                    statement.setString(1, appointment.getDescription());
                    statement.setDate(2, Date.valueOf(appointment.getDate()));
                    statement.setString(3, appointment.getClientFullname());
                    statement.setString(4, appointment.getClientPhone());
                    statement.setInt(5, appointment.getIdRealEstate());
                    statement.setInt(6, appointment.getIdUser());

                    int numRowsAffected = statement.executeUpdate();

                    if (numRowsAffected > 0) {
                        alerts.showAlertSuccessfuly("Added","Apointment");
                    } else {
                        alerts.showAlertFailedTo("Add","Appointment");
                    }
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }else{
            alerts.showAlertPermissionError("Appointment","Add");
        }
    }

    public static void deleteAppointment(User user, int id) {
        Alerts alerts = new Alerts();
        if(user.getRole().getClientManager() || true) {
            String query = "delete from Appointment where id = ?";

            try (Connection connection = Database.connect()) {
                assert connection != null;
                try (PreparedStatement statement = connection.prepareStatement(query)) {

                    statement.setString(1, String.valueOf(id));
                    int numRowsAffected = statement.executeUpdate();

                    if (numRowsAffected > 0) {
                        alerts.showAlertSuccessfuly("Deleted","Apointment");
                    } else {
                        alerts.showAlertFailedTo("Add","Appointment");
                    }
                }

            }
            catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }else{
            alerts.showAlertPermissionError("Appointment","Delete");
        }
    }
}
