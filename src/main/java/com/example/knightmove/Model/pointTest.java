package com.example.knightmove.Model;
import org.junit.Assert;
import static org.junit.Assert.assertTrue;

class pointTest {

    @org.junit.Test
    static void setX() {
        point p = new point(2,3);
        assertTrue(p.x == 2);
    }

    @org.junit.Test
    static void setY() {
        point p = new point(2,3);
        assertTrue(p.y == 5);
    }

    @org.junit.Test
    static void testEquals(Object o) {
        point firstPoint = new point(2,3);
        assertTrue(firstPoint.equals(new point(2,3)));
    }

    @org.junit.Test
    static void testToString() {
        point p = new point(2,3); // you didn't supply the object, so I guessed
        String expected = "point{" +
                "x=" + p.x +
                ", y=" + p.y +
                '}'; // put the expected value here

        Assert.assertEquals(expected, p.toString());
    }


    public static void main(String[] args) {
        pointTest.testToString();
        pointTest.setY(); // will throw an exception!
    }
}
