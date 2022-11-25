package com.example.knightmove.controllers;

import com.example.knightmove.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class GamePageController {

    private Stage stage;
    private Scene scene;
    private Parent root;
    public void returnToAppIntroPage(ActionEvent event) throws IOException {
        root = FXMLLoader.load(HelloApplication.class.getResource("AppIntroPage.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private Text CurrentTurnText;

    @FXML
    private Text CurrentScoreText;

    @FXML
    private Text timeText;


    @FXML
    private ImageView boardCurrentStateImage;

}