package com.example.ei_docteur;

import javafx.application.Platform;
import javafx.beans.binding.ObjectExpression;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

import java.net.URL;
import java.nio.channels.NotYetBoundException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class lineChartController implements Initializable {
    @FXML
    private LineChart<Number,Number> chart;
    @FXML
    private  NumberAxis xAxis;
    @FXML
    private NumberAxis yAxis;
    private Number n = 0;
    ScheduledExecutorService scheduledExecutorService;
    final int WINDOW_SIZE = 10;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        chart.setTitle("Hello");
        xAxis.setLabel("temps");
        yAxis.setLabel("quantite");
        xAxis.setAnimated(false);
        yAxis.setAnimated(false);

        XYChart.Series series = new XYChart.Series();
        series.setName("essai");
//        series.getData().add(new XYChart.Data(1, 23));
//        series.getData().add(new XYChart.Data(2, 14));
//        series.getData().add(new XYChart.Data(3, 15));
//        series.getData().add(new XYChart.Data(4, 24));
//        series.getData().add(new XYChart.Data(5, 34));
//        series.getData().add(new XYChart.Data(6, 36));
//        series.getData().add(new XYChart.Data(7, 22));
//        series.getData().add(new XYChart.Data(8, 45));
//        series.getData().add(new XYChart.Data(9, 43));
//        series.getData().add(new XYChart.Data(10, 17));
//        series.getData().add(new XYChart.Data(11, 29));
//        series.getData().add(new XYChart.Data(12, 25));
        chart.getData().addAll(series);
        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
        scheduledExecutorService.scheduleAtFixedRate(() -> {
            // get a random integer between 0-10

            // Update the chart
            Platform.runLater(() -> {
                // get current time
                Integer random = n.intValue();
                int m = n.intValue()+1;
                n = m;
                // put random number with current time
                series.getData().add(new XYChart.Data<>(random, random));

//                if (series.getData().size() > WINDOW_SIZE)
//                    series.getData().remove(0);
            });
        }, 0, 1000, TimeUnit.MILLISECONDS);
    }
    public void startAcquisition(){


}
    }

