package com.example.bible;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class FrontController {
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private ComboBox<String> language;
    @FXML
    private ComboBox<String> versions;
    @FXML
    private ComboBox<String> book;
    @FXML
    private ComboBox<String> chapter;
    @FXML
    private ComboBox<Integer> verse;
    @FXML
    private ComboBox<Integer> till;
    @FXML
    private TextField search;
    @FXML
    private RadioButton darkMode;

    private final String whiteColor = "0xf4f4f4ff";
    private final String blackColor = "#000000";

    @FXML
    private void onDarkModeAction() {
        darkModeColorsChanger(anchorPane.getBackground().getFills().get(0).getFill().toString());
    }

    private void darkModeColorsChanger(String color) {
        if (color.equals(whiteColor)) {
            anchorPane.setStyle("-fx-background-color:" + blackColor);
            language.setStyle("-fx-text-fill:" + "white");
            versions.setStyle("-fx-text-fill:" + "white");
            book.setStyle("-fx-text-fill:" + "white");
            chapter.setStyle("-fx-text-fill:" + "white");
            verse.setStyle("-fx-text-fill:" + "white");
            till.setStyle("-fx-text-fill:" + "white");
            search.setStyle("-fx-text-fill:" + "white");
            darkMode.setStyle("-fx-text-fill:" + "white");
        } else {
            anchorPane.setStyle("-fx-background-color:" + whiteColor);
            language.setStyle("-fx-text-fill:" + blackColor);
            versions.setStyle("-fx-text-fill:" + blackColor);
            book.setStyle("-fx-text-fill:" + blackColor);
            chapter.setStyle("-fx-text-fill:" + blackColor);
            verse.setStyle("-fx-text-fill:" + blackColor);
            till.setStyle("-fx-text-fill:" + blackColor);
            search.setStyle("-fx-text-fill:" + blackColor);
            darkMode.setStyle("-fx-text-fill:" + blackColor);
        }
    }
}