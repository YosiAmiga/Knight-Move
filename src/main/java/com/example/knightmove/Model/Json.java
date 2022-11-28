package com.example.knightmove.Model;


import com.example.knightmove.HelloApplication;
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

    public static HashSet<Question> readFromJSON(){
        HashSet <Question> questions = new HashSet<>();
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader("Questions.json"));
            JSONObject jsonObject = (JSONObject) obj;
            JSONArray allQuestions = (JSONArray) jsonObject.get("questions");
            Iterator<Object> iterator = allQuestions.iterator();
            while(iterator.hasNext()){ //going through all the questions
                ArrayList<String> answers = new ArrayList<>();
                JSONObject jsonObject2 = (JSONObject)iterator.next();
                JSONArray answersArray = (JSONArray) jsonObject2.get("answers");
                Iterator<String> iterator2 = answersArray.iterator();
                while (iterator2.hasNext()){ //going through this question's possible answers
                    String a =iterator2.next();
                    answers.add(a);
                }
                String q = (String)jsonObject2.get("question");
                String team = (String)jsonObject2.get("team");
                String level = (String) jsonObject2.get("level");
                String correctans = (String) jsonObject2.get("correct_ans");
                System.out.println("question " + q);
                System.out.println("answers " + answers);
                System.out.println("team " + team);
                System.out.println("level " + level);
                Question newQuestion = new Question(q,answers,Integer.valueOf(correctans),Integer.valueOf(level),team);
                questions.add(newQuestion);
            }


        } catch (IOException | ParseException e) {
            e.printStackTrace();

        }

        return questions;
    }


    public static void writeToJson(Question q){

        JSONObject newQuestion = new JSONObject();
        newQuestion.put("question",q.getQuestion());
        JSONArray answersArray = new JSONArray();
        answersArray.add(q.getAnswers());
        newQuestion.put("answers",answersArray);
        newQuestion.put("correct_ans",q.getCorrectAnswer());
        newQuestion.put("level",q.getLevel());
        newQuestion.put("team",q.getTeam());


        try(FileWriter file = new FileWriter("Questions.json")){
            JSONObject doc = new JSONObject();
            JSONArray questionArray = new JSONArray();
            JSONObject questObj = new JSONObject();
            for (Question question1 :HelloApplication.s.getQuestions()){
                JSONArray answersArray2 = new JSONArray();
                questObj.put("question", question1.getQuestion());
                answersArray2.add(question1.getAnswers());
                questObj.put("answers", answersArray2);
                questObj.put("correct_ans", question1.getCorrectAnswer());
                questObj.put("level", question1.getLevel());
                questObj.put("team", question1.getTeam());
                questionArray.add(questObj);

            }
            questionArray.add(newQuestion);
            doc.put("questions",questionArray);
            file.write(doc.toJSONString());
            file.flush();
            System.out.println(questionArray);
        }catch (IOException e){e.printStackTrace();}


    }
}
