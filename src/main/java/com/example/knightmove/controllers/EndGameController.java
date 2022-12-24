package com.example.knightmove.controllers;

import com.example.knightmove.HelloApplication;
//import com.example.knightmove.Model.Game;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class EndGameController {
    DatabaseController dbc = new DatabaseController();
    @FXML
    private Label earnPointLabel;

    @FXML
    private Pane endGamepopup;

    @FXML
    private Button returnButton;

    @FXML
    private Label textLabel;
    static String userName;
    private Stage stage;
    private Scene scene;
    private Parent root;

    private String score;

    public void initialize() throws SQLException {
        earnPointLabel.setText(userName+", You earned " + GamePageController.score + " points");
        dbc.addPlayerAndScore(userName, GamePageController.score);
    }
    public void returnToAppIntroPage(ActionEvent event) throws IOException {
        root = FXMLLoader.load(HelloApplication.class.getResource("AppIntroPage.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}

