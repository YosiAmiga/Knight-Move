package com.example.knightmove.Controllers;
import com.example.knightmove.Controllers.EndGameController;
import com.example.knightmove.Controllers.GamePageController;
import org.json.simple.parser.ParseException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;

public class medalCheck {
    GamePageController game=null;
    EndGameController endgame=null;
    @Before
    public void Before() throws IOException, ParseException {
        this.game=new GamePageController();
        this.endgame=new EndGameController();
    }
    @Test
    public void userGotMedal() throws IOException, ParseException, SQLException {
        GamePageController.score=300;
        assertEquals(endgame.gotMedal(),true);
    }
    @Test
    public void userDidntGotMedal() throws IOException, ParseException, SQLException {
        GamePageController.score=100;
        assertEquals(endgame.gotMedal(),false);
    }

    @After
    public void After() throws IOException, ParseException {
        this.game=null;
        this.endgame=null;
    }
}
