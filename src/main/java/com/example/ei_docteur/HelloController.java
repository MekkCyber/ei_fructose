package com.example.ei_docteur;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;

import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class HelloController {
    @FXML
    private VBox root;
    public void seConnecter() throws IOException {
        Stage stage = (Stage) root.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("choosePatient.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 430, 220);
            stage.setTitle("Hello!");
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.show();

    }

}