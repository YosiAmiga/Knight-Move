package com.example.knightmove.Model;

import org.junit.Assert;

class ForgetSquareTest {

    @org.junit.Test
    public static void testToString() {
        ForgetSquare fg = new ForgetSquare(2,3);
        String expected =  "Forget square data: x=" + fg.x + " y=" + fg.y;
        Assert.assertEquals(expected, fg.toString());
    }


    public static void main(String[] args) {
        ForgetSquareTest.testToString();
    }
}