package com.example.knightmove.Model;

import com.example.knightmove.controllers.GamePageController;

import java.util.ArrayList;


public class Queen extends Piece {
    public Queen(String color, int posX, int posY){
        super(color, posX, posY);
        this.type = "Queen";
        setImage();
    }


    @Override
    public ArrayList<String> getAllPossibleMoves() {
        // TODO: change hard coded parts to enumerations
        int x = this.posX;
        int y = this.posY;
        Square s = new Square();

        String name;

        this.possibleMoves = new ArrayList<>();

        for (int i = x - 1; i >= 0; i--) {
            for(Square ss: GamePageController.cb.getSquares()){
                if(i==ss.getX()&&y==ss.getY()){
                    s=ss;
                }
            }
            name = s.getType() + i + y;
//            if (getSquareByName(name).occupied && getPieceByName(name).getColor().equals(Game.currentPlayer)) break;

            possibleMoves.add(name);

//            if (getSquareByName(name).occupied && !getPieceByName(name).getColor().equals(Game.currentPlayer)) break;
        }

        for (int i = x + 1; i < Consts.SQUARES_IN_COLUMN; i++) {
            for(Square ss: GamePageController.cb.getSquares()){
                if(i==ss.getX()&&y==ss.getY()){
                    s=ss;
                }
            }
            name = s.getType() + i + y;
//            if (getSquareByName(name).occupied && getPieceByName(name).getColor().equals(Game.currentPlayer)) break;

            possibleMoves.add(name);

//            if (getSquareByName(name).occupied && !getPieceByName(name).getColor().equals(Game.currentPlayer)) break;
        }

        for (int j = y - 1; j >= 0; j--) {
            for(Square ss: GamePageController.cb.getSquares()){
                if(x==ss.getX()&&j==ss.getY()){
                    s=ss;
                }
            }
            name = s.getType() + x + j;
//            if (getSquareByName(name).occupied && getPieceByName(name).getColor().equals(Game.currentPlayer)) break;

            possibleMoves.add(name);

//            if (getSquareByName(name).occupied && !getPieceByName(name).getColor().equals(Game.currentPlayer)) break;
        }

        for (int j = y + 1; j < Consts.SQUARES_IN_COLUMN; j++) {
            for(Square ss: GamePageController.cb.getSquares()){
                if(x==ss.getX()&&j==ss.getY()){
                    s=ss;
                }
            }
            name = s.getType() + x + j;
//            if (getSquareByName(name).occupied && getPieceByName(name).getColor().equals(Game.currentPlayer)) break;

            possibleMoves.add(name);

//            if (getSquareByName(name).occupied && !getPieceByName(name).getColor().equals(Game.currentPlayer)) break;
        }

        for (int i = x - 1, j = y + 1; i >= 0 && j < Consts.SQUARES_IN_COLUMN; i--, j++) {
            for(Square ss: GamePageController.cb.getSquares()){
                if(i==ss.getX()&&j==ss.getY()){
                    s=ss;
                }
            }
            name = s.getType() + i + j;
//            if (getSquareByName(name).occupied && getPieceByName(name).getColor().equals(Game.currentPlayer)) break;

            possibleMoves.add(name);

//            if (getSquareByName(name).occupied && !getPieceByName(name).getColor().equals(Game.currentPlayer)) break;
        }

        for (int i = x + 1, j = y + 1; i < Consts.SQUARES_IN_COLUMN && j < Consts.SQUARES_IN_COLUMN; i++, j++) {
            for(Square ss: GamePageController.cb.getSquares()){
                if(i==ss.getX()&&j==ss.getY()){
                    s=ss;
                }
            }
            name = s.getType() + i + j;
//            if (getSquareByName(name).occupied && getPieceByName(name).getColor().equals(Game.currentPlayer)) break;

            possibleMoves.add(name);

//            if (getSquareByName(name).occupied && !getPieceByName(name).getColor().equals(Game.currentPlayer)) break;
        }

        for (int i = x + 1, j = y - 1; i < Consts.SQUARES_IN_ROW && j >= 0; i++, j--) {
            for(Square ss: GamePageController.cb.getSquares()){
                if(i==ss.getX()&&j==ss.getY()){
                    s=ss;
                }
            }
            name = s.getType() + i + j;
//            if (getSquareByName(name).occupied && getPieceByName(name).getColor().equals(Game.currentPlayer)) break;

            possibleMoves.add(name);

//            if (getSquareByName(name).occupied && !getPieceByName(name).getColor().equals(Game.currentPlayer)) break;
        }

        for (int i = x - 1, j = y - 1; i >= 0 && j >= 0; i--, j--) {
            for(Square ss: GamePageController.cb.getSquares()){
                if(i==ss.getX()&&j==ss.getY()){
                    s=ss;
                }
            }
            name = s.getType() + i + j;
//            if (getSquareByName(name).occupied && getPieceByName(name).getColor().equals(Game.currentPlayer)) break;

            possibleMoves.add(name);

//            if (getSquareByName(name).occupied && !getPieceByName(name).getColor().equals(Game.currentPlayer)) break;
        }
        return possibleMoves;
    }

    /**
     * Random movement for queen, used for lower levels.
     * @param possibleMoves - ArrayList of possible moves (each move is array list of [x,y] positions)
     * @return the random move
     */
    public static ArrayList<Integer> getQueenRandomMove(ArrayList<ArrayList<Integer>> possibleMoves){
        ArrayList<Integer> selectedMove = new ArrayList<Integer>();
        ArrayList<Integer> randomMove = possibleMoves.get(new java.util.Random().nextInt(possibleMoves.size()));
        int xRandom = randomMove.get(0);
        int yRandom = randomMove.get(1);
        selectedMove.add(xRandom);
        selectedMove.add(yRandom);
        return selectedMove;
    }

    /**
     * Added smart movement by the queen using Manhattan distance between the
     * current location of the knight and current location of the queen
     * @param possibleMoves - ArrayList of possible moves (each move is array list of [x,y] positions)
     * @param knightPositions - array of current knight position
     * @return the bestMove = array list of [x,y] positions
     */
    public static ArrayList<Integer> getQueenBestMove(ArrayList<ArrayList<Integer>> possibleMoves, int[] knightPositions) {
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

    private static int getManhattanDistance(int[] pos1, int[] pos2) {
        return Math.abs(pos1[0] - pos2[0]) + Math.abs(pos1[1] - pos2[1]);
    }


    public ArrayList<Integer> selectQueenMovements(String movementName, ArrayList<ArrayList<Integer>> possibleMoves, int[] knightPositions){
        if(movementName.equals("random")){
            return getQueenRandomMove(possibleMoves);
        }
        else if (movementName.equals("smart")){
            return getQueenBestMove(possibleMoves,knightPositions);
        }
        return null;
    }
}