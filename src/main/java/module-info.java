module com.example.bible {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.bible to javafx.fxml;
    exports com.example.bible;
}