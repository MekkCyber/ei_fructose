package com.example.ei_docteur;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TableController implements Initializable {
    @FXML
    private TableView<TauxGly> table;
    @FXML
    private TableColumn<TauxGly,String> temps;
    @FXML
    private TableColumn<TauxGly,Double> taux;
    @FXML
    private TextField max;
    @FXML
    private TextField min;
    int numberOfRowsStart;
    ScheduledExecutorService scheduledExecutorService;
    int n =0;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        temps.setCellValueFactory(new PropertyValueFactory<TauxGly,String>("temps"));
        taux.setCellValueFactory(new PropertyValueFactory<TauxGly,Double>("taux"));
//        try {
//            table.setItems(TauxGly.getValues());
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        ObservableList list = FXCollections.observableArrayList();

        try {
            n = TauxGly.getNumberOfRows();
            numberOfRowsStart = TauxGly.getNumberOfRows();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        scheduledExecutorService.scheduleAtFixedRate(() -> {

            // Update the chart
            Platform.runLater(() -> {
                try {
                    list.add(TauxGly.getById(n));
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                table.setItems(list);
                if (list.size() > 100)
                    list.remove(0);
                System.out.println(n);
                n++;
            });
        }, 0, 3500, TimeUnit.MILLISECONDS);
    }

    public void endService(){
        scheduledExecutorService.shutdown();
    }

    public void rechercher() throws SQLException, ClassNotFoundException {
        String max = this.max.getText();
        String min = this.min.getText();
        int id = numberOfRowsStart;
        table.setItems(TauxGly.getValuesInfId(id,Double.parseDouble(min),Double.parseDouble(max)));
    }

}
