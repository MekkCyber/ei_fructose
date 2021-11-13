package com.example.ei_docteur;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class TauxGly {
    private String temps;
    private double taux;

    public TauxGly(String temps, double taux) {
        this.temps = temps;
        this.taux = taux;
    }

    public String getTemps() {
        return temps;
    }

    public void setTemps(String temps) {
        this.temps = temps;
    }

    public double getTaux() {
        return taux;
    }

    public void setTaux(double taux) {
        this.taux = taux;
    }

    public static ObservableList getValues() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/glycemie", "root", "1234");
        ObservableList list = FXCollections.observableArrayList();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("select * from glycemie");
        while (rs.next()) {
            list.add(new TauxGly(rs.getString("temps"), rs.getDouble("taux_glycemie")));
        }
        return list;
    }
    public static ArrayList<String> getValuesChartTemps() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/glycemie", "root", "1234");
        SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, 1);
        Date date1 = new Date();
        date1 = c.getTime();
        ArrayList<String> list = new ArrayList<>();
        PreparedStatement st = conn.prepareStatement("select * from glycemie where temps between ? and ? and temps like ?" );
        st.setString(1,dt.format(date));
        st.setString(2,dt.format(date1));
        st.setString(3,dt.format(date).split(" ")[0]+" __:00:00");
        ResultSet rs  = st.executeQuery();
        while (rs.next()) {
            list.add(rs.getString("temps"));
        }
        return list;
    }
    public static ArrayList<Double> getValuesChartTaux() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/glycemie", "root", "1234");
        SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, 1);
        Date date1 = new Date();
        date1 = c.getTime();
        ArrayList<Double> list = new ArrayList<>();
        PreparedStatement st = conn.prepareStatement("select * from glycemie where temps between ? and ? and temps like ?");
        st.setString(1,dt.format(date)+" 00:00:00");
        st.setString(2,dt.format(date1)+" 00:00:00");
        st.setString(3,dt.format(date)+" __:00:00");
        ResultSet rs = st.executeQuery();
        while (rs.next()) {
            list.add(rs.getDouble("taux_glycemie"));
        }
        return list;
    }

    public static int getNumberOfRows() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/glycemie", "root", "1234");
        PreparedStatement st = conn.prepareStatement("select count(*) as num from glycemie ");
        ResultSet rs = st.executeQuery();
        rs.next();
        int num = rs.getInt("num");
        return num;
    }

    public static TauxGly getById(int id) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/glycemie", "root", "1234");
        PreparedStatement st = conn.prepareStatement("select * from glycemie where id=? ");
        st.setInt(1,id);
        ResultSet rs = st.executeQuery();
        rs.next();
        TauxGly obj = new TauxGly(rs.getString("temps"),rs.getDouble("taux_glycemie"));
        return obj;

    }

    public static ObservableList getValuesInfId(int id,double min,double max) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/glycemie", "root", "1234");
        PreparedStatement st = conn.prepareStatement("select * from glycemie where id>? and taux_glycemie<? and taux_glycemie>? ");
        st.setInt(1,id);
        st.setDouble(2,max);
        st.setDouble(3,min);
        ObservableList list = FXCollections.observableArrayList();
        ResultSet rs = st.executeQuery();
        while (rs.next()){
            list.add(new TauxGly(rs.getString("temps"),rs.getDouble("taux_glycemie")));
        }
        return list;
    }

    public static void main(String[] args) {
        SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, -1);
        Date date1 = new Date();
        date1 = c.getTime();
        System.out.println(dt.format(date)+" 00:00:00");
        System.out.println(dt.format(date1)+" 00:00:00");
    }

}