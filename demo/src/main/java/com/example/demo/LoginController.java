package com.example.demo;

import com.example.demo.user.User;

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

    @FXML
    private Button loginBtn;

    @FXML
    private PasswordField password;

    @FXML
    private TextField username;

    @FXML
    private Label welcomeLabel;

    @FXML
    void loginBtnClicked2(ActionEvent event) throws IOException {
        if(username.getText().isEmpty() || password.getText().isEmpty()){
            //Affichage d'un message
            welcomeLabel.setText("Veiller remplire le nom d'utilisateur ..");

        }else {

            //Search on DB
            /*  if user & pswrd
             *       user = hadik l'entité
             *       switch scene & userInfo
             *   else
             *       error label
             */

            //Connected & Switch the scene
            FXMLLoader loader = new FXMLLoader(getClass().getResource("homePage.fxml"));
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
    public void loginBtnClicked(){

        String sql = "SELECT * FROM admin WHERE username = ? and password = ?";

        connect = Database.connect();

        try{
            assert connect != null;
            prepare = connect.prepareStatement(sql);
            prepare.setString(1, username.getText());
            prepare.setString(2, password.getText());

            result = prepare.executeQuery();
            Alert alert;

            if(username.getText().isEmpty() || password.getText().isEmpty()){
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            }else{
                if(result.next()){
                    getData.username = username.getText();

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Login");
                    alert.showAndWait();

                }else{
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Wrong Username/Password");
                    alert.showAndWait();
                }
            }

        }catch(Exception e){e.printStackTrace();}

    }




}
