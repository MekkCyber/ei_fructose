module com.example.ei_docteur {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.sql;


    opens com.example.ei_docteur to javafx.fxml;
    exports com.example.ei_docteur;
}