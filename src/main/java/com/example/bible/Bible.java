package com.example.bible;

import com.example.bible.requests.LinkData;
import com.example.bible.runtimeData.BibleVersions;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Bible extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Bible.class.getResource("fxml files/front.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1600, 240);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("styles/style.css")).toExternalForm());
        stage.setTitle("Bible");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}