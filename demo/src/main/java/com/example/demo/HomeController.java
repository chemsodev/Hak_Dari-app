package com.example.demo;

import com.example.demo.user.User;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class HomeController {
    
    @FXML
    private Label IdLabel;

    @FXML
    private Label WelcomeLabel;

    @FXML
    private Button btnTest;

    @FXML
    private Label passwordLabel;

    @FXML
    private Label roleLabel;

    public void displayInfo(User user){
        WelcomeLabel.setText("Welcome " + user.getUsername());
        IdLabel.setText("ID : " + user.getId());
        passwordLabel.setText("Password : " + user.getPassword());
        roleLabel.setText("Role : " + user.getRole());
    }
}

