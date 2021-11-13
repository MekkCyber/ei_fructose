package com.example.ei_docteur;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ChoosePatientController implements Initializable {
    @FXML
    private ComboBox choose;
    @FXML
    private VBox root;
    String name = "";
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            choose.getItems().addAll(Patient.getPatients());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void choose() throws IOException {
        String chosenName = (String) choose.getValue();
//        name = new String(chosenName);
//        System.out.println(name);
        Stage stage = (Stage) root.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("patient.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1200, 800);
        PatientController controller = fxmlLoader.getController();
        controller.name = chosenName;
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

}
