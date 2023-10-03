package com.example.bible.controllers;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Observable;
import java.util.Observer;

public class Ff {
    @FXML
    private AnchorPane projectorAnchorPane;

    private int myVariable;
    private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    public int getMyVariable() {
        return myVariable;
    }

    public void setMyVariable(int newValue) {
        int oldValue = myVariable;
        myVariable = newValue;
        propertyChangeSupport.firePropertyChange("myVariable", oldValue, newValue);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(listener);
    }


    public void on(StackPane stackPane) {
        projectorAnchorPane.getChildren().add(stackPane);
        System.out.println("Success!");
    }
}
