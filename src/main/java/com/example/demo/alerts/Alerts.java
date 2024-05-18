package com.example.demo.alerts;

import javafx.scene.control.Alert;

public class Alerts implements AlertsInterface{
    @Override
    public void showAlertPermissionError(String permissionName,String permissionType) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Permission");
        alert.setHeaderText("Permission denied");
        alert.setContentText("You do not have permission to " + permissionType + " " + permissionName);
        alert.showAndWait();
    }
    @Override
    public void showAlertTextFieldEmptyError(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error Message");
        alert.setHeaderText(null);
        alert.setContentText("Some text fields are empty. Please make sure to fill all the text fields.");
        alert.showAndWait();
    }
    @Override
    public void showAlertSelectionEmptyError(String selection){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error Message");
        alert.setHeaderText(null);
        alert.setContentText("Please make sure to select " + selection + " .");
        alert.showAndWait();
    }
    @Override
    public void showAlertFailedTo(String addOrDelete ,String what){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error Message");
        alert.setHeaderText(null);
        alert.setContentText("Failed to  "+ addOrDelete + what + " .");
        alert.showAndWait();
    }

    public void showAlertSuccessfuly(String addedOrDeleted ,String what){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Error Message");
        alert.setHeaderText(null);
        alert.setContentText(what + " Successfuly " + addedOrDeleted + " .");
        alert.showAndWait();
    }

}
