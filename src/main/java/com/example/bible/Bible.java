package com.example.bible;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Objects;

public class Bible extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        LinkInfo linkInfo = new LinkInfo();
        linkInfo.setLinkInfo("geo",4,1,1,5);
        FXMLLoader fxmlLoader = new FXMLLoader(Bible.class.getResource("front.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1600, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}