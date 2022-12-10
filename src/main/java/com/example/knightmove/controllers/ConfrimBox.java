package com.example.knightmove.controllers;

import javafx.geometry.Pos;
import javafx.scene.Scene;
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
}