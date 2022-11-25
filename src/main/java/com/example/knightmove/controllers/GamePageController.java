package com.example.knightmove.controllers;

import com.example.knightmove.HelloApplication;
import com.example.knightmove.Model.Game;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class GamePageController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    private Timeline timeline = new Timeline();

    private int startTimeSec;


    public void initialize() {

        // Themes are Coral, Dusk, Wheat, Marine, Emerald, Sandcastle
        Game game = new Game(chessBoard, "Sandcastle");
        startTimeSec = 10; // Change to 60!

        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                startTimeSec--;
                boolean isSecondsZero = startTimeSec == 0;
                boolean timeToChangeLevel = startTimeSec == 0;

                if (isSecondsZero) {
                    startTimeSec = 10;
                }
                if (timeToChangeLevel) {
                    timeline.stop();
                    startTimeSec = 10;
                    if(currentLevelText.getText().equals("1")){
                        currentLevelText.setText("2");
                    }
                    else if(currentLevelText.getText().equals("2")){
                        currentLevelText.setText("3");
                    }
                    else if(currentLevelText.getText().equals("3")){
                        currentLevelText.setText("4");
                    }
                }

                currentTimeText.setText(String.format("%02d sec", startTimeSec));

            }
        }));
        timeline.playFromStart();
    }

    public void returnToAppIntroPage(ActionEvent event) throws IOException {
        root = FXMLLoader.load(HelloApplication.class.getResource("AppIntroPage.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setFullScreen(true); // set full screen
        stage.show();
    }

    public void newLevel(ActionEvent event) {
        this.initialize();
    }

    @FXML
    private Text CurrentTurnText;

    @FXML
    private Text CurrentScoreText;

    @FXML
    private Text timeText;

    @FXML
    GridPane chessBoard;

    @FXML
    private Label currentLevelText;

    @FXML
    private Label currentTimeText;

}
