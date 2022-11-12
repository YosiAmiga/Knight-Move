package com.example.knightmove.controllers;
import com.example.knightmove.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.IOException;

public class LogInController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private TextField userNameTextField;

    @FXML
    private Button LogInButton;

    @FXML
    private Text LogInTitle;

    @FXML
    protected void onLogInClick() {
        LogInTitle.setText("Logged in!\n Let's start playing!");
    }
    public void switchAppIntroPage(ActionEvent event) throws IOException {
        setUserNameTextField();
        root = FXMLLoader.load(HelloApplication.class.getResource("AppIntroPage.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void setUserNameTextField(){
        AppIntroPageController.userName = userNameTextField.getText();
    }



}
