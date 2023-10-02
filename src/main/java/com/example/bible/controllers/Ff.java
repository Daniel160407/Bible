package com.example.bible.controllers;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Observable;
import java.util.Observer;

public class Ff extends Observable implements Observer {
    @FXML
    private AnchorPane projectorAnchorPane;

    private int myVariable;

    public int getMyVariable() {
        return myVariable;
    }

    public void setMyVariable(int newValue) {
        if (newValue != myVariable) {
            myVariable = newValue;
            setChanged(); // Mark the state as changed
            notifyObservers(newValue); // Notify observers about the change
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof Ff) {
            int newValue = (int) arg;
            // Handle the variable change
            System.out.println(newValue);
        }
    }

    @FXML
    public void on() {
        projectorAnchorPane.getChildren().add(new Pane());
        System.out.println("Success!");
    }
}
