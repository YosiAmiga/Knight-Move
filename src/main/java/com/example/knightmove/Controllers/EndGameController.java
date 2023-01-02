package com.example.knightmove.Controllers;

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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
    private Label textLabel;

    @FXML
    private ImageView medalPicture;
    static String userName;
    private Stage stage;
    private Scene scene;
    private Parent root;



    private String score;

    public void initialize() throws SQLException {
        earnPointLabel.setText(userName+", You earned " + GamePageController.score + " points");
        dbc.addPlayerAndScore(userName, GamePageController.score);
        if(GamePageController.score>=200)
        {
            String theme1Url = String.valueOf(getClass().getResource("/picture/medal.png"));
            Image image = new Image(theme1Url);
            medalPicture.setImage(image);
        }
        GamePageController.level=1;
    }

    public ImageView getImageView(){ return this.medalPicture;}

    /**
     * for test checking
     * @return if the player got medal
     */
    public boolean gotMedal(){
        if(GamePageController.score >=200)
        {
            return true;
        }
        return false;
    }
}

