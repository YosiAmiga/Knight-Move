package com.example.knightmove.Model;

import org.junit.Assert;

import static org.junit.jupiter.api.Assertions.*;

class ForgetSquareTest {

    @org.junit.jupiter.api.Test
    void testToString() {
        ForgetSquare fg = new ForgetSquare(2,3);
        String expected =  "Forget square data: x=" + fg.x + " y=" + fg.y;
        Assert.assertEquals(expected, fg.toString());
    }
}