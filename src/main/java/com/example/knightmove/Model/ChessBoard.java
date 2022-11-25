package com.example.knightmove.Model;

import javafx.geometry.Insets;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.layout.GridPane;
import java.util.ArrayList;
import java.lang.Math;


public class ChessBoard {

    GridPane chessBoard;
    String theme;
    public ArrayList<Square> squares = new ArrayList<>();

    public ChessBoard(GridPane chessBoard, String theme){
        this.chessBoard = chessBoard;
        this.theme = theme;
        makeBoard(this.chessBoard, theme);
    }


    private void makeBoard(GridPane chessBoard, String theme){
        for(int i=0; i<Consts.SQUARES_IN_ROW; i++){
            for(int j=0; j<Consts.SQUARES_IN_COLUMN; j++){
                Square square = new Square(i,j);
                square.setName("Square" + i + j);
                square.setPrefHeight(Consts.SQUARE_SIZE);
                square.setPrefWidth(Consts.SQUARE_SIZE);

                // NOTE: BoardStroke args (colurOfLinesBetweenSquares, typeOfLineBetweenSquares - could be dotted or full line)
                square.setBorder(new Border(new BorderStroke(Color.BLACK,
                        BorderStrokeStyle.DOTTED, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
                setTheme(square, theme, i, j);
                chessBoard.add(square, i, j, 1, 1);
                squares.add(square);
            }
        }
        addPieces();
    }

    private void setTheme(Square square, String theme, int i, int j){
        Color color1 = Color.web("#ffffff00");
        Color color2 = Color.web("#ffffff00");

        switch (theme) {
            case "Coral" -> {
                color1 = Color.web("#b1e4b9");
                color2 = Color.web("#70a2a3");
            }
            case "Dusk" -> {
                color1 = Color.web("#cbb7ae");
                color2 = Color.web("#716677");
            }
            case "Wheat" -> {
                color1 = Color.web("#eaefce");
                color2 = Color.web("#bbbe65");
            }
            case "Marine" -> {
                color1 = Color.web("#9dacff");
                color2 = Color.web("#6f74d2");
            }
            case "Emerald" -> {
                color1 = Color.web("#adbd90");
                color2 = Color.web("#6e8f72");
            }
            case "Sandcastle" -> {
                color1 = Color.web("#e4c16f");
                color2 = Color.web("#b88b4a");
            }
        }

        if((i+j)%2==0){
            square.setBackground(new Background(new BackgroundFill(color1, CornerRadii.EMPTY, Insets.EMPTY)));
        }else{
            square.setBackground(new Background(new BackgroundFill(color2, CornerRadii.EMPTY, Insets.EMPTY)));
        }

    }

    private void addPiece(Square square, Piece piece){
        square.getChildren().add(piece);
        square.occupied = true;
    }

    private void addPieces(){
        /**
         * Add pieces to their init location: pre-defined. (check out Const.java for enumerates).
         */
        for(Square square : squares){
            if(square.occupied) continue;
            // set horse init location
            if(square.x == Consts.HORSE_INIT_LOCATION_X && square.y == Consts.HORSE_INIT_LOCATION_Y){
                addPiece(square, new Knight("black", square.x, square.y));
            }
            // set queen init location
            else if(square.x == Consts.QUEEN_INIT_LOCATION_X && square.y == Consts.QUEEN_INIT_LOCATION_Y){
                addPiece(square, new Queen("black", square.x, square.y));
            }
            // set king init location
            else if(square.x == Consts.KING_INIT_LOCATION_X && square.y == Consts.KING_INIT_LOCATION_Y){
                addPiece(square, new King("black", square.x, square.y,Consts.KING_INIT_SPEED));

            }

        }

    }
}

