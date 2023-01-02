package com.example.knightmove.Controllers;

import com.example.knightmove.Model.Point;
import org.json.simple.parser.ParseException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class EatKnightTest {
    GamePageController game=null;
    @Before
    public void Before() throws IOException, ParseException {
        this.game=new GamePageController();
    }
    @Test
    public void QueenEatKnightTest() throws IOException, ParseException {
        game.queenEatKnight(new Point(0,0),new Point(0,0));
        assertTrue(GamePageController.isGameOver);
    }

    @Test
    public void KingEatKnightTest() throws IOException, ParseException {
        game.kingEatKnight(new Point(0,0),new Point(0,0));
        assertEquals(GamePageController.isGameOver,true);
    }

    @After
    public void After() throws IOException, ParseException {
        this.game=null;
    }
}
