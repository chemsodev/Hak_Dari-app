package com.example.demo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCombination;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
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
        Parent root = FXMLLoader.load(getClass().getResource("loginPage1.fxml"));
        stage.setTitle("Agency");
        stage.setScene(new Scene(root,1200,700));
        //stage.setFullScreen(true);
        stage.setResizable(false);
        //stage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
        stage.show();

        //Database Connection
        //Connection c = Database.connect();
    }

    public static void main(String[] args) {
        launch();
    }
}