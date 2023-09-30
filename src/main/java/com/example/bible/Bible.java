package com.example.bible;

import com.example.bible.requests.LinkData;
import com.example.bible.runtimeData.BibleVersions;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Bible extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        BibleVersions bibleVersions = new BibleVersions();
        LinkData linkData = new LinkData();
        linkData.setLinkInfo("rus", bibleVersions.modernTranslation, 4, 1, 1, 5);

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