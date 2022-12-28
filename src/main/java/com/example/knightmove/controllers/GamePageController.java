package com.example.knightmove.controllers;

import com.example.knightmove.HelloApplication;
import com.example.knightmove.Model.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventTarget;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class GamePageController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    private Timeline timeline = new Timeline();
    public static boolean isGameOver =false;
    public Point knightCurrentPosition; // point of knight
    private int startTimeSec; // the timer

    public static Piece currentPiece; // piece playing (king/queen/knight)

    public static ChessBoard cb;
    private boolean game; // if game is running

    public static int level=1; // current level in the game

    public static int king_speed; // control king speed

    public static int score; // score of the player
    public static String queenMovement = "random"; // random move of queen

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

    ArrayList<Square> visitedSquares; // squares they already visited at.

    public GamePageController() throws IOException, ParseException {
    }

    public void initialize() {

        // Themes are Coral, Dusk, Wheat, Marine, Emerald, Sandcastle

        if (GamePageController.level == 1) {
            cb = new ChessBoard(chessBoard, "Sandcastle", 0, 0, 3, 3);
        }
        currentPiece = null;
        this.game = true; // start game
        score = 0; // new score
        GamePageController.score+=20;
        visitedSquares = new ArrayList<>();
        addEventHandlers(cb.chessBoard);
        knightCurrentPosition = new Point(0, 0); // start point of knight

        GamePageController.king_speed=5; // help us control king speed
        timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() { // start timer
            @Override
            public void handle(ActionEvent event) {
                try {
                    checkIsGameOver();
                    if(GamePageController.level==3 || GamePageController.level==4) { // change king speed in level 3 & 4
                        if(startTimeSec % GamePageController.king_speed ==0) {
                            moveKing(new Square(knightCurrentPosition.getX(), knightCurrentPosition.getY())); // move king
                        }
                    }
                   change_king_speed(startTimeSec); // change king speed depend the timer
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                startTimeSec--; // countdown

                boolean timeToChangeLevel = startTimeSec == 0;
                if (timeToChangeLevel) {
                    timeline.stop();
                    if (GamePageController.level == 2) {
                        startTimeSec = 15; // Change to 60!
                    } else{
                        startTimeSec = 15; // Change to 60!
                    }
                    queenMovement = "random";
                    if (currentLevelText.getText().equals("1")) {
                        try {
                            changeLevel(++GamePageController.level); // change level
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        updateScore();
                        currentLevelText.setText("2");
                        queenMovement = "smart";
                    } else if (currentLevelText.getText().equals("2")) {
                        try {
                            changeLevel(++GamePageController.level); // change level
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        updateScore();
                        currentLevelText.setText("3");
                    } else if (currentLevelText.getText().equals("3")) {
                        try {
                            changeLevel(++GamePageController.level); // change level
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        updateScore();
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


    // return to main panel from the game
    public void returnToAppIntroPage(ActionEvent event) throws IOException {
        GamePageController.level=1;
        root = FXMLLoader.load(HelloApplication.class.getResource("AppIntroPage.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * change the king speed by (60 % king_speed) every 10 second
     * @param second - time until the level over
     */
    public void change_king_speed(int second)
    {
        if(startTimeSec==50)
        {
            GamePageController.king_speed--;
        }
        if(startTimeSec==40)
        {
            GamePageController.king_speed--;
        }
        if(startTimeSec==30)
        {
            GamePageController.king_speed--;
        }
        if(startTimeSec==20) // every second
        {
            GamePageController.king_speed--;
        }

    }

    /**
     * update the label score
     */
    public void updateScore()
    {
        this.currentScore.setText(Integer.toString(GamePageController.score));
    }

    // when click on start game btn
    public void startGame(ActionEvent event) {

        startTimeSec = 15; // Change to 60!
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

    //display question when click on question square
    public static void createQuestionPopUp(){
        HashSet<Question> allQuestionsInJSON= Json.readFromJSON();
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
            GamePageController.score += questionLevel;
            System.out.println("Game.score " + GamePageController.score);
        }else {
            wrongAnswer.showAndWait();
            GamePageController.score -= (questionLevel+1);
            if(GamePageController.score<0)
            {
                GamePageController.score=0;
            }
            System.out.println("Game.score " + GamePageController.score);
        }
    }

    public static void questionPopUp(Integer level) {
        HashSet<Question> questionsByLevel = HelloApplication.s.getQuestionsByLevel(level);
        Question theQuestion = null;
        int size = questionsByLevel.size();
        int randomNumber = new Random().nextInt(size);
        int i = 0;
        //get random question
        for (Question q : questionsByLevel) {
            if (i == randomNumber)
                theQuestion = q;
            i++;
        }
        // create an Alert object
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "", new ButtonType(theQuestion.getAnswers().get(0)), new ButtonType(theQuestion.getAnswers().get(1)), new ButtonType(theQuestion.getAnswers().get(2)), new ButtonType(theQuestion.getAnswers().get(3)));
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
        wrongAnswer.setContentText("Sorry, that is the wrong answer. The right one is: " + theQuestion.getRightAnswer());

        // check the user's response
        if (playerSelectedAnswer.equals(theQuestion.getRightAnswer())) {
            correctAnswer.showAndWait();
            GamePageController.score += level;
            System.out.println("Game.score " + GamePageController.score);
        } else {
            wrongAnswer.showAndWait();
            GamePageController.score -= (level + 1);
            if(GamePageController.score<0)
            {
                GamePageController.score=0;
            }
            wrongAnswer.close();
            System.out.println("Game.score " + GamePageController.score);
        }
    }

    public ArrayList<Square> getVisitedSquares() {
        return visitedSquares;
    }

    /**
     * add square to visited square
     * @param sq the square we click
     */
    public void addToVisitedSquares(Square sq){
        if(sq != null){
            if(!visitedSquares.contains(sq))
                this.visitedSquares.add(sq);
        }
    }
    private void addEventHandlers(GridPane chessBoard){
        chessBoard.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                EventTarget target = event.getTarget();

                //Clicked on the Knight
                if (target.toString().equals("Knight")) {
                    Piece newPiece = (Piece) target;
                    Square square = (Square) newPiece.getParent();
                    square.setBackground(new Background(new BackgroundFill(Consts.colorVisitedSquare, CornerRadii.EMPTY, Insets.EMPTY)));
                    //addToVisitedSquares(square);
                    //System.out.println("Knight possible moves:\n" + newPiece.possibleMoves);
                    // Selecting a new piece
                    if (currentPiece == null) {
                        currentPiece = newPiece;
                        if (!currentPiece.getColor().equals(Consts.currentPlayer)) {
                            currentPiece = null;
                            return;
                        }
                        selectPiece(game);
                    }
                    // Selecting other piece of same color || Killing a piece
                    else {
                        if (currentPiece.getColor().equals(newPiece.getColor())) {
                            // System.out.println("inside internal if of knight");
                            deselectPiece(false);
                            currentPiece = newPiece;
                            selectPiece(game);
                        } else {
                            killPiece(square);
                        }
                    }

                }
                // Clicked on square
                if (target.toString().equals("Square") || target.toString().equals("Random") ||
                        target.toString().equals("Forget") || target.toString().equals("Question")){
                    Square square = (Square) target;
                    if(currentPiece!=null && !currentPiece.getAllPossibleMoves().contains(square.getName())) {
                        if (square.occupied) {
                            Piece newPiece = (Piece) square.getChildren().get(0);
                            // Selecting a new piece
                            if (currentPiece == null) {
                                currentPiece = newPiece;
                                if (!currentPiece.getColor().equals(Consts.currentPlayer)) {
                                    currentPiece = null;
                                    return;
                                }
                                selectPiece(game);
                            }
                            // Selecting other piece of same color || Killing a piece
                            else {
//                            System.out.println("inside else of square");
                                if (currentPiece.getColor().equals(newPiece.getColor())) {
                                    System.out.println("inside internal if of square");
                                    deselectPiece(false);
                                    currentPiece = newPiece;
                                    selectPiece(game);
                                } else {
                                    System.out.println("inside internal else of square");
                                    killPiece(square);
                                }
                            }
                        }
                    }
                    // Dropping a piece on blank square
                    else {
                        //removing the blockingSquares from possibleMoves
                        ArrayList<Point> blockingSquares = new ArrayList<Point>(cb.blockingSquaresLocations);
                        //removing the blockingSquares from possibleMoves
                        for (Point p : blockingSquares) {
                            String squareString = "Square" + p.getX() + p.getY();
                            if (currentPiece.possibleMoves.contains(squareString)) {
                                currentPiece.possibleMoves.remove(squareString);
                            }
                        }

                     //   System.out.println("currentPiece moves after drop:\n " + currentPiece.possibleMoves);
                        if (currentPiece!=null && currentPiece.toString().equals("Knight")) {
                            square.setBackground(new Background(new BackgroundFill(Consts.colorVisitedSquare, CornerRadii.EMPTY, Insets.EMPTY)));
                            //addToVisitedSquares(square);
                        }
                        dropPiece(square);
                        if (visitedSquares.contains(square)) {
                            GamePageController.score--;
                            if(GamePageController.score<0){
                                GamePageController.score=0;
                            }
                        } else {
                            GamePageController.score++;
                        }
                        addToVisitedSquares(square);
                        for(Square s : getVisitedSquares()){
                  //          System.out.println("Visited Square:\n " + s.getX()+","+s.getY());
                        }

                        /**
                         * The knight clicked on empty square, afterwards move the queen
                         */
                        int queenNextPositionX = -1;
                        int queenNextPositionY = -1;
                        int[] knightPositions = new int[2];
                        knightPositions[0] = square.getX();
                        knightPositions[1] = square.getY();
                        knightCurrentPosition.setX(square.getX());
                        knightCurrentPosition.setY(square.getY());
                        Piece foundQueen = null;
                        for (Square sq : cb.getSquares()) {

                            if (sq.getChildren().size() > 0) {
                                String pieceName = String.valueOf(sq.getChildren().get(0));
                                if (pieceName.equals("Queen")) {
                                    Piece queen = (Piece) sq.getChildren().get(0);
                                    Square queenSquare = (Square) queen.getParent();
                                    Queen newQueen = (Queen) queen;
                                    currentPiece = newQueen;
                                    foundQueen = newQueen;
                                    ArrayList<String> possibleMoves = newQueen.getAllPossibleMoves();
                                    ArrayList<ArrayList<Integer>> possibleMovesInArrayOfTwo = newQueen.convertMovesToIntArrays(newQueen.getAllPossibleMoves());
//                                    ArrayList<Integer> movesSelector = newQueen.selectQueenMovements("random", possibleMovesInArrayOfTwo, knightPositions);
                                    ArrayList<Integer> movesSelector = newQueen.selectQueenMovements(queenMovement, possibleMovesInArrayOfTwo, knightPositions);
                                    killPiece(queenSquare);
                                    //Doing Random/Smart movement (with Manhattan Distance) for Queen
                                    queenNextPositionX = movesSelector.get(0);
                                    queenNextPositionY = movesSelector.get(1);

                                }
                            }

                        }
                        if (foundQueen != null) {
                            currentPiece = foundQueen;
                        }
                        for (Square sq : cb.getSquares()) {
                            if (sq.getX() == queenNextPositionX && sq.getY() == queenNextPositionY && foundQueen != null) {
                                currentPiece = foundQueen;
                                Point queenCurrentPosition = new Point(sq.getX(), sq.getY());
                                dropPiece(sq);
                                queenEatKnight(knightCurrentPosition, queenCurrentPosition);
                            }
                        }
                    }
                }
            }
        });
    }

    /**
     * move the king to the best square that close to knight
     * @param square the knight current square
     */
    public void moveKing(Square square) {
        Piece now_piece = GamePageController.currentPiece; // current piece playing is the knight
        int kingNextPositionX = -1;
        int kingNextPositionY = -1;
        int[] knightPositions = new int[2];
        knightPositions[0] = square.getX();
        knightPositions[1] = square.getY();
        knightCurrentPosition.setX(square.getX());
        knightCurrentPosition.setY(square.getY());

        King foundKing=null;
        for (Square sq : cb.getSquares()) {
            if (sq.getChildren().size() > 0) {
                String pieceName = String.valueOf(sq.getChildren().get(0));
                if (pieceName.equals("King")) {
                    Piece king = (Piece) sq.getChildren().get(0);
                    Square kingSquare = (Square) king.getParent();
                    King newKing = (King) king;
                    currentPiece = newKing; // change currentPiece to the king and return in the end to knight
                    foundKing = newKing;
                    ArrayList<String> possibleMoves = newKing.getAllPossibleMoves();
                    System.out.println(possibleMoves);
                    ArrayList<ArrayList<Integer>> possibleMovesInArrayOfTwo = newKing.convertMovesToIntArrays(newKing.getAllPossibleMoves());
                    ArrayList<Integer> movesSelector = newKing.getKingBestMove(possibleMovesInArrayOfTwo, knightPositions);
                    killPiece(kingSquare);
                    kingNextPositionX = movesSelector.get(0);
                    kingNextPositionY = movesSelector.get(1);
                }
            }
        }

        Point kingCurrentPosition = null;
        Square temp = null;
        // move the display of the king
        for (Square sq : cb.getSquares()) {
            if (sq.getX() == kingNextPositionX && sq.getY() == kingNextPositionY) {
                currentPiece = foundKing;
                kingCurrentPosition = new Point(sq.getX(), sq.getY());
                temp = sq;
                kingEatKnight(knightCurrentPosition,kingCurrentPosition);
            }
            GamePageController.currentPiece = now_piece; // back the current piece playing to knight
        }
        if(temp!=null) {
            currentPiece = foundKing;
            dropPiece(temp);
            GamePageController.currentPiece = now_piece;
        }
    }

    /**
     * change the background of selected square and display possible moves
     * @param game if the game is playing
     */
    private void selectPiece(boolean game){
        if(!game){
            currentPiece = null;
            return;
        }
        DropShadow borderGlow = new DropShadow();
        borderGlow.setColor(Color.BLACK);
        borderGlow.setOffsetX(0f);
        borderGlow.setOffsetY(0f);
        currentPiece.setEffect(borderGlow);
        currentPiece.getAllPossibleMoves();
        currentPiece.showAllPossibleMoves(true);
    }

    /**
     * cancel the chosen of square (after playiny)
     * @param changeSquare - if we chose new square
     */
    private void deselectPiece(boolean changeSquare){
        currentPiece.setEffect(null);
        currentPiece.showAllPossibleMoves(false);
        currentPiece = null;
    }

    /**
     * move piece to the square we recieve
     * @param square where to move the piece (king/queen/knight)
     */
    private void dropPiece(Square square){
        if(currentPiece==null || !currentPiece.possibleMoves.contains(square.name)) return;
       // System.out.println("move to square " + square.name);
        Square initialSquare = (Square) currentPiece.getParent();
        square.getChildren().add(currentPiece);
        square.occupied = true;
        initialSquare.getChildren().removeAll();
        initialSquare.occupied = false;
        currentPiece.setPosX(square.getX());
        currentPiece.setPosY(square.getY());
        deselectPiece(true);
        updateScore();
        if(square.getType()=="Question")
        {
            randnewSpecialSquare(square);
            System.out.println(GamePageController.cb.squares);
        }
    }

    /**
     * remove the display of the piece from square
     * @param square The square the piece is on
     */
    private void killPiece(Square square){
        if(!currentPiece.possibleMoves.contains(square.name)) return;

        Piece killedPiece = (Piece) square.getChildren().get(0);
        if(killedPiece.type.equals("King")) this.game = false;
        // System.out.println("move from square " + square.name);
        Square initialSquare = (Square) currentPiece.getParent();
        square.getChildren().remove(0);
        square.getChildren().add(currentPiece);
        square.occupied = true;
        if(square instanceof QuestionSquare){
            System.out.println("QUESTION SQUARE!!!!");
        }
        initialSquare.getChildren().removeAll();
        initialSquare.occupied = false;
        currentPiece.setPosX(square.getX());
        currentPiece.setPosY(square.getY());
        deselectPiece(true);

    }

    /**
     * check if the queen "eat" the knight
     * @param knightCurrentPosition
     * @param queenCurrentPosition
     */
    public void queenEatKnight(Point knightCurrentPosition, Point queenCurrentPosition){
        if(knightCurrentPosition.getX()== queenCurrentPosition.getX() &&
                knightCurrentPosition.getY()== queenCurrentPosition.getY()){
            isGameOver = true;
        }
    }

    /**
     * check if the king "eat" the knight
     * @param knightCurrentPosition
     * @param kingCurrentPosition
     */
    public void kingEatKnight(Point knightCurrentPosition, Point kingCurrentPosition){
        if(knightCurrentPosition.getX()== kingCurrentPosition.getX() &&
                knightCurrentPosition.getY()== kingCurrentPosition.getY()){
            isGameOver = true;
        }
    }

    /**
     * check if the game is over (when queen/king eat knight)
     * @throws IOException
     */
    public void checkIsGameOver() throws IOException {
        System.out.println("isGameOver " + isGameOver);
        if(isGameOver){
            GamePageController.isGameOver=false; //for new game
            try {
                root = FXMLLoader.load(HelloApplication.class.getResource("EndGamePage.fxml"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            stage = (Stage) mainPane.getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.setUserData(currentScore);
            stage.show();
        }
    }

    /**
     * remove question square and rnd new question square
     * @param square - the question square we click on
     */
    public void randnewSpecialSquare(Square square)
    {
        if(square.getType() == "Question")
        {
            //System.out.println(GamePageController.cb.questionSquaresLocations);
            //System.out.println("---------------");
            GamePageController.cb.removeAndCreateQuestionSquare(square.getX(), square.getY(), this.visitedSquares);
            //System.out.println(GamePageController.cb.questionSquaresLocations);
           // System.out.println("---------------");

        }
    }

    /**
     * change the level and create new board
     * @param level - the next level
     */
    public void changeLevel(int level) throws IOException {
        if(level==2)
        {
            if(GamePageController.score>=15) {
                cb = new ChessBoard(chessBoard, "Sandcastle", 0, 3, 0, 3);
            }
            else{
                // score <15 --> Game Over
                isGameOver=true;
                checkIsGameOver();
            }
        }
        if(level==3)
        {
            if(GamePageController.score>=30) {
                cb = new ChessBoard(chessBoard, "Sandcastle", 0, 3, 0, 3);
            }
            else{
                // score <30 --> Game Over
                isGameOver=true;
                checkIsGameOver();
            }
        }
        if(level==4)
        {
            if(GamePageController.score>=45) {
                cb = new ChessBoard(chessBoard, "Sandcastle", 0, 3, 0, 3);
            }
            else{
                // score <45 --> Game Over
                isGameOver=true;
                checkIsGameOver();
            }
        }
        knightCurrentPosition = new Point(0,0);
        visitedSquares = new ArrayList<>();
        currentPiece=null; // the user need to select the knigth in the beginning
    }

    /*
    public void resetVisitedSquares() {
        for(Square square : visitedSquares){
            if((square.getY()+square.getX())%2==0){
                square.setBackground(new Background(new BackgroundFill(Consts.color1, CornerRadii.EMPTY, Insets.EMPTY)));
            }else{
                square.setBackground(new Background(new BackgroundFill(Consts.color2, CornerRadii.EMPTY, Insets.EMPTY)));
            }
        }
        this.visitedSquares = new ArrayList<>();
    }
     */
}
