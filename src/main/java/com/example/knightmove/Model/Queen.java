package com.example.knightmove.Model;

import javafx.scene.image.Image;

import java.util.ArrayList;

public class Queen extends Piece {
    public Queen(String color, int posX, int posY){
        super(color, posX, posY);
        this.type = "Queen";
        setImage();
    }

    @Override
    public void getAllPossibleMoves() {
        // TODO: change hard coded parts to enumerations
        int x = this.posX;
        int y = this.posY;
        String name;

        this.possibleMoves = new ArrayList<>();

        for (int i = x - 1; i >= 0; i--) {
            name = "Square" + i + y;
            if (getSquareByName(name).occupied && getPieceByName(name).getColor().equals(Game.currentPlayer)) break;

            possibleMoves.add(name);

            if (getSquareByName(name).occupied && !getPieceByName(name).getColor().equals(Game.currentPlayer)) break;
        }

        for (int i = x + 1; i < Consts.SQUARES_IN_COLUMN; i++) {
            name = "Square" + i + y;
            if (getSquareByName(name).occupied && getPieceByName(name).getColor().equals(Game.currentPlayer)) break;

            possibleMoves.add(name);

            if (getSquareByName(name).occupied && !getPieceByName(name).getColor().equals(Game.currentPlayer)) break;
        }

        for (int j = y - 1; j >= 0; j--) {
            name = "Square" + x + j;
            if (getSquareByName(name).occupied && getPieceByName(name).getColor().equals(Game.currentPlayer)) break;

            possibleMoves.add(name);

            if (getSquareByName(name).occupied && !getPieceByName(name).getColor().equals(Game.currentPlayer)) break;
        }

        for (int j = y + 1; j < Consts.SQUARES_IN_COLUMN; j++) {
            name = "Square" + x + j;
            if (getSquareByName(name).occupied && getPieceByName(name).getColor().equals(Game.currentPlayer)) break;

            possibleMoves.add(name);

            if (getSquareByName(name).occupied && !getPieceByName(name).getColor().equals(Game.currentPlayer)) break;
        }

        for (int i = x - 1, j = y + 1; i >= 0 && j < Consts.SQUARES_IN_COLUMN; i--, j++) {
            name = "Square" + i + j;
            if (getSquareByName(name).occupied && getPieceByName(name).getColor().equals(Game.currentPlayer)) break;

            possibleMoves.add(name);

            if (getSquareByName(name).occupied && !getPieceByName(name).getColor().equals(Game.currentPlayer)) break;
        }

        for (int i = x + 1, j = y + 1; i < Consts.SQUARES_IN_COLUMN && j < Consts.SQUARES_IN_COLUMN; i++, j++) {
            name = "Square" + i + j;
            if (getSquareByName(name).occupied && getPieceByName(name).getColor().equals(Game.currentPlayer)) break;

            possibleMoves.add(name);

            if (getSquareByName(name).occupied && !getPieceByName(name).getColor().equals(Game.currentPlayer)) break;
        }

        for (int i = x + 1, j = y - 1; i < Consts.SQUARES_IN_ROW && j >= 0; i++, j--) {
            name = "Square" + i + j;
            if (getSquareByName(name).occupied && getPieceByName(name).getColor().equals(Game.currentPlayer)) break;

            possibleMoves.add(name);

            if (getSquareByName(name).occupied && !getPieceByName(name).getColor().equals(Game.currentPlayer)) break;
        }

        for (int i = x - 1, j = y - 1; i >= 0 && j >= 0; i--, j--) {
            name = "Square" + i + j;
            if (getSquareByName(name).occupied && getPieceByName(name).getColor().equals(Game.currentPlayer)) break;

            possibleMoves.add(name);

            if (getSquareByName(name).occupied && !getPieceByName(name).getColor().equals(Game.currentPlayer)) break;
        }
    }
}