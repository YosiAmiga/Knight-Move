package com.example.knightmove.controllers;

import com.example.knightmove.HelloApplication;
import com.example.knightmove.Model.Question;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import com.example.knightmove.Model.Game;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
// import the required classes
import org.json.simple.parser.ParseException;
import static com.example.knightmove.Model.Json.readFromJSON;

public class GamePageController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    private Timeline timeline = new Timeline();

    private int startTimeSec;

    public GamePageController() throws IOException, ParseException {
    }


    public void initialize() {

        // Themes are Coral, Dusk, Wheat, Marine, Emerald, Sandcastle
        Game game = new Game(chessBoard, "Sandcastle");

        startTimeSec = 60; // Change to 60!
        timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                startTimeSec--;
                boolean isSecondsZero = startTimeSec == 0;
                boolean timeToChangeLevel = startTimeSec == 0;

                if (timeToChangeLevel) {
                    timeline.stop();
                    startTimeSec = 60;
                    if (currentLevelText.getText().equals("1")) {
                        Game.score+=5;
                        currentScore.setText(Integer.toString(Game.score));
                        currentLevelText.setText("2");
                    } else if (currentLevelText.getText().equals("2")) {
                        currentLevelText.setText("3");
                    } else if (currentLevelText.getText().equals("3")) {
                        Game.score+=105;
                        currentScore.setText(Integer.toString(Game.score));
                        currentLevelText.setText("4");
                    } else if (currentLevelText.getText().equals("4")) {
                        currentLevelText.setText("End");
                        timeline.stop();
                        try {
                            root = FXMLLoader.load(HelloApplication.class.getResource("EndGamePage.fxml"));
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        stage = (Stage) mainPane.getScene().getWindow();
                        stage.setTitle("Game Over");
                        scene = new Scene(root);
                        stage.setScene(scene);
                        stage.setUserData(currentScore);
                        stage.show();
                    }
                }
                currentTimeText.setText(String.format("%02d sec", startTimeSec));
                if (!currentLevelText.getText().equals("End")) {
                    timeline.playFromStart();
                }
            }
        }));
    }


    public void returnToAppIntroPage(ActionEvent event) throws IOException {
        root = FXMLLoader.load(HelloApplication.class.getResource("AppIntroPage.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void newLevel(ActionEvent event) {
        startTimeSec = 60; // Change to 60!
        timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                startTimeSec--;
                boolean isSecondsZero = startTimeSec == 0;
                boolean timeToChangeLevel = startTimeSec == 0;

                currentTimeText.setText(String.format("%02d sec", startTimeSec));
            }
        }));
        timeline.playFromStart();
    }

    @FXML
    private Text CurrentTurnText;

    @FXML
    private Text CurrentScoreText;

    @FXML
    public Label currentScore;
    @FXML
    private Text timeText;

    @FXML
    GridPane chessBoard;

    @FXML
    private ImageView boardCurrentStateImage;

    @FXML
    private Pane mainPane;

    @FXML
    private Label currentLevelText;

    @FXML
    private Label currentTimeText;

    public static void createQuestionPopUp(){
        HashSet<Question> allQuestionsInJSON= readFromJSON();
        // convert the HashSet to an array
        Object[] array = allQuestionsInJSON.toArray();
        // get a random questions from the array
        Object randomQuestion = array[ThreadLocalRandom.current().nextInt(array.length)];
        Question questionObject = (Question) randomQuestion;
        String questionNameToPopUp = questionObject.getQuestion();
        Integer questionLevel = questionObject.getLevel();
        ArrayList<String> answers = questionObject.getAnswers();
        ArrayList<ButtonType> answersOptions = new ArrayList<>();
        for(String answer : answers){
            answersOptions.add(new ButtonType(answer));
        }
        Integer correctAnswerNumber = questionObject.getCorrectAnswer();
        String correctAnswerStringByIndex = answers.get(correctAnswerNumber-1);
        
        // create an Alert object
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"",answersOptions.get(0),answersOptions.get(1),answersOptions.get(2),answersOptions.get(3));
        alert.setHeaderText(questionNameToPopUp);
        // set the alert's message to the first question
        alert.setContentText("Select your answer:");
        // show the alert and get the user's response
        ButtonType response = alert.showAndWait().orElse(null);
        String playerSelectedAnswer = response.getText();

        Alert correctAnswer = new Alert(Alert.AlertType.INFORMATION);
        correctAnswer.setTitle("Correct Answer");
        correctAnswer.setHeaderText("Correct Answer");
        correctAnswer.setContentText("Congratulations, that is the correct answer.");

        Alert wrongAnswer = new Alert(Alert.AlertType.ERROR);
        wrongAnswer.setTitle("Wrong Answer");
        wrongAnswer.setHeaderText("Wrong Answer");
        wrongAnswer.setContentText("Sorry, that is the wrong answer. Please try again.");

        // check the user's response
        if (playerSelectedAnswer.equals(correctAnswerStringByIndex)) {
            correctAnswer.showAndWait();
            Game.score += questionLevel;
            System.out.println("Game.score " + Game.score);
        }else {
            wrongAnswer.showAndWait();
            Game.score -= (questionLevel+1);
            System.out.println("Game.score " + Game.score);
        }
    }

    public static void questionPopUp(Integer level){
        HashSet<Question> questionsByLevel = HelloApplication.s.getQuestionsByLevel(level);
        Question theQuestion = null;
        int size = questionsByLevel.size();
        int randomNumber = new Random().nextInt(size);
        int i = 0;
        //get random question
        for (Question q : questionsByLevel) {
            if (i == randomNumber)
                theQuestion=q;
            i++;
        }
        // create an Alert object
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"", new ButtonType(theQuestion.getAnswers().get(0)),new ButtonType(theQuestion.getAnswers().get(1)),new ButtonType(theQuestion.getAnswers().get(2)),new ButtonType(theQuestion.getAnswers().get(3)));
        alert.setHeaderText(theQuestion.getQuestion());
        // set the alert's message to the first question
        alert.setContentText("Select your answer:");
        // show the alert and get the user's response
        ButtonType response = alert.showAndWait().orElse(null);
        String playerSelectedAnswer = response.getText();

        Alert correctAnswer = new Alert(Alert.AlertType.INFORMATION);
        correctAnswer.setTitle("Correct Answer");
        correctAnswer.setHeaderText("Correct Answer");
        correctAnswer.setContentText("Congratulations, that is the correct answer.");

        Alert wrongAnswer = new Alert(Alert.AlertType.ERROR);
        wrongAnswer.setTitle("Wrong Answer");
        wrongAnswer.setHeaderText("Wrong Answer");
        wrongAnswer.setContentText("Sorry, that is the wrong answer. The right one is: "+ theQuestion.getRightAnswer());

        // check the user's response
        if (playerSelectedAnswer.equals(theQuestion.getRightAnswer())) {
            correctAnswer.showAndWait();
            Game.score += level;
            System.out.println("Game.score " + Game.score);
        }else {
            wrongAnswer.showAndWait();
            Game.score -= (level+1);
            System.out.println("Game.score " + Game.score);
        }

}
}
