package com.example.ei_docteur;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.binding.ObjectExpression;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.nio.channels.NotYetBoundException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class lineChartController implements Initializable {
    @FXML
    private LineChart<String, Number> chart;
    @FXML
    private CategoryAxis xAxis;
    @FXML
    private NumberAxis yAxis;
    @FXML
    private Label date;
    @FXML
    private BorderPane root;
    @FXML
    private TableView<TauxGly> table;
    @FXML
    private TableColumn<TauxGly,String> heure;
    @FXML
    private TableColumn<TauxGly,Double> taux;
    public int numberOfLinesBefore;
    String name;
    ScheduledExecutorService scheduledExecutorService;
    final int WINDOW_SIZE = 14;
//    ArrayList<Integer> xData = new ArrayList<>();
//    ArrayList<Integer> yData = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        xAxis.setLabel("temps");
        yAxis.setLabel("taux de glycémie");
        yAxis.setMaxHeight(10);
        yAxis.setMinHeight(10);
//        xAxis.setAnimated(false);
//        yAxis.setAnimated(false);

    }

    public void commencerAcquisition() throws SQLException, ClassNotFoundException {
        numberOfLinesBefore = TauxGly.getNumberOfRows();
        XYChart.Series series = new XYChart.Series();
        SimpleDateFormat dt2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat dt = new SimpleDateFormat("HH:mm:ss");
        SimpleDateFormat dt1 = new SimpleDateFormat("yyyy-MM-dd");
        this.date.setText(dt1.format(new Date()));
        series.setName("essai");
        chart.getData().addAll(series);
        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.scheduleAtFixedRate(() -> {

            // Update the chart
            Platform.runLater(() -> {
                Date date = new Date();
                double num = 10+Math.random();
                FluxDonnees flux = new FluxDonnees(dt.format(date),num);
                try {
                    flux.insertValues(dt2.format(date),num);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                ObservableList list = FXCollections.observableArrayList();
                list.add(new TauxGly(dt.format(date),num));
                heure.setCellValueFactory(new PropertyValueFactory<TauxGly,String>("temps"));
                taux.setCellValueFactory(new PropertyValueFactory<TauxGly,Double>("taux"));
                table.setItems(list);
                series.getData().add(new XYChart.Data<>(flux.xData,flux.yData));
                if (series.getData().size() > WINDOW_SIZE)
                    series.getData().remove(0);
            });
        }, 0, 3000, TimeUnit.MILLISECONDS);
    }



    public void table() throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("table.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 600);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public void lineChart24() throws IOException {
        Stage stage = (Stage) root.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("lineChart24.fxml"));
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

    public void endService(){
        scheduledExecutorService.shutdown();
    }
}


