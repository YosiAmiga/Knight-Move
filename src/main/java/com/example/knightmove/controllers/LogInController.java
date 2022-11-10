package com.example.knightmove.controllers;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class LogInController {

    @FXML
    private TextField userNameTextField;

    @FXML
    private Button LogInButton;

    @FXML
    private Text LogInTitle;

    @FXML
    protected void onLogInClick() {
        LogInTitle.setText("Logged in!\n Let's start playing!");

    }


}
