package com.example.knightmove.Model;

import javafx.scene.layout.StackPane;
import java.util.ArrayList;
import java.util.List;

public class Square extends StackPane {

    int x,y;
    boolean occupied;
    String name;
    String type;

    String color;

    public Square() {

    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Square(int x, int y){
        this.x = x;
        this.y = y;
        this.occupied = false;
        this.type= "Normal";
        this.color="White";
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
            if(p.x == this.x && p.y == this.y){
                return true; // that's a special square
            }
        }
        // not a blocking square if we return false
        return false;

    }

    public boolean checkIfRandomSquare( ArrayList<point> randomSquaresCoords){
        /**
         * given a point (x,y) and the list of special locations of squares
         * return true if (x,y) is a special square location
         */
        for(point p : randomSquaresCoords){
            if(p.x == this.x && p.y == this.y){
                return true; // that's a special square
            }
        }

        // not a random square if we return false
        return false;

    }

    public boolean checkIfJumpSquare( ArrayList<point> jumpSquaresCoords){
        /**
         * given a point (x,y) and the list of special locations of squares
         * return true if (x,y) is a special square location
         */
        for(point p : jumpSquaresCoords){
            if(p.x == this.x && p.y == this.y){
                return true; // that's a special square
            }
        }

        // not a Jump square if we return false
        return false;

    }
    public boolean checkIfQuestionSquare( ArrayList<point> questionSquaresCoords){
        /**
         * given a point (x,y) and the list of special locations of squares
         * return true if (x,y) is a special square location
         */
        for(point p : questionSquaresCoords){
            if(p.x == this.x && p.y == this.y){
                return true; // that's a special square
            }
        }

        // not a Question square if we return false
        return false;

    }


    public List<Object>  checkIfSquareIsSpecial(ArrayList<point> blockingSquaresCoords
            ,ArrayList<point> randomSquaresCoords, ArrayList<point> jumpSquaresCoords, ArrayList<point> questionSquaresCoords){
        /**
         * INPUT: square object
         * OUTPUT: Array size 2 --> [ifSpecialSquare: boolean, typeOfSquare: String]
         */
        List<Object> squareSummary = new ArrayList<Object>();
        if(checkIfBlockingSquare( this,blockingSquaresCoords)){
            squareSummary.add(true);
            squareSummary.add("blocking");
            return squareSummary;

        }
        else if(checkIfRandomSquare( blockingSquaresCoords)){
            squareSummary.add(true);
            squareSummary.add("random");
            return squareSummary;

        }
        else if(checkIfJumpSquare( blockingSquaresCoords)){
            squareSummary.add(true);
            squareSummary.add("blocking");
            return squareSummary;

        }

        else if(checkIfQuestionSquare( questionSquaresCoords)){
            squareSummary.add(true);
            squareSummary.add("question");
            return squareSummary;

        }

        squareSummary.add(false);
        squareSummary.add("Regular");
        return squareSummary;

    }




}