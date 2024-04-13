package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

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
    void loginBtnClicked(ActionEvent event) throws IOException {
        if(username.getText().isEmpty() || password.getText().isEmpty()){
            //Affichage d'un message
            welcomeLabel.setText("Veiller remplire le nom d'utilisateur ..");

        }else{

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

            user.setName(username.getText());
            user.setPassword(password.getText());

            HomeController homeController = loader.getController();
            homeController.displayInfo(user);

            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        }
    }
}
