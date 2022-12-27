package com.example.knightmove.Model;


import com.example.knightmove.controllers.GamePageController;
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
    public String type;
    String color;
    int posX, posY;
    public ArrayList<String> possibleMoves;

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

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

    public abstract ArrayList<String> getAllPossibleMoves();

    /**
     * show all possible move's of the piece
     * @param val - show or hide
     */
    public void showAllPossibleMoves(boolean val){
        if(val){
            Glow glow = new Glow();
            glow.setLevel(1);
            for(String move : possibleMoves){
                Square square = getSquareByName(move);
                square.setEffect(glow);
                Piece piece = getPieceByName(move);
                if(piece == null) continue;
            }
        }
        else{
            System.out.println(possibleMoves);
            for(String move : possibleMoves){
                Square square = getSquareByName(move);
                square.setEffect(null);
            }
        }
    }

    /**
     * get name and return the square
     * @param name - like "Normal43"
     * @return
     */
    public Square getSquareByName(String name){
        for(Square square : GamePageController.cb.squares){
            if(square.name.equals(name)){
                return square;
            }
        }
        return null;
    }

    /**
     * return the piece by name
     * @param name - king/queen/knight
     * @return
     */
    public Piece getPieceByName(String name){
        for(Square square : GamePageController.cb.squares){
            if(square.getChildren().size() == 0) continue;

            if(square.name.equals(name))
                return (Piece) square.getChildren().get(0);

        }
        return null;
    }


    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    // used in king and queen best move function
    public static int getManhattanDistance(int[] pos1, int[] pos2) {
        return Math.abs(pos1[0] - pos2[0]) + Math.abs(pos1[1] - pos2[1]);
    }

    // recive moves and change them to x+y array
    public static ArrayList<ArrayList<Integer>> convertMovesToIntArrays(ArrayList<String> moves) {
        ArrayList<ArrayList<Integer>> intArrays = new ArrayList<>(moves.size());
        for (String move : moves) {
            String[] parts = move.split("Normal|Random|Block|Question|Jump|Forget");
            int row = Integer.parseInt(parts[1].substring(0, 1));
            int col = Integer.parseInt(parts[1].substring(1, 2));
            ArrayList<Integer> coord = new ArrayList<>(2);
            coord.add(row);
            coord.add(col);
            intArrays.add(coord);
        }
        return intArrays;
    }
    @Override
    public String toString() {
        return this.type;
    }
    /*
    public ArrayList getRandomPieceLocation(){
        /**
         * return a random location for a square

        ArrayList<Integer> randomSquareLocation = new  ArrayList<Integer>();
        int randonXvalue = getRandomNumber(0,Consts.SQUARES_IN_ROW);
        int randonYvalue = getRandomNumber(0,Consts.SQUARES_IN_ROW);
        randomSquareLocation.add(randonXvalue);
        randomSquareLocation.add(randonYvalue);
        return randomSquareLocation;
    }
    */
}