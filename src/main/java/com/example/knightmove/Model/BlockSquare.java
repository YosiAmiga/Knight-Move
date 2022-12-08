package com.example.knightmove.Model;

public class BlockSquare extends Square{
    public BlockSquare(int x, int y) {
        super(x, y);
        this.type="Block";
        this.color="Red";
    }
}