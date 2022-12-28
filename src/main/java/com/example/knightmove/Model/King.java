package com.example.knightmove.Model;

import com.example.knightmove.controllers.GamePageController;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class King extends Piece{
    String gameGoal = "Hit the horse";
    float speed; // speed should be changeable

    public King(String color, int posX, int posY,float speed) {
        super(color, posX, posY);
        this.type = "King";
        this.speed = Consts.KING_INIT_SPEED; // set initial speed
        setImage();
    }

    /**
     * get possible move of the king
     * @return
     */
    @Override
    public ArrayList<String> getAllPossibleMoves() {
        /**
         * King can move by the following logic:
         *
         */
        //float currentSpeed = this.speed; // should take count of the speed changes in possible moves
        int x = this.posX;
        int y = this.posY;
        Square s = new Square();
        String name;
        ArrayList<String> moves = new ArrayList<>();
        this.possibleMoves = new ArrayList<>();
        ArrayList<String> possible_types = new ArrayList<String>(Arrays.asList(new String[] {"Normal","Random","Forget","Question"}));

        for(String type : possible_types) {
            moves.add(type + (x) + (y - 1));
            moves.add(type + (x + 1) + (y - 1));
            moves.add(type + (x + 1) + (y));
            moves.add(type + (x + 1) + (y + 1));
            moves.add(type + (x) + (y + 1));
            moves.add(type + (x - 1) + (y + 1));
            moves.add(type + (x - 1) + (y));
            moves.add(type + (x - 1) + (y - 1));
        }
        for(String move : moves){
            if(getSquareByName(move) != null){
                System.out.println(move);
                possibleMoves.add(move);
            }
        }
        return this.possibleMoves;
    }

    /**
     * return king best move = The square closest to the knight
     * @param possibleMoves - all king possible move
     * @param knightPositions - knight current point
     * @return
     */
    public static ArrayList<Integer> getKingBestMove(ArrayList<ArrayList<Integer>> possibleMoves, int[] knightPositions) {
        int minDistance = Integer.MAX_VALUE;
        ArrayList<Integer> bestMove = null;

        for (ArrayList<Integer> move : possibleMoves) {
            int[] intMove = new int[] { move.get(0), move.get(1) };
            int distance = getManhattanDistance(intMove, knightPositions);
            if (distance < minDistance) {
                minDistance = distance;
                bestMove = move;
            }
        }
        return bestMove;
    }
}