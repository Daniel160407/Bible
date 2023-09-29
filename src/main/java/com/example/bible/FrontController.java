package com.example.bible;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class FrontController {
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private ComboBox<String> language;
    @FXML
    private ComboBox<String> versions;
    @FXML
    private ComboBox<String> books;
    @FXML
    private ComboBox<Integer> chapter;
    @FXML
    private ComboBox<Integer> verse;
    @FXML
    private ComboBox<Integer> till;
    @FXML
    private TextField search;
    @FXML
    private RadioButton darkMode;
    @FXML
    private AnchorPane mainAnchorPane;

    private final String whiteColor = "0xf4f4f4ff";
    private final String blackColor = "#000000";
    private List<String> versionsList = new ArrayList<>();
    private List<String> booksList = new ArrayList<>();
    private InputtedData inputtedData = new InputtedData();
    private BibleVersions bibleVersions = new BibleVersions();
    private LinkInfo linkInfo = new LinkInfo();
    private int previousLayoutYPath = -27;


    @FXML
    private void onDarkModeAction() {
        darkModeColorsChanger(anchorPane.getBackground().getFills().get(0).getFill().toString());
    }

    @FXML
    private void onVersionsMouseClicked() {
        getVersionsAndBooksInfo();

    }

    @FXML
    private void onLanguageAction() {
        inputtedData.setLanguage(language.getValue().toLowerCase());
    }

    @FXML
    private void onVersionsAction() {
        inputtedData.setVersionIndex(versions.getSelectionModel().getSelectedIndex());
    }

    @FXML
    private void onBooksAction() {
        inputtedData.setBook(books.getValue());
    }

    @FXML
    private void onChapterAction() {
        inputtedData.setChapter(Integer.parseInt(chapter.getEditor().getText()));
    }

    @FXML
    private void onVerseAction() {
        mainAnchorPane.getChildren().removeIf(node -> node instanceof StackPane);
        inputtedData.setVerse(Integer.parseInt(verse.getEditor().getText()));
        versionDefinition();
        linkInfo.setLinkInfo(inputtedData.getLanguage(), inputtedData.getVersion(), books.getItems().indexOf(inputtedData.getBook()) + 1, inputtedData.getChapter(), inputtedData.getVerse(), inputtedData.getTill());
        String str = "";
        for (int i = 0; i < linkInfo.verses.size(); i++) {
            str += linkInfo.verses.get(i) + " ";
        }
        linkInfo.verses.clear();
        Label newLabel = new Label();
        newLabel.prefHeight(70);
        newLabel.setStyle("-fx-text-fill: white; -fx-font-size: 15px;");
        StackPane stackPane = new StackPane();
        Rectangle rec = new Rectangle(1067, 83, Color.DARKBLUE);
        stackPane.getChildren().add(rec);
        stackPane.getChildren().add(newLabel);
        stackPane.setLayoutX(335);
        stackPane.setLayoutY(73);
        mainAnchorPane.getChildren().add(stackPane);
        newLabel.setText(str);
        System.out.println("tt: " + str);
    }

    @FXML
    private void onTillAction() {
        mainAnchorPane.getChildren().removeIf(node -> node instanceof StackPane);
        previousLayoutYPath = -27;
        inputtedData.setTill(Integer.parseInt(till.getEditor().getText()));
        linkInfo.setLinkInfo(inputtedData.getLanguage(), inputtedData.getVersion(), books.getItems().indexOf(inputtedData.getBook()) + 1, inputtedData.getChapter(), inputtedData.getVerse(), inputtedData.getTill());
        String str = "";
        for (int i = 0; i < linkInfo.verses.size(); i++) {
            Text newLabel = new Text();
            newLabel.setStyle("-fx-fill: white; -fx-font-size: 15px;");
            StackPane stackPane = new StackPane();
            Rectangle rec = new Rectangle(1100, 83, Color.DARKBLUE);
            stackPane.getChildren().add(rec);
            stackPane.getChildren().add(newLabel);
            stackPane.setLayoutX(335);
            stackPane.setLayoutY(previousLayoutYPath + 100);
            mainAnchorPane.getChildren().add(stackPane);
            previousLayoutYPath = (int) stackPane.getLayoutY();
            newLabel.setText(linkInfo.verses.get(i));

            newLabel.setTextAlignment(TextAlignment.CENTER);
            newLabel.setWrappingWidth(rec.getWidth());

            if (linkInfo.verses.size() > 5) {
                int prefHeight = (int) mainAnchorPane.getPrefHeight();
                mainAnchorPane.setPrefHeight(prefHeight + 60);
            }
        }
        previousLayoutYPath = 73;
        linkInfo.verses.clear();
        System.out.println(str);
    }

    @FXML
    private void onClearButtonAction() {
        mainAnchorPane.getChildren().removeIf(node -> node instanceof StackPane);
        mainAnchorPane.setPrefHeight(478);
    }

    private void darkModeColorsChanger(String color) {
        if (color.equals(whiteColor)) {
            anchorPane.setStyle("-fx-background-color:" + blackColor);
            mainAnchorPane.setStyle("-fx-background-color:" + blackColor);
            language.setStyle("-fx-text-fill:" + "white");
            versions.setStyle("-fx-text-fill:" + "white");
            books.setStyle("-fx-text-fill:" + "white");
            chapter.setStyle("-fx-text-fill:" + "white");
            verse.setStyle("-fx-text-fill:" + "white");
            till.setStyle("-fx-text-fill:" + "white");
            search.setStyle("-fx-text-fill:" + "white");
            darkMode.setStyle("-fx-text-fill:" + "white");
        } else {
            anchorPane.setStyle("-fx-background-color:" + whiteColor);
            mainAnchorPane.setStyle("-fx-background-color:" + whiteColor);
            language.setStyle("-fx-text-fill:" + blackColor);
            versions.setStyle("-fx-text-fill:" + blackColor);
            books.setStyle("-fx-text-fill:" + blackColor);
            chapter.setStyle("-fx-text-fill:" + blackColor);
            verse.setStyle("-fx-text-fill:" + blackColor);
            till.setStyle("-fx-text-fill:" + blackColor);
            search.setStyle("-fx-text-fill:" + blackColor);
            darkMode.setStyle("-fx-text-fill:" + blackColor);
        }
    }

    private void getVersionsAndBooksInfo() {
        try {
            if (inputtedData.getLanguage().equals("geo")) {
                versionsList.clear();
                versionsList.addAll(Arrays.asList(bibleVersions.georgianVersions.split(",")));
                booksList.clear();
                booksList.addAll(Arrays.asList(bibleVersions.georgianBooks.split(",")));
            } else if (inputtedData.getLanguage().equals("eng")) {
                versionsList.clear();
                versionsList.addAll(Arrays.asList(bibleVersions.englishVersions.split(",")));
                booksList.clear();
                booksList.addAll(Arrays.asList(bibleVersions.englishBooks.split(",")));
            }

            versions.getItems().clear();
            books.getItems().clear();
            versions.getItems().addAll(versionsList);
            books.getItems().addAll(booksList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void versionDefinition() {
        if (inputtedData.getLanguage().equals("geo")) {
            switch (inputtedData.getVersionIndex()) {
                case 1:
                    inputtedData.setVersion(bibleVersions.sbs2013);
                    break;
                case 2:
                    inputtedData.setVersion(bibleVersions.sbsStockholm2001);
                    break;
                case 3:
                    inputtedData.setVersion(bibleVersions.patriarchate);
                    break;
                case 4:
                    inputtedData.setVersion(bibleVersions.mtskhetaManuscript);
                    break;
                case 5:
                    inputtedData.setVersion(bibleVersions.theFourChaptersOfAddish);
                    break;
                case 6:
                    inputtedData.setVersion(bibleVersions.newWorldTranslation);
                    break;
                case 7:
                    inputtedData.setVersion(bibleVersions.newTestamentStockholm1985);
                    break;
                default:
                    inputtedData.setVersion(bibleVersions.newRedactedEdition2015);
                    break;
            }
        } else if (inputtedData.getLanguage().equals("eng")) {
            switch (inputtedData.getVersionIndex()) {
                case 1:
                    inputtedData.setVersion(bibleVersions.NIVNewInternationalVersion);
                    break;
                case 2:
                    inputtedData.setVersion(bibleVersions.KJVKingJamesVersion);
                    break;
                case 3:
                    inputtedData.setVersion(bibleVersions.genevaBible1599);
                    break;
                case 4:
                    inputtedData.setVersion(bibleVersions.NRSVNewRevisedStandardBible);
                    break;
                case 5:
                    inputtedData.setVersion(bibleVersions.darbysNewTranslation);
                    break;
                case 6:
                    inputtedData.setVersion(bibleVersions.ESVEnglishStandardVersion2001);
                    break;
                case 7:
                    inputtedData.setVersion(bibleVersions.douayRheimsBible);
                    break;
                case 8:
                    inputtedData.setVersion(bibleVersions.WEBWorldEnglishBible);
                    break;
                case 9:
                    inputtedData.setVersion(bibleVersions.modernKJV);
                    break;
                case 10:
                    inputtedData.setVersion(bibleVersions.ASVAmericanStandardVersion1901);
                    break;
                case 11:
                    inputtedData.setVersion(bibleVersions.basicEnglishBible);
                    break;
                case 12:
                    inputtedData.setVersion(bibleVersions.catholicPublicDomainVersion2009);
                    break;
                default:
                    inputtedData.setVersion(bibleVersions.NASBNewAmericanStandardBible);
                    break;
            }
        }

    }
}