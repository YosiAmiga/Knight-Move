package com.example.knightmove.Model;

import javafx.scene.layout.StackPane;

import java.util.ArrayList;

public class Square extends StackPane {

    int x,y;
    boolean occupied;
    String name;
    String type;

    String color;

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

    public void checkIfSpecialSquare(Square square, ArrayList<ArrayList<Integer>> specialLocations){
        for(int i = 0 ; i<specialLocations.size() ; i++){
            if(square.x == specialLocations.get(i).get(0) & square.y == specialLocations.get(i).get(1)){
                System.out.println("this is a special location");
                return;
            }
        }
        // if we don't print anything then we can say that the point is not in a special location
        return;

    }


}