package com.example.knightmove.Model;

import com.example.knightmove.controllers.GamePageController;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.layout.GridPane;
import java.util.ArrayList;
import java.lang.Math;
import java.util.Random;


public class ChessBoard {

    public GridPane chessBoard;
    String theme;
    public ArrayList<Square> squares = new ArrayList<>();

    public ArrayList<Square> getSquares() {
        return squares;
    }
    public ArrayList<point> forgettingSquaresLocations = new ArrayList<>();
    public ArrayList<point> randomJumpSquaresLocations = new ArrayList<>();
    public ArrayList<point> blockingSquaresLocations = new ArrayList<>();
    public ArrayList<point> questionSquaresLocations = new ArrayList<>();

    public ArrayList<point> occupiedSquaresLocations = new ArrayList<>();
    public ChessBoard(GridPane chessBoard, String theme,int num_block,int num_forget,int num_rand,int num_ques ){
        this.chessBoard = chessBoard;
        this.theme = theme;
        makeBoard(this.chessBoard, theme,num_block,num_forget,num_rand,num_ques);
    }

    public ArrayList<point> getQuestionSquaresLocations() {
        return questionSquaresLocations;
    }

    public void setQuestionSquaresLocations(ArrayList<point> questionSquaresLocations) {
        this.questionSquaresLocations = questionSquaresLocations;
    }

