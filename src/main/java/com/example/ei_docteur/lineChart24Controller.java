package com.example.ei_docteur;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

public class lineChart24Controller implements Initializable {
    @FXML
    private LineChart<Number, Number> chart;
    @FXML
    private CategoryAxis xAxis;
    @FXML
    private NumberAxis yAxis;
    @FXML
    private Label date;
    @FXML
    private BorderPane root;
    String name;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        xAxis.setLabel("temps");
        yAxis.setLabel("taux de glycémie");
        yAxis.setMaxHeight(10);
        yAxis.setMinHeight(10);
        SimpleDateFormat dt1 = new SimpleDateFormat("yyyy-MM-dd");
        this.date.setText(dt1.format(new Date()));
        XYChart.Series series = new XYChart.Series();
        series.setName("");
        ArrayList<String> temps = new ArrayList<>();
        ArrayList<Double> taux = new ArrayList<>();
        try {
            temps = TauxGly.getValuesChartTemps();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            taux = TauxGly.getValuesChartTaux();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for (int i = 0 ; i < taux.size() ; i++){
            series.getData().add(new XYChart.Data(temps.get(i).split(" ")[1], taux.get(i)));
        }
        chart.getData().addAll(series);

    }


    public void lineChart() throws IOException {
        Stage stage = (Stage) root.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("lineChart.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1200, 800);
        stage.setTitle("EI 2021");
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    public void patient() throws IOException {
        Stage stage = (Stage) root.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("patient.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1200, 800);
        PatientController controller = fxmlLoader.getController();
        controller.name = this.name;
        stage.setTitle("EI 2021");
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

}
