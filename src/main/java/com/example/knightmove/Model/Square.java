package com.example.knightmove.Model;

import javafx.scene.layout.StackPane;

import java.util.ArrayList;

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

    public boolean checkIfSpecialSquare(Square square, ArrayList<point> specialLocations){
        /**
         * given a point (x,y) and the list of special locations of squares
         * return true if (x,y) is a special square location
         */
        for(point p : specialLocations){
            if(p.x == square.x && p.y == square.y){
                return true; // that's a special square
            }
        }

        // not a special square if we return false
        return false;

    }


}