package com.example.knightmove.Model;

import javafx.geometry.Insets;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.layout.GridPane;
import java.util.ArrayList;

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
        for(int i=0; i<8; i++){
            for(int j=0; j<8; j++){
                Square square = new Square(i,j);
                square.setName("Square" + i + j);
                square.setPrefHeight(Consts.SQUARE_SIZE);
                square.setPrefWidth(Consts.SQUARE_SIZE);
                square.setBorder(new Border(new BorderStroke(Color.BLACK,
                        BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
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
        for(Square square : squares){
            if(square.occupied) continue;
            if(square.x == 0 && square.y == 0){
                addPiece(square, new Knight("black", square.x, square.y));
            }
            else if(square.x == 7 && square.y == 0){
                addPiece(square, new Queen("black", square.x, square.y));

            }
            else if(square.x == 7 && square.y == 7){
                addPiece(square, new King("black", square.x, square.y));

            }

        }

    }
}

