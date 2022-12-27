package com.example.knightmove;
import com.example.knightmove.Model.Json;
import com.example.knightmove.Model.SysData;
import com.example.knightmove.controllers.GamePageController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;


public class HelloApplication extends Application {

    public static SysData s = new SysData();

    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(HelloApplication.class.getResource("GamePage.fxml"));
        Scene scene = new Scene(root, 600, 600);
        stage.setTitle("Team Panda");
        stage.setFullScreen(true); // set full screen
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) throws IOException {
        launch();
    } //TEST

}