package com.example.demo;

import com.example.demo.user.User;
import com.example.demo.user.Role;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginController {

    private Parent root;
    private Stage stage;
    private Scene scene;


    User user = new User();
    Role role = new Role();

    @FXML
    private Button loginBtn;

    @FXML
    private PasswordField password;

    @FXML
    private TextField username;

    @FXML
    private Label welcomeLabel;

    @FXML
    private Label LoginErrorLabel;

    @FXML
    void loginBtnClicked2(ActionEvent event) throws IOException {
        if(username.getText().isEmpty() || password.getText().isEmpty()){
            //Affichage d'un message
            welcomeLabel.setText("Veiller remplire le nom d'utilisateur ..");

        }else {



            //Connected & Switch the scene
            FXMLLoader loader = new FXMLLoader(getClass().getResource("dashboard.fxml"));
            root = loader.load();

            user.setUsername(username.getText());
            user.setPassword(password.getText());

            HomeController homeController = loader.getController();
            homeController.displayInfo(user);

            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        }
    }

    //    DATABASE TOOLS
    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;


    private double x = 0;
    private double y = 0;
    public void loginBtnClicked(ActionEvent event){

        String sql = "SELECT * FROM users WHERE username = ? and password = ?";

        connect = Database.connect();

        try{
            assert connect != null;
            prepare = connect.prepareStatement(sql);
            prepare.setString(1, username.getText());
            prepare.setString(2, password.getText());

            result = prepare.executeQuery();

            if(username.getText().isEmpty() || password.getText().isEmpty()){
                LoginErrorLabel.setText("Please fill all blank fields");
            }else{
                if(result.next()){
                    getData.username = username.getText();

                    //init data from db to user
                    user.setUsername(result.getString(2));
                    user.setPassword(result.getString(3));
                    role.setUserManager(result.getBoolean(4));
                    role.setClientManager(result.getBoolean(5));
                    role.setRealEstateManager(result.getBoolean(6));
                    user.setRole(role);


                    FXMLLoader loader = new FXMLLoader(getClass().getResource("homePage.fxml"));
                    root = loader.load();

                    HomeController homeController = loader.getController();
                    homeController.displayInfo(user);

                    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();


                }else{
                    LoginErrorLabel.setText("Invalid username or password");
                }
            }

        }catch(Exception e){e.printStackTrace();}

    }




}
