module com.example.bible {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.json;
    requires com.google.gson;


    opens com.example.bible to javafx.fxml;
    exports com.example.bible;
    exports com.example.bible.controllers;
    opens com.example.bible.controllers to javafx.fxml;
    exports com.example.bible.requests;
    opens com.example.bible.requests to javafx.fxml;
    exports com.example.bible.runtimeData;
    opens com.example.bible.runtimeData to javafx.fxml;
}