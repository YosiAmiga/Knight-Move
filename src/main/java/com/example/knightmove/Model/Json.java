package com.example.knightmove.Model;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class Json {

    public static void main(String[] args) throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader("Questions.json"));
            JSONObject jsonObject = (JSONObject) obj;
            JSONArray allQuestions = (JSONArray) jsonObject.get("questions");
            Iterator<Object> iterator = allQuestions.iterator();
            while(iterator.hasNext()){
                JSONObject jsonObject2 = (JSONObject)iterator.next();
                String q = (String)jsonObject2.get("question");
                System.out.println(q);
                System.out.println("hiiiiii");
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();

        } //TEST
    }
//    public void exporToJson() throws IOException, ParseException {
//        JSONParser jsonParser = new JSONParser();
//        FileReader reader = new FileReader("Questions.json");
//        Object obj = jsonParser.parse(reader);
//         JSONObject jo = (JSONObject)obj;
//         String q = (String) jo.get("question");
//        System.out.println((String) jo.get("answers"));
//
//    }
//
//    public void exportFromJson(){
//
//    }

    public void readFromJSON(){
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader("Questions.json"));
            JSONObject jsonObject = (JSONObject) obj;
            JSONArray allQuestions = (JSONArray) jsonObject.get("questions");
            Iterator<Object> iterator = allQuestions.iterator();
            while(iterator.hasNext()){
                JSONObject jsonObject2 = (JSONObject)iterator.next();
                String q = (String)jsonObject2.get("question");
                System.out.println(q);
                System.out.println("hiiiiii");
            }


        } catch (IOException | ParseException e) {
            e.printStackTrace();

        }
    }


    public void writeTojson (Question q){

    }
}
