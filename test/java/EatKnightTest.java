package com.example.knightmove.controllers;

import com.example.knightmove.Model.Point;
import com.example.knightmove.Model.Question;
import org.json.simple.parser.ParseException;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;


public class EatKnightTest {
    GamePageController game=null;
    @Before
    public void Before() throws IOException, ParseException {
        this.game=new GamePageController();
    }
    @Test
    public void QueenEatKnightTest() throws IOException, ParseException {
        game.queenEatKnight(new Point(0,0),new Point(0,0));
        assertEquals(GamePageController.isGameOver,true);
    }

    @Test
    public void QueenNotEatKnightTest() throws IOException, ParseException {
        game.queenEatKnight(new Point(0,2),new Point(4,0));
        assertEquals(GamePageController.isGameOver,false);
    }

    @Test
    public void KingEatKnightTest() throws IOException, ParseException {
        game.kingEatKnight(new Point(0,0),new Point(0,0));
        assertEquals(GamePageController.isGameOver,true);
    }

    @Test
    public void KingNotEatKnightTest() throws IOException, ParseException {
        game.kingEatKnight(new Point(0,2),new Point(1,0));
        assertEquals(GamePageController.isGameOver,false);
    }
}
