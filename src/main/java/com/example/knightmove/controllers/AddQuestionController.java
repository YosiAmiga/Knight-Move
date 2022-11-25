package com.example.knightmove.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;

import java.net.URL;
import java.util.ResourceBundle;

public class AddQuestionController implements Initializable {

    @FXML
    private ChoiceBox<Integer> Level;

    @FXML
    private CheckBox OptFour;

    @FXML
    private CheckBox OptOne;

    @FXML
    private CheckBox OptThree;

    @FXML
    private CheckBox OptTwo;

    @FXML
    void BackPage(ActionEvent event) {
    System.out.println("hi");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    Level.getItems().add(1);
    Level.getItems().add(2);
    Level.getItems().add(3);
    }
}
