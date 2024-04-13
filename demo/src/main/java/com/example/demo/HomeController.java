package com.example.demo;

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
        WelcomeLabel.setText("Welcome " + user.getName());
        IdLabel.setText("ID : " + user.getID());
        passwordLabel.setText("Password : " + user.getPassword());
        roleLabel.setText("Role : " + user.getRole());
    }
}

