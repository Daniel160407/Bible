module com.example.bible {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.json;
    requires com.google.gson;


    opens com.example.bible to javafx.fxml;
    exports com.example.bible;
}