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
            Object obj = parser.parse(new FileReader("src/Questions.json"));
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
                String level = jsonObject2.get("level").toString();
                String correctans = jsonObject2.get("correct_ans").toString();
//                System.out.println("question " + q);
//                System.out.println("answers " + answers);
//                System.out.println("team " + team);
//                System.out.println("level " + level);
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
        for (String answer :q.getAnswers()) {
            answersArray.add(answer);
        }
        newQuestion.put("answers",answersArray);
        newQuestion.put("correct_ans",q.getCorrectAnswer());
        newQuestion.put("level",q.getLevel());
        newQuestion.put("team",q.getTeam());


        try(FileWriter file = new FileWriter("src/Questions.json")){
            JSONObject doc = new JSONObject();
            JSONArray questionArray = new JSONArray();
            JSONObject questObj = new JSONObject();
            for (Question question1 :HelloApplication.s.getQuestions()){
                JSONArray answersArray2 = new JSONArray();
                questObj.put("question", question1.getQuestion());
                for (String answer :question1.getAnswers()) {
                    answersArray2.add(answer);
                }
                questObj.put("answers", answersArray2);
                questObj.put("correct_ans", question1.getCorrectAnswer());
                questObj.put("level", question1.getLevel());
                questObj.put("team", question1.getTeam());
                questionArray.add(questObj);
                System.out.println(question1);

            }
            questionArray.add(newQuestion);
            doc.put("questions",questionArray);
            file.write(doc.toJSONString());
            file.flush();
        }catch (IOException e){e.printStackTrace();}

}

        public static void updateJson(){

            try(FileWriter file = new FileWriter("src/Questions.json")){
                JSONObject doc = new JSONObject();
                JSONArray questionArray = new JSONArray();

                for (Question question1 :HelloApplication.s.getQuestions()){
                    JSONObject questObj = new JSONObject();
                    System.out.println("start writing to json");
                    System.out.println("The Question " + question1);
                    JSONArray answersArray2 = new JSONArray();
                    questObj.put("question", question1.getQuestion());
                    for (String answer :question1.getAnswers()) {
                        answersArray2.add(answer);
                    }
                    questObj.put("answers", answersArray2);
                    questObj.put("correct_ans", question1.getCorrectAnswer());
                    questObj.put("level", question1.getLevel());
                    questObj.put("team", question1.getTeam());
                    questionArray.add(questObj);

                }
                doc.put("questions",questionArray);
                file.write(doc.toJSONString());
                file.flush();
            }catch (IOException e){e.printStackTrace();}
        }
}