    private void makeBoard(GridPane chessBoard, String theme, int num_block,int num_forget,int num_rand,int num_ques){
        /**
         * Algo:
         * 1. Create random locations of blocking squares
         * 2. For each square set it's theme based on its type.
         *
         */
        chessBoard.getChildren().clear();
        ArrayList<point> BlockingSquaresLocations=createBlockingSquaresLocations(num_block);
        this.blockingSquaresLocations = BlockingSquaresLocations;

        ArrayList<point> ForgettingSquaresLocations=createForgettingSquare(num_forget);
        this.forgettingSquaresLocations = ForgettingSquaresLocations;

        ArrayList<point>  RandomJumpSquaresLocations = createRandomJumpSquare(num_rand);
        this.randomJumpSquaresLocations = RandomJumpSquaresLocations;

        ArrayList<point>  questionSquaresLocations = createQuestionSquare(num_ques);
        this.questionSquaresLocations = questionSquaresLocations;

        SquareFactory squarefactory = new SquareFactory();
        for(int i=0; i<Consts.SQUARES_IN_ROW; i++){
            for(int j=0; j<Consts.SQUARES_IN_COLUMN; j++){
                Square square;
                point point = new point(i,j);
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
                // NOTE: BoardStroke args (colurOfLinesBetweenSquares, typeOfLineBetweenSquares - could be dotted or full line)
                square.setBorder(new Border(new BorderStroke(Color.BLACK,
                        BorderStrokeStyle.DOTTED, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
                setTheme(square, theme, i, j,BlockingSquaresLocations,ForgettingSquaresLocations,RandomJumpSquaresLocations,questionSquaresLocations);
                chessBoard.add(square, i, j, 1, 1);
                squares.add(square);
            }
        }
        addPieces();
    }

    private void setTheme(Square square, String theme, int i, int j,ArrayList<point> BlockingSquaresLocations,ArrayList<point> ForgettingSquaresLocations,ArrayList<point> RandomJumpSquaresLocations, ArrayList<point> QuestionSquaresLocations){
        point currentPoint = new point(i,j);
//        Color color1 = Color.web("#ffffff00");
//        Color color2 = Color.web("#ffffff00");
//        Color colorBlockingSquare = Color.web("#FF0000");
//        Color colorRandomJumpSquare = Color.web("#9ACD32");
//        Color colorForgettingSquare = Color.web("#9dacff");

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
        for(point p : BlockingSquaresLocations){
            if(currentPoint.x == p.x && currentPoint.y == p.y){
                square.setBackground(new Background(new BackgroundFill(Consts.colorBlockingSquare, CornerRadii.EMPTY, Insets.EMPTY)));
                return;
            }
        }

        for(point p : ForgettingSquaresLocations){
            if(currentPoint.x == p.x && currentPoint.y == p.y){
                square.setBackground(new Background(new BackgroundFill(Consts.colorForgettingSquare, CornerRadii.EMPTY, Insets.EMPTY)));
                return;
            }
        }
        for(point p : RandomJumpSquaresLocations){
            if(currentPoint.x == p.x && currentPoint.y == p.y){
                square.setBackground(new Background(new BackgroundFill(Consts.colorRandomJumpSquare, CornerRadii.EMPTY, Insets.EMPTY)));
                return;
            }
        }
        for(point p : QuestionSquaresLocations){
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

    private void addPiece(Square square, Piece piece){
        square.getChildren().add(piece);
        square.occupied = true;
    }

    private void addPieces(){
        /**
         * Add pieces to their init location: pre-defined. (check out Const.java for enumerates).
         */
        for(Square square : squares){
            if(square.occupied) continue;
            // set horse init location
            if(square.x == Consts.KNIGHT_INIT_LOCATION_X && square.y == Consts.KNIGHT_INIT_LOCATION_Y){
                addPiece(square, new Knight("black", square.x, square.y));
            }
            // set queen init location
            else if((GamePageController.level==1 || GamePageController.level==2) && square.x == Consts.QUEEN_INIT_LOCATION_X && square.y == Consts.QUEEN_INIT_LOCATION_Y){
                 addPiece(square, new Queen("black", square.x, square.y));
            }
            // set king init location
            else if((GamePageController.level==3 || GamePageController.level==4) && square.x == Consts.KING_INIT_LOCATION_X && square.y == Consts.KING_INIT_LOCATION_Y){
                addPiece(square, new King("black", square.x, square.y,Consts.KING_INIT_SPEED));
            }

        }

    }
    private void specialSquareMessege(point currentPosition , ArrayList<point> specialSquaresLocations){
        for(point p : specialSquaresLocations){
            if(currentPosition.equals(p)){
                Alert al = new Alert(Alert.AlertType.ERROR);
                al.setContentText("GAME ENDED!");
                al.setHeaderText("GAME ENDED!");
                al.setTitle("GAME ENDED!");
                al.setResizable(false);
                al.showAndWait();;
            }
        }

    }

    public ArrayList<point> createForgettingSquare(int number_of_square){
        ArrayList<point> ForgettingSquares = new ArrayList<point>();
        while(ForgettingSquares.size() < number_of_square){
            Random rand = new Random();
            int randX = rand.nextInt(7); // random x value in range of (0,7)
            int randY = rand.nextInt(7);// random y value in range of (0,7)
            if((randX!=0 && randY!=0)&&!checkIfPointExist(ForgettingSquares, randX, randY)&&!checkIfPointExist(randomJumpSquaresLocations,randX,randY)&&!checkIfPointExist(blockingSquaresLocations,randX,randY)&&!checkIfPointExist(questionSquaresLocations, randX, randY)){
                point specialSquarePoint = new point(randX,randY);
                ForgettingSquares.add(specialSquarePoint);
            }
//            System.out.println("current list length: "+ ForgettingSquares.size());
        }

        for(point p : ForgettingSquares){
            System.out.println("forgetting squares points: " + p.x +", "+ p.y);
        }
        return ForgettingSquares;
    }

    public ArrayList<point> createRandomJumpSquare(int number_of_square){
        ArrayList<point> RandomJumpSquares = new ArrayList<point>();
        while(RandomJumpSquares.size() <number_of_square){
            Random rand = new Random();
            int randX = rand.nextInt(7); // random x value in range of (0,7)
            int randY = rand.nextInt(7);// random y value in range of (0,7)
            if((randX!=0 && randY!=0)&&!checkIfPointExist(RandomJumpSquares, randX, randY)&&!checkIfPointExist(forgettingSquaresLocations,randX,randY)&&!checkIfPointExist(blockingSquaresLocations,randX,randY)&&!checkIfPointExist(questionSquaresLocations, randX, randY)){
                point specialSquarePoint = new point(randX,randY);
                RandomJumpSquares.add(specialSquarePoint);
            }
//            System.out.println("random list length: "+ RandomJumpSquares.size());
        }

        for(point p : RandomJumpSquares){
            System.out.println("random jump square points: " + p.x +", "+ p.y);
        }
        return RandomJumpSquares;
    }


    private boolean checkIfPointExist(ArrayList<point> specialSquaresLocations,int randX,int randY){
        for(point p : specialSquaresLocations){
            if(p.x == randX && p.y == randY){
                return true;
            }
        }
        return false;
    }

    private ArrayList<point> createBlockingSquaresLocations(int number_of_square){
        ArrayList<point> specialSquaresLocations = new ArrayList<point>();
        while(specialSquaresLocations.size() <number_of_square){
            Random rand = new Random();
            int randX = rand.nextInt(7); // random x value in range of (0,7)
            int randY = rand.nextInt(7);// random y value in range of (0,7)
            if((randX!=0 && randY!=0)&&!checkIfPointExist(specialSquaresLocations, randX, randY)&&!checkIfPointExist(randomJumpSquaresLocations,randX,randY)&&!checkIfPointExist(forgettingSquaresLocations,randX,randY)&&!checkIfPointExist(questionSquaresLocations, randX, randY)){
                point specialSquarePoint = new point(randX,randY);
                specialSquaresLocations.add(specialSquarePoint);
            }
//            System.out.println("current list length: "+ specialSquaresLocations.size());
        }

        for(point p : specialSquaresLocations){
            System.out.println("blocking square points: " + p.x +", "+ p.y);
        }
        return specialSquaresLocations;
    }

    public ArrayList<point> createQuestionSquare(int number_of_square){
        //create sorted arraylist according to the question level. for example: in array[0] - there is the location for an easy question
        ArrayList<point> QuestionsSquares = new ArrayList<point>();
        while(QuestionsSquares.size() <number_of_square){
            Random rand = new Random();
            int randX = rand.nextInt(7); // random x value in range of (0,7)
            int randY = rand.nextInt(7);// random y value in range of (0,7)
            if((randX!=0 && randY!=0)&&!checkIfPointExist(QuestionsSquares, randX, randY)&&!checkIfPointExist(forgettingSquaresLocations,randX,randY)&&!checkIfPointExist(blockingSquaresLocations,randX,randY)&&!checkIfPointExist(randomJumpSquaresLocations, randX, randY)){
                point specialSquarePoint = new point(randX,randY);
                QuestionsSquares.add(specialSquarePoint);
            }
        }

        for(point p : QuestionsSquares){
            System.out.println("Question  square points: " + p.x +", "+ p.y);
        }
        return QuestionsSquares;
    }
    public void removeAndCreateQuestionSquare(int x,int y, ArrayList<Square> visitedSqaure){
        //create sorted arraylist according to the question level. for example: in array[0] - there is the location for an easy question
        Boolean succes = false;
        point ques_point = null;
        Square change_sqaure=null;
        for(point p : this.questionSquaresLocations){
            if(p.getX() == x && p.getY()==y)
            {
                ques_point = p;
            }
        }
        Integer questionLevel = GamePageController.getLevelByThePostion(questionSquaresLocations, ques_point);
        Integer index = questionLevel - 1; //we save the points in array list with corresponding to their level, and arrays start with 0 index

        while(!succes){
            Random rand = new Random();
            int randX = rand.nextInt(7); // random x value in range of (0,7)
            int randY = rand.nextInt(7);// random y value in range of (0,7)
            boolean existinVisited = false;
            for(Square sqr :visitedSqaure)
            {
                if(sqr.x==x && sqr.y==y)
                {
                    existinVisited=true;
                }
            }
            if(!checkIfPointExist(questionSquaresLocations, randX, randY)&&!checkIfPointExist(forgettingSquaresLocations,randX,randY)&&!checkIfPointExist(blockingSquaresLocations,randX,randY)
                    &&!checkIfPointExist(randomJumpSquaresLocations, randX, randY) && !existinVisited){
                point specialSquarePoint = new point(randX,randY);
                this.questionSquaresLocations.set(index,specialSquarePoint);

                System.out.println("new Question square points: " + specialSquarePoint.x +", "+ specialSquarePoint.y);
                Square remove_sqr=null;
                for(Square sqr : this.squares){
                    if(sqr.x==specialSquarePoint.x && sqr.y==specialSquarePoint.y)
                    {
                        remove_sqr = sqr;
                        change_sqaure = new QuestionSquare(sqr.x,sqr.y);
                        change_sqaure.setName("Question" + specialSquarePoint.x + specialSquarePoint.y);
                        setTheme(sqr, theme, sqr.x, sqr.y,this.blockingSquaresLocations,this.forgettingSquaresLocations,this.randomJumpSquaresLocations,questionSquaresLocations);
                    }
                }
                this.squares.add(change_sqaure);
                this.squares.remove(remove_sqr);
                succes = true;
            }
        }
    }


}

