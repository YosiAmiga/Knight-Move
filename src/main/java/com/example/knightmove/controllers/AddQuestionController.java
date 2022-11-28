package com.example.knightmove.controllers;
import com.example.knightmove.HelloApplication;
import com.example.knightmove.Model.Json;
import com.example.knightmove.Model.Question;
import com.example.knightmove.Model.SysData;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.ResourceBundle;

public class AddQuestionController implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private ChoiceBox<Integer> Level;
    @FXML
    private TextArea Question;
    @FXML
    private RadioButton OptFour;

    @FXML
    private RadioButton OptOne;

    @FXML
    private RadioButton OptThree;

    @FXML
    private RadioButton OptTwo;
    @FXML
    private TextField Ans1;

    @FXML
    private TextField Ans2;

    @FXML
    private TextField Ans3;

    @FXML
    private TextField Ans4;


    @FXML
    void BackPage(ActionEvent event) throws IOException {
        System.out.println("hi");
        root = FXMLLoader.load(HelloApplication.class.getResource("Questions.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    Level.getItems().add(1);
    Level.getItems().add(2);
    Level.getItems().add(3);
    }


    @FXML
    void EnterAddQuestion(ActionEvent event) {
        ArrayList<String> answers = new ArrayList<>();
        answers.add(Ans1.getText());
        answers.add(Ans2.getText());
        answers.add(Ans3.getText());
        answers.add(Ans4.getText());
        Question q = new Question(Question.getText(),answers,1, Level.getValue(), "Panda");
        HelloApplication.s.getQuestions().add(q);
        Json.writeToJson(q);

    }
}
