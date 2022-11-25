package com.example.knightmove.Model;

import java.util.ArrayList;

public class Knight extends Piece{
    String goal = "get max points by moving on squares and answering Questions. can't get back to square already visited";
    ArrayList<ArrayList<Integer>> visitedSquares; // squares the knight already visited at.



    public Knight(String color, int posX, int posY) {
        super(color, posX, posY);
        this.type = "Knight";
        setImage();
    }

    @Override
    public void getAllPossibleMoves() { // possible moves should take count of visitedSquares arrayList & players level {will be discessed by the course team later}
        int x = this.posX;
        int y = this.posY;
        ArrayList<String> moves = new ArrayList<>();
        this.possibleMoves = new ArrayList<>();

        moves.add("Square" + (x+2) + (y+1));
        moves.add("Square" + (x+2) + (y-1));
        moves.add("Square" + (x+1) + (y+2));
        moves.add("Square" + (x-1) + (y+2));
        moves.add("Square" + (x-2) + (y+1));
        moves.add("Square" + (x-2) + (y-1));
        moves.add("Square" + (x+1) + (y-2));
        moves.add("Square" + (x-1) + (y-2));


        for(String move : moves){
            if(getSquareByName(move) != null){
                if(getSquareByName(move).occupied && getPieceByName(move).getColor().equals(Game.currentPlayer)) continue;

                possibleMoves.add(move);

            }
        }


    }

}