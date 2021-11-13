package com.example.ei_docteur;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class PatientController {
    @FXML
    private TextArea info;
    @FXML
    private BorderPane root;
    String name;


    public void showInfo() throws SQLException, ClassNotFoundException {
        String name = this.name;
        System.out.println(name);
        Patient patient = Patient.findPatient(name);
        info.setText("id : "+ patient.id+"\n"+
                            "nom : "+ patient.name+"\n"+
                            "Date de naissance : "+patient.naissance+"\n"+
                            "Premiere visite : "+patient.premiere_visite+"\n"+
                            "Etat de santé : "+patient.Etat);
    }

    public void lineChart24() throws IOException {
        Stage stage = (Stage) root.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("lineChart24.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1200, 800);
        lineChart24Controller controller = fxmlLoader.getController();
        controller.name = this.name;
        stage.setTitle("EI 2021");
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    public void lineChart() throws IOException {
        Stage stage = (Stage) root.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("lineChart.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1200, 800);
        lineChartController controller = fxmlLoader.getController();
        controller.name = this.name;
        stage.setTitle("EI 2021");
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    public void seDeconnecter() throws IOException {
        Stage stage = (Stage) root.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 550);
        stage.setTitle("EI 2021");
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }
}
