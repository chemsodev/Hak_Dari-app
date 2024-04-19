package com.example.demo;

import com.example.demo.user.User;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class HomeController {


    @FXML
    private Button logOutButton;

    @FXML
    private Label passwordLabelTest;

    @FXML
    private Label roleLabelTest;

    @FXML
    private Button testButton;

    @FXML
    private Label userWelcomerLabel;

    @FXML
    private Label usernameLabelTest;

    User user = new User();

    public void displayInfo(User user){
        userWelcomerLabel.setText("Welcome " + user.getUsername());
        this.user = user;
    }

    @FXML
    void testButtonClicked(ActionEvent event) {
        usernameLabelTest.setText(user.getUsername());
        passwordLabelTest.setText(user.getPassword());
        if(user.getRole().getRealEstateManager()){
            roleLabelTest.setText("you have permition");
        }else{
            roleLabelTest.setText("you do not have permition");
        }

    }

}

