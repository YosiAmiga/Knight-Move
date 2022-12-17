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

                //Clicked on the Knight
                if(target.toString().equals("Knight")){
                    Piece newPiece = (Piece) target;
                    Square square = (Square) newPiece.getParent();
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
                //Clicked on the queen - IN COMMENT TO PREVENT CLICKING
//                if (target.toString().equals("Queen")){
//                    Piece newPiece = (Piece) target;
//                    currentPiece = newPiece;
//                    Square square = (Square) newPiece.getParent();
//                    // Selecting a new piece
//                    if(currentPiece == null){
//                        System.out.println("inside if");
//                        currentPiece = newPiece;
//                        if(!currentPiece.getColor().equals(currentPlayer)){
//                            currentPiece = null;
//                            return;
//                        }
//                        selectPiece(game);
//                    }
//                    // Selecting other piece of same color || Killing a piece
//                    else{
//                        System.out.println("inside else");
//                        if(currentPiece.color.equals(newPiece.color)){
//                            deselectPiece(false);
//                            currentPiece = newPiece;
//                            selectPiece(game);
//                        }
//                        else{
//                            System.out.println("inside internal else");
//                            killPiece(square);
//                        }
//                    }
//                }
                // Clicked on square
                if(target.toString().equals("Square")){
                    Square square = (Square) target;
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
                            System.out.println("inside else of square");
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
                        System.out.println("Dropping a piece on blank square");
                        dropPiece(square);

                        //knight clicked on empty square, afterwards move the queen
                        int XRandom = -1;
                        int YRandom = -1;
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
//                                    System.out.println("newQueen position:" + newQueen.getPosX() + " " + newQueen.getPosY());
//                                    System.out.println("newQueen possible moves after knight:\n"+newQueen.getAllPossibleMoves());
                                    killPiece(queenSquare);
                                    String randomMove = possibleMoves.get(new java.util.Random().nextInt(possibleMoves.size()));
                                    Character xChar = randomMove.charAt(6);
                                    XRandom = Integer.parseInt(String.valueOf(xChar));
                                    Character yChar = randomMove.charAt(7);
                                    YRandom = Integer.parseInt(String.valueOf(yChar));
                                    System.out.println("randomMove "+randomMove);

                                }
                            }

                        }
                        if(foundQueen!= null){
                            currentPiece = foundQueen;
                        }
                        for(Square sq : cb.getSquares()){
                            if(sq.getX() == XRandom && sq.getY() == YRandom && foundQueen != null){
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