package com.example.knightmove.controllers;

import com.example.knightmove.Model.Question;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ConfrimBox {
    static Boolean answer;
    public static Boolean display(String title, String message) { //this method shows a confirmation box from the title and massage
        Stage window = new Stage ();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setMinWidth(250);
        window.setTitle(title);
        Label label = new Label ();
        label.setText(message);
        Button yesButton = new Button ("YES");
        Button noButton = new Button ("NO");
        yesButton.setOnAction(e->{  //Lambda expression in case the user chose yes
            answer = true;
            window.close();
        });
        noButton.setOnAction(e->{  //Lambda expression in case the user chose yes
            answer = false;
            window.close();
        });
        VBox layout = new VBox (10);
        layout.getChildren().addAll(label, yesButton, noButton);
        layout.setAlignment(Pos.CENTER);
        Scene scene = new Scene (layout);
        window.setScene(scene);
        window.showAndWait();

        return answer;
    }
    public static void displayQuestion(Question q) { //this method shows a confirmation box from the title and massage
        Stage window = new Stage ();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setMinWidth(250);
        window.setTitle("Question");
        Label label = new Label ();
        label.setText(q.getQuestion());
        Button Ans1 = new Button (q.getAnswers().get(0));
        Button Ans2 = new Button (q.getAnswers().get(1));
        Button Ans3 = new Button (q.getAnswers().get(2));
        Button Ans4 = new Button (q.getAnswers().get(3));
        String Rightanswer = q.getRightAnswer();

        Alert correctAnswer = new Alert(Alert.AlertType.INFORMATION);
        correctAnswer.setTitle("Correct Answer");
        correctAnswer.setHeaderText("Correct Answer");
        correctAnswer.setContentText("Congratulations, that is the correct answer.");

        Alert wrongAnswer = new Alert(Alert.AlertType.ERROR);
        wrongAnswer.setTitle("Wrong Answer");
        wrongAnswer.setHeaderText("Wrong Answer");
        wrongAnswer.setContentText("The right Answer is: " + q.getRightAnswer());

        Ans1.setOnAction(e->{
            if (Rightanswer.equals(Ans1.getText())){
                correctAnswer.showAndWait();
                GamePageController.score += q.getLevel();
                window.close();
            }
            else {
                wrongAnswer.showAndWait();
                GamePageController.score -= (q.getLevel() + 1);
                window.close();
            }
        });
        Ans2.setOnAction(e->{
            if (Rightanswer.equals(Ans2.getText())){
                correctAnswer.showAndWait();
                GamePageController.score += q.getLevel();
                window.close();
            }
            else {
                wrongAnswer.showAndWait();
                GamePageController.score -= (q.getLevel() + 1);
                window.close();
            }
        });
        Ans3.setOnAction(e->{
            if (Rightanswer.equals(Ans3.getText())){
                correctAnswer.showAndWait();
                GamePageController.score += q.getLevel();
                window.close();
            }
            else {
                wrongAnswer.showAndWait();
                GamePageController.score -= (q.getLevel() + 1);
                window.close();
            }
        });
        Ans4.setOnAction(e->{
            if (Rightanswer.equals(Ans4.getText())){
                correctAnswer.showAndWait();
                GamePageController.score += q.getLevel();
                window.close();
            }
            else {
                wrongAnswer.showAndWait();
                GamePageController.score -= (q.getLevel() + 1);
                window.close();
            }
        });

        VBox layout = new VBox (10);
        layout.getChildren().addAll(label, Ans1, Ans2, Ans3, Ans4);
        layout.setAlignment(Pos.CENTER);
        Scene scene = new Scene (layout);
        window.setScene(scene);
        window.showAndWait();
    }


}