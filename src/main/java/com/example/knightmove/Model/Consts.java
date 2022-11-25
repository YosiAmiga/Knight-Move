package com.example.knightmove.Model;
import java.net.URLDecoder;
import java.sql.*;
import java.util.ArrayList;

public class Consts {
    public static final String ADD_NEW_PLAYER =
            "{ call ADD_NEW_PLAYER(?) }";
    public static final String GET_ALL_PLAYERS =
            "{ call GET_ALL_PLAYERS }";

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
    public static final int HORSE_INIT_LOCATION_X = 0;
    public static final int HORSE_INIT_LOCATION_Y = 0;

    //queen - starts at top right corner
    public static final int QUEEN_INIT_LOCATION_X = SQUARES_IN_ROW-1;
    public static final int QUEEN_INIT_LOCATION_Y = 0;




}

