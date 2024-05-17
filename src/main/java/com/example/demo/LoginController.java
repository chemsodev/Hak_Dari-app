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
import java.sql.SQLException;

public class LoginController {

    private Parent root;
    private Stage stage;
    private Scene scene;


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
                    int id = result.getInt("Id");
                    String username = result.getString("username");
                    String password = result.getString("password");
                    boolean userManag = result.getBoolean("userManag");
                    boolean clientManag = result.getBoolean("clientManag");
                    boolean realEstateManag = result.getBoolean("realEstateManag");
                    boolean transactionManag = result.getBoolean("transactionManag");
                    boolean chargeManag = result.getBoolean("chargeManag");


                    Role role = new Role(userManag,realEstateManag,clientManag,transactionManag, chargeManag);
                    User user = new User(id,username,password,role);


                    FXMLLoader loader = new FXMLLoader(getClass().getResource("dashboard.fxml"));
                    root = loader.load();
                    BoardController boardController = loader.getController();
                    boardController.displayInfo(user);
                    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    scene = new Scene(root);
                    stage.setScene(scene);
                    stage.setResizable(false);
                    stage.show();
                    boardController.initialze();


                }else LoginErrorLabel.setText("Invalid username or password");
            }

        }catch (SQLException e){
            System.err.println("SQL Exeption");
        }
        catch(Exception e){
            e.printStackTrace();
        }

    }
    public void close(){
        System.exit(0);
    }



}
