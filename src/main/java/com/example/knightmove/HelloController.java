package com.example.knightmove;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.text.Text;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    private Text branchTest;
    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
        branchTest.setText("Testing new branch creation!");
    }
}