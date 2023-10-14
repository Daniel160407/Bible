package com.example.bible.controllers;

import com.example.bible.Bible;
import com.example.bible.runtimeData.BibleVersions;
import com.example.bible.runtimeData.InputtedData;
import com.example.bible.requests.LinkData;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.util.*;
import java.util.List;

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
    private ScrollPane scrollPane;
    @FXML
    private AnchorPane mainAnchorPane;
    @FXML
    private ComboBox<String> fontSize;
    @FXML
    private RadioButton img1;
    @FXML
    private RadioButton img2;
    @FXML
    private RadioButton img3;
    @FXML
    private RadioButton img4;
    @FXML
    private RadioButton img5;
    @FXML
    private RadioButton img6;
    @FXML
    private RadioButton img7;
    @FXML
    private RadioButton img8;
    @FXML
    private RadioButton img9;
    @FXML
    private RadioButton img10;
    @FXML
    private RadioButton img11;
    @FXML
    private RadioButton img12;
    @FXML
    private RadioButton img13;
    @FXML
    private RadioButton img14;
    @FXML
    private RadioButton img15;
    @FXML
    private RadioButton img16;
    @FXML
    private RadioButton img17;
    @FXML
    private RadioButton img18;
    @FXML
    private RadioButton img19;
    @FXML
    private RadioButton img20;
    @FXML
    private ComboBox<String> geoProjectorVersions;
    @FXML
    private ComboBox<String> engProjectorVersions;
    @FXML
    private ComboBox<String> rusProjectorVersions;
    @FXML
    private CheckBox geoCheckBox;
    @FXML
    private CheckBox engCheckBox;
    @FXML
    private CheckBox rusCheckBox;
    @FXML
    private Label fontSizeLabel;
    @FXML
    private Label langLab1;
    @FXML
    private Label langLab2;
    @FXML
    private Label langLab3;
    @FXML
    private Label ideaOf;
    @FXML
    private Label madeBy;
    @FXML
    private Label eso;
    @FXML
    private Label daniel;


    private final List<String> versionsList = new ArrayList<>();
    private final List<String> booksList = new ArrayList<>();
    private final InputtedData inputtedData = new InputtedData();
    private static final BibleVersions bibleVersions = new BibleVersions();
    private final LinkData linkData = new LinkData();
    private int previousLayoutYPath = -27;
    private ProjectorController projectorController;
    private String backgroundImage;


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

        linkData.setLinkInfo(inputtedData.getLanguage(), inputtedData.getVersion(), books.getItems().indexOf(inputtedData.getBook()) + 1, 1, 1, 1);
        chapter.getItems().clear();
        for (int i = 0; i < linkData.chapterCount; i++) {
            chapter.getItems().add(i + 1);
        }
    }

    @FXML
    private void onChapterAction() {
        if (chapter.getEditor().getText() != null && !chapter.getEditor().getText().equals("")) {
            inputtedData.setChapter(Integer.parseInt(chapter.getEditor().getText()));
        }
        linkData.setLinkInfo(inputtedData.getLanguage(), inputtedData.getVersion(), books.getItems().indexOf(inputtedData.getBook()) + 1, inputtedData.getChapter(), 1, 1);
        verse.getItems().clear();
        till.getItems().clear();
        for (int i = 0; i < linkData.verseCount; i++) {
            verse.getItems().add(i + 1);
            till.getItems().add(i + 1);
        }
    }

    @FXML
    private void onVerseAction() {
        if (verse.getEditor().getText() != null && !verse.getEditor().getText().equals("")) {
            mainAnchorPane.getChildren().removeIf(node -> node instanceof StackPane);
            linkData.verses.clear();
            mainAnchorPane.setPrefHeight(scrollPane.getHeight());
            inputtedData.setVerse(Integer.parseInt(verse.getEditor().getText()));
            inputtedData.setVersion(versionDefinition());
            linkData.setLinkInfo(inputtedData.getLanguage(), inputtedData.getVersion(), books.getItems().indexOf(inputtedData.getBook()) + 1, inputtedData.getChapter(), inputtedData.getVerse(), inputtedData.getVerse());
            StringBuilder str = new StringBuilder();
            for (int i = 0; i < linkData.verses.size(); i++) {
                str.append(linkData.verses.get(i)).append(" ");
            }

            Text newVerseBox = new Text();
            newVerseBox.prefHeight(70);
            newVerseBox.getStyleClass().add("newVerseBox");
            StackPane stackPane = new StackPane();
            Rectangle rec = new Rectangle(1067, 83, Color.DARKBLUE);
            rec.setArcWidth(20);
            rec.setArcHeight(20);
            stackPane.getChildren().add(rec);
            stackPane.getChildren().add(newVerseBox);
            stackPane.setLayoutX(400);
            stackPane.setLayoutY(73);
            mainAnchorPane.getChildren().add(stackPane);
            newVerseBox.setText(str.toString());
            newVerseBox.setTextAlignment(TextAlignment.CENTER);
            newVerseBox.setWrappingWidth(rec.getWidth());
        }

    }

    @FXML
    private void onTillAction() {
        if (till.getEditor().getText() != null && !till.getEditor().getText().equals("")) {
            mainAnchorPane.getChildren().removeIf(node -> node instanceof StackPane);
            linkData.verses.clear();
            mainAnchorPane.setPrefHeight(scrollPane.getHeight());
            previousLayoutYPath = -27;
            inputtedData.setTill(Integer.parseInt(till.getEditor().getText()));
            linkData.setLinkInfo(inputtedData.getLanguage(), inputtedData.getVersion(), books.getItems().indexOf(inputtedData.getBook()) + 1, inputtedData.getChapter(), inputtedData.getVerse(), inputtedData.getTill());

            for (int i = 0; i < linkData.verses.size(); i++) {
                Text newVerseBox = new Text();
                newVerseBox.getStyleClass().add("newVerseBox");
                StackPane stackPane = new StackPane();
                Rectangle rec = new Rectangle(1100, 83, Color.DARKBLUE);
                rec.setArcWidth(20);
                rec.setArcHeight(20);
                stackPane.getChildren().add(rec);
                stackPane.getChildren().add(newVerseBox);
                stackPane.setLayoutX(400);
                stackPane.setLayoutY(previousLayoutYPath + 100);
                mainAnchorPane.getChildren().add(stackPane);
                previousLayoutYPath = (int) stackPane.getLayoutY();
                newVerseBox.setText(linkData.verses.get(i));

                newVerseBox.setTextAlignment(TextAlignment.CENTER);
                newVerseBox.setWrappingWidth(rec.getWidth());

                if (linkData.verses.size() > 4) {
                    int prefHeight = (int) mainAnchorPane.getPrefHeight();
                    mainAnchorPane.setPrefHeight(prefHeight + 87);
                }
            }

            previousLayoutYPath = -73;

        }

    }

    @FXML
    private void onChapterXAction() {
        chapter.getEditor().clear();
    }

    @FXML
    private void onVerseXAction() {
        verse.getEditor().clear();
    }

    @FXML
    private void onTillXAction() {
        till.getEditor().clear();
    }

    @FXML
    private void onClearVersesAction() {
        mainAnchorPane.getChildren().removeIf(node -> node instanceof StackPane);
        mainAnchorPane.setPrefHeight(478);
        linkData.verses.clear();
        chapter.getEditor().clear();
        verse.getEditor().clear();
        till.getEditor().clear();
    }

    @FXML
    private void onClearButtonAction() {

        projectorController.projectorAnchorPane.getChildren().clear();
    }

    @FXML
    private void onOpenPresentViewAction() throws IOException {
        FXMLLoader loader = new FXMLLoader(Bible.class.getResource("fxml files/projector.fxml"));
        Parent root = loader.load();
        projectorController = loader.getController();
        Stage newStage = new Stage();
        newStage.getIcons().add(new Image("https://cdn-icons-png.flaticon.com/512/3004/3004416.png"));
        newStage.setTitle("Present View");
        Scene scene = new Scene(root, 800, 600);
        scene.getStylesheets().add(Objects.requireNonNull(Bible.class.getResource("styles/style.css")).toExternalForm());
        newStage.setScene(scene);
        newStage.show();
        projectorController.projectorAnchorPane.setStyle("-fx-background-image: url('" + backgroundImage + "');");
    }

    @FXML
    private void onShowButtonAction() {
        projectorController.projectorAnchorPane.getChildren().removeIf(node -> node instanceof Text);
        String allVersesInOne = "";
        allVersesInOne = requestManager(allVersesInOne);

        projectorController.projectorTextBox.getStyleClass().add("projectorTextBox");
        projectorController.projectorTextBox.setStyle("-fx-font-size: " + Integer.parseInt(fontSize.getValue()) + 2 + "px;");
        projectorController.projectorAnchorPane.getChildren().add(projectorController.projectorTextBox);
        projectorController.projectorTextBox.setText(allVersesInOne);
        projectorController.projectorTextBox.setTextAlignment(TextAlignment.CENTER);
    }

    @FXML
    private void onSearchAction() {
        mainAnchorPane.getChildren().removeIf(node -> node instanceof StackPane);
        linkData.verses.clear();
        mainAnchorPane.setPrefHeight(scrollPane.getPrefHeight());
        inputtedData.setVersion(versionDefinition());
        linkData.search = search.getText();
        linkData.setLinkInfo(inputtedData.getLanguage(), inputtedData.getVersion(), books.getItems().indexOf(inputtedData.getBook()) + 1, inputtedData.getChapter(), 1, 1);

        for (int i = 0; i < linkData.verses.size(); i++) {
            Text newVerseBox = new Text();
            newVerseBox.getStyleClass().add("newVerseBox");
            StackPane stackPane = new StackPane();
            Rectangle rec = new Rectangle(1100, 83, Color.DARKBLUE);
            rec.setArcWidth(20);
            rec.setArcHeight(20);
            Button separateButton = new Button();
            VBox vBox = new VBox();
            vBox.setAlignment(Pos.CENTER);
            vBox.setSpacing(10);
            vBox.getChildren().addAll(newVerseBox, separateButton);
            stackPane.getChildren().addAll(rec, vBox);

            stackPane.setLayoutX(400);
            stackPane.setLayoutY(previousLayoutYPath + 100);
            mainAnchorPane.getChildren().add(stackPane);
            previousLayoutYPath = (int) stackPane.getLayoutY();
            newVerseBox.setText(linkData.verses.get(i));

            separateButton.getStyleClass().add("separate-button");
            separateButton.setLayoutY(100);
            separateButton.setText("Separate");
            separateButton.setOnAction(event -> {
                mainAnchorPane.getChildren().removeIf(node -> node instanceof StackPane);
                StackPane stackPane2 = new StackPane();
                stackPane2.setLayoutX(400);
                stackPane2.setLayoutY(73);
                stackPane2.getChildren().addAll(rec, newVerseBox);
                mainAnchorPane.getChildren().add(stackPane2);
                mainAnchorPane.setPrefHeight(scrollPane.getPrefHeight());
                linkData.verses.clear();
                linkData.verses.add(newVerseBox.getText());
            });

            newVerseBox.setTextAlignment(TextAlignment.CENTER);
            newVerseBox.setWrappingWidth(rec.getWidth());
            if (linkData.verses.size() > 4) {
                int prefHeight = (int) mainAnchorPane.getPrefHeight();
                mainAnchorPane.setPrefHeight(prefHeight + rec.getHeight() + 9);
            }

        }
        previousLayoutYPath = -73;
        linkData.search = null;
    }

    @FXML
    private void onEsoMouseClicked() {
        try {
            String url = "https://www.facebook.com/esaia.gafrindashvili/";
            URI uri = new URI(url);
            Desktop desktop = Desktop.getDesktop();
            desktop.browse(uri);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onDocumentationAction() throws IOException {
        FXMLLoader loader = new FXMLLoader(Bible.class.getResource("fxml files/documentation.fxml"));
        Parent root = loader.load();
        Stage newStage = new Stage();
        newStage.getIcons().add(new Image("https://cdn-icons-png.flaticon.com/512/3004/3004416.png"));
        newStage.setTitle("Documentation");
        Scene scene = new Scene(root, 800, 600);
        scene.getStylesheets().add(Objects.requireNonNull(Bible.class.getResource("styles/style.css")).toExternalForm());
        newStage.setScene(scene);
        newStage.show();
    }

    @FXML
    private void onDanielMouseClicked() {
        try {
            String url = "https://www.facebook.com/daniel.abulashvili.5";
            URI uri = new URI(url);
            Desktop desktop = Desktop.getDesktop();
            desktop.browse(uri);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onImg1Action() {
        backgroundImage = "https://bibleversesgeo.netlify.app/images/1.jpeg";
        radioButtonSwitch();
        img1.setSelected(true);
    }

    @FXML
    private void onImg2Action() {
        backgroundImage = "https://bibleversesgeo.netlify.app/images/2.jpeg";
        radioButtonSwitch();
        img2.setSelected(true);
    }

    @FXML
    private void onImg3Action() {
        backgroundImage = "https://bibleversesgeo.netlify.app/images/3.jpeg";
        radioButtonSwitch();
        img3.setSelected(true);
    }

    @FXML
    private void onImg4Action() {
        backgroundImage = "https://bibleversesgeo.netlify.app/images/4.jpeg";
        radioButtonSwitch();
        img4.setSelected(true);
    }

    @FXML
    private void onImg5Action() {
        backgroundImage = "https://bibleversesgeo.netlify.app/images/5.jpeg";
        radioButtonSwitch();
        img5.setSelected(true);
    }

    @FXML
    private void onImg6Action() {
        backgroundImage = "https://bibleversesgeo.netlify.app/images/6.jpeg";
        radioButtonSwitch();
        img6.setSelected(true);
    }

    @FXML
    private void onImg7Action() {
        backgroundImage = "https://bibleversesgeo.netlify.app/images/7.jpeg";
        radioButtonSwitch();
        img7.setSelected(true);
    }

    @FXML
    private void onImg8Action() {
        backgroundImage = "https://bibleversesgeo.netlify.app/images/8.jpeg";
        radioButtonSwitch();
        img8.setSelected(true);
    }

    @FXML
    private void onImg9Action() {
        backgroundImage = "https://bibleversesgeo.netlify.app/images/9.jpeg";
        radioButtonSwitch();
        img9.setSelected(true);
    }

    @FXML
    private void onImg10Action() {
        backgroundImage = "https://bibleversesgeo.netlify.app/images/10.jpeg";
        radioButtonSwitch();
        img10.setSelected(true);
    }

    @FXML
    private void onImg11Action() {
        backgroundImage = "https://bibleversesgeo.netlify.app/images/11.jpeg";
        radioButtonSwitch();
        img11.setSelected(true);
    }

    @FXML
    private void onImg12Action() {
        backgroundImage = "https://bibleversesgeo.netlify.app/images/12.jpeg";
        radioButtonSwitch();
        img12.setSelected(true);
    }

    @FXML
    private void onImg13Action() {
        backgroundImage = "https://bibleversesgeo.netlify.app/images/13.jpeg";
        radioButtonSwitch();
        img13.setSelected(true);
    }

    @FXML
    private void onImg14Action() {
        backgroundImage = "https://bibleversesgeo.netlify.app/images/14.jpeg";
        radioButtonSwitch();
        img14.setSelected(true);
    }

    @FXML
    private void onImg15Action() {
        backgroundImage = "https://bibleversesgeo.netlify.app/images/15.jpeg";
        radioButtonSwitch();
        img15.setSelected(true);
    }

    @FXML
    private void onImg16Action() {
        backgroundImage = "https://blackscreen.space/images/pro/black-screen_39.png";
        radioButtonSwitch();
        img16.setSelected(true);
    }

    @FXML
    private void onImg17Action() {
        backgroundImage = "https://bibleversesgeo.netlify.app/images/17.jpeg";
        radioButtonSwitch();
        img17.setSelected(true);
    }

    @FXML
    private void onImg18Action() {
        backgroundImage = "https://bibleversesgeo.netlify.app/images/18.jpeg";
        radioButtonSwitch();
        img18.setSelected(true);
    }

    @FXML
    private void onImg19Action() {
        backgroundImage = "https://bibleversesgeo.netlify.app/images/19.jpeg";
        radioButtonSwitch();
        img19.setSelected(true);
    }

    @FXML
    private void onImg20Action() {
        backgroundImage = "https://bibleversesgeo.netlify.app/images/20.jpeg";
        radioButtonSwitch();
        img20.setSelected(true);
    }


    private void darkModeColorsChanger(String color) {
        String whiteColor = "0xf4f4f4ff";
        String darkColor = "#030028";
        if (color.equals(whiteColor)) {
            anchorPane.setStyle("-fx-background-color: " + darkColor);
            mainAnchorPane.setStyle("-fx-background-color:" + darkColor);
            language.setStyle("-fx-text-fill:" + "white");
            versions.setStyle("-fx-text-fill:" + "white");
            books.setStyle("-fx-text-fill:" + "white");
            chapter.setStyle("-fx-text-fill:" + "white");
            verse.setStyle("-fx-text-fill:" + "white");
            till.setStyle("-fx-text-fill:" + "white");
            search.setStyle("-fx-text-fill:" + "white");
            darkMode.setStyle("-fx-text-fill:" + "white");
            fontSizeLabel.setStyle("-fx-text-fill:" + "white");
            langLab1.setStyle("-fx-text-fill:" + "white");
            langLab2.setStyle("-fx-text-fill:" + "white");
            langLab3.setStyle("-fx-text-fill:" + "white");
            ideaOf.setStyle("-fx-text-fill:" + "white");
            madeBy.setStyle("-fx-text-fill:" + "white");
            eso.setStyle("-fx-text-fill:" + "white");
            daniel.setStyle("-fx-text-fill:" + "white");
        } else {
            anchorPane.setStyle("-fx-background-color:" + whiteColor);
            mainAnchorPane.setStyle("-fx-background-color:" + whiteColor);
            language.setStyle("-fx-text-fill:" + darkColor);
            versions.setStyle("-fx-text-fill:" + darkColor);
            books.setStyle("-fx-text-fill:" + darkColor);
            chapter.setStyle("-fx-text-fill:" + darkColor);
            verse.setStyle("-fx-text-fill:" + darkColor);
            till.setStyle("-fx-text-fill:" + darkColor);
            search.setStyle("-fx-text-fill:" + darkColor);
            darkMode.setStyle("-fx-text-fill:" + darkColor);
            fontSizeLabel.setStyle("-fx-text-fill:" + darkColor);
            langLab1.setStyle("-fx-text-fill:" + darkColor);
            langLab2.setStyle("-fx-text-fill:" + darkColor);
            langLab3.setStyle("-fx-text-fill:" + darkColor);
            ideaOf.setStyle("-fx-text-fill:" + darkColor);
            madeBy.setStyle("-fx-text-fill:" + darkColor);
            eso.setStyle("-fx-text-fill:" + darkColor);
            daniel.setStyle("-fx-text-fill:" + darkColor);
        }
    }

    private void getVersionsAndBooksInfo() {
        try {
            switch (inputtedData.getLanguage()) {
                case "geo":
                    versionsList.clear();
                    versionsList.addAll(Arrays.asList(bibleVersions.georgianVersions.split(",")));
                    booksList.clear();
                    booksList.addAll(Arrays.asList(bibleVersions.georgianBooks.split(",")));
                    break;
                case "eng":
                    versionsList.clear();
                    versionsList.addAll(Arrays.asList(bibleVersions.englishVersions.split(",")));
                    booksList.clear();
                    booksList.addAll(Arrays.asList(bibleVersions.englishBooks.split(",")));
                    break;
                case "rus":
                    versionsList.clear();
                    versionsList.addAll(Arrays.asList(bibleVersions.russianVersions.split(",")));
                    booksList.clear();
                    booksList.addAll(Arrays.asList(bibleVersions.russianBooks.split(",")));
                    break;
            }

            versions.getItems().clear();
            books.getItems().clear();
            geoProjectorVersions.getItems().clear();
            engProjectorVersions.getItems().clear();
            rusProjectorVersions.getItems().clear();
            versions.getItems().addAll(versionsList);
            books.getItems().addAll(booksList);
            geoProjectorVersions.getItems().addAll(bibleVersions.georgianVersions.split(","));
            engProjectorVersions.getItems().addAll(bibleVersions.englishVersions.split(","));
            rusProjectorVersions.getItems().addAll(bibleVersions.russianVersions.split(","));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String versionDefinition() {
        String version = "";
        switch (inputtedData.getLanguage()) {
            case "geo":
                switch (inputtedData.getVersionIndex()) {
                    case 1:
                        version = bibleVersions.sbs2013;
                        break;
                    case 2:
                        version = bibleVersions.sbsStockholm2001;
                        break;
                    case 3:
                        version = bibleVersions.patriarchate;
                        break;
                    case 4:
                        version = bibleVersions.mtskhetaManuscript;
                        break;
                    case 5:
                        version = bibleVersions.theFourChaptersOfAddish;
                        break;
                    case 6:
                        version = bibleVersions.newWorldTranslation;
                        break;
                    case 7:
                        version = bibleVersions.newTestamentStockholm1985;
                        break;
                    default:
                        version = bibleVersions.newRedactedEdition2015;
                        break;
                }
                break;
            case "eng":
                switch (inputtedData.getVersionIndex()) {
                    case 1:
                        version = bibleVersions.NIVNewInternationalVersion;
                        break;
                    case 2:
                        version = bibleVersions.KJVKingJamesVersion;
                        break;
                    case 3:
                        version = bibleVersions.genevaBible1599;
                        break;
                    case 4:
                        version = bibleVersions.NRSVNewRevisedStandardBible;
                        break;
                    case 5:
                        version = bibleVersions.darbysNewTranslation;
                        break;
                    case 6:
                        version = bibleVersions.ESVEnglishStandardVersion2001;
                        break;
                    case 7:
                        version = bibleVersions.douayRheimsBible;
                        break;
                    case 8:
                        version = bibleVersions.WEBWorldEnglishBible;
                        break;
                    case 9:
                        version = bibleVersions.modernKJV;
                        break;
                    case 10:
                        version = bibleVersions.ASVAmericanStandardVersion1901;
                        break;
                    case 11:
                        version = bibleVersions.basicEnglishBible;
                        break;
                    case 12:
                        version = bibleVersions.catholicPublicDomainVersion2009;
                        break;
                    default:
                        version = bibleVersions.NASBNewAmericanStandardBible;
                        break;
                }
                break;
            case "rus":
                switch (inputtedData.getVersionIndex()) {
                    case 1:
                        version = bibleVersions.modernTranslation;
                        break;
                    case 2:
                        version = bibleVersions.newRussianTranslationIBS;
                        break;
                    case 3:
                        version = bibleVersions.bibleByHermannMenge;
                        break;
                    case 4:
                        version = bibleVersions.holyBibleMeaningfulTranslation;
                        break;
                    case 5:
                        version = bibleVersions.churchSlavonicBibleOfCyrilAndMethodius;
                        break;
                    case 6:
                        version = bibleVersions.newTestamentRestorationTranslation1998;
                        break;
                    case 7:
                        version = bibleVersions.wordOfLifeNewTestament1991;
                        break;
                    case 8:
                        version = bibleVersions.newTestamentBishopsTranslationCassianaBezobrazova;
                        break;
                    default:
                        version = bibleVersions.synodalTranslation;
                        break;
                }
                break;
        }
        return version;
    }

    private String projectorVersionDefinition(String language) {
        String version = "";
        if (geoCheckBox.isSelected() && language.equals("geo")) {
            switch (inputtedData.getVersionIndex()) {
                case 1:
                    version = bibleVersions.sbs2013;
                    break;
                case 2:
                    version = bibleVersions.sbsStockholm2001;
                    break;
                case 3:
                    version = bibleVersions.patriarchate;
                    break;
                case 4:
                    version = bibleVersions.mtskhetaManuscript;
                    break;
                case 5:
                    version = bibleVersions.theFourChaptersOfAddish;
                    break;
                case 6:
                    version = bibleVersions.newWorldTranslation;
                    break;
                case 7:
                    version = bibleVersions.newTestamentStockholm1985;
                    break;
                default:
                    version = bibleVersions.newRedactedEdition2015;
                    break;
            }
        } else if (engCheckBox.isSelected() && language.equals("eng")) {
            switch (inputtedData.getVersionIndex()) {
                case 1:
                    version = bibleVersions.NIVNewInternationalVersion;
                    break;
                case 2:
                    version = bibleVersions.KJVKingJamesVersion;
                    break;
                case 3:
                    version = bibleVersions.genevaBible1599;
                    break;
                case 4:
                    version = bibleVersions.NRSVNewRevisedStandardBible;
                    break;
                case 5:
                    version = bibleVersions.darbysNewTranslation;
                    break;
                case 6:
                    version = bibleVersions.ESVEnglishStandardVersion2001;
                    break;
                case 7:
                    version = bibleVersions.douayRheimsBible;
                    break;
                case 8:
                    version = bibleVersions.WEBWorldEnglishBible;
                    break;
                case 9:
                    version = bibleVersions.modernKJV;
                    break;
                case 10:
                    version = bibleVersions.ASVAmericanStandardVersion1901;
                    break;
                case 11:
                    version = bibleVersions.basicEnglishBible;
                    break;
                case 12:
                    version = bibleVersions.catholicPublicDomainVersion2009;
                    break;
                default:
                    version = bibleVersions.NASBNewAmericanStandardBible;
                    break;
            }
        } else if (rusCheckBox.isSelected() && language.equals("rus")) {
            switch (inputtedData.getVersionIndex()) {
                case 1:
                    version = bibleVersions.modernTranslation;
                    break;
                case 2:
                    version = bibleVersions.newRussianTranslationIBS;
                    break;
                case 3:
                    version = bibleVersions.bibleByHermannMenge;
                    break;
                case 4:
                    version = bibleVersions.holyBibleMeaningfulTranslation;
                    break;
                case 5:
                    version = bibleVersions.churchSlavonicBibleOfCyrilAndMethodius;
                    break;
                case 6:
                    version = bibleVersions.newTestamentRestorationTranslation1998;
                    break;
                case 7:
                    version = bibleVersions.wordOfLifeNewTestament1991;
                    break;
                case 8:
                    version = bibleVersions.newTestamentBishopsTranslationCassianaBezobrazova;
                    break;
                default:
                    version = bibleVersions.synodalTranslation;
                    break;
            }
        }
        return version;
    }

    private void radioButtonSwitch() {
        img1.setSelected(false);
        img2.setSelected(false);
        img3.setSelected(false);
        img4.setSelected(false);
        img5.setSelected(false);
        img6.setSelected(false);
        img7.setSelected(false);
        img8.setSelected(false);
        img9.setSelected(false);
        img10.setSelected(false);
        img11.setSelected(false);
        img12.setSelected(false);
        img13.setSelected(false);
        img14.setSelected(false);
        img15.setSelected(false);
        img16.setSelected(false);
        img17.setSelected(false);
        img18.setSelected(false);
        img19.setSelected(false);
        img20.setSelected(false);
        projectorController.projectorAnchorPane.setStyle("-fx-background-image: url('" + backgroundImage + "');");
    }

    private String requestManager(String allVersesInOne) {
        int versesCount = linkData.verses.size();
        List<String> versesData = new ArrayList<>(linkData.verses);
        linkData.verses.clear();

        if (geoCheckBox.isSelected()) {
            inputtedData.setVersionIndex(geoProjectorVersions.getSelectionModel().getSelectedIndex());
            inputtedData.setGeoVersion(projectorVersionDefinition("geo"));
            if (versesCount == 1) {
                linkData.setLinkInfo("geo", inputtedData.getGeoVersion(), books.getItems().indexOf(inputtedData.getBook()) + 1, inputtedData.getChapter(), inputtedData.getVerse(), inputtedData.getVerse());
            } else if (versesCount > 1) {
                linkData.setLinkInfo("geo", inputtedData.getGeoVersion(), books.getItems().indexOf(inputtedData.getBook()) + 1, inputtedData.getChapter(), inputtedData.getVerse(), inputtedData.getTill());
            }
            StringBuilder allVersesInOneBuilder = new StringBuilder(allVersesInOne);
            for (int i = 0; i < linkData.verses.size(); i++) {
                allVersesInOneBuilder.append(linkData.verses.get(i)).append(" ");
            }
            allVersesInOne = allVersesInOneBuilder.toString();
            allVersesInOne += "\n";
            linkData.verses.clear();
        }


        if (engCheckBox.isSelected()) {
            inputtedData.setVersionIndex(engProjectorVersions.getSelectionModel().getSelectedIndex());
            inputtedData.setEngVersion(projectorVersionDefinition("eng"));
            if (versesCount == 1) {
                linkData.setLinkInfo("eng", inputtedData.getEngVersion(), books.getItems().indexOf(inputtedData.getBook()) + 1, inputtedData.getChapter(), inputtedData.getVerse(), inputtedData.getVerse());
            } else if (versesCount > 1) {
                linkData.setLinkInfo("eng", inputtedData.getEngVersion(), books.getItems().indexOf(inputtedData.getBook()) + 1, inputtedData.getChapter(), inputtedData.getVerse(), inputtedData.getTill());
            }
            StringBuilder allVersesInOneBuilder = new StringBuilder(allVersesInOne);
            for (int i = 0; i < linkData.verses.size(); i++) {
                allVersesInOneBuilder.append(linkData.verses.get(i)).append(" ");
            }
            allVersesInOne = allVersesInOneBuilder.toString();
            allVersesInOne += "\n";
            linkData.verses.clear();
        }


        if (rusCheckBox.isSelected()) {
            inputtedData.setVersionIndex(rusProjectorVersions.getSelectionModel().getSelectedIndex());
            inputtedData.setRusVersion(projectorVersionDefinition("rus"));
            if (versesCount == 1) {
                linkData.setLinkInfo("rus", inputtedData.getRusVersion(), books.getItems().indexOf(inputtedData.getBook()) + 1, inputtedData.getChapter(), inputtedData.getVerse(), inputtedData.getVerse());
            } else if (versesCount > 1) {
                linkData.setLinkInfo("rus", inputtedData.getRusVersion(), books.getItems().indexOf(inputtedData.getBook()) + 1, inputtedData.getChapter(), inputtedData.getVerse(), inputtedData.getTill());
            }
            StringBuilder allVersesInOneBuilder = new StringBuilder(allVersesInOne);
            for (int i = 0; i < linkData.verses.size(); i++) {
                allVersesInOneBuilder.append(linkData.verses.get(i)).append(" ");
            }
            allVersesInOne = allVersesInOneBuilder.toString();
            allVersesInOne += "\n";
            linkData.verses.clear();
        }

        linkData.verses.addAll(versesData);

        return allVersesInOne;
    }
}