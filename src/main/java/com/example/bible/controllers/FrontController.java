package com.example.bible.controllers;

import com.example.bible.Bible;
import com.example.bible.runtimeData.BibleVersions;
import com.example.bible.runtimeData.InputtedData;
import com.example.bible.requests.LinkData;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.util.*;

public class FrontController extends ProjectorController {
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
    private static BibleVersions bibleVersions = new BibleVersions();
    private LinkData linkData = new LinkData();
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
        chapter.getItems().clear();
        //there must be a link request
        System.out.println(linkData.chaptersCount);
        for (int i = 1; i <= linkData.chaptersCount; i++) {
            chapter.getItems().add(i);
        }
        System.out.println("FFFFFFFFFFFFFFF");
    }

    @FXML
    private void onChapterAction() {
        if (chapter.getEditor().getText() != null && !chapter.getEditor().getText().equals("")) {
            inputtedData.setChapter(Integer.parseInt(chapter.getEditor().getText()));

        }
    }


    @FXML
    private void onVerseAction() {
        if (verse.getEditor().getText() != null && !verse.getEditor().getText().equals("")) {
            mainAnchorPane.getChildren().removeIf(node -> node instanceof StackPane);
            linkData.verses.clear();
            inputtedData.setVerse(Integer.parseInt(verse.getEditor().getText()));
            versionDefinition();
            linkData.setLinkInfo(inputtedData.getLanguage(), inputtedData.getVersion(), books.getItems().indexOf(inputtedData.getBook()) + 1, inputtedData.getChapter(), inputtedData.getVerse(), inputtedData.getVerse());
            String str = "";
            for (int i = 0; i < linkData.verses.size(); i++) {
                str += linkData.verses.get(i) + " ";
            }

            Text newVerseBox = new Text();
            newVerseBox.prefHeight(70);
            newVerseBox.setStyle("-fx-fill: white; -fx-font-size: 15px;");
            StackPane stackPane = new StackPane();
            Rectangle rec = new Rectangle(1067, 83, Color.DARKBLUE);
            stackPane.getChildren().add(rec);
            stackPane.getChildren().add(newVerseBox);
            stackPane.setLayoutX(400);
            stackPane.setLayoutY(73);
            mainAnchorPane.getChildren().add(stackPane);
            newVerseBox.setText(str);
            System.out.println("tt: " + str);
        }

    }

    @FXML
    private void onTillAction() {
        if (till.getEditor().getText() != null && !till.getEditor().getText().equals("")) {
            mainAnchorPane.getChildren().removeIf(node -> node instanceof StackPane);
            linkData.verses.clear();
            previousLayoutYPath = -27;
            inputtedData.setTill(Integer.parseInt(till.getEditor().getText()));
            linkData.setLinkInfo(inputtedData.getLanguage(), inputtedData.getVersion(), books.getItems().indexOf(inputtedData.getBook()) + 1, inputtedData.getChapter(), inputtedData.getVerse(), inputtedData.getTill());
            String str = "";
            for (int i = 0; i < linkData.verses.size(); i++) {
                Text newVerseBox = new Text();
                newVerseBox.setStyle("-fx-fill: white; -fx-font-size: 15px;");
                StackPane stackPane = new StackPane();
                Rectangle rec = new Rectangle(1100, 83, Color.DARKBLUE);
                stackPane.getChildren().add(rec);
                stackPane.getChildren().add(newVerseBox);
                stackPane.setLayoutX(400);
                stackPane.setLayoutY(previousLayoutYPath + 100);
                mainAnchorPane.getChildren().add(stackPane);
                previousLayoutYPath = (int) stackPane.getLayoutY();
                newVerseBox.setText(linkData.verses.get(i));

                newVerseBox.setTextAlignment(TextAlignment.CENTER);
                newVerseBox.setWrappingWidth(rec.getWidth());

                if (linkData.verses.size() > 5) {
                    int prefHeight = (int) mainAnchorPane.getPrefHeight();
                    mainAnchorPane.setPrefHeight(prefHeight + 60);
                }
            }
            linkData.verses.clear();
            previousLayoutYPath = 73;
            System.out.println(str);
        }

    }

    @FXML
    private void onClearButtonAction() {
        mainAnchorPane.getChildren().removeIf(node -> node instanceof StackPane);
        mainAnchorPane.setPrefHeight(478);


        chapter.getItems().clear();
        chapter.setValue(null);
        verse.getItems().clear();
        verse.setValue(null);
        till.getItems().clear();
        till.setValue(null);

    }

    @FXML
    private void onOpenPresentViewAction() throws IOException {
        FXMLLoader loader = new FXMLLoader(Bible.class.getResource("projector.fxml"));
        Parent root = loader.load();
        Stage newStage = new Stage();
        newStage.setTitle("Present View");
        newStage.setScene(new Scene(root, 1600, 400));
        newStage.show();
    }

    @FXML
    private void onShowButtonAction() {
        previousLayoutYPath = -27;
        String str = "";
        for (int i = 0; i < linkData.verses.size(); i++) {
            Text newVerseBox = new Text();
            StackPane stackPane = new StackPane();
            newVerseBox.setStyle("-fx-fill: black; -fx-font-size: 15px;");
            newVerseBox.setLayoutX(400);
            newVerseBox.setLayoutY(previousLayoutYPath + 100);
            stackPane.getChildren().add(newVerseBox);

            projectorAnchorPane.getChildren().add(stackPane);


            newVerseBox.setText(linkData.verses.get(i));
            previousLayoutYPath = (int) newVerseBox.getLayoutY();
            System.out.println("text: " + newVerseBox.getText());

            newVerseBox.setTextAlignment(TextAlignment.CENTER);
            newVerseBox.setWrappingWidth(1000);


        }
        previousLayoutYPath = 73;
        System.out.println(str);
    }

    @FXML
    private void onSearchAction() {
        mainAnchorPane.getChildren().removeIf(node -> node instanceof StackPane);
        linkData.verses.clear();
        versionDefinition();
        linkData.setLinkInfo(inputtedData.getLanguage(), inputtedData.getVersion(), books.getItems().indexOf(inputtedData.getBook()) + 1, inputtedData.getChapter(), inputtedData.getVerse(), inputtedData.getVerse());
        linkData.searchText = search.getText();
        for (int i = 0; i < linkData.verses.size(); i++) {
            Text newVerseBox = new Text();
            newVerseBox.setStyle("-fx-fill: white; -fx-font-size: 15px;");
            StackPane stackPane = new StackPane();
            Rectangle rec = new Rectangle(1100, 83, Color.DARKBLUE);
            stackPane.getChildren().add(rec);
            stackPane.getChildren().add(newVerseBox);
            stackPane.setLayoutX(400);
            stackPane.setLayoutY(previousLayoutYPath + 100);
            mainAnchorPane.getChildren().add(stackPane);
            previousLayoutYPath = (int) stackPane.getLayoutY();
            newVerseBox.setText(linkData.verses.get(i));

            newVerseBox.setTextAlignment(TextAlignment.CENTER);
            newVerseBox.setWrappingWidth(rec.getWidth());

        }
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
            } else if (inputtedData.getLanguage().equals("rus")) {
                versionsList.clear();
                versionsList.addAll(Arrays.asList(bibleVersions.russianVersions.split(",")));
                booksList.clear();
                booksList.addAll(Arrays.asList(bibleVersions.russianBooks.split(",")));
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
        } else if (inputtedData.getLanguage().equals("rus")) {
            switch (inputtedData.getVersionIndex()) {
                case 1:
                    inputtedData.setVersion(bibleVersions.modernTranslation);
                    break;
                case 2:
                    inputtedData.setVersion(bibleVersions.newRussianTranslationIBS);
                    break;
                case 3:
                    inputtedData.setVersion(bibleVersions.bibleByHermannMenge);
                    break;
                case 4:
                    inputtedData.setVersion(bibleVersions.holyBibleMeaningfulTranslation);
                    break;
                case 5:
                    inputtedData.setVersion(bibleVersions.churchSlavonicBibleOfCyrilAndMethodius);
                    break;
                case 6:
                    inputtedData.setVersion(bibleVersions.newTestamentRestorationTranslation1998);
                    break;
                case 7:
                    inputtedData.setVersion(bibleVersions.wordOfLifeNewTestament1991);
                    break;
                case 8:
                    inputtedData.setVersion(bibleVersions.newTestamentBishopsTranslationCassianaBezobrazova);
                    break;
                default:
                    inputtedData.setVersion(bibleVersions.synodalTranslation);
                    break;
            }
        }

    }
}