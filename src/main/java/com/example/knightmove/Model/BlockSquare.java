package com.example.knightmove.Model;

public class BlockSquare extends Square{
    public BlockSquare(int x, int y) {
        super(x, y);
        this.type="Block";
        this.color="Red";
    }

    @Override
    public String toString() {
        String status;
        if(this.occupied) status = "Occupied";
        else status = "Not occupied";
//        return "Square" + this.x + this.y + " - " + status;
        return "Block";
    }


}