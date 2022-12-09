package com.example.knightmove.controllers;
import com.example.knightmove.HelloApplication;
import com.example.knightmove.Model.Json;
import com.example.knightmove.Model.Question;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;

public class QuestionsController implements Initializable {

    @FXML
    private TabPane TabPane;
    @FXML
    private TableView<Question> EasyTable;

    @FXML
    private TableView<Question> MedTable;

    @FXML
    private TableView<Question> HardTable;

    @FXML
    private TableColumn<Question, String> easyQuestionCol;
    @FXML
    private TableColumn<Question, String> easyAnsCol;

    @FXML
    private TableColumn<Question, Integer> EasyNumCol;

    @FXML
    private TableColumn<Question, String> medAnsCol;
    @FXML
    private Tab easyView;
    @FXML
    private Tab hardView;
    @FXML
    private Tab medView;
    @FXML
    private TableColumn<Question,String> medQuestionCol;
    @FXML
    private TableColumn<Question, String> hardAnsCol;
    @FXML
    private TableColumn<Question, String> hardQuesCol;
    private Stage stage;
    private Scene scene;
    private Parent root;

    public static Question selectedQuestion;

//    HashSet <Question> easy = new HashSet (HelloApplication.s.getQuestionsByLevel(1));
    ObservableList<Question> list = FXCollections.observableArrayList(HelloApplication.s.getQuestionsByLevel(1));
    ObservableList<Question> list2 = FXCollections.observableArrayList(HelloApplication.s.getQuestionsByLevel(2));
    ObservableList<Question> list3 = FXCollections.observableArrayList(HelloApplication.s.getQuestionsByLevel(3));
    @FXML
    void BackScreen(ActionEvent event) throws IOException{
        root = FXMLLoader.load(HelloApplication.class.getResource("AppIntroPage.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }


    public void addQuestionPage(ActionEvent event) throws IOException {
        root = FXMLLoader.load(HelloApplication.class.getResource("AddQuestion.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void editQuestion(ActionEvent event) throws IOException {
        if (easyView.isSelected()){
            selectedQuestion = EasyTable.getSelectionModel().getSelectedItem();

        }
        else if (medView.isSelected()){
            selectedQuestion = MedTable.getSelectionModel().getSelectedItem();
        }

        else {
            selectedQuestion = HardTable.getSelectionModel().getSelectedItem();
        }

        root = FXMLLoader.load(HelloApplication.class.getResource("EditQuestion.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void deleteQuestion(ActionEvent event) {
        Boolean answer = ConfrimBox.display("Confrimation", "Are you sure?"); //asking for confirmation
        Question q;
        if (answer == true) {

            if (easyView.isSelected()){
                int selectID = EasyTable.getSelectionModel().getSelectedIndex();
                q = EasyTable.getSelectionModel().getSelectedItem();
                EasyTable.getItems().remove(selectID);
            }
            else if (medView.isSelected()){
                int selectID = MedTable.getSelectionModel().getSelectedIndex();
                q = MedTable.getSelectionModel().getSelectedItem();
                MedTable.getItems().remove(selectID);

            }

            else {
                int selectID = HardTable.getSelectionModel().getSelectedIndex();
                q = HardTable.getSelectionModel().getSelectedItem();
                HardTable.getItems().remove(selectID);

            }
            HelloApplication.s.removeQuestion(q);
            Json.updateJson();
        }

        AlertBox.display("REMOVE", "Successfully removed");

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
                easyQuestionCol.setCellValueFactory(new PropertyValueFactory<Question,String>("question"));
                easyAnsCol.setCellValueFactory(new PropertyValueFactory<Question,String>("rightAnswer"));
                EasyTable.setItems(list);
                medQuestionCol.setCellValueFactory(new PropertyValueFactory<Question,String>("question"));
                medAnsCol.setCellValueFactory(new PropertyValueFactory<Question,String>("rightAnswer"));
                  MedTable.setItems(list2);
//                  hardQuesCol.setCellValueFactory(new PropertyValueFactory<Question,String>("question"));
//                   hardAnsCol.setCellValueFactory(new PropertyValueFactory<Question,String>("rightAnswer"));
//                   HardTable.setItems(list3);
    }


}
