package com.example.knightmove.Model;

import javafx.scene.layout.StackPane;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Square extends StackPane {

    int x,y;
    public boolean occupied;
    public String name;
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

    public String getName() {
        return name;
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
        return "Square";
    }

    public void setName(String name){
        this.name = name;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Square square = (Square) o;
        return x == square.x && y == square.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }


}