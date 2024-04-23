/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo;

import com.example.demo.user.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class boardcontroller {

    @FXML
    private AnchorPane home_form;
    @FXML
    private AnchorPane clientManag_form;
    @FXML
    private AnchorPane realEstate_form;
    @FXML
    private AnchorPane transaction_form;
    @FXML
    private AnchorPane charge_form;
    @FXML
    private AnchorPane userManag_form;
    @FXML
    private Label usernamelabel;
    @FXML
    private Button home_btn;

    @FXML
    private Button client_btn;
    @FXML
    private Button realEstate_btn;
    @FXML
    private Button transaction_btn;
    @FXML
    private Button charge_btn;
    @FXML
    private Button user_btn;
    public void switchForm(ActionEvent event) {

        if (event.getSource() == home_btn) {
            home_form.setVisible(true);
            clientManag_form.setVisible(false);
            realEstate_form.setVisible(false);
            transaction_form.setVisible(false);
            charge_form.setVisible(false);
            userManag_form.setVisible(false);

            home_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #3a4368, #28966c);");
            client_btn.setStyle("-fx-background-color:transparent");
            realEstate_btn.setStyle("-fx-background-color:transparent");
            transaction_btn.setStyle("-fx-background-color:transparent");
            charge_btn.setStyle("-fx-background-color:transparent");
            user_btn.setStyle("-fx-background-color:transparent");
        } else if (event.getSource() == client_btn) {
            home_form.setVisible(false);
            clientManag_form.setVisible(true);
            realEstate_form.setVisible(false);
            transaction_form.setVisible(false);
            charge_form.setVisible(false);
            userManag_form.setVisible(false);

            home_btn.setStyle("-fx-background-color:transparent");
            client_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #3a4368, #28966c);");
            realEstate_btn.setStyle("-fx-background-color:transparent");
            transaction_btn.setStyle("-fx-background-color:transparent");
            charge_btn.setStyle("-fx-background-color:transparent");
            user_btn.setStyle("-fx-background-color:transparent");

        } else if (event.getSource() == realEstate_btn) {
            home_form.setVisible(false);
            clientManag_form.setVisible(false);
            realEstate_form.setVisible(true);
            transaction_form.setVisible(false);
            charge_form.setVisible(false);
            userManag_form.setVisible(false);

            home_btn.setStyle("-fx-background-color:transparent");
            client_btn.setStyle("-fx-background-color:transparent");
            realEstate_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #3a4368, #28966c);");
            transaction_btn.setStyle("-fx-background-color:transparent");
            charge_btn.setStyle("-fx-background-color:transparent");
            user_btn.setStyle("-fx-background-color:transparent");

        }else if (event.getSource() == transaction_btn) {
            home_form.setVisible(false);
            clientManag_form.setVisible(false);
            realEstate_form.setVisible(false);
            transaction_form.setVisible(true);
            charge_form.setVisible(false);
            userManag_form.setVisible(false);

            home_btn.setStyle("-fx-background-color:transparent");
            client_btn.setStyle("-fx-background-color:transparent");
            realEstate_btn.setStyle("-fx-background-color:transparent");
            transaction_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #3a4368, #28966c);");
            charge_btn.setStyle("-fx-background-color:transparent");
            user_btn.setStyle("-fx-background-color:transparent");


        }else if (event.getSource() == charge_btn ) {
            home_form.setVisible(false);
            clientManag_form.setVisible(false);
            realEstate_form.setVisible(false);
            transaction_form.setVisible(false);
            charge_form.setVisible(true);
            userManag_form.setVisible(false);

            home_btn.setStyle("-fx-background-color:transparent");
            client_btn.setStyle("-fx-background-color:transparent");
            realEstate_btn.setStyle("-fx-background-color:transparent");
            transaction_btn.setStyle("-fx-background-color:transparent");
            charge_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #3a4368, #28966c);");
            user_btn.setStyle("-fx-background-color:transparent");


        }else if (event.getSource() == user_btn) {
            home_form.setVisible(false);
            clientManag_form.setVisible(false);
            realEstate_form.setVisible(false);
            transaction_form.setVisible(false);
            charge_form.setVisible(false);
            userManag_form.setVisible(true);

            home_btn.setStyle("-fx-background-color:transparent");
            client_btn.setStyle("-fx-background-color:transparent");
            realEstate_btn.setStyle("-fx-background-color:transparent");
            transaction_btn.setStyle("-fx-background-color:transparent");
            charge_btn.setStyle("-fx-background-color:transparent");
            user_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #3a4368, #28966c);");


        }




    }
    User user = new User();
    public void displayInfo(User user){
        usernamelabel.setText("Welcome " + user.getUsername());
        this.user = user;
    }
}
