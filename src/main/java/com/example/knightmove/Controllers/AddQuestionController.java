package com.example.knightmove.Controllers;
import com.example.knightmove.Exceptions.NotAllFieldsFullException;
import com.example.knightmove.Exceptions.SameValueException;
import com.example.knightmove.HelloApplication;
import com.example.knightmove.Model.Json;
import com.example.knightmove.Model.Question;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;

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
    private PieChart pieChart;


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
    private ToggleGroup Answer;


    @FXML
    void BackPage(ActionEvent event) throws IOException {
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
        ObservableList<PieChart.Data> pieCharData =
                FXCollections.observableArrayList(
                        new PieChart.Data("Easy",HelloApplication.s.getQuestionsByLevel(1).size()),
                        new PieChart.Data("Medium", HelloApplication.s.getQuestionsByLevel(2).size()),
                        new PieChart.Data("Hard",HelloApplication.s.getQuestionsByLevel(3).size())
                );
        pieChart.getData().addAll(pieCharData);
    }


    @FXML
    void EnterAddQuestion(ActionEvent event) throws IOException, NotAllFieldsFullException {
        ArrayList<String> answers = new ArrayList<>();
        try{

            if (Ans1.getText().equals(" ")||Ans2.getText().equals(" ")|| Ans3.getText().equals(" ")|| Ans4.getText().equals(" ")||Question.getText().equals(" ")||Level.getSelectionModel().isEmpty()||Question.getText().isEmpty()||Ans1.getText().isEmpty()||Ans2.getText().isEmpty()||Ans3.getText().isEmpty()||Ans4.getText().isEmpty()||Answer.getSelectedToggle().isSelected()==false)
                throw new NotAllFieldsFullException();
            Integer correctAnswer;

            answers.add(Ans1.getText());
            answers.add(Ans2.getText());
            answers.add(Ans3.getText());
            answers.add(Ans4.getText());

            //checking there is no duplicate answers
            Set<String> set = new HashSet<String>(answers);
            if(set.size() < answers.size()){
                throw new SameValueException();
            }
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

            Question q = new Question(Question.getText(),answers,correctAnswer, Level.getValue(), "Panda"); //creating new question
            if (HelloApplication.s.addQuestion(q)){ //adding question to the questions hashset
                Json.updateJson(); //updating the json file
            }

            AlertBox.display("Added", "Successfully Added");
            updatePieChart();

        } catch (NotAllFieldsFullException e){
            AlertBox.display("ERROR" , e.getMessage());
        } catch (SameValueException e) {
            AlertBox.display("ERROR" , e.getMessage());
        }

    }

    public void updatePieChart(){
        ObservableList<PieChart.Data> pieCharData =
                FXCollections.observableArrayList(
                        new PieChart.Data("Easy",HelloApplication.s.getQuestionsByLevel(1).size()),
                        new PieChart.Data("Medium", HelloApplication.s.getQuestionsByLevel(2).size()),
                        new PieChart.Data("Hard",HelloApplication.s.getQuestionsByLevel(3).size())
                );
        pieChart.getData().setAll(pieCharData);
    }
}