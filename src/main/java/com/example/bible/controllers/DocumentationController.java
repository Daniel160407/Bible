package com.example.bible.controllers;


import com.example.bible.runtimeData.DocumentationData;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.text.Text;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;

public class DocumentationController {
    @FXML
    private Label title;
    @FXML
    private Text secondTitle;
    @FXML
    private Text firstText;
    @FXML
    private Text secondText;
    @FXML
    private Text thirdText;
    @FXML
    private Text fourthText;
    @FXML
    private Text fivethText;
    @FXML
    private Text sixthText;
    @FXML
    private Text seventhText;
    @FXML
    private ComboBox<String> language;

    @FXML
    public void initialize() {
        title.setText("დოკუმენტაცია");
        firstText.setText(DocumentationData.geo.get(0));
        secondText.setText(DocumentationData.geo.get(1));
        thirdText.setText(DocumentationData.geo.get(2));
        fourthText.setText(DocumentationData.geo.get(3));
        fivethText.setText(DocumentationData.geo.get(4));
        sixthText.setText(DocumentationData.geo.get(5));
        seventhText.setText(DocumentationData.geo.get(6));
    }

    @FXML
    private void onLanguageAction() {
        if (language.getValue().equals("GEO")) {
            title.setText("დოკუმენტაცია");
            firstText.setText(DocumentationData.geo.get(0));
            secondText.setText(DocumentationData.geo.get(1));
            thirdText.setText(DocumentationData.geo.get(2));
            fourthText.setText(DocumentationData.geo.get(3));
            fivethText.setText(DocumentationData.geo.get(4));
            sixthText.setText(DocumentationData.geo.get(5));
            seventhText.setText(DocumentationData.geo.get(6));
        } else if (language.getValue().equals("ENG")) {
            title.setText("Documentation");
            firstText.setText(DocumentationData.eng.get(0));
            secondText.setText(DocumentationData.eng.get(1));
            thirdText.setText(DocumentationData.eng.get(2));
            fourthText.setText(DocumentationData.eng.get(3));
            fivethText.setText(DocumentationData.eng.get(4));
            sixthText.setText(DocumentationData.eng.get(5));
        } else if (language.getValue().equals("RUS")) {
            title.setText("Документацыя");
            firstText.setText(DocumentationData.rus.get(0));
            secondText.setText(DocumentationData.rus.get(1));
            thirdText.setText(DocumentationData.rus.get(2));
            fourthText.setText(DocumentationData.rus.get(3));
            fivethText.setText(DocumentationData.rus.get(4));
            sixthText.setText(DocumentationData.rus.get(5));
            seventhText.setText(DocumentationData.rus.get(6));
        }
    }

    @FXML
    private void onMessengerMouseClicked() {
        try {
            String url = "https://www.facebook.com/daniel.abulashvili.5";
            URI uri = new URI(url);
            Desktop desktop = Desktop.getDesktop();
            desktop.browse(uri);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
