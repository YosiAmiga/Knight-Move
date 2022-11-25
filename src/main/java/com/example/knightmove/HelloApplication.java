package com.example.knightmove;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


import java.io.IOException;


public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(HelloApplication.class.getResource("LoginKnightMove.fxml"));
        Scene scene = new Scene(root, 600, 600);
        stage.setTitle("Team Panda");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    } //TEST

}