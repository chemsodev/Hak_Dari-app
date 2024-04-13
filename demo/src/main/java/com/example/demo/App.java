package com.example.demo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Statement;

public class App extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        /*FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("loginPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("Gestion");
        stage.setScene(scene);
        stage.show();*/
        Parent root = FXMLLoader.load(getClass().getResource("loginPage.fxml"));
        stage.setTitle("Agency");
        stage.setScene(new Scene(root,800,600));
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}