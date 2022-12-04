package com.example.knightmove.Model;
import com.example.knightmove.controllers.DatabaseController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.example.knightmove.controllers.LogInController;

import java.sql.SQLException;
import java.util.ArrayList;

public class Tests {
    @Test
    public void testUserLogin() throws SQLException {
        DatabaseController dbc = new DatabaseController();
        boolean result = false;
        ArrayList<Player> players = dbc.getAllPlayers();
        for (Player p : players) {
            if (p.getNickname().equals("Yosi")){
                result=true;
            }
        }
        Assertions.assertEquals(true,result);
    }
}
