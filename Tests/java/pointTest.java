package com.example.knightmove.Controllers;

import com.example.knightmove.Model.Point;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class pointTest {

        @Test
         public void setX() {
            Point p = new Point(2,3);
            assertTrue(p.getX() == 2);
        }

        @Test
        public void setY() {
            Point p = new Point(2,3);
            assertTrue(p.getY() == 3);
        }

        @Test
        public void testEquals() {
            Point firstPoint = new Point(2,3);
            assertTrue(firstPoint.equals(new Point(2,3)));
        }

        @Test
        public void testToString() {
            Point p = new Point(2,3); // you didn't supply the object, so I guessed
            String expected = "point{" +
                    "x=" + p.getX() +
                    ", y=" + p.getY() +
                    '}'; // put the expected value here

            assertEquals(expected, p.toString());
        }
}
