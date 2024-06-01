package com.example.bible.controllers;


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
    public void initialize() throws IOException {
        title.setText("დოკუმენტაცია");
        FileReader fileReader = new FileReader("src/main/resources/com/example/bible/txt files/geoDocumentation.txt");
        getInfoFromTxtFile(fileReader);
    }

    @FXML
    private void onLanguageAction() throws IOException {

        if (language.getValue().equals("GEO")) {
            title.setText("დოკუმენტაცია");
            FileReader fileReader = new FileReader("src/main/resources/com/example/bible/txt files/geoDocumentation.txt");
            getInfoFromTxtFile(fileReader);
        } else if (language.getValue().equals("ENG")) {
            title.setText("Documentation");
            FileReader fileReader = new FileReader("src/main/resources/com/example/bible/txt files/engDocumentation.txt");
            getInfoFromTxtFile(fileReader);
        } else if (language.getValue().equals("RUS")) {
            title.setText("Документацыя");
            FileReader fileReader = new FileReader("src/main/resources/com/example/bible/txt files/rusDocumentation.txt");
            getInfoFromTxtFile(fileReader);
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

    private void getInfoFromTxtFile(FileReader fileReader) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String str;
        if ((str = bufferedReader.readLine()) != null) {
            firstText.setText(str);
        }
        if ((str = bufferedReader.readLine()) != null) {
            secondText.setText(str);
        }
        if ((str = bufferedReader.readLine()) != null) {
            thirdText.setText(str);
        }
        if ((str = bufferedReader.readLine()) != null) {
            fourthText.setText(str);
        }
        if ((str = bufferedReader.readLine()) != null) {
            secondTitle.setText(str);
        }
        if ((str = bufferedReader.readLine()) != null) {
            fivethText.setText(str);
        }
        if ((str = bufferedReader.readLine()) != null) {
            sixthText.setText(str);
        }
        if ((str = bufferedReader.readLine()) != null) {
            seventhText.setText(str);
        }
    }

}
