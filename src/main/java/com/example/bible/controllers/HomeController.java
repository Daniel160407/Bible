package com.example.bible.controllers;

import com.example.bible.Bible;
import com.example.bible.requests.LinkData;
import com.example.bible.runtimeData.BibleVersions;
import com.example.bible.runtimeData.InputtedData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.FileChooser;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class HomeController extends ProjectorController {
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
    private ComboBox<String> textColor;
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
    private RadioButton myImg;
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
    @FXML
    private ImageView selectedImage;
    @FXML
    private TextArea schedule;
    @FXML
    private Pane schedulePane;

    private final List<String> versionsList = new ArrayList<>();
    private final List<String> booksList = new ArrayList<>();
    private final InputtedData inputtedData = new InputtedData();
    private static final BibleVersions bibleVersions = new BibleVersions();
    private final LinkData linkData = new LinkData();
    private int previousLayoutYPath = -27;
    private ProjectorController projectorController;
    private String backgroundImage;
    private Image selectedBackgroundImage;
    private int scheduledVerse = -1;
    private List<String> versePath = new ArrayList<>();

    @FXML
    private void onDarkModeAction() {
        darkModeColorsChanger(anchorPane.getBackground().getFills().get(0).getFill().toString());
    }


    @FXML
    private void onLanguageAction() {
        inputtedData.setLanguage(language.getValue().toLowerCase());
    }

    @FXML
    private void onVersionsMouseClicked() {
        getVersionsAndBooksInfo();
    }

    @FXML
    private void onVersionsAction() {
        inputtedData.setVersionIndex(versions.getSelectionModel().getSelectedIndex());
    }

    @FXML
    private void onBooksAction() {
        inputtedData.setBook(books.getValue());

        linkData.setLinkInfo(inputtedData.getLanguage(), inputtedData.getVersion(), books.getItems().indexOf(inputtedData.getBook()) + 1, 1, 1, 1, false);
        chapter.getItems().clear();
        for (int i = 0; i < linkData.chapterCount; i++) {
            chapter.getItems().add(i + 1);
        }
        linkData.versePath.clear();
    }

    @FXML
    private void onChapterAction() {
        if (chapter.getEditor().getText() != null && !chapter.getEditor().getText().isEmpty()) {
            inputtedData.setChapter(Integer.parseInt(chapter.getEditor().getText()));
            inputtedData.setVersion(versionDefinition());
        }
        linkData.setLinkInfo(inputtedData.getLanguage(), inputtedData.getVersion(), books.getItems().indexOf(inputtedData.getBook()) + 1, inputtedData.getChapter(), 1, 1, false);
        verse.getItems().clear();
        till.getItems().clear();
        for (int i = 0; i < linkData.verseCount; i++) {
            verse.getItems().add(i + 1);
            till.getItems().add(i + 1);
        }
        verse.getEditor().clear();
        till.getEditor().clear();
    }

    @FXML
    private void onVerseAction() {
        if (verse.getEditor().getText() != null && !verse.getEditor().getText().isEmpty()) {
            mainAnchorPane.getChildren().removeIf(node -> node instanceof StackPane);
            scheduledVerse = 0;
            linkData.verses.clear();
            mainAnchorPane.setPrefHeight(scrollPane.getHeight());
            inputtedData.setVerse(Integer.parseInt(verse.getEditor().getText()));
            inputtedData.setVersion(versionDefinition());
            inputtedData.setTill(0);
            linkData.setLinkInfo(inputtedData.getLanguage(), inputtedData.getVersion(), books.getItems().indexOf(inputtedData.getBook()) + 1, inputtedData.getChapter(), inputtedData.getVerse(), inputtedData.getVerse(), false);
            StringBuilder str = new StringBuilder();
            for (int i = 0; i < linkData.verses.size(); i++) {
                str.append(linkData.verses.get(i)).append(" ");
            }

            Text newVerseBox = new Text();
            newVerseBox.prefHeight(70);
            newVerseBox.getStyleClass().add("newVerseBox");
            StackPane stackPane = new StackPane();
            Rectangle rec = new Rectangle(1067, 83, Color.web("#374151"));
            rec.setArcWidth(20);
            rec.setArcHeight(20);
            stackPane.getChildren().add(rec);
            stackPane.getChildren().add(newVerseBox);
            stackPane.setLayoutX(400);
            stackPane.setLayoutY(73);
            mainAnchorPane.getChildren().add(stackPane);
            newVerseBox.setText(str + "\n" + inputtedData.getBook() + " " + linkData.versePath.get(0).get(0) + ":" + linkData.versePath.get(0).get(1));
            linkData.versePath.clear();
            newVerseBox.setTextAlignment(TextAlignment.CENTER);
            newVerseBox.setWrappingWidth(rec.getWidth());
            till.getEditor().clear();
        }

    }

    @FXML
    private void onTillAction() {
        if (till.getEditor().getText() != null && !till.getEditor().getText().isEmpty()) {
            mainAnchorPane.getChildren().removeIf(node -> node instanceof StackPane);
            linkData.verses.clear();
            mainAnchorPane.setPrefHeight(scrollPane.getHeight());
            previousLayoutYPath = -27;
            inputtedData.setTill(Integer.parseInt(till.getEditor().getText()));
            linkData.setLinkInfo(inputtedData.getLanguage(), inputtedData.getVersion(), books.getItems().indexOf(inputtedData.getBook()) + 1, inputtedData.getChapter(), inputtedData.getVerse(), inputtedData.getTill(), false);

            for (int i = 0; i < linkData.verses.size(); i++) {
                Text newVerseBox = new Text();
                newVerseBox.getStyleClass().add("newVerseBox");
                StackPane stackPane = new StackPane();
                Rectangle rec = new Rectangle(1100, 83, Color.web("#374151"));
                rec.setArcWidth(20);
                rec.setArcHeight(20);
                stackPane.getChildren().add(rec);
                stackPane.getChildren().add(newVerseBox);
                stackPane.setLayoutX(400);
                stackPane.setLayoutY(previousLayoutYPath + 100);
                mainAnchorPane.getChildren().add(stackPane);
                previousLayoutYPath = (int) stackPane.getLayoutY();
                newVerseBox.setText(linkData.verses.get(i) + "\n" + inputtedData.getBook() + " " + linkData.versePath.get(i).get(0) + ":" + linkData.versePath.get(i).get(1));

                newVerseBox.setTextAlignment(TextAlignment.CENTER);
                newVerseBox.setWrappingWidth(rec.getWidth());

                if (linkData.verses.size() > 4) {
                    int prefHeight = (int) mainAnchorPane.getPrefHeight();
                    mainAnchorPane.setPrefHeight(prefHeight + 87);
                }
            }
            linkData.versePath.clear();
            previousLayoutYPath = -73;

        }

    }

    @FXML
    private void onVerseMouseClicked() {
        till.getEditor().clear();
        inputtedData.setTill(0);
    }

    @FXML
    private void onChapterXAction() {
        chapter.getEditor().clear();
        verse.getEditor().clear();
        till.getEditor().clear();
        inputtedData.setChapter(0);
        inputtedData.setVerse(0);
        inputtedData.setTill(0);
    }

    @FXML
    private void onVerseXAction() {
        verse.getEditor().clear();
        till.getEditor().clear();
        inputtedData.setVerse(0);
        inputtedData.setTill(0);
    }

    @FXML
    private void onTillXAction() {
        till.getEditor().clear();
        inputtedData.setTill(0);
    }

    @FXML
    private void onClearVersesAction() {
        mainAnchorPane.getChildren().removeIf(node -> node instanceof StackPane);
        mainAnchorPane.setPrefHeight(592);
        linkData.verses.clear();
        search.clear();
        chapter.getEditor().clear();
        verse.getEditor().clear();
        till.getEditor().clear();
        inputtedData.setChapter(0);
        inputtedData.setVerse(0);
        inputtedData.setTill(0);
    }

    @FXML
    private void onClearButtonAction() {
        projectorController.projectorAnchorPane.getChildren().clear();
    }

    @FXML
    private void onRightVerseArrowMouseClicked() {
        if (!verse.getEditor().getText().isEmpty() && Integer.parseInt(verse.getEditor().getText()) + 1 < verse.getItems().get(verse.getItems().size() - 1)) {
            mainAnchorPane.getChildren().removeIf(node -> node instanceof StackPane);
            scheduledVerse = 0;
            linkData.verses.clear();
            mainAnchorPane.setPrefHeight(scrollPane.getHeight());
            verse.getEditor().setText(String.valueOf(Integer.parseInt(verse.getEditor().getText()) + 1));
            inputtedData.setVerse(Integer.parseInt(verse.getEditor().getText()));
            inputtedData.setVersion(versionDefinition());
            inputtedData.setTill(0);
            linkData.setLinkInfo(inputtedData.getLanguage(), inputtedData.getVersion(), books.getItems().indexOf(inputtedData.getBook()) + 1, inputtedData.getChapter(), inputtedData.getVerse(), inputtedData.getVerse(), false);
            StringBuilder str = new StringBuilder();
            for (int i = 0; i < linkData.verses.size(); i++) {
                str.append(linkData.verses.get(i)).append(" ");
            }

            Text newVerseBox = new Text();
            newVerseBox.prefHeight(70);
            newVerseBox.getStyleClass().add("newVerseBox");
            StackPane stackPane = new StackPane();
            Rectangle rec = new Rectangle(1067, 83, Color.web("#374151"));
            rec.setArcWidth(20);
            rec.setArcHeight(20);
            stackPane.getChildren().add(rec);
            stackPane.getChildren().add(newVerseBox);
            stackPane.setLayoutX(400);
            stackPane.setLayoutY(73);
            mainAnchorPane.getChildren().add(stackPane);
            newVerseBox.setText(str + "\n" + inputtedData.getBook() + " " + linkData.versePath.get(0).get(0) + ":" + linkData.versePath.get(0).get(1));
            linkData.versePath.clear();
            newVerseBox.setTextAlignment(TextAlignment.CENTER);
            newVerseBox.setWrappingWidth(rec.getWidth());
            till.getEditor().clear();
        }
    }

    @FXML
    private void onLeftVerseArrowMouseClicked() {
        if (!verse.getEditor().getText().isEmpty() && Integer.parseInt(verse.getEditor().getText()) - 1 > 0) {
            mainAnchorPane.getChildren().removeIf(node -> node instanceof StackPane);
            scheduledVerse = 0;
            linkData.verses.clear();
            mainAnchorPane.setPrefHeight(scrollPane.getHeight());
            verse.getEditor().setText(String.valueOf(Integer.parseInt(verse.getEditor().getText()) - 1));
            inputtedData.setVerse(Integer.parseInt(verse.getEditor().getText()));
            inputtedData.setVersion(versionDefinition());
            inputtedData.setTill(0);
            linkData.setLinkInfo(inputtedData.getLanguage(), inputtedData.getVersion(), books.getItems().indexOf(inputtedData.getBook()) + 1, inputtedData.getChapter(), inputtedData.getVerse(), inputtedData.getVerse(), false);
            StringBuilder str = new StringBuilder();
            for (int i = 0; i < linkData.verses.size(); i++) {
                str.append(linkData.verses.get(i)).append(" ");
            }

            Text newVerseBox = new Text();
            newVerseBox.prefHeight(70);
            newVerseBox.getStyleClass().add("newVerseBox");
            StackPane stackPane = new StackPane();
            Rectangle rec = new Rectangle(1067, 83, Color.web("#374151"));
            rec.setArcWidth(20);
            rec.setArcHeight(20);
            stackPane.getChildren().add(rec);
            stackPane.getChildren().add(newVerseBox);
            stackPane.setLayoutX(400);
            stackPane.setLayoutY(73);
            mainAnchorPane.getChildren().add(stackPane);
            newVerseBox.setText(str + "\n" + inputtedData.getBook() + " " + linkData.versePath.get(0).get(0) + ":" + linkData.versePath.get(0).get(1));
            linkData.versePath.clear();
            newVerseBox.setTextAlignment(TextAlignment.CENTER);
            newVerseBox.setWrappingWidth(rec.getWidth());
            till.getEditor().clear();
        }
    }

    @FXML
    private void onOpenPresentViewAction() throws IOException {
        Screen secondScreen = null;
        for (Screen screen : Screen.getScreens()) {
            if (!screen.equals(Screen.getPrimary())) {
                secondScreen = screen;
                break;
            }
        }
        if (secondScreen != null) {
            Rectangle2D visualBounds = secondScreen.getVisualBounds();
            FXMLLoader loader = new FXMLLoader(Bible.class.getResource("fxml files/projector.fxml"));
            Parent root = loader.load();
            projectorController = loader.getController();
            Stage newStage = new Stage();
            newStage.setX(visualBounds.getMinX());
            newStage.setY(visualBounds.getMinY());
            newStage.setWidth(visualBounds.getWidth());
            newStage.setHeight(visualBounds.getHeight());
            newStage.getIcons().add(new Image("https://cdn-icons-png.flaticon.com/512/3004/3004416.png"));
            newStage.setTitle("Present View");
            Scene scene = new Scene(root, 800, 600);
            scene.getStylesheets().add(Objects.requireNonNull(Bible.class.getResource("styles/style.css")).toExternalForm());
            newStage.setScene(scene);
            newStage.setFullScreenExitHint("");
            newStage.setFullScreen(true);
            newStage.show();
            projectorController.projectorAnchorPane.setStyle("-fx-background-image: url('" + backgroundImage + "');");
        }
    }

    @FXML
    private void onShowButtonAction() {
        projectorController.projectorAnchorPane.getChildren().removeIf(node -> node instanceof Text);
        String allVersesInOne = requestManager();

        projectorController.projectorTextBox.getStyleClass().add("projectorTextBox");
        projectorController.projectorAnchorPane.getChildren().add(projectorController.projectorTextBox);

        List<String> geoBooks = Arrays.stream(bibleVersions.georgianBooks.split(",")).collect(Collectors.toList());
        List<String> engBooks = Arrays.stream(bibleVersions.englishBooks.split(",")).collect(Collectors.toList());
        List<String> rusBooks = Arrays.stream(bibleVersions.russianBooks.split(",")).collect(Collectors.toList());

        inputtedData.setBookIndex(books.getItems().indexOf(inputtedData.getBook()));

        StringBuilder bookInSelectedLanguages = new StringBuilder();
        if (geoCheckBox.isSelected()) {
            bookInSelectedLanguages.append(geoBooks.get(inputtedData.getBookIndex())).append(" ");
        }
        if (engCheckBox.isSelected()) {
            bookInSelectedLanguages.append(engBooks.get(inputtedData.getBookIndex())).append(" ");
        }
        if (rusCheckBox.isSelected()) {
            bookInSelectedLanguages.append(rusBooks.get(inputtedData.getBookIndex())).append(" ");
        }

        projectorController.projectorTextBox.setText(allVersesInOne + "\n" + bookInSelectedLanguages + " "
                + linkData.versePath.get(scheduledVerse).get(0) + ":"
                + linkData.versePath.get(scheduledVerse).get(1) + (inputtedData.getTill() != 0 ? "-"
                + linkData.versePath.get(linkData.versePath.size() - 1).get(1) : ""));
        linkData.versePath.clear();
        projectorController.projectorTextBox.setTextAlignment(TextAlignment.CENTER);
    }

    @FXML
    private void onFontSizeAction() {
        projectorController.projectorTextBox.setStyle("-fx-font-size: " + fontSize.getValue() + 2 + "px; " + (textColor.getValue() != null ? "-fx-fill: " + textColor.getValue() : "-fx-fill: white"));
    }

    @FXML
    private void onSearchMouseClicked() {
        mainAnchorPane.getChildren().removeIf(node -> node instanceof StackPane);
        linkData.verses.clear();
        linkData.versePath.clear();
        chapter.getEditor().clear();
        verse.getEditor().clear();
        till.getEditor().clear();
        if (till.getValue() != null) {
            till.setValue(null);
            inputtedData.setTill(0);
        }
    }

    @FXML
    private void onSearchAction() {
        mainAnchorPane.getChildren().removeIf(node -> node instanceof StackPane);
        linkData.verses.clear();
        linkData.versePath.clear();
        scheduledVerse = 0;
        mainAnchorPane.setPrefHeight(592);
        inputtedData.setVersion(versionDefinition());
        String searchText = search.getText();
        if (searchText.matches(".*\\d.*")) {
            String patternString = "(\\d?\\D+?) (\\d+):(\\d+)(?:-(\\d+))?";

            Pattern pattern = Pattern.compile(patternString);
            Matcher matcher = pattern.matcher(searchText);

            if (matcher.find()) {
                String searchedBook = matcher.group(1);
                for (String book : booksList) {
                    if (book.toLowerCase().startsWith(searchedBook)) {
                        inputtedData.setBook(book);
                        break;
                    }
                }

                int searchedChapter = Integer.parseInt(matcher.group(2));
                inputtedData.setChapter(searchedChapter);

                int searchedVerse = Integer.parseInt(matcher.group(3));
                inputtedData.setVerse(searchedVerse);

                int searchedTill = Integer.parseInt(matcher.group(4) == null ? "0" : matcher.group(4));
                inputtedData.setTill(searchedTill);
            }

            linkData.setLinkInfo(inputtedData.getLanguage(), inputtedData.getVersion(), books.getItems().indexOf(inputtedData.getBook()) + 1, inputtedData.getChapter(), inputtedData.getVerse(), inputtedData.getTill(), false);

            for (int i = 0; i < linkData.verses.size(); i++) {
                Text newVerseBox = new Text();
                newVerseBox.getStyleClass().add("newVerseBox");
                StackPane stackPane = new StackPane();
                Rectangle rec = new Rectangle(1100, 100, Color.web("#374151"));
                rec.setArcWidth(20);
                rec.setArcHeight(20);
                stackPane.getChildren().addAll(rec, newVerseBox);
                stackPane.setLayoutX(400);
                stackPane.setLayoutY(previousLayoutYPath + 117);
                mainAnchorPane.getChildren().add(stackPane);
                previousLayoutYPath = (int) stackPane.getLayoutY();
                newVerseBox.setText(linkData.verses.get(i) + "\n" + inputtedData.getBook() + " " + linkData.versePath.get(i).get(0) + ":" + linkData.versePath.get(i).get(1));

                newVerseBox.setTextAlignment(TextAlignment.CENTER);
                newVerseBox.setWrappingWidth(rec.getWidth());
                if (linkData.verses.size() > 4) {
                    int prefHeight = (int) mainAnchorPane.getPrefHeight();
                    mainAnchorPane.setPrefHeight(prefHeight + rec.getHeight() + 9);
                }

            }

            previousLayoutYPath = -73;
            linkData.search = null;
        } else {
            linkData.search = searchText;
            linkData.setLinkInfo(inputtedData.getLanguage(), inputtedData.getVersion(), books.getItems().indexOf(inputtedData.getBook()) + 1, inputtedData.getChapter(), 1, 1, false);

            for (int i = 0; i < linkData.verses.size(); i++) {
                Text newVerseBox = new Text();
                newVerseBox.getStyleClass().add("newVerseBox");
                StackPane stackPane = new StackPane();
                Rectangle rec = new Rectangle(1100, 100, Color.web("#374151"));
                rec.setArcWidth(20);
                rec.setArcHeight(20);
                Button separateButton = new Button();
                separateButton.setId(Integer.toString(i));
                VBox vBox = new VBox();
                vBox.setAlignment(Pos.CENTER);
                vBox.setSpacing(10);
                vBox.getChildren().addAll(newVerseBox, separateButton);
                stackPane.getChildren().addAll(rec, vBox);
                stackPane.setLayoutX(400);
                stackPane.setLayoutY(previousLayoutYPath + 117);
                mainAnchorPane.getChildren().add(stackPane);
                previousLayoutYPath = (int) stackPane.getLayoutY();
                newVerseBox.setText(linkData.verses.get(i) + "\n" + inputtedData.getBook() + " " + linkData.versePath.get(i).get(0) + ":" + linkData.versePath.get(i).get(1));
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
                    mainAnchorPane.setPrefHeight(592);
                    String chosenVerse = linkData.verses.get(Integer.parseInt(separateButton.getId()));
                    linkData.verses.clear();
                    inputtedData.setChapter(Integer.parseInt(linkData.versePath.get(Integer.parseInt(separateButton.getId())).get(0)));
                    inputtedData.setVerse(Integer.parseInt(linkData.versePath.get(Integer.parseInt(separateButton.getId())).get(1)));
                    linkData.verses.add(chosenVerse + "\n" + inputtedData.getBook() + " " + linkData.versePath.get(Integer.parseInt(separateButton.getId())).get(0) +
                            ":" + linkData.versePath.get(Integer.parseInt(separateButton.getId())).get(1));
                    List<String> list = new ArrayList<>();
                    list.add(linkData.versePath.get(Integer.parseInt(separateButton.getId())).get(0));
                    list.add(linkData.versePath.get(Integer.parseInt(separateButton.getId())).get(1));
                    linkData.versePath.add(list);
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
    private void onAddYourAction(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open File");
        File initialDirectory = new File(System.getProperty("user.home"));
        fileChooser.setInitialDirectory(initialDirectory);
        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png", "*.gif", "*.jpeg");
        fileChooser.getExtensionFilters().add(filter);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        File selectedFile = fileChooser.showOpenDialog(stage);
        if (selectedFile != null) {
            selectedBackgroundImage = new Image(selectedFile.toURI().toString());
            selectedImage.setImage(selectedBackgroundImage);
            if (myImg.isSelected()) {
                projectorController.projectorAnchorPane.setStyle("-fx-background-image: url('" + selectedBackgroundImage.getUrl() + "');");
            }
        }
    }

    @FXML
    private void onTextColorAction() {
        switch (textColor.getValue()) {
            case "White":
                projectorController.projectorTextBox.setStyle("-fx-fill: white;" + "-fx-font-size: " + fontSize.getValue() + 2 + "px; ");
                break;
            case "Black":
                projectorController.projectorTextBox.setStyle("-fx-fill: black;" + "-fx-font-size: " + fontSize.getValue() + 2 + "px; ");
                break;
            case "Gray":
                projectorController.projectorTextBox.setStyle("-fx-fill: gray;" + "-fx-font-size: " + fontSize.getValue() + 2 + "px; ");
                break;
            case "Blue":
                projectorController.projectorTextBox.setStyle("-fx-fill: Blue;" + "-fx-font-size: " + fontSize.getValue() + 2 + "px; ");
                break;
            case "Red":
                projectorController.projectorTextBox.setStyle("-fx-fill: Red;" + "-fx-font-size: " + fontSize.getValue() + 2 + "px; ");
                break;
            case "Yellow":
                projectorController.projectorTextBox.setStyle("-fx-fill: yellow;" + "-fx-font-size: " + fontSize.getValue() + 2 + "px; ");
                break;
            case "Green":
                projectorController.projectorTextBox.setStyle("-fx-fill: green;" + "-fx-font-size: " + fontSize.getValue() + 2 + "px; ");
                break;
        }
    }

    @FXML
    private void onBibleButtonAction() throws IOException {
        FXMLLoader loader = new FXMLLoader(Bible.class.getResource("fxml files/bible.fxml"));
        Parent root = loader.load();
        BibleController bibleController = loader.getController();
        if (darkMode.isSelected()) {
            bibleController.getMainAnchorPane().setStyle("-fx-background-color:  #101828");
            bibleController.getOuterAnchorPane().setStyle("-fx-background-color:  #101828");
            bibleController.setBackgroundColor("#101828");
        } else {
            bibleController.getMainAnchorPane().setStyle("-fx-background-color:  white");
            bibleController.getOuterAnchorPane().setStyle("-fx-background-color:  white");
            bibleController.setBackgroundColor("white");
        }
        Stage newStage = new Stage();
        newStage.getIcons().add(new Image("https://cdn-icons-png.flaticon.com/512/3004/3004416.png"));
        newStage.setTitle("Bible");
        Scene scene = new Scene(root, 800, 600);
        newStage.setScene(scene);
        newStage.show();

    }

    @FXML
    private void onAddScheduleButtonAction() {
        schedulePane.setVisible(true);
    }

    @FXML
    private void onSaveButtonAction() {
        schedulePane.setVisible(false);
        inputtedData.setSchedule(schedule.getText());
        String geoPatternString = "(?:\\\\d*\\\\s*)?([\\\\p{L}ა-ჰ]+)";
        String engPatternString = "(?:\\\\d*\\\\s*)?([\\\\p{L}a-z]+)";
        String rusPatternString = "(?:\\\\d*\\\\s*)?([\\\\p{L}а-я]+)";
        Pattern pattern = Pattern.compile(inputtedData.getLanguage().equals("geo") ? geoPatternString : inputtedData.getLanguage().equals("eng") ? engPatternString : rusPatternString);
        Matcher matcher = pattern.matcher(inputtedData.getSchedule());
        inputtedData.getScheduleBooks().clear();
        getVersionsAndBooksInfo();
        while (matcher.find()) {
            String scheduleBook = matcher.group();
            for (String book : booksList) {
                if (book.toLowerCase().startsWith(scheduleBook.toLowerCase())) {
                    inputtedData.addScheduleBook(book);
                    break;
                }
            }
            System.out.println();
        }
        String patternString = "[ |:]([0-9]+)";
        pattern = Pattern.compile(patternString);
        matcher = pattern.matcher(inputtedData.getSchedule());
        int i = 0;
        versePath.clear();
        while (matcher.find()) {
            if (i == 0) {
                versePath.add(matcher.group(1));
            } else {
                versePath.add(matcher.group(1));
            }
            i++;
        }
    }

    @FXML
    private void onLeftArrowMouseClicked() {
        mainAnchorPane.getChildren().removeIf(node -> node instanceof StackPane);
        linkData.versePath.clear();
        for (int i = 1; i < versePath.size(); i++) {
            if (i % 2 != 0) {
                List<String> list = new ArrayList<>();
                list.add(versePath.get(i - 1));
                list.add(versePath.get(i));
                linkData.versePath.add(list);
            }
        }
        if (scheduledVerse > 0) {
            scheduledVerse--;
        }
        mainActionOnArrowsMouseClicked();
    }

    @FXML
    private void onRightArrowMouseClicked() {
        mainAnchorPane.getChildren().removeIf(node -> node instanceof StackPane);
        linkData.versePath.clear();
        for (int i = 1; i < versePath.size(); i++) {
            if (i % 2 != 0) {
                List<String> list = new ArrayList<>();
                list.add(versePath.get(i - 1));
                list.add(versePath.get(i));
                linkData.versePath.add(list);
            }
        }
        if (linkData.versePath.size() - 1 > scheduledVerse) {
            scheduledVerse++;
        }
        mainActionOnArrowsMouseClicked();
    }

    private void mainActionOnArrowsMouseClicked() {
        inputtedData.setChapter(Integer.parseInt(linkData.versePath.get(scheduledVerse).get(0)));
        inputtedData.setVerse(Integer.parseInt(linkData.versePath.get(scheduledVerse).get(1)));
        linkData.verses.clear();
        mainAnchorPane.setPrefHeight(scrollPane.getHeight());
        inputtedData.setBookIndex(books.getItems().indexOf(inputtedData.getScheduleBooks().get(scheduledVerse)) + 1);
        inputtedData.setBook(inputtedData.getScheduleBooks().get(scheduledVerse));
        inputtedData.setVersionIndex(0);
        linkData.setLinkInfo(inputtedData.getLanguage(), inputtedData.getVersion() != null ? inputtedData.getVersion() : versionDefinition(), inputtedData.getBookIndex(), inputtedData.getChapter(), inputtedData.getVerse(), inputtedData.getTill(), false);
        linkData.versePath.remove(linkData.versePath.size() - 1);
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < linkData.verses.size(); i++) {
            str.append(linkData.verses.get(i)).append(" ");
        }
        Text newVerseBox = new Text();
        newVerseBox.prefHeight(70);
        newVerseBox.getStyleClass().add("newVerseBox");
        StackPane stackPane = new StackPane();
        Rectangle rec = new Rectangle(1067, 83, Color.web("#374151"));
        rec.setArcWidth(20);
        rec.setArcHeight(20);
        stackPane.getChildren().add(rec);
        stackPane.getChildren().add(newVerseBox);
        stackPane.setLayoutX(400);
        stackPane.setLayoutY(73);
        mainAnchorPane.getChildren().add(stackPane);
        newVerseBox.setText(str + "\n" + inputtedData.getScheduleBooks().get(scheduledVerse) + " " + linkData.versePath.get(scheduledVerse).get(0) + ":" + linkData.versePath.get(scheduledVerse).get(1));
        newVerseBox.setTextAlignment(TextAlignment.CENTER);
        newVerseBox.setWrappingWidth(rec.getWidth());
        books.getEditor().clear();
        chapter.getEditor().clear();
        verse.getEditor().clear();
        till.getEditor().clear();
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

    @FXML
    private void onMyImgAction() {
        backgroundImage = selectedBackgroundImage.getUrl();
        radioButtonSwitch();
        myImg.setSelected(true);
    }


    private void darkModeColorsChanger(String color) {
        String whiteColor = "0xf4f4f4ff";
        String darkColor = "#101828";
        if (color.equals(whiteColor)) {
            anchorPane.setStyle("-fx-background-color: " + darkColor);
            mainAnchorPane.setStyle("-fx-background-color:" + darkColor);
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

    protected String versionDefinition() {
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
        myImg.setSelected(false);
        projectorController.projectorAnchorPane.setStyle("-fx-background-image: url('" + backgroundImage + "');");
    }

    private String requestManager() {

        int versesCount = linkData.verses.size();
        List<String> versesData = new ArrayList<>(linkData.verses);
        linkData.verses.clear();
        StringBuilder allLangVersesInOne = new StringBuilder();
        List<Callable<String>> tasks = new ArrayList<>();
        ExecutorService executor = Executors.newFixedThreadPool(3);
        tasks.add(() -> {
            String allVersesInOne = "";
            if (geoCheckBox.isSelected()) {
                inputtedData.setVersionIndex(geoProjectorVersions.getSelectionModel().getSelectedIndex());
                inputtedData.setGeoVersion(projectorVersionDefinition("geo"));
                if (versesCount == 1) {
                    linkData.setLinkInfo("geo", inputtedData.getGeoVersion(), books.getItems().indexOf(inputtedData.getBook()) + 1, inputtedData.getChapter(), inputtedData.getVerse(), inputtedData.getVerse(), false);
                } else if (versesCount > 1) {
                    linkData.setLinkInfo("geo", inputtedData.getGeoVersion(), books.getItems().indexOf(inputtedData.getBook()) + 1, inputtedData.getChapter(), inputtedData.getVerse(), inputtedData.getTill(), false);
                }
                StringBuilder allVersesInOneBuilder = new StringBuilder(allVersesInOne);
                for (int i = 0; i < linkData.verses.size(); i++) {
                    allVersesInOneBuilder.append(linkData.verses.get(i)).append(" ");
                }
                allVersesInOne = allVersesInOneBuilder.toString();
                allVersesInOne += "\n";
                linkData.verses.clear();
                return allVersesInOne;
            }
            return "";
        });

        tasks.add(() -> {
            String allVersesInOne = "";
            if (engCheckBox.isSelected()) {
                inputtedData.setVersionIndex(engProjectorVersions.getSelectionModel().getSelectedIndex());
                inputtedData.setEngVersion(projectorVersionDefinition("eng"));
                Map<Integer, Integer> englishBooksIndexes = new HashMap<>() {{
                    put(48, 62);
                    put(49, 63);
                    put(50, 64);
                    put(51, 65);
                    put(52, 66);
                    put(53, 67);
                    put(54, 68);
                    put(55, 48);
                    put(56, 49);
                    put(57, 50);
                    put(58, 51);
                    put(59, 52);
                    put(60, 53);
                    put(61, 54);
                    put(62, 55);
                    put(63, 56);
                    put(64, 57);
                    put(65, 58);
                    put(66, 59);
                    put(67, 60);
                    put(68, 61);
                }};
                if (versesCount == 1 && !englishBooksIndexes.containsKey(books.getItems().indexOf(inputtedData.getBook()) + 1)) {
                    linkData.setLinkInfo("eng", inputtedData.getEngVersion(), books.getItems().indexOf(inputtedData.getBook()) + 1, inputtedData.getChapter(), inputtedData.getVerse(), inputtedData.getVerse(), false);
                } else if (versesCount > 1 && !englishBooksIndexes.containsKey(books.getItems().indexOf(inputtedData.getBook()) + 1)) {
                    linkData.setLinkInfo("eng", inputtedData.getEngVersion(), books.getItems().indexOf(inputtedData.getBook()) + 1, inputtedData.getChapter(), inputtedData.getVerse(), inputtedData.getTill(), false);
                } else if (versesCount == 1 && englishBooksIndexes.containsKey(books.getItems().indexOf(inputtedData.getBook()) + 1)) {
                    linkData.setLinkInfo("eng", inputtedData.getEngVersion(), englishBooksIndexes.get(books.getItems().indexOf(inputtedData.getBook()) + 1), inputtedData.getChapter(), inputtedData.getVerse(), inputtedData.getVerse(), false);
                } else if (versesCount > 1 && englishBooksIndexes.containsKey(books.getItems().indexOf(inputtedData.getBook()) + 1)) {
                    linkData.setLinkInfo("eng", inputtedData.getEngVersion(), englishBooksIndexes.get(books.getItems().indexOf(inputtedData.getBook()) + 1), inputtedData.getChapter(), inputtedData.getVerse(), inputtedData.getTill(), false);
                }
                StringBuilder allVersesInOneBuilder = new StringBuilder(allVersesInOne);
                for (int i = 0; i < linkData.verses.size(); i++) {
                    allVersesInOneBuilder.append(linkData.verses.get(i)).append(" ");
                }
                allVersesInOne = allVersesInOneBuilder.toString();
                allVersesInOne += "\n";
                linkData.verses.clear();
                return allVersesInOne;
            }
            return "";
        });

        tasks.add(() -> {
            String allVersesInOne = "";
            if (rusCheckBox.isSelected()) {
                inputtedData.setVersionIndex(rusProjectorVersions.getSelectionModel().getSelectedIndex());
                inputtedData.setRusVersion(projectorVersionDefinition("rus"));
                if (versesCount == 1) {
                    linkData.setLinkInfo("rus", inputtedData.getRusVersion(), books.getItems().indexOf(inputtedData.getBook()) + 1, inputtedData.getChapter(), inputtedData.getVerse(), inputtedData.getVerse(), false);
                } else if (versesCount > 1) {
                    linkData.setLinkInfo("rus", inputtedData.getRusVersion(), books.getItems().indexOf(inputtedData.getBook()) + 1, inputtedData.getChapter(), inputtedData.getVerse(), inputtedData.getTill(), false);
                }
                StringBuilder allVersesInOneBuilder = new StringBuilder();

                for (int i = 0; i < linkData.verses.size(); i++) {
                    allVersesInOneBuilder.append(linkData.verses.get(i)).append(" ");
                }
                allVersesInOne = allVersesInOneBuilder.toString();
                allVersesInOne += "\n";
                linkData.verses.clear();
            }
            return allVersesInOne;
        });
        try {
            List<Future<String>> results = executor.invokeAll(tasks);
            for (Future<String> result : results) {
                try {
                    allLangVersesInOne.append(result.get());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            executor.shutdown();
            try {
                executor.awaitTermination(1, TimeUnit.MINUTES);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


        linkData.verses.addAll(versesData);

        return allLangVersesInOne.toString();
    }
}