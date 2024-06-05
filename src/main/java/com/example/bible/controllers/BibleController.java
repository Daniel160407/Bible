package com.example.bible.controllers;

import com.example.bible.requests.LinkData;
import com.example.bible.runtimeData.BibleVersions;
import com.example.bible.runtimeData.InputtedData;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;

import java.util.ArrayList;
import java.util.List;

public class BibleController extends HomeController {
    @FXML
    private ComboBox<String> language;
    @FXML
    private ComboBox<String> versions;
    @FXML
    protected ComboBox<String> books;
    @FXML
    private ComboBox<Integer> chapter;
    @FXML
    private AnchorPane mainAnchorPane;
    @FXML
    private AnchorPane outerAnchorPane;


    protected LinkData linkData = new LinkData();
    protected InputtedData inputtedData = new InputtedData();
    private final BibleVersions bibleVersions = new BibleVersions();
    private List<String> versionsList;
    private List<String> booksList;
    private String backgroundColor;

    @FXML
    private void onLanguageAction() {
        inputtedData.setLanguage(language.getValue().toLowerCase());
        versions.getItems().clear();
        books.getItems().clear();
        chapter.getItems().clear();
        chapter.getEditor().clear();
    }

    @FXML
    private void onVersionsMouseClicked() {
        if (language.getValue() == null) {
            language.setValue("GEO");
        }
        if (language.getValue().equals("GEO")) {
            versionsList = List.of(bibleVersions.georgianVersions.split(","));
            booksList = List.of(bibleVersions.georgianBooks.split(","));
        } else if (language.getValue().equals("ENG")) {
            versionsList = List.of(bibleVersions.englishVersions.split(","));
            booksList = List.of(bibleVersions.englishBooks.split(","));
        } else if (language.getValue().equals("RUS")) {
            versionsList = List.of(bibleVersions.russianVersions.split(","));
            booksList = List.of(bibleVersions.russianBooks.split(","));
        }

        versions.getItems().addAll(versionsList);
        books.getItems().addAll(booksList);
    }

    @FXML
    private void onVersionsAction() {
        inputtedData.setVersionIndex(versions.getSelectionModel().getSelectedIndex());
        inputtedData.setVersion(versionDefinition());
    }

    @FXML
    private void onBooksAction() {
        chapter.getItems().clear();
        chapter.getEditor().clear();
        inputtedData.setBook(books.getValue());
        linkData.verses.clear();
        linkData.versePath.clear();
        linkData.setLinkInfo(inputtedData.getLanguage(), inputtedData.getVersion(), books.getItems().indexOf(inputtedData.getBook()) + 1, 1, 0, 0, true);
        int layoutY = 86;
        mainAnchorPane.getChildren().removeIf(node -> node instanceof Label);
        Label title = new Label();
        title.setLayoutX(737);
        title.setLayoutY(34);
        title.setFont(Font.font(25));
        if (backgroundColor.equals("#101828")) {
            title.setStyle("-fx-text-fill: white");
        } else {
            title.setStyle("-fx-text-fill: black");
        }
        title.setText(inputtedData.getBook());
        mainAnchorPane.getChildren().add(title);
        for (int i = 0; i < linkData.verses.size(); i++) {
            Label contentText = new Label();

            contentText.setLayoutX(37);
            contentText.setLayoutY(layoutY);
            if (backgroundColor.equals("#101828")) {
                contentText.setStyle("-fx-text-fill: white");
            } else {
                contentText.setStyle("-fx-text-fill: black");
            }
            contentText.setFont(Font.font(15));

            contentText.setText(linkData.versePath.get(i).get(0) + ":" + linkData.versePath.get(i).get(1) + " " + linkData.verses.get(i));
            mainAnchorPane.getChildren().add(contentText);
            mainAnchorPane.setPrefHeight(linkData.verses.size() * 70);
            layoutY += 50;
        }


        List<Integer> chapters = new ArrayList<>();
        for (int i = 0; i < linkData.chapterCount; i++) {
            chapters.add(i + 1);
        }
        chapter.setItems(FXCollections.observableList(chapters));
    }

    @FXML
    private void onChapterAction() {
        inputtedData.setChapter(Integer.parseInt(String.valueOf(chapter.getValue())));
        linkData.verses.clear();
        linkData.versePath.clear();
        linkData.setLinkInfo(inputtedData.getLanguage(), inputtedData.getVersion(), books.getItems().indexOf(inputtedData.getBook()) + 1, inputtedData.getChapter(), 0, 0, true);
        int layoutY = 86;
        mainAnchorPane.getChildren().removeIf(node -> node instanceof Label);
        Label title = new Label();
        title.setLayoutX(737);
        title.setLayoutY(34);
        title.setFont(Font.font(25));
        if (backgroundColor.equals("#101828")) {
            title.setStyle("-fx-text-fill: white");
        } else {
            title.setStyle("-fx-text-fill: black");
        }

        title.setText(inputtedData.getBook());
        mainAnchorPane.getChildren().add(title);
        for (int i = 0; i < linkData.verses.size(); i++) {
            Label contentText = new Label();

            contentText.setLayoutX(37);
            contentText.setLayoutY(layoutY);
            if (backgroundColor.equals("#101828")) {
                contentText.setStyle("-fx-text-fill: white");
            } else {
                contentText.setStyle("-fx-text-fill: black");
            }
            contentText.setFont(Font.font(15));

            contentText.setText(linkData.versePath.get(i).get(0) + ":" + linkData.versePath.get(i).get(1) + " " + linkData.verses.get(i));
            mainAnchorPane.getChildren().add(contentText);
            mainAnchorPane.setPrefHeight(linkData.verses.size() * 70);
            layoutY += 50;
        }
    }

    public AnchorPane getMainAnchorPane() {
        return mainAnchorPane;
    }

    public AnchorPane getOuterAnchorPane() {
        return outerAnchorPane;
    }

    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }
}
