package com.example.knightmove.Model;

public class PieceFactory {
    // The constracot paramter of King,Knight,Queen will change
    public Piece getPiece(String PieceType) {
        if (PieceType == null) {
            return null;
        }
        if (PieceType.equalsIgnoreCase("KING")) {
            return new King("black",Consts.KING_INIT_LOCATION_X,Consts.KING_INIT_LOCATION_Y,Consts.KING_INIT_SPEED);
        }
        if (PieceType.equalsIgnoreCase("QUEEN")) {
            return new Queen("black",Consts.QUEEN_INIT_LOCATION_X, Consts.QUEEN_INIT_LOCATION_Y);
        }
        if (PieceType.equalsIgnoreCase("KNIGHT")) {
            return new Knight("black",Consts.KNIGHT_INIT_LOCATION_X, Consts.KNIGHT_INIT_LOCATION_Y);
        }
        return null;
    }
}

