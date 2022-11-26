package com.example.knightmove.Model;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

public class Json {

    public static void main(String[] args) throws IOException, ParseException {
        HashSet <Question> questions = new HashSet<>();
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader("Questions.json"));
            JSONObject jsonObject = (JSONObject) obj;
            JSONArray allQuestions = (JSONArray) jsonObject.get("questions");
            Iterator<Object> iterator = allQuestions.iterator();
            while(iterator.hasNext()){
                ArrayList<String> answers = new ArrayList<>();
                JSONObject jsonObject2 = (JSONObject)iterator.next();
                JSONArray answersArray = (JSONArray) jsonObject2.get("answers");
                Iterator<String> iterator2 = answersArray.iterator();
                while (iterator2.hasNext()){
                    String a =iterator2.next();
                    answers.add(a);
                }
                System.out.println(answers);
                String q = (String)jsonObject2.get("question");
                String team = (String)jsonObject2.get("team");
                String level = (String) jsonObject2.get("level");
                String correctans = (String) jsonObject2.get("correct_ans");
                Question newQuestion = new Question(q,answers,Integer.valueOf(correctans),Integer.valueOf(level),team);
                questions.add(newQuestion);
            }


        } catch (IOException | ParseException e) {
            e.printStackTrace();

        }

        // creates Iterator oblect.
        Iterator itr = questions.iterator();

        // check element is present or not. if not loop will
        // break.
        while (itr.hasNext()) {
            System.out.println(itr.next());
        }

    } //TEST

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

    public static HashSet<Question> readFromJSON(){
        HashSet <Question> questions = new HashSet<>();
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader("Questions.json"));
            JSONObject jsonObject = (JSONObject) obj;
            JSONArray allQuestions = (JSONArray) jsonObject.get("questions");
            Iterator<Object> iterator = allQuestions.iterator();
            while(iterator.hasNext()){
                ArrayList<String> answers = new ArrayList<>();
                JSONObject jsonObject2 = (JSONObject)iterator.next();
                JSONArray answersArray = (JSONArray) jsonObject2.get("answers");
                Iterator<String> iterator2 = answersArray.iterator();
                while (iterator2.hasNext()){
                    String a =iterator2.next();
                    answers.add(a);
                }
                System.out.println(answers);
                String q = (String)jsonObject2.get("question");
                String team = (String)jsonObject2.get("team");
                String level = (String) jsonObject2.get("level");
                String correctans = (String) jsonObject2.get("correct_ans");
                Question newQuestion = new Question(q,answers,Integer.valueOf(correctans),Integer.valueOf(level),team);
                questions.add(newQuestion);
            }


        } catch (IOException | ParseException e) {
            e.printStackTrace();

        }

        return questions;
    }


    public static void writeToJson(Question q){
        JSONObject question = new JSONObject();
        question.put("question",q.getQuestion());
        JSONArray answersArray = new JSONArray();
        answersArray.add(q.getAnswers());
        question.put("answers",answersArray);
        question.put("correct_ans",q.getCorrectAnswer());
        question.put("level",q.getLevel());
        question.put("team",q.getTeam());

        try(FileWriter file = new FileWriter("Questions.json")){
            file.write(question.toJSONString());
            file.flush();
        }catch (IOException e){e.printStackTrace();}
    }
}
