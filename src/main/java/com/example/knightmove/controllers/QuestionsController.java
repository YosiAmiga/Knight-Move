package com.example.knightmove.controllers;
import com.example.knightmove.HelloApplication;
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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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
    private TableColumn<Question,String> medQuestionCol;
    @FXML
    private TableColumn<Question, String> hardAnsCol;
    @FXML
    private TableColumn<Question, String> hardQuesCol;
    private Stage stage;
    private Scene scene;
    private Parent root;

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
