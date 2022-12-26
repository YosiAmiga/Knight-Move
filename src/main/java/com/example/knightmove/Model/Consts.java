package com.example.knightmove.Model;
import javafx.scene.paint.Color;

import java.net.URLDecoder;
import java.sql.*;
import java.util.ArrayList;

public class Consts {

    public static final String databasePath = "jdbc:ucanaccess://src//DB//Knight_Move_DB.accdb";
    public static final String ADD_NEW_PLAYER =
            "{ call ADD_NEW_PLAYER(?) }";
    public static final String ADD_NEW_PLAYER_SCORE =
            "{ call ADD_NEW_PLAYER_SCORE(?,?) }";
    public static final String GET_ALL_PLAYERS =
            "SELECT * FROM Players_tbl";
    public static final String GET_ALL_GAME_HISTORY =
            "SELECT * FROM Game_History_tbl";
    public static final String DELETE_ALL_GAME_HISTORY =
            "DELETE FROM Game_History_tbl";
    public static final int SQUARE_SIZE = 50;

    public static final int SQUARES_IN_ROW = 8;
    public static final int SQUARES_IN_COLUMN = 8;


    /**
     *     INIT PIECES LOCATIONS
     */

    //king - starts at top right corner
    public static final int KING_INIT_LOCATION_X = SQUARES_IN_ROW-1;
    public static final int KING_INIT_LOCATION_Y = 0;

    //hourse - starts at top left corner
    public static final int KNIGHT_INIT_LOCATION_X = 0;
    public static final int KNIGHT_INIT_LOCATION_Y = 0;

    //queen - starts at top right corner
    public static final int QUEEN_INIT_LOCATION_X = SQUARES_IN_ROW-1;
    public static final int QUEEN_INIT_LOCATION_Y = 0;


    /**
     * INIT PIECES SPEED --> Not sure if all pieces needs speed attribute
     */
    public static final float KING_INIT_SPEED = 10;
//    public static final float KNIGHT_INIT_SPEED = 10;
//    public static final float QUEEN_INIT_SPEED = 10;

    /**
     * ------------------------------------ squares amounts for each type
     */
    public static final float NUMBER_OF_BLOCKING_SQUARES = 3;
    public static final float NUMBER_OF_FORGETTING_SQUARES = 1;
    public static final float NUMBER_OF_RANDOM_JUMP_SQUARES = 2;

    /**
     * ------------------------------------- board color constants
     */
    public static  Color color1 = Color.web("#ffffff00");
    public static  Color color2 = Color.web("#ffffff00");
    public static  Color colorBlockingSquare = Color.BLACK;
    public static  Color colorRandomJumpSquare = Color.web("#9ACD32");
    public static  Color colorForgettingSquare = Color.web("#9dacff");
    public static  Color colorVisitedSquare = Color.web("#808080");

    public static  Color colorQuestionSquare = Color.web("#FACC2E");
    public static Color colorEasyQuestionSquare = Color.WHITE;
    public static Color colorMediumQuestionSquare = Color.YELLOW;
    public static Color colorHardQuestionSquare = Color.RED;


}

