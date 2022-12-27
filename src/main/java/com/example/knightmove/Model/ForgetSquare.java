package com.example.knightmove.Model;

public class ForgetSquare extends Square{
    public ForgetSquare(int x, int y) {
        super(x, y);
        this.type="Forget";
    }
    @Override
    public String toString() {
        return "Forget square data: x=" + this.x + " y=" + this.y;
    }
}