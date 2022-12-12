package com.example.knightmove.Model;

import javafx.scene.image.Image;

import java.util.ArrayList;

public class Knight extends Piece{
    String goal = "get max points by moving on squares and answering Questions. can't get back to square already visited";
    point currentLocation = new point(Consts.KNIGHT_INIT_LOCATION_X,Consts.KNIGHT_INIT_LOCATION_Y);
    ArrayList<point> visitedSquares; // squares the knight already visited at.



    public Knight(String color, int posX, int posY) {
        super(color, posX, posY);
        this.type = "Knight";
        setImage();
    }

    @Override
    public void getAllPossibleMoves() { // possible moves should take count of visitedSquares arrayList & players level {will be discessed by the course team later}
        int x = this.posX;
        int y = this.posY;
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
            // Add the possible move to the list
            moves.add("Square" + String.valueOf(newX) + String.valueOf(newY));
        }

        for(String move : moves){
            if(getSquareByName(move) != null){
                if(getSquareByName(move).occupied && getPieceByName(move).getColor().equals(Game.currentPlayer)) continue;
                possibleMoves.add(move);
            }
        }
//        if(y==0 && (x-2)>=0 && (x+2)<=7) // 2<=X<=5 in the first row
//        {
//            moves.add("Square" + (x+1) + "6");
//            moves.add("Square" + (x-1) + "6");
//        }
//        if(y==7 && (x-2)>=0 && (x+2)<=7) // 2<=X<=5 in the last row
//        {
//            moves.add("Square" + (x+1) + "1");
//            moves.add("Square" + (x-1) + "1");
//        }
//        if(y==1 && (x-2)>=0 && (x+2)<=7) // 2<=X<=5 in the second row
//        {
//            moves.add("Square" + (x+1) + "7");
//            moves.add("Square" + (x-1) + "7");
//        }
//        if(y==6 && (x-2)>=0 && (x+2)<=7) // 2<=X<=5 in the sixth row
//        {
//            moves.add("Square" + (x+1) + "0");
//            moves.add("Square" + (x-1) + "0");
//        }
//        if(x==0 && (y-2)>=0 && (y+2)<=7) // 2<=y<=5 in the first column
//        {
//            moves.add("Square" + "6" + (y+1));
//            moves.add("Square" + "6" + (y-1));
//        }
//        if(x==7 && (y-2)>=0 && (y+2)<=7) // 2<=y<=5 in the last column
//        {
//            moves.add("Square" + "1" + (y+1));
//            moves.add("Square" + "1" + (y-1));
//        }
//        if(x==6 && (y-2)>=0 && (y+2)<=7) // 2<=y<=5 in the sixth column
//        {
//            moves.add("Square" + "0" + (y+1));
//            moves.add("Square" + "0" +(y-1));
//        }
//        if(x==1 && (y-2)>=0 && (y+2)<=7) // 2<=y<=5 in the second column
//        {
//            moves.add("Square" + "7" + (y+1));
//            moves.add("Square" + "7" + (y-1));
//        }
//        if(x==0 && y==0)
//        {
//            moves.add("Square" + "6" + "6");
//            moves.add("Square" + (Consts.SQUARES_IN_ROW-1) + "6");
//            moves.add("Square" + "6" + "0");
//            moves.add("Square" + (Consts.SQUARES_IN_ROW-1) + "2");
//            moves.add("Square" + "2" + (Consts.SQUARES_IN_COLUMN-1));
//            moves.add("Square" + "1" + "6");
//        }
//        if(x==7 && y==0)
//        {
//            moves.add("Square" + "5" + "5");
//            moves.add("Square" + "6" + "6");
//            moves.add("Square" + "0" + "6");
//            moves.add("Square" + "1" + "1");
//            moves.add("Square" + "1" + "7");
//            moves.add("Square" + "0" + "2");
//        }
//        if(x==0 && y==7)
//        {
//            moves.add("Square" + "7" + "5");
//            moves.add("Square" + "0" + "2");
//            moves.add("Square" + "7" + "1");
//            moves.add("Square" + "1" + "1");
//            moves.add("Square" + "6" + "0");
//            moves.add("Square" + "6" + "6");
//        }
//        if(x==7 && y==7)
//        {
//            moves.add("Square" + "0" + "5");
//            moves.add("Square" + "1" + "6");
//            moves.add("Square" + "1" + "0");
//            moves.add("Square" + "5" + "0");
//            moves.add("Square" + "6" + "1");
//            moves.add("Square" + "0" + "1");
//        }
//        if(x==1 && y==0)
//        {
//            moves.add("Square" + "7" + "1");
//            moves.add("Square" + "7" + "7");
//            moves.add("Square" + "0" + "6");
//            moves.add("Square" + "2" + "6");
//            moves.add("Square" + "4" + "7");
//        }
//        if(x==0 && y==1)
//        {
//            moves.add("Square" + "1" + "7");
//            moves.add("Square" + "7" + "7");
//            moves.add("Square" + "7" + "3");
//            moves.add("Square" + "6" + "0");
//            moves.add("Square" + "6" + "2");
//        }
//        if(x==1 && y==1)
//        {
//            moves.add("Square" + "0" + "7");
//            moves.add("Square" + "2" + "7");
//            moves.add("Square" + "7" + "2");
//            moves.add("Square" + "7" + "0");
//        }
//        if((x==6 && y==1) || (x==1 && y==6))
//        {
//            moves.add("Square" + "7" + "7");
//            moves.add("Square" + "7" + "5");
//            moves.add("Square" + "0" + "0");
//            moves.add("Square" + "0" + "2");
//        }
//        if(x==6 && y==6)
//        {
//            moves.add("Square" + "0" + "5");
//            moves.add("Square" + "0" + "7");
//            moves.add("Square" + "7" + "0");
//            moves.add("Square" + "5" + "0");
//        }
//        if(x==1 && y==7)
//        {
//            moves.add("Square" + "0" + "3");
//            moves.add("Square" + "2" + "1");
//            moves.add("Square" + "0" + "1");
//            moves.add("Square" + "7" + "0");
//            moves.add("Square" + "7" + "6");
//        }
//        if(x==7 && y==1)
//        {
//            moves.add("Square" + "1" + "0");
//            moves.add("Square" + "1" + "2");
//            moves.add("Square" + "0" + "3");
//            moves.add("Square" + "0" + "7");
//            moves.add("Square" + "6" + "7");
//        }
//        if(x==0 && y==6)
//        {
//            moves.add("Square" + "6" + "5");
//            moves.add("Square" + "6" + "7");
//            moves.add("Square" + "7" + "4");
//            moves.add("Square" + "1" + "0");
//            moves.add("Square" + "7" + "0");
//        }
//        if(x==6 && y==0)
//        {
//            moves.add("Square" + "4" + "7");
//            moves.add("Square" + "5" + "6");
//            moves.add("Square" + "7" + "6");
//            moves.add("Square" + "0" + "1");
//            moves.add("Square" + "0" + "7");
//        }
//        if(x==6 && y==7)
//        {
//            moves.add("Square" + "7" + "1");
//            moves.add("Square" + "5" + "1");
//            moves.add("Square" + "4" + "0");
//            moves.add("Square" + "0" + "6");
//            moves.add("Square" + "0" + "0");
//        }
//        if(x==7 && y==6)
//        {
//            moves.add("Square" + "1" + "7");
//            moves.add("Square" + "1" + "5");
//            moves.add("Square" + "6" + "0");
//            moves.add("Square" + "0" + "0");
//            moves.add("Square" + "0" + "4");
//        }
//
//        moves.add("Square" + (x+2) + (y+1));
//        moves.add("Square" + (x+2) + (y-1));
//        moves.add("Square" + (x+1) + (y+2));
//        moves.add("Square" + (x-1) + (y+2));
//        moves.add("Square" + (x-2) + (y+1));
//        moves.add("Square" + (x-2) + (y-1));
//        moves.add("Square" + (x+1) + (y-2));
//        moves.add("Square" + (x-1) + (y-2));

    }
}