/*package com.example.knightmove.Model;

import com.sun.javafx.UnmodifiableArrayList;
import javafx.event.EventHandler;
import javafx.event.EventTarget;
import javafx.geometry.Insets;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class Game   {

    public static Piece currentPiece;
    public static String currentPlayer;
    public static ChessBoard cb;
    private boolean game;
    public static int level = 2 ;


    public static int score;

    ArrayList<Square> visitedSquares; // squares they already visited at.

    public ArrayList<Square> getVisitedSquares() {
        return visitedSquares;
    }

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

    public void addToVisitedSquares(Square sq){
        if(sq != null){
            this.visitedSquares.add(sq);
        }
    }

    public Game(GridPane chessBoard, String theme){
        cb = new ChessBoard(chessBoard, theme,0,0,3,0);
        currentPiece = null;
        currentPlayer = "black";
        this.game = true;
        score=0;
        visitedSquares = new ArrayList<>();
        addEventHandlers(cb.chessBoard);
    }

    private void addEventHandlers(GridPane chessBoard){
        chessBoard.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                EventTarget target = event.getTarget();

                //Clicked on the Knight
                if(target.toString().equals("Knight")){
                    Piece newPiece = (Piece) target;
                    Square square = (Square) newPiece.getParent();
                    square.setBackground(new Background(new BackgroundFill(Consts.colorVisitedSquare, CornerRadii.EMPTY, Insets.EMPTY)));
                    addToVisitedSquares(square);
                    System.out.println("Knight possible moves:\n"+newPiece.possibleMoves);
                     // Selecting a new piece
                    if(currentPiece == null){
                        currentPiece = newPiece;
                        if(!currentPiece.getColor().equals(currentPlayer)) {
                            currentPiece = null;
                            return;
                        }
                        selectPiece(game);
                    }
                    // Selecting other piece of same color || Killing a piece
                    else{
                        if(currentPiece.color.equals(newPiece.color)){
                            System.out.println("inside internal if of knight");
                            deselectPiece(false);
                            currentPiece = newPiece;
                            selectPiece(game);
                        }
                        else{
                            killPiece(square);
                        }
                    }

                }
//                Square dana = (Square) target;
//                System.out.println("THE TARGET CLICK IS "+ dana.getX() + dana.getY());
                if(target.toString().equals("Question")){
                    System.out.println("HIIIIII!! QUESTION SQUARE");
                }
                //Clicked on the queen - DELETED!
                // Clicked on square
                if(target.toString().equals("Square")){
                    Square square = (Square) target;
                    if(square.toString().equals("Question")){
                        System.out.println("HIIIIII!! QUESTION SQUARE");
                    }
                    if(square.occupied ){
                        Piece newPiece = (Piece) square.getChildren().get(0);
                        // Selecting a new piece
                        if(currentPiece == null){
                            currentPiece = newPiece;
                            if(!currentPiece.getColor().equals(currentPlayer)){
                                currentPiece = null;
                                return;
                            }
                            selectPiece(game);
                        }
                        // Selecting other piece of same color || Killing a piece
                        else{
//                            System.out.println("inside else of square");
                            if(currentPiece.color.equals(newPiece.color)){
                                System.out.println("inside internal if of square");
                                deselectPiece(false);
                                currentPiece = newPiece;
                                selectPiece(game);
                            }
                            else{
                                System.out.println("inside internal else of square");
                                killPiece(square);
                            }
                        }

                    }
                    // Dropping a piece on blank square
                    else{
                        //removing the blockingSquares from possibleMoves
                        ArrayList<Point> blockingSquares = new ArrayList<Point>(cb.blockingSquaresLocations);
                        //removing the blockingSquares from possibleMoves
                        for(Point p : blockingSquares){
                            String squareString = "Square"+p.getX()+p.getY();
                            if(currentPiece.possibleMoves.contains(squareString)){
                                currentPiece.possibleMoves.remove(squareString);
                            }
                        }
                        System.out.println("currentPiece moves after drop:\n " + currentPiece.possibleMoves);
                        if(currentPiece.toString().equals("Knight")){
                            square.setBackground(new Background(new BackgroundFill(Consts.colorVisitedSquare, CornerRadii.EMPTY, Insets.EMPTY)));
                            addToVisitedSquares(square);
                        }
                        dropPiece(square);
                        for(Square s : getVisitedSquares()){
                            System.out.println("Visited Square:\n " + s.getX()+","+s.getY());
                        }

                        /**
                         * The knight clicked on empty square, afterwards move the queen


                        int queenNextPositionX = -1;
                        int queenNextPositionY = -1;
                        int[] knightPositions = new int[2];
                        knightPositions[0] = square.getX();
                        knightPositions[1] = square.getY();
                        Piece foundQueen = null;
                        for(Square sq : cb.getSquares()) {

                            if(sq.getChildren().size() > 0){
                                String pieceName = String.valueOf(sq.getChildren().get(0));
                                if(pieceName.equals("Queen")){
                                    Piece queen = (Piece) sq.getChildren().get(0);
                                    Square queenSquare = (Square) queen.getParent();
                                    Queen newQueen = (Queen) queen;
                                    currentPiece = newQueen;
                                    foundQueen = newQueen;
                                    ArrayList<String> possibleMoves = newQueen.getAllPossibleMoves();
                                    ArrayList<ArrayList<Integer>> possibleMovesInArrayOfTwo = newQueen.convertMovesToIntArrays(newQueen.getAllPossibleMoves());
                                    System.out.println("Current level is: " + Game.level + "*******");
                                    if(Game.level == 1 || Game.level == 2){
                                        System.out.println("Performing random strategy**********");
                                        ArrayList<Integer> movesSelector = newQueen.selectQueenMovements("random", possibleMovesInArrayOfTwo, knightPositions);
                                        killPiece(queenSquare);
                                        //Doing Random/Smart movement (with Manhattan Distance) for Queen
                                        queenNextPositionX = movesSelector.get(0);
                                        queenNextPositionY = movesSelector.get(1);
                                    }
                                    else if(Game.level == 3 || Game.level == 4){
                                        System.out.println("Performing smart strategy**********");
                                        ArrayList<Integer> movesSelector = newQueen.selectQueenMovements("smart", possibleMovesInArrayOfTwo, knightPositions);
                                        killPiece(queenSquare);
                                        //Doing Random/Smart movement (with Manhattan Distance) for Queen
                                        queenNextPositionX = movesSelector.get(0);
                                        queenNextPositionY = movesSelector.get(1);

                                    }




                                }
                            }

                        }
                        if(foundQueen!= null){
                            currentPiece = foundQueen;
                        }
                        for(Square sq : cb.getSquares()){
                            if(sq.getX() == queenNextPositionX && sq.getY() == queenNextPositionY && foundQueen != null){
                                currentPiece = foundQueen;
                                dropPiece(sq);
                            }
                        }

                    }
                }
            }
        });
    }

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

    private void deselectPiece(boolean changePlayer){
        currentPiece.setEffect(null);
        currentPiece.showAllPossibleMoves(false);
        currentPiece = null;
    }

    private void dropPiece(Square square){
        if(!currentPiece.possibleMoves.contains(square.name)) return;
        System.out.println("move to square " + square.name);
        Square initialSquare = (Square) currentPiece.getParent();
        square.getChildren().add(currentPiece);
        square.occupied = true;
        initialSquare.getChildren().removeAll();
        initialSquare.occupied = false;
        currentPiece.posX = square.x;
        currentPiece.posY = square.y;
        deselectPiece(true);
//        GamePageController.createQuestionPopUp();
    }

    private void killPiece(Square square){
        if(!currentPiece.possibleMoves.contains(square.name)) return;

        Piece killedPiece = (Piece) square.getChildren().get(0);
        if(killedPiece.type.equals("King")) this.game = false;
        System.out.println("move from square " + square.name);
        Square initialSquare = (Square) currentPiece.getParent();
        square.getChildren().remove(0);
        square.getChildren().add(currentPiece);
        square.occupied = true;
        initialSquare.getChildren().removeAll();
        initialSquare.occupied = false;
        currentPiece.posX = square.x;
        currentPiece.posY = square.y;
        deselectPiece(true);
    }

}
*/