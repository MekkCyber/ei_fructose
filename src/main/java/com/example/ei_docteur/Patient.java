package com.example.ei_docteur;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class Patient {
    int id;
    String name;
    String naissance;
    String premiere_visite;
    String Etat;

    public Patient(int id, String name, String naissance, String premiere_visite, String etat) {
        this.id = id;
        this.name = name;
        this.naissance = naissance;
        this.premiere_visite = premiere_visite;
        Etat = etat;
    }
    public static ObservableList getPatients() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/glycemie", "root", "1234");
        PreparedStatement st = conn.prepareStatement("select * from patient ");
        ObservableList patients = FXCollections.observableArrayList();
        ResultSet rs = st.executeQuery();
        while (rs.next()){
            patients.add(rs.getString("name"));
        }
        return patients;
    }

    public static Patient findPatient(String name) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/glycemie", "root", "1234");
        PreparedStatement st = conn.prepareStatement("select * from patient where name =?");
        st.setString(1,name);
        ResultSet rs = st.executeQuery();
        rs.next();
        Patient patient = new Patient(rs.getInt("id"),rs.getString("name"),rs.getString("naissance"),rs.getString("premiere_visite"),rs.getString("etat"));
        return patient;

    }

}
