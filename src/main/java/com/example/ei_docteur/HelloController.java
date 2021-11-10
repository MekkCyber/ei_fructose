package com.example.ei_docteur;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;

import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class HelloController {
    public static  void connecion() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/oups","root","1234");
        Statement stmt = conn.createStatement();
        stmt.executeUpdate("insert into new values(10,\"sdrt\")");
    }
    public static  void connecion1() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/oups","root","1234");
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("select id from new");
        rs.next();
        rs.next();
        rs.next();
        System.out.println(rs.getInt("id"));
    }


   public static void main(String[] args) throws SQLException, ClassNotFoundException {
        HelloController.connecion1();
       HelloController.connecion();
    }
    public void accueil() throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloController.class.getResource("lineChart.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 550);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }
}