package com.example.knightmove.Model;


import javafx.event.EventHandler;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.util.ArrayList;

public abstract class Piece extends ImageView {
    String type;
    String color;
    int posX, posY;
    ArrayList<String> possibleMoves;

    public Piece(String color, int posX, int posY){
        this.color = color;
        this.posX = posX;
        this.posY = posY;
        addEventHandler();
    }

    public String getColor(){
        return this.color;
    }

    public void setPiece(Image image){
        this.setImage(image);
    }

    public void setImage(){
        String theme1Url = String.valueOf(getClass().getResource("/picture/"+this.type +".png"));
        this.setPiece(new Image(theme1Url));
    }

    private void addEventHandler(){
        this.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                getAllPossibleMoves();
            }
        });


    }

    public abstract void getAllPossibleMoves();

    public void showAllPossibleMoves(boolean val){
        if(val){
            Glow glow = new Glow();
            glow.setLevel(0.3);
            System.out.println("possibleMoves \n" + possibleMoves);

            for(String move : possibleMoves){
                Square square = getSquareByName(move);
                square.setEffect(glow);
                Piece piece = getPieceByName(move);
                if(piece == null) continue;
            }
        }
        else{
            for(String move : possibleMoves){
                Square square = getSquareByName(move);
                square.setEffect(null);

            }
        }
    }

    public Square getSquareByName(String name){
        for(Square square : Game.cb.squares){
            if(square.name.equals(name)){
                return square;
            }
        }
        return null;
    }

    public Piece getPieceByName(String name){
        for(Square square : Game.cb.squares){
            if(square.getChildren().size() == 0) continue;

            if(square.name.equals(name))
                return (Piece) square.getChildren().get(0);

        }
        return null;
    }


    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    public ArrayList getRandomPieceLocation(){
        /**
         * return a random location for a square
         */
        ArrayList<Integer> randomSquareLocation = new  ArrayList<Integer>();
        int randonXvalue = getRandomNumber(0,Consts.SQUARES_IN_ROW);
        int randonYvalue = getRandomNumber(0,Consts.SQUARES_IN_ROW);
        randomSquareLocation.add(randonXvalue);
        randomSquareLocation.add(randonYvalue);
        return randomSquareLocation;
    }

    @Override
    public String toString() {
        return this.type;
    }

}