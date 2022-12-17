package com.example.knightmove.Model;

import com.example.knightmove.controllers.GamePageController;
import javafx.event.EventHandler;
import javafx.event.EventTarget;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class Game {

    public static Piece currentPiece;
    public static String currentPlayer;
    public static ChessBoard cb;
    private boolean game;

    public static int score;

    public Game(GridPane chessBoard, String theme){
        cb = new ChessBoard(chessBoard, theme);
        currentPiece = null;
        currentPlayer = "black";
        this.game = true;
        score=0;
        addEventHandlers(cb.chessBoard);
    }

    private void addEventHandlers(GridPane chessBoard){
        chessBoard.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                EventTarget target = event.getTarget();

                // Clicked on square
                if(target.toString().equals("Square")){
                    Square square = (Square) target;
                    if(square.occupied ){
                        Piece newPiece = (Piece) square.getChildren().get(0);
                        // Selecting a new piece
                        if(currentPiece == null){
                            currentPiece = newPiece;
//                            currentPiece.getAllPossibleMoves();
                            if(!currentPiece.getColor().equals(currentPlayer)){
                                currentPiece = null;
                                return;
                            }
                            selectPiece(game);
                        }
                        // Selecting other piece of same color || Killing a piece
                        else{
                            if(currentPiece.color.equals(newPiece.color)){
                                deselectPiece(false);
                                currentPiece = newPiece;
//                                currentPiece.getAllPossibleMoves();
                                selectPiece(game);
                            }
                            else{
                                killPiece(square);
                            }
                        }

                    }
                    // Dropping a piece on blank square
                    else{
                        dropPiece(square);
                    }
                }
                // Clicked on piece
                else{
                        // random movement
                        System.out.println("Instance of pressed piece: " + currentPiece);
                        Piece newPiece = (Piece) target;
                        Square square = (Square) newPiece.getParent();
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
                            if(currentPiece.color.equals(newPiece.color)){
                                deselectPiece(false);
                                currentPiece = newPiece;
                                selectPiece(game);
                            }
                            else{
                                killPiece(square);
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
        ArrayList<String> possibleMoves =currentPiece.getAllPossibleMoves();
        ArrayList<point> possibleMovesReformatted = new ArrayList<>();
        possibleMovesReformatted = reformatMoves(possibleMoves);
//        System.out.println("******************************");
//        System.out.println("possible moves string format:\n" + possibleMoves);
        System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
        System.out.println("possible moves before removing squares constraints\n" + possibleMovesReformatted);
        currentPiece.possibleMovesReformatted = possibleMovesReformatted;
        System.out.println("\n*********************************\n\n");
        System.out.println("Blocking squares (red) locations: \n" + this.cb.blockingSquaresLocations);
        ArrayList<point> blockingSquaresCoords = this.cb.blockingSquaresLocations;
        System.out.println("Those are the valid moves --> removing the blocking squares (the red squares):\n\n");

        for(point moveOption :  blockingSquaresCoords){
            if(currentPiece.possibleMovesReformatted.contains(moveOption)){
                currentPiece.possibleMovesReformatted.remove(moveOption);
                System.out.println("removed this point {location of blocking square}:");
                System.out.println(moveOption);
            }
        }

        System.out.println(currentPiece.possibleMovesReformatted); // moves without blocking squares

         ArrayList<String> possibleMovesFinalStringRep= reformatMovesBackToStringRepresentation(currentPiece.possibleMovesReformatted);
        currentPiece.possibleMoves=possibleMovesFinalStringRep;
        currentPiece.showAllPossibleMoves(true);







    }

    private ArrayList<point> reformatMoves(ArrayList<String> possibleMoves){
        ArrayList<point> possibleMovesReformatted = new ArrayList<>();

        for(String move : possibleMoves){
            int i = 0;
            for(Character c : move.toCharArray()){
                System.out.println(c + "  index: "+ i);
                i++;
            }
            char x = move.charAt(6);
            char y = move.charAt(7);
            System.out.println("x is: " + x);
            System.out.println("y is: " + y);



            possibleMovesReformatted.add(new point(Character.getNumericValue(x),Character.getNumericValue(y)));
        }
        return possibleMovesReformatted;
    }

    private ArrayList<String> reformatMovesBackToStringRepresentation(ArrayList<point> possibleMoves){
        ArrayList<String> possibleMovesReformattedString = new ArrayList<>();

        for(point move : possibleMoves){
            String moveStringRep = "Square" + move.x+move.y;
            System.out.println("converted point from :" + move +" To string: "+ moveStringRep);
            possibleMovesReformattedString.add(moveStringRep);
            }
        return possibleMovesReformattedString;
    }


    private void deselectPiece(boolean changePlayer){
        currentPiece.setEffect(null);
        currentPiece.showAllPossibleMoves(false);
        currentPiece = null;
    }

    private void dropPiece(Square square){
        if(!currentPiece.possibleMoves.contains(square.name)) return;

        Square initialSquare = (Square) currentPiece.getParent();
        square.getChildren().add(currentPiece);
        square.occupied = true;
        initialSquare.getChildren().removeAll();
        initialSquare.occupied = false;
        currentPiece.posX = square.x;
        currentPiece.posY = square.y;
        deselectPiece(true);
        GamePageController.createQuestionPopUp();
    }

    private void killPiece(Square square){
        if(!currentPiece.possibleMoves.contains(square.name)) return;

        Piece killedPiece = (Piece) square.getChildren().get(0);
        if(killedPiece.type.equals("King")) this.game = false;


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