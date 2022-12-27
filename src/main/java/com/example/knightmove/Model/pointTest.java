package com.example.knightmove.Model;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

class pointTest {

    @org.junit.jupiter.api.Test
    void setX() {
        point p = new point(2,3);
        assertTrue(p.x == 2);
    }

    @org.junit.jupiter.api.Test
    void setY() {
        point p = new point(2,3);
        assertTrue(p.y == 3);
    }

    @org.junit.jupiter.api.Test
    void testEquals(Object o) {
        point firstPoint = new point(2,3);
        assertTrue(firstPoint.equals(new point(2,3)));
    }

    @org.junit.jupiter.api.Test
    void testToString() {
        point p = new point(2,3); // you didn't supply the object, so I guessed
        String expected = "point{" +
                "x=" + p.x +
                ", y=" + p.y +
                '}'; // put the expected value here

        Assert.assertEquals(expected, p.toString());
    }
}
