package com.example.knightmove.Model;

import javafx.scene.image.Image;

import java.util.ArrayList;

public class King extends Piece{
    String gameGoal = "Hit the horse";
    float speed; // speed should be changeable

    public King(String color, int posX, int posY,float speed) {
        super(color, posX, posY);
        this.type = "King";
        this.speed = Consts.KING_INIT_SPEED; // set initial speed
        setImage();
    }

    @Override
    public void getAllPossibleMoves() {
        /**
         * King can move by the following logic:
         *
         */

        float currentSpeed = this.speed; // should take count of the speed changes in possible moves
        int x = this.posX;
        int y = this.posY;
        ArrayList<String> moves = new ArrayList<>();
        this.possibleMoves = new ArrayList<>();

        // Possible moves of King
        moves.add("Square" + (x) + (y-1));
        moves.add("Square" + (x+1) + (y-1));
        moves.add("Square" + (x+1) + (y));
        moves.add("Square" + (x+1) + (y+1));
        moves.add("Square" + (x) + (y+1));
        moves.add("Square" + (x-1) + (y+1));
        moves.add("Square" + (x-1) + (y));
        moves.add("Square" + (x-1) + (y-1));


        for(String move : moves){
            if(getSquareByName(move) != null){
                if(getSquareByName(move).occupied && getPieceByName(move).getColor().equals(Game.currentPlayer)) continue;

                possibleMoves.add(move);

            }
        }
    }
    public void showAllPossibleMoves() {}; // need to implement

    public void setImage() {
        String theme1Url = getClass().getResource("/picture/King.png").toExternalForm();
        this.setPiece(new Image(theme1Url));
    }
}