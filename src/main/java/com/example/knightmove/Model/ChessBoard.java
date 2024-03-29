package com.example.knightmove.Model;

import com.example.knightmove.Controllers.GamePageController;
import javafx.geometry.Insets;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.layout.GridPane;
import java.util.ArrayList;
import java.util.Random;


public class ChessBoard {

    public GridPane chessBoard;
    String theme;
    public ArrayList<Square> squares = new ArrayList<>(); // all squares in the board

    public ArrayList<Point> forgettingSquaresLocations = new ArrayList<>();
    public ArrayList<Point> randomJumpSquaresLocations = new ArrayList<>();
    public ArrayList<Point> blockingSquaresLocations = new ArrayList<>();
    public ArrayList<Point> questionSquaresLocations = new ArrayList<>();

    // get the number of special squares each level and build the board
    public ChessBoard(GridPane chessBoard, String theme,int num_block,int num_forget,int num_rand,int num_ques ){
        this.chessBoard = chessBoard;
        this.theme = theme;
        makeBoard(this.chessBoard, theme,num_block,num_forget,num_rand,num_ques);
    }

    public ArrayList<Point> getQuestionSquaresLocations() {
        return questionSquaresLocations;
    }

    public ArrayList<Square> getSquares() {
        return squares;
    }

    private void makeBoard(GridPane chessBoard, String theme, int num_block,int num_forget,int num_rand,int num_ques){
        /**
         * Algo:
         * 1. Create random locations of blocking squares
         * 2. For each square set it's theme based on its type.
         *
         */
        chessBoard.getChildren().clear();
        ArrayList<Point> BlockingSquaresLocations=createBlockingSquaresLocations(num_block);
        this.blockingSquaresLocations = BlockingSquaresLocations;

        ArrayList<Point> ForgettingSquaresLocations=createForgettingSquare(num_forget);
        this.forgettingSquaresLocations = ForgettingSquaresLocations;

        ArrayList<Point>  RandomJumpSquaresLocations = createRandomJumpSquare(num_rand);
        this.randomJumpSquaresLocations = RandomJumpSquaresLocations;

        ArrayList<Point>  questionSquaresLocations = createQuestionSquare(num_ques);
        this.questionSquaresLocations = questionSquaresLocations;

        SquareFactory squarefactory = new SquareFactory();
        for(int i=0; i<Consts.SQUARES_IN_ROW; i++){
            for(int j=0; j<Consts.SQUARES_IN_COLUMN; j++){
                Square square;
                Point point = new Point(i,j);
                if(BlockingSquaresLocations.contains(point)){
                    square = squarefactory.getSquare("BLOCKSQUARE",i,j);
                }
                else if(ForgettingSquaresLocations.contains(point)){
                    square = squarefactory.getSquare("FORGETSQUARE",i,j);
                }
                else if(RandomJumpSquaresLocations.contains(point)){
                    square = squarefactory.getSquare("RANDOMSQUARE",i,j);
                }
                else if(questionSquaresLocations.contains(point)){
                    square = squarefactory.getSquare("QUESTIONSQUARE",i,j);
                }
                else{
                    square = squarefactory.getSquare("REGULARSQAURE",i,j);
                }
                square.setName(square.getType() + i + j);
                square.setPrefHeight(Consts.SQUARE_SIZE);
                square.setPrefWidth(Consts.SQUARE_SIZE);
                square.setBorder(new Border(new BorderStroke(Color.WHITE,
                        BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
                setTheme(square, theme, i, j,BlockingSquaresLocations,ForgettingSquaresLocations,RandomJumpSquaresLocations,questionSquaresLocations);
                chessBoard.add(square, i, j);
                squares.add(square);
            }
        }
        addPieces(); // add the king/queen and knight
    }

    // change the theme of the square
    private void setTheme(Square square, String theme, int i, int j, ArrayList<Point> BlockingSquaresLocations, ArrayList<Point> ForgettingSquaresLocations, ArrayList<Point> RandomJumpSquaresLocations, ArrayList<Point> QuestionSquaresLocations){
        Point currentPoint = new Point(i,j);

        switch (theme) {
            case "Coral" -> {
                Consts.color1 = Color.web("#b1e4b9");
                Consts.color2 = Color.web("#70a2a3");
            }
            case "Dusk" -> {
                Consts.color1 = Color.web("#cbb7ae");
                Consts.color2 = Color.web("#716677");
            }
            case "Wheat" -> {
                Consts.color1 = Color.web("#eaefce");
                Consts.color2 = Color.web("#bbbe65");
            }
            case "Marine" -> {
                Consts.color1 = Color.web("#9dacff");
                Consts.color2 = Color.web("#6f74d2");
            }
            case "Emerald" -> {
                Consts.color1 = Color.web("#adbd90");
                Consts.color2 = Color.web("#6e8f72");
            }
            case "Sandcastle" -> {
                Consts.color1 = Color.web("#e4c16f");
                Consts.color2 = Color.web("#b88b4a");
            }
        }
        for(Point p : BlockingSquaresLocations){
            if(currentPoint.x == p.x && currentPoint.y == p.y){
                square.setBackground(new Background(new BackgroundFill(Consts.colorBlockingSquare, CornerRadii.EMPTY, Insets.EMPTY)));
                return;
            }
        }

        for(Point p : QuestionSquaresLocations){
            if(currentPoint.x == p.x && currentPoint.y == p.y){
                Integer level = GamePageController.getLevelByThePostion(questionSquaresLocations, p);
                if(level==1){
                    square.setBackground(new Background(new BackgroundFill(Consts.colorEasyQuestionSquare, CornerRadii.EMPTY, Insets.EMPTY)));
                    return;
                }
                else if(level==2){
                    square.setBackground(new Background(new BackgroundFill(Consts.colorMediumQuestionSquare, CornerRadii.EMPTY, Insets.EMPTY)));
                    return;
                }
                else if(level==3){
                    square.setBackground(new Background(new BackgroundFill(Consts.colorHardQuestionSquare, CornerRadii.EMPTY, Insets.EMPTY)));
                    return;
                }
            }
        }
        if((i+j)%2==0){
            square.setBackground(new Background(new BackgroundFill(Consts.color1, CornerRadii.EMPTY, Insets.EMPTY)));

        }else{
            square.setBackground(new Background(new BackgroundFill(Consts.color2, CornerRadii.EMPTY, Insets.EMPTY)));
        }
    }

    /**
     * add piece to square
     * @param square
     * @param piece
     */
    private void addPiece(Square square, Piece piece){
        square.getChildren().add(piece);
        square.occupied = true;
    }

    private void addPieces(){
        PieceFactory piece_create = new PieceFactory();
        /**
         * Add pieces to their init location: pre-defined. (check out Const.java for enumerates).
         */
        for(Square square : squares){
            if(square.occupied) continue;
            // set horse init location
            if(square.x == Consts.KNIGHT_INIT_LOCATION_X && square.y == Consts.KNIGHT_INIT_LOCATION_Y){
                addPiece(square,piece_create.getPiece("Knight"));
            }
            // set queen init location
            else if((GamePageController.level==1 || GamePageController.level==2) && square.x == Consts.QUEEN_INIT_LOCATION_X && square.y == Consts.QUEEN_INIT_LOCATION_Y){
                 addPiece(square,piece_create.getPiece("Queen"));
            }
            // set king init location
            else if((GamePageController.level==3 || GamePageController.level==4) && square.x == Consts.KING_INIT_LOCATION_X && square.y == Consts.KING_INIT_LOCATION_Y){
                addPiece(square,piece_create.getPiece("King"));
            }

        }

    }

    /**
     * create num squares of forgetting square
     * @param number_of_square
     * @return
     */
    public ArrayList<Point> createForgettingSquare(int number_of_square){
        ArrayList<Point> ForgettingSquares = new ArrayList<Point>();
        while(ForgettingSquares.size() < number_of_square){
            Random rand = new Random();
            int randX = rand.nextInt(7); // random x value in range of (0,7)
            int randY = rand.nextInt(7);// random y value in range of (0,7)
            if((randX!=0 && randY!=0)&&!checkIfPointExist(ForgettingSquares, randX, randY)&&!checkIfPointExist(randomJumpSquaresLocations,randX,randY)&&!checkIfPointExist(blockingSquaresLocations,randX,randY)&&!checkIfPointExist(questionSquaresLocations, randX, randY)){
                Point specialSquarePoint = new Point(randX,randY);
                ForgettingSquares.add(specialSquarePoint);
            }
        }


        return ForgettingSquares;
    }

    /**
     * create num squares of random square
     * @param number_of_square
     * @return
     */
    public ArrayList<Point> createRandomJumpSquare(int number_of_square){
        ArrayList<Point> RandomJumpSquares = new ArrayList<Point>();
        while(RandomJumpSquares.size() <number_of_square){
            Random rand = new Random();
            int randX = rand.nextInt(7); // random x value in range of (0,7)
            int randY = rand.nextInt(7);// random y value in range of (0,7)
            if((randX!=0 && randY!=0)&&!checkIfPointExist(RandomJumpSquares, randX, randY)&&!checkIfPointExist(forgettingSquaresLocations,randX,randY)&&!checkIfPointExist(blockingSquaresLocations,randX,randY)&&!checkIfPointExist(questionSquaresLocations, randX, randY)){
                Point specialSquarePoint = new Point(randX,randY);
                RandomJumpSquares.add(specialSquarePoint);
            }
        }

        for(Point p : RandomJumpSquares){
        }
        return RandomJumpSquares;
    }

    /**
     * check if the point exist on the board
     * @param specialSquaresLocations
     * @param randX
     * @param randY
     * @return
     */
    private boolean checkIfPointExist(ArrayList<Point> specialSquaresLocations, int randX, int randY){
        for(Point p : specialSquaresLocations){
            if(p.x == randX && p.y == randY){
                return true;
            }
        }
        return false;
    }

    /**
     * create num squares of blocking square
     * @param number_of_square
     * @return
     */
    private ArrayList<Point> createBlockingSquaresLocations(int number_of_square){
        ArrayList<Point> specialSquaresLocations = new ArrayList<Point>();
        while(specialSquaresLocations.size() <number_of_square){
            Random rand = new Random();
            int randX = rand.nextInt(7); // random x value in range of (0,7)
            int randY = rand.nextInt(7);// random y value in range of (0,7)
            if((randX!=0 && randY!=0)&&!checkIfPointExist(specialSquaresLocations, randX, randY)&&!checkIfPointExist(randomJumpSquaresLocations,randX,randY)&&!checkIfPointExist(forgettingSquaresLocations,randX,randY)&&!checkIfPointExist(questionSquaresLocations, randX, randY)){
                Point specialSquarePoint = new Point(randX,randY);
                specialSquaresLocations.add(specialSquarePoint);
            }
        }

        return specialSquaresLocations;
    }

    /**
     * create num squares of questions square
     * @param number_of_square
     * @return
     */
    public ArrayList<Point> createQuestionSquare(int number_of_square){
        //create sorted arraylist according to the question level. for example: in array[0] - there is the location for an easy question
        ArrayList<Point> QuestionsSquares = new ArrayList<Point>();
        while(QuestionsSquares.size() <number_of_square){
            Random rand = new Random();
            int randX = rand.nextInt(7); // random x value in range of (0,7)
            int randY = rand.nextInt(7);// random y value in range of (0,7)
            if((randX!=0 && randY!=0)&&!checkIfPointExist(QuestionsSquares, randX, randY)&&!checkIfPointExist(forgettingSquaresLocations,randX,randY)&&!checkIfPointExist(blockingSquaresLocations,randX,randY)&&!checkIfPointExist(randomJumpSquaresLocations, randX, randY)){
                Point specialSquarePoint = new Point(randX,randY);
                QuestionsSquares.add(specialSquarePoint);
            }
        }

        return QuestionsSquares;
    }

    /**
     * rand new question square in unvisited place
     * @param x -of square
     * @param y -of square
     * @param visitedSqaure - all visited square
     */
    public void removeAndCreateQuestionSquare(int x,int y, ArrayList<Square> visitedSqaure){
        //create sorted arraylist according to the question level. for example: in array[0] - there is the location for an easy question

        Point ques_point = new Point(x,y);
        Square change_sqaure=null;
        Boolean success = false;

        while(!success){
            Random rand = new Random();
            int randX = rand.nextInt(7); // random x value in range of (0,7)
            int randY = rand.nextInt(7);// random y value in range of (0,7)
            boolean existinVisited = false;
            if (visitedSqaure.contains(new Square(randX,randY))){ //checking if the point is already grey - visited
                existinVisited=true;
            }
            if(!checkIfPointExist(questionSquaresLocations, randX, randY)&&!checkIfPointExist(forgettingSquaresLocations,randX,randY)&&!checkIfPointExist(blockingSquaresLocations,randX,randY)
                    &&!checkIfPointExist(randomJumpSquaresLocations, randX, randY) && !existinVisited){
                Point specialSquarePoint = new Point(randX,randY);
                Integer questionLevel = GamePageController.getLevelByThePostion(this.questionSquaresLocations, ques_point);
                int index = questionLevel -1; //we save the points in array list with corresponding to their level, and arrays start with 0 index
                this.questionSquaresLocations.set(index,specialSquarePoint);

                for(Square sqr : this.squares){
                    if (sqr.getX()==ques_point.getX()&&sqr.getY()==ques_point.getY()){
                        sqr.setType("Normal");
                        sqr.setName("Normal"+sqr.getX()+sqr.getY());
                    }
                    if(sqr.x==specialSquarePoint.x && sqr.y==specialSquarePoint.y)
                    {
                        sqr.setType("Question");
                        sqr.setName("Question" + specialSquarePoint.x + specialSquarePoint.y);
                        setTheme(sqr, theme, sqr.x, sqr.y,this.blockingSquaresLocations,this.forgettingSquaresLocations,this.randomJumpSquaresLocations,this.questionSquaresLocations);
                    }
                }

                success=true;
            }

        }
    }



}

