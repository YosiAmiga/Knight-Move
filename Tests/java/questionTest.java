package com.example.knightmove.Controllers;

import com.example.knightmove.Model.Question;
import org.json.simple.parser.ParseException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class questionTest {
    ArrayList <String> list;
    Question q;
    @Before
    public void Before() throws IOException, ParseException {
        list = new ArrayList<>();
        list.add("good");
        list.add("amazing");
        list.add("bad");
        list.add("worse");
        q = new Question("How you doin",list,1,1,"Panda");
    }
    @Test
    public void getterSetterTest(){
        assertEquals(q.getQuestion(),"How you doin");
    }
    @After
    public void After() throws IOException, ParseException {
        this.q=null;
        this.list=null;
    }
}
