module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.apache.pdfbox;


    opens com.example.demo to javafx.fxml;
    opens com.example.demo.client to javafx.base;
    opens com.example.demo.realEstate to javafx.base;
    opens com.example.demo.user to javafx.base;
    opens com.example.demo.transaction to javafx.base;
    opens com.example.demo.charge to javafx.base;
    opens com.example.demo.appointment to javafx.base;
    opens com.example.demo.historique to javafx.base;
    exports com.example.demo;
}