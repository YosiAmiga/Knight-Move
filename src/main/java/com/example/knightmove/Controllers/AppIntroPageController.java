package com.example.knightmove.Controllers;

import com.example.knightmove.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AppIntroPageController implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;
    static String userName;
    @FXML
    private Button GameRulesButton;

    @FXML
    private Button PlayButton;

    @FXML
    private Button questionButton;

    @FXML
    public Text userLoginText;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        userLoginText.setText(userName);
        if(!userName.equals("Yosi")&&!userName.equals("Naor")&&!userName.equals("Itay")&&!userName.equals("Dana")&&!userName.equals("Tsvika")){
            questionButton.setVisible(false);
        }
        else {
            questionButton.setVisible(true);
        }

    }


    public void switchToGamePage(ActionEvent event) throws IOException {
        EndGameController.userName = userName;
        root = FXMLLoader.load(HelloApplication.class.getResource("GamePage.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
//        stage.setFullScreen(true); // set full screen
        stage.show();
    }

    public void switchToGameRulesPage(ActionEvent event) throws IOException {
        Stage popUpStage = new Stage();
        popUpStage.setScene(new Scene(FXMLLoader.load(HelloApplication.class.getResource("GameRulesPagePopUp.fxml"))));
        popUpStage.setX(100);
        popUpStage.setY(100);
        popUpStage.show();
    }

    public void returnToLoginPage(ActionEvent event) throws IOException {
        root = FXMLLoader.load(HelloApplication.class.getResource("LogInKnightMove.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
//        stage.setFullScreen(true); // set full screen
        stage.show();
    }

    public void goToDatabasePage(ActionEvent event) throws IOException {
        root = FXMLLoader.load(HelloApplication.class.getResource("GameHistoryPage.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
//        stage.setFullScreen(true); // set full screen
        stage.show();
    }

    public void QuestionPage(ActionEvent event) throws IOException {
        root = FXMLLoader.load(HelloApplication.class.getResource("Questions.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }



}