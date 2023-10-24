package com.example.bible.controllers;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ProjectorController {

    @FXML
    protected AnchorPane projectorAnchorPane;

    @FXML
    protected Text projectorTextBox;

    @FXML
    private void onFullScreenMouseClicked() {
        projectorAnchorPane.getChildren().removeIf(node -> node instanceof ImageView);
        Stage stage = (Stage) projectorAnchorPane.getScene().getWindow();
        stage.setFullScreen(true);
    }
}
