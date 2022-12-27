package com.example.knightmove.Model;

public class PieceFactory {
    public Piece getPiece(String PieceType) {
        if (PieceType == null) {
            return null;
        }
        if (PieceType.equalsIgnoreCase("KING")) {
            return new King("Black",7,7,1);
        }
        if (PieceType.equalsIgnoreCase("QUEEN")) {
            return new Queen("Black",7, 7);
        }
        if (PieceType.equalsIgnoreCase("KNIGHT")) {
            return new Knight("Black",1, 1);
        }
        return null;
    }
}

