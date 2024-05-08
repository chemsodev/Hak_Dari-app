module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.demo to javafx.fxml;
    opens com.example.demo.client to javafx.base;
    opens com.example.demo.realEstate to javafx.base;
    opens com.example.demo.user to javafx.base;
    opens com.example.demo.transaction to javafx.base;
    exports com.example.demo;
}