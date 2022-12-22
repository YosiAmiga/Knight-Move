package com.example.knightmove.Model;

public class RandomSquare extends Square{
    public RandomSquare(int x, int y) {
        super(x, y);
        this.type="Random";
    }
    @Override
    public String toString() {
        String status;
        if(this.occupied) status = "Occupied";
        else status = "Not occupied";
//        return "Square" + this.x + this.y + " - " + status;
        return "Random";
    }
}
