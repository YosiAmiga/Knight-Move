package com.example.knightmove.Model;

import com.example.knightmove.Controllers.GamePageController;

import java.util.ArrayList;

public class Knight extends Piece{
    String goal = "get max points by moving on squares and answering Questions. can't get back to square already visited";
    Point currentLocation = new Point(Consts.KNIGHT_INIT_LOCATION_X,Consts.KNIGHT_INIT_LOCATION_Y);
    ArrayList<Point> visitedSquares; // squares the knight already visited at.



    public Knight(String color, int posX, int posY) {
        super(color, posX, posY);
        this.type = "Knight";
        setImage();
    }

    @Override
    public ArrayList<String> getAllPossibleMoves() { // possible moves should take count of visitedSquares arrayList & players level {will be discessed by the course team later}
        int x = this.posX;
        int y = this.posY;
        Square s = new Square();
        for (Square ss: GamePageController.cb.getSquares()){
            if(x==ss.getX()&&y==ss.getY()){
                s=ss;
            }
        }

        // Define the x and y offsets for the possible knight moves
        int[] xOffsets = {2, 2, 1, 1, -1, -1, -2, -2};
        int[] yOffsets = {1, -1, 2, -2, 2, -2, 1, -1};
        ArrayList<String> moves = new ArrayList<>();
        this.possibleMoves = new ArrayList<>();

        for (int i = 0; i < xOffsets.length; i++) {
            int newX = x + xOffsets[i];
            int newY = y + yOffsets[i];
            newX = (newX + 8) % 8;
            newY = (newY + 8) % 8;
            Square pos_sqr = new Square();
            // Add the possible move to the list
            for (Square ss: GamePageController.cb.getSquares()){
                if(newX==ss.getX()&&newY==ss.getY()){
                    pos_sqr=ss;
                }
            }
            moves.add(pos_sqr.getType() + String.valueOf(newX) + String.valueOf(newY));
        }
        for(String move : moves){
            if(getSquareByName(move) != null){
                if(getSquareByName(move).occupied) continue;
                possibleMoves.add(move);
            }
        }
        return this.possibleMoves;
    }
}