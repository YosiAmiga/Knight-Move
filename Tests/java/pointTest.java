package com.example.knightmove.Controllers;

import com.example.knightmove.Model.Point;
import org.json.simple.parser.ParseException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class pointTest {
    Point p;
    @Before
    public void Before()
    {
        p = new Point(2,3); // you didn't supply the object, so I guessed
    }

        @Test
         public void setX() {
            assertTrue(p.getX() == 2);
        }

        @Test
        public void setY() {
            assertTrue(p.getY() == 3);
        }

        @Test
        public void testEquals() {
            assertTrue(p.equals(new Point(2,3)));
        }

        @Test
        public void testToString() {
            String expected = "point{" +
                    "x=" + p.getX() +
                    ", y=" + p.getY() +
                    '}'; // put the expected value here

            assertEquals(expected, p.toString());
        }
    @After
    public void After() throws IOException, ParseException {
        this.p=null;
    }
}
