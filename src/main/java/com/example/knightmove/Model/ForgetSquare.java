package com.example.knightmove.Model;

public class ForgetSquare extends Square{
    public ForgetSquare(int x, int y) {
        super(x, y);
        this.type="Forget";
    }
    @Override
    public String toString() {
        String status;
        if(this.occupied) status = "Occupied";
        else status = "Not occupied";
//        return "Square" + this.x + this.y + " - " + status;
        return "Forget";
    }
}