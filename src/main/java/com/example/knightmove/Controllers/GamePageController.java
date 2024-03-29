package com.example.knightmove.Controllers;

import com.example.knightmove.HelloApplication;
import com.example.knightmove.Model.*;
import javafx.animation.Animation;
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
import javafx.scene.control.Button;
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
    public static ArrayList<Integer> pointsPerMove;

    public static void addToPoints(Integer currentPoint){
        pointsPerMove.add(currentPoint);
    }
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Button startLevelBtn;
    static  Timeline timeline = new Timeline();
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
    String themeLevel1 = "Sandcastle";
    String themeLevel2 = "Marine";
    String themeLevel3 = "Coral";
    String themeLevel4 = "Emerald";

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
        GamePageController.isGameOver=false; //for new game
        startLevelBtn.setDisable(false);
        currentScore.setText("0");
        currentLevelText.setText("1");

        // Themes are Coral, Dusk, Wheat, Marine, Emerald, Sandcastle
        startTimeSec=60;
        timeline.stop();
        if (GamePageController.level == 1) {
            cb = new ChessBoard(chessBoard, themeLevel1, 0, 0, 3, 3);
        }
        currentPiece = null;
        this.game = true; // start game
        score = 0; // new score
        pointsPerMove = new ArrayList<>();
        visitedSquares = new ArrayList<>();
        addEventHandlers(cb.chessBoard);
        cb.chessBoard.setDisable(true);
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

                startTimeSec--;
                boolean timeToChangeLevel = startTimeSec == 0;
                if (timeToChangeLevel) {

                    if (currentLevelText.getText().equals("1") && GamePageController.score > 15) {
                        GamePageController.level++;
                        visitedSquares.clear();
                        updateScore();
                        try {
                            changeLevel(2);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        currentLevelText.setText("2");
                        startTimeSec=60;
                        queenMovement = "smart";
                    } else if (currentLevelText.getText().equals("2")  && GamePageController.score > 30) {
                        GamePageController.level++;
                        visitedSquares.clear();
                        updateScore();
                        try {
                            changeLevel(3);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        currentLevelText.setText("3");
                        startTimeSec=60;
                    } else if (currentLevelText.getText().equals("3") && GamePageController.score > 45) {
                        GamePageController.level++;
                        visitedSquares.clear();
                        updateScore();
                        try {
                            changeLevel(4);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        currentLevelText.setText("4");
                        startTimeSec=60;
                    } else if (currentLevelText.getText().equals("4")) {
                        visitedSquares.clear();
                        currentLevelText.setText("End");
                        timeline.stop();
                        isGameOver = true; // game over
                        try {
                            checkIsGameOver(); // end game
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    } else {
                        GamePageController.isGameOver = true;
                        try {
                            checkIsGameOver();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
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
        GamePageController.timeline.stop();
        GamePageController.timeline.getKeyFrames().clear();
        root = FXMLLoader.load(HelloApplication.class.getResource("AppIntroPage.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void showGameRulesPopUp(ActionEvent event) throws IOException {
        Stage popUpStage = new Stage();
        popUpStage.setScene(new Scene(FXMLLoader.load(HelloApplication.class.getResource("GameRulesPagePopUp.fxml"))));
        popUpStage.setX(100);
        popUpStage.setY(100);
        popUpStage.show();
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
    public void startGame(ActionEvent event) {
        startLevelBtn.setDisable(true);
        cb.chessBoard.setDisable(false);

        startTimeSec = 60;// Change to 60!
        timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(1), evt ->updateTime()));
        currentTimeText.setText(String.format("%02d sec", startTimeSec));
        timeline.playFromStart();

    }
    private void updateTime(){
        startTimeSec--;
        boolean isSecondsZero = startTimeSec == 0;
        boolean timeToChangeLevel = startTimeSec == 0;
    }

    public static void questionPopUp(Integer level) {
        HashSet<Question> questionsByLevel = HelloApplication.s.getQuestionsByLevel(level);
        Question theQuestion = null;
        int randomNumber;
        int size = questionsByLevel.size();
        if (size==0){
            randomNumber = new Random().nextInt(1);
        }
        else {
            randomNumber = new Random().nextInt(size);
            int i = 0;
            //get random question
            for (Question q : questionsByLevel) {
                if (i == randomNumber)
                    theQuestion = q;
                i++;
            }
            //display the question
            ConfrimBox.displayQuestion(theQuestion);
            GamePageController.timeline.play();
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
//            if(!visitedSquares.contains(sq))
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
                    if(target.toString().equals("Random") && currentPiece.getAllPossibleMoves().contains(square.getName())){
                        // Need to pass the piece to a random new location.
                        AlertBox.display("Random Square", "You will jump to a random square");
                        square.setBackground(new Background(new BackgroundFill(Consts.colorVisitedSquare, CornerRadii.EMPTY, Insets.EMPTY))); // mark as visited
                        addToVisitedSquares(square);
                        ArrayList<String> possibleMovesForRandom = currentPiece.getAllPossibleMoves();


                        if(possibleMovesForRandom != null){
                            int randomIDX =getRandomNumber(0,possibleMovesForRandom.size()-1);
                            int xPositionRandom = Character.getNumericValue(possibleMovesForRandom.get(randomIDX).charAt(6));
                            int yPositionRandom = Character.getNumericValue(possibleMovesForRandom.get(randomIDX).charAt(7));

                            for(Square s : cb.getSquares()){
                                if(s.getX() == xPositionRandom && s.getY() == yPositionRandom){
                                    square = s;
                                }
                            }
                        }

                    }
                    else if(target.toString().equals("Forget")) {
                        AlertBox.display("Forget Square", "Your last 3 steps will be reverted");
                        deleteLastThreeSteps();
                    }



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
                                if (currentPiece.getColor().equals(newPiece.getColor())) {
                                    deselectPiece(false);
                                    currentPiece = newPiece;
                                    selectPiece(game);
                                } else {
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

                        if (currentPiece != null && currentPiece.toString().equals("Knight")) {
                            square.setBackground(new Background(new BackgroundFill(Consts.colorVisitedSquare, CornerRadii.EMPTY, Insets.EMPTY)));

                        }
                        if (dropPiece(square)) {
                            if (visitedSquares.contains(square)) {
                                GamePageController.score--;
                                addToPoints(-1);
                                if (GamePageController.score < 0) {
                                    GamePageController.score = 0;
                                }
                            } else {
                                GamePageController.score++;
                                addToPoints(1);
                            }
                            addToVisitedSquares(square);
                            for (Square s : getVisitedSquares()) {
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
                                        ArrayList<Integer> movesSelector = newQueen.selectQueenMovements(queenMovement, possibleMovesInArrayOfTwo, knightPositions);
                                        killPiece(queenSquare);
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
        clickPieceSound();
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
    private Boolean dropPiece(Square square){
        if(currentPiece!=null && !currentPiece.possibleMoves.contains(square.name)) return false;
        if(currentPiece==null) return false;
        Square initialSquare = (Square) currentPiece.getParent();
        square.getChildren().add(currentPiece);
        square.occupied = true;
        initialSquare.getChildren().removeAll();
        initialSquare.occupied = false;
        currentPiece.setPosX(square.getX());
        currentPiece.setPosY(square.getY());
        String currentPieceName = currentPiece.toString();
        deselectPiece(true);
        updateScore();
        if(square.getType()=="Question" && currentPieceName.equals("Knight"))
        {
            Point p= new Point(square.getX(), square.getY());
            Integer level = getLevelByThePostion(cb.getQuestionSquaresLocations(),p);
            questionPopUp(level); // pops up a question window
            cb.removeAndCreateQuestionSquare(square.getX(),square.getY(),this.visitedSquares);
        }
        return true;
    }

    /**
     * remove the display of the piece from square
     * @param square The square the piece is on
     */
    private void killPiece(Square square){
        if(!currentPiece.possibleMoves.contains(square.name)) return;

        Piece killedPiece = (Piece) square.getChildren().get(0);
        if(killedPiece.type.equals("King")) this.game = false;
        Square initialSquare = (Square) currentPiece.getParent();
        square.getChildren().remove(0);
        square.getChildren().add(currentPiece);
        square.occupied = true;
        if(square instanceof QuestionSquare){
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
        if(isGameOver){
            GamePageController.timeline.getKeyFrames().clear();
            gameOverSound();
            queenMovement = "random";

            try {
                GamePageController.timeline.stop();
                root = FXMLLoader.load(HelloApplication.class.getResource("EndGamePage.fxml"));
                stage = (Stage) mainPane.getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.setUserData(currentScore);
                stage.show();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
    }


    public void changeLevel(int level) throws IOException {
        if(level==2)
        {
            newLevelSound();
            if(GamePageController.score<15)
            {
                isGameOver=true;
                checkIsGameOver();
                GamePageController.timeline.stop();

            }
            else{ cb = new ChessBoard(chessBoard, themeLevel2,0,3,0,3); }
        }
        if(level==3)
        {
            newLevelSound();
            if(GamePageController.score<30)
            {
                isGameOver=true;
                checkIsGameOver();
            }
            else {cb = new ChessBoard(chessBoard, themeLevel3,0,2,2,3); }
        }
        if(level==4)
        {
            newLevelSound();
            if(GamePageController.score<45)
            {
                isGameOver=true;
                checkIsGameOver();
            }
            else {cb = new ChessBoard(chessBoard, themeLevel4,8,0,0,3);}
        }
        knightCurrentPosition = new Point(0,0);
        this.visitedSquares=new ArrayList<>();
        currentPiece=null;
    }


    public static Integer getLevelByThePostion(ArrayList<Point> a, Point point){ //get a po
        Integer l=1;
        for (Point p:a){
            if (p.equals(point)){
                return l;
            }
            l++;
        }
        return l;
    }

    public void deleteLastThreeSteps(){
        ArrayList<Square> rePaintSquares = new ArrayList<>();
        int firstPoint = pointsPerMove.remove(pointsPerMove.size() -1);
        int secondPoint = pointsPerMove.remove(pointsPerMove.size() -1);
        int thirdPoint = pointsPerMove.remove(pointsPerMove.size() -1);
        if(visitedSquares.size() > 3){
            GamePageController.score -= firstPoint;
            GamePageController.score -= secondPoint;
            GamePageController.score -= thirdPoint;
            rePaintSquares.add(visitedSquares.get(visitedSquares.size() - 1));
            rePaintSquares.add(visitedSquares.get(visitedSquares.size() - 2));
            rePaintSquares.add(visitedSquares.get(visitedSquares.size() - 3));
            visitedSquares.remove(visitedSquares.size() - 1);
            visitedSquares.remove(visitedSquares.size() - 1);
            visitedSquares.remove(visitedSquares.size() - 1);

            //color back to original board color
            rePaintBoardAfterForgettingSquares(rePaintSquares);
        }
        else{
            int lessThanThreeStepsIndex = getVisitedSquares().size() - 1;

            while(lessThanThreeStepsIndex >= 0){
                GamePageController.score -= pointsPerMove.remove(lessThanThreeStepsIndex);
                rePaintSquares.add(visitedSquares.get(lessThanThreeStepsIndex));
                visitedSquares.remove(visitedSquares.size() - 1);
                lessThanThreeStepsIndex--;
            }

            //color back to original board color
            rePaintBoardAfterForgettingSquares(rePaintSquares);

        }
    }

    public void rePaintBoardAfterForgettingSquares(ArrayList<Square> rePaintSquares){
        for(Square square : rePaintSquares){
            if((square.getY()+square.getX())%2==0){
                square.setBackground(new Background(new BackgroundFill(Consts.color1, CornerRadii.EMPTY, Insets.EMPTY)));
            }else{
                square.setBackground(new Background(new BackgroundFill(Consts.color2, CornerRadii.EMPTY, Insets.EMPTY)));
            }
        }
    }
    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
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

    public static void gameOverSound() {
        Sound s = new Sound();
        try {
            s.gameOverSound();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static void newLevelSound() {
        Sound s = new Sound();
        try {
            s.newLevelSound();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
    public static void clickPieceSound() {
        Sound s = new Sound();
        try {
            s.clickSound();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

}
