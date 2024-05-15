package com.example.demo.user;

import javafx.scene.control.Alert;

public class UserManagement {

    //Create user
    public static void createUser(User user,User newUser){
        if(user.getRole().getUserManager()){

        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Permision Error");
            alert.setHeaderText(null);
            alert.setContentText("You don't have permission to add User.");
            alert.showAndWait();
        }
    }

    public static void updateUser(User user,User updatedUser){
        if(user.getRole().getUserManager()){

        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Permision Error");
            alert.setHeaderText(null);
            alert.setContentText("You don't have permission to Update User.");
            alert.showAndWait();
        }
    }

    //Te9der tbedel les parametre chof wsh ysa3dk chemsou
    public static void deleteUser(User user,User deletedUser){
        if(user.getRole().getUserManager()){

        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Permision Error");
            alert.setHeaderText(null);
            alert.setContentText("You don't have permission to Delete User.");
            alert.showAndWait();
        }
    }

}
