package com.example.knightmove.controllers;

import com.example.knightmove.Exceptions.NotAllFieldsFullException;
import com.example.knightmove.HelloApplication;
import com.example.knightmove.Model.Json;
import com.example.knightmove.Model.Question;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class EditQuestionController implements Initializable {
    @FXML
    private TextField Ans1;

    @FXML
    private TextField Ans2;

    @FXML
    private TextField Ans3;

    @FXML
    private TextField Ans4;

    @FXML
    private ToggleGroup Answer;

    @FXML
    private ChoiceBox<Integer> Level;

    @FXML
    private RadioButton OptFour;

    @FXML
    private RadioButton OptOne;

    @FXML
    private RadioButton OptThree;

    @FXML
    private RadioButton OptTwo;

    @FXML
    private TextArea Question;
    private Stage stage;
    private Scene scene;
    private Parent root;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
            Question.setText(QuestionsController.selectedQuestion.getQuestion());
            Level.setValue(QuestionsController.selectedQuestion.getLevel());
            Level.getItems().add(1);
            Level.getItems().add(2);
            Level.getItems().add(3);
            Ans1.setText(QuestionsController.selectedQuestion.getAnswers().get(0));
            Ans2.setText(QuestionsController.selectedQuestion.getAnswers().get(1));
            Ans3.setText(QuestionsController.selectedQuestion.getAnswers().get(2));
            Ans4.setText(QuestionsController.selectedQuestion.getAnswers().get(3));
            if (QuestionsController.selectedQuestion.getCorrectAnswer()==1){
                Answer.selectToggle(OptOne);
            }
            else if (QuestionsController.selectedQuestion.getCorrectAnswer()==2){
                Answer.selectToggle(OptTwo);
            }
            else if(QuestionsController.selectedQuestion.getCorrectAnswer()==3){
                Answer.selectToggle(OptThree);
            }
            else Answer.selectToggle(OptFour);
    }
    public void EnterAddQuestion(ActionEvent event) throws NotAllFieldsFullException {
        ArrayList<String> answers = new ArrayList<>();
        if (Level.getSelectionModel().isEmpty()||Question.getText().isEmpty()||Ans1.getText().isEmpty()||Ans2.getText().isEmpty()||Ans3.getText().isEmpty()||Ans4.getText().isEmpty()||Answer.getSelectedToggle().isSelected()==false)
            throw new NotAllFieldsFullException();
        Integer correctAnswer;
        answers.add(Ans1.getText());
        answers.add(Ans2.getText());
        answers.add(Ans3.getText());
        answers.add(Ans4.getText());
        //checking for the correct answer marked by user
        if (OptOne.isSelected()){
            correctAnswer = 1;
        }
        else if (OptTwo.isSelected()){
            correctAnswer = 2;
        }
        else if (OptThree.isSelected()){
            correctAnswer = 3;
        }
        else{
            correctAnswer = 4;
        }
        Question q = new Question(Question.getText(),answers,correctAnswer, Level.getValue(), "Panda"); //creating a question object with the edited filled
        if (HelloApplication.s.editQuestion(q)){ //editing the question
            System.out.println("UPDATED");
            Json.updateJson(); //updating the json file
        }
    }

    public void BackPage(ActionEvent event) throws IOException {
        root = FXMLLoader.load(HelloApplication.class.getResource("Questions.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


}
