package com.example.knightmove.Model;

import com.example.knightmove.controllers.GamePageController;
import javafx.event.EventHandler;
import javafx.event.EventTarget;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

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

                System.out.println("I clicked " + target.toString());
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
                        System.out.println("inside else of knight");
                        if(currentPiece.color.equals(newPiece.color)){
                            System.out.println("inside internal if of knight");
                            deselectPiece(false);
                            currentPiece = newPiece;
                            selectPiece(game);
                        }
                        else{
                            System.out.println("inside internal else of knight");
                            killPiece(square);
                        }
                    }

                }
                //Clicked on the queen
                if (target.toString().equals("Queen")){
                    Piece newPiece = (Piece) target;
                    currentPiece = newPiece;
                    Square square = (Square) newPiece.getParent();
                    System.out.println("Queen possible moves:\n"+newPiece.possibleMoves);
                    //get a random string from and array of strings
//                    String randomMove = newPiece.possibleMoves.get(new java.util.Random().nextInt(newPiece.possibleMoves.size()));
//                    Character xChar = randomMove.charAt(6);
//                    int XRandom = Integer.parseInt(String.valueOf(xChar));
//                    Character yChar = randomMove.charAt(7);
//                    int YRandom = Integer.parseInt(String.valueOf(yChar));
//                    square.setX(XRandom);
//                    square.setY(YRandom);
//                    System.out.println("randomMove "+randomMove);
                    // Selecting a new piece
                    if(currentPiece == null){
                        System.out.println("inside if");
                        currentPiece = newPiece;
                        if(!currentPiece.getColor().equals(currentPlayer)){
                            currentPiece = null;
                            return;
                        }
                        selectPiece(game);
                    }
                    // Selecting other piece of same color || Killing a piece
                    else{
                        System.out.println("inside else");
                        if(currentPiece.color.equals(newPiece.color)){
                            deselectPiece(false);
                            currentPiece = newPiece;
                            selectPiece(game);
                        }
                        else{
                            System.out.println("inside internal else");
                            killPiece(square);
                        }
                    }
                }
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

                        for(Square sq : cb.getSquares()) {
                            if(sq.getChildren().size() > 0){
//                                System.out.println("square.getChildren() is :\n"+sq .getChildren());
//                                System.out.println("sq.getChildren().get(0) is :\n"+sq.getChildren().get(0).getClass());
                                String pieceName = String.valueOf(sq.getChildren().get(0));
                                if(pieceName.equals("Knight")){
//                                    System.out.println("square contains Knight!");
                                }
                                if(pieceName.equals("Queen")){
                                    Piece newPiece = (Piece) sq.getChildren().get(0);
                                    Queen queen = (Queen) newPiece;
//                                    currentPiece = newPiece;
                                    System.out.println("Queen:" + queen);

                                    Square queenSquare = (Square) newPiece.getParent();
//                                    Queen newPiece = new Queen("black",sq.getX(),sq.getY());
                                    System.out.println("Queen position:" + queen.getX() + " " + queen.getY());
//                                    System.out.println("Queen possible moves:\n"+newPiece.possibleMoves);
                                    System.out.println("Queen square position is :\n"+queenSquare.getX()+"\n"+queenSquare.getY());

                                }
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