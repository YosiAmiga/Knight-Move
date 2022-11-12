package com.example.knightmove.controllers;

import com.example.knightmove.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class AppIntroPageController {

    private Stage stage;
    private Scene scene;
    private Parent root;


    public void switchToGamePage(ActionEvent event) throws IOException {
        root = FXMLLoader.load(HelloApplication.class.getResource("GamePage.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToGameRulesPage(ActionEvent event) throws IOException {
        root = FXMLLoader.load(HelloApplication.class.getResource("GameRulesPage.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void returnToLoginPage(ActionEvent event) throws IOException {
        root = FXMLLoader.load(HelloApplication.class.getResource("LogInKnightMove.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private Button GameRulesButton;

    @FXML
    private Button PlayButton;


    @FXML // TODO: write a function that passes to the game rules page --> should be in resources dir
    protected void onGameRulesButtonClick() {
        return;

    }
    @FXML // TODO: write a function that passes to the game page --> should be in resources dir
    protected void onPlayButtonClick() {
        return;

    }


}
