package com.example.knightmove.Model;

import javafx.scene.layout.StackPane;

import java.util.ArrayList;
import java.util.List;

public class Square extends StackPane {

    int x,y;
    boolean occupied;
    String name;

    public Square(int x, int y){
        this.x = x;
        this.y = y;
        this.occupied = false;
    }

    @Override
    public String toString() {
        String status;
        if(this.occupied) status = "Occupied";
        else status = "Not occupied";
//        return "Square" + this.x + this.y + " - " + status;
        return "Square";
    }

    public void setName(String name){
        this.name = name;
    }
    public boolean checkIfBlockingSquare(Square square, ArrayList<point> blockingSquaresCoords){
        for(point p : blockingSquaresCoords){
            if(p.x == square.x && p.y == square.y){
                return true; // that's a special square
            }
        }
        // not a blocking square if we return false
        return false;

    }

    public boolean checkIfRandomSquare(Square square, ArrayList<point> randomSquaresCoords){
        /**
         * given a point (x,y) and the list of special locations of squares
         * return true if (x,y) is a special square location
         */
        for(point p : randomSquaresCoords){
            if(p.x == square.x && p.y == square.y){
                return true; // that's a special square
            }
        }

        // not a random square if we return false
        return false;

    }

    public boolean checkIfJumpSquare(Square square, ArrayList<point> jumpSquaresCoords){
        /**
         * given a point (x,y) and the list of special locations of squares
         * return true if (x,y) is a special square location
         */
        for(point p : jumpSquaresCoords){
            if(p.x == square.x && p.y == square.y){
                return true; // that's a special square
            }
        }

        // not a Jump square if we return false
        return false;

    }


    public List<Object>  checkIfSquareIsSpecial(Square square,ArrayList<point> blockingSquaresCoords
            ,ArrayList<point> randomSquaresCoords, ArrayList<point> jumpSquaresCoords){
        /**
         * INPUT: square object
         * OUTPUT: Array size 2 --> [ifSpecialSquare: boolean, typeOfSquare: String]
         */
        List<Object> squareSummary = new ArrayList<Object>();
        if(checkIfBlockingSquare( square,blockingSquaresCoords)){
            squareSummary.add(true);
            squareSummary.add("blocking");
            return squareSummary;

        }
        else if(checkIfRandomSquare( square,blockingSquaresCoords)){
            squareSummary.add(true);
            squareSummary.add("random");
            return squareSummary;

        }
        else if(checkIfJumpSquare( square,blockingSquaresCoords)){
            squareSummary.add(true);
            squareSummary.add("blocking");
            return squareSummary;

        }

        squareSummary.add(false);
        squareSummary.add("Regular");
        return squareSummary;

    }



}