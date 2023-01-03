package com.example.knightmove.Controllers;
import com.example.knightmove.HelloApplication;
import com.example.knightmove.Model.Player;
import com.example.knightmove.Model.Sound;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class LogInController {


    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private CheckBox iAmNewUser;
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
    public void switchAppIntroPage(ActionEvent event) throws IOException, SQLException {
        if(iAmNewUser.isSelected()){
            //check box is selected and a new player, add him
            if(isNewPlayer()){
                if(addNewPlayer()){
                    successAdded();
                    setUserNameTextField();
                    root = FXMLLoader.load(HelloApplication.class.getResource("AppIntroPage.fxml"));
                    stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                    scene = new Scene(root);
                    stage.setScene(scene);
                    stage.setFullScreen(false);
                    stage.show();
                }
            }//not a new player, failLogin()
            else{
                failLogin();;
            }
        }
        else{//check box not selected
            if(isNewPlayer()){//and layer added successfully
                failLoginCheckboxUnselected();;
            }//nickname is not new
            else{
                successLogin();
                setUserNameTextField();
                root = FXMLLoader.load(HelloApplication.class.getResource("AppIntroPage.fxml"));
                stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
        }

    }

    public void setUserNameTextField(){
        AppIntroPageController.userName = userNameTextField.getText();
    }

    public boolean isNewPlayer() {
        String nickname = userNameTextField.getText();
        DatabaseController dbc = new DatabaseController();
        ArrayList<Player> players = dbc.getAllPlayers();
        for (Player p : players) {
            if (p.getNickname().equals(nickname)){
                return false;
            }
        }
        return true;
    }

    public boolean addNewPlayer() throws SQLException {
        String nickname = userNameTextField.getText();
        try{
            if(nickname.equals("")){
                throw new SQLException();
            }else{
                DatabaseController dbc = new DatabaseController();
                return dbc.isUserInDatabase(nickname);
            }
        }
        catch(SQLException s){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error");
            alert.setContentText("Please enter a valid nickname");
            alert.showAndWait();
            return false;
        }
    }

    public void failLogin() {
        badSound();
        Alert al = new Alert(Alert.AlertType.ERROR);
        al.setContentText("Failed to add new user with nickname: " + userNameTextField.getText() + "Change it!");
        al.setHeaderText("Could not add user to system!");
        al.setTitle("Database");
        al.setResizable(false);
        al.showAndWait();
    }
    public void failLoginCheckboxUnselected() {
        badSound();
        Alert al = new Alert(Alert.AlertType.ERROR);
        al.setContentText("Please select the check box if you are a new user!");
        al.setHeaderText("Checkbox Unselected!");
        al.setTitle("Database");
        al.setResizable(false);
        al.showAndWait();
    }
    public void successAdded() {
        goodSound();
        Alert al = new Alert(Alert.AlertType.INFORMATION);
        al.setContentText(userNameTextField.getText()+" Added Successfully! Moving to App intro page");
        al.setHeaderText("Success");
        al.setTitle("Database");
        al.setResizable(false);
        al.showAndWait();
    }

    public void successLogin() {
        goodSound();
        Alert al = new Alert(Alert.AlertType.INFORMATION);
        al.setContentText(" Welcome back! "+userNameTextField.getText()+" Moving to App intro page");
        al.setHeaderText("Success");
        al.setTitle("Database");
        al.setResizable(false);
        al.showAndWait();
    }
    public static void badSound() {
        Sound s = new Sound();
        try {
            s.errorSound();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static void goodSound() {
        Sound s = new Sound();
        try {
            s.successSound();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

}