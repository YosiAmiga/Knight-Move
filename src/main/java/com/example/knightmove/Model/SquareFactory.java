package com.example.knightmove.Model;

public class SquareFactory {
    public Square getSquare(String SquareType, int x, int y) {
        if (SquareType == null) {
            return null;
        }
        if (SquareType.equalsIgnoreCase("REGULARSQAURE")) {
            return new Square(x, y);
        }
        if (SquareType.equalsIgnoreCase("RANDOMSQUARE")) {
            return new RandomSquare(x, y);
        }
        if (SquareType.equalsIgnoreCase("FORGETSQUARE")) {
            return new ForgetSquare(x, y);
        }
        if (SquareType.equalsIgnoreCase("BLOCKSQUARE")) {
            return new BlockSquare(x, y);
        }
        if (SquareType.equalsIgnoreCase("QUESTIONSQUARE")) {
            return new QuestionSquare(x, y);
        }
        return null;
    }
}

