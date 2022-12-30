package com.example.knightmove.controllers;

import com.example.knightmove.Model.point;
import org.json.simple.parser.ParseException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;


public class EatKnightTest {
    GamePageController game=null;
    @Before
    public void Before() throws IOException, ParseException {
        this.game=new GamePageController();
    }
    @Test
    public void QueenEatKnightTest() throws IOException, ParseException {
        game.queenEatKnight(new point(0,0),new point(0,0));
        assertEquals(GamePageController.isGameOver,true);
    }

    @Test
    public void QueenNotEatKnightTest() throws IOException, ParseException {
        game.queenEatKnight(new point(0,2),new point(4,0));
        assertEquals(GamePageController.isGameOver,false);
    }

    @Test
    public void KingEatKnightTest() throws IOException, ParseException {
        game.kingEatKnight(new point(0,0),new point(0,0));
        assertEquals(GamePageController.isGameOver,true);
    }

    @Test
    public void KingNotEatKnightTest() throws IOException, ParseException {
        game.kingEatKnight(new point(0,2),new point(1,0));
        assertEquals(GamePageController.isGameOver,false);
    }

    @After
    public void After() throws IOException, ParseException {
        this.game=null;
    }
}
