package com.example.ei_docteur;

import java.sql.*;
import java.util.ArrayList;

public class FluxDonnees {
    String  xData;
    double yData;
    public FluxDonnees(String x, double y){
        xData=x;
        yData=y;
    }

    public void insertValues(String date,double value2) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/glycemie","root","1234");
        PreparedStatement st = conn.prepareStatement("insert into glycemie (temps,taux_glycemie,patient_id) values(?,?,1)");
        st.setString(1,date);
        st.setDouble(2,value2);
        st.executeUpdate();
    }
    public  void connecion1() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/try","root","1234");
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("select * from try1 order by temps DESC");
        rs.next();
        System.out.println(rs.getString("temps"));

    }

}
