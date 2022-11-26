//package com.example.knightmove.controllers;
//
//import com.example.knightmove.Model.Question;
//import org.json.simple.DeserializationException;
//import org.json.simple.JsonArray;
//import org.json.simple.JsonObject;
//import org.json.simple.Jsoner;
//
//import java.io.FileReader;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.Iterator;
//
//public class QuestionLogic {
//    private static QuestionLogic instance;
//    private HashMap <Integer, Question> questions = new HashMap<>();
//
//    public QuestionLogic() {
//    }
//    public static QuestionLogic getInstance(){
//        if (instance == null){
//            instance = new QuestionLogic();
//        }
//
//        return instance;
//    }
//
//    public ArrayList<Question> getJson(){
//        ArrayList<Question> arr = new ArrayList();
//
//        try {
//            Throwable var2 = null;
//            Object var3 = null;
//
//            try {
//                FileReader reader = new FileReader("Questions.json");
//
//                try {
//                    JsonObject doc = (JsonObject) Jsoner.deserialize(reader);
//                    JsonArray arrEntity = (JsonArray)doc.get("Questions.json");
//                    Iterator<Object> iterator = arrEntity.iterator();
//
//                    while(iterator.hasNext()) {
//                        JsonObject obj = (JsonObject)iterator.next();
//                        JsonArray answersArray = (JsonArray)obj.get("answers");
//                        ArrayList<String> answers= new ArrayList<>();
//                        Iterator<Object> iter2 = answersArray.iterator();
//                        while (iter2.hasNext()){
//                            answers.add((String) iter2.next());
//                        }
//                        Question entity = new Question((String)obj.get("question"), answers, (Integer) obj.get("correct_ans"), (Integer) obj.get("level"), (String) obj.get("team"));
//                        arr.add(entity);
//                    }
//                } finally {
//                    if (reader != null) {
//                        reader.close();
//                    }
//
//                }
//            } catch (Throwable var17) {
//                if (var2 == null) {
//                    var2 = var17;
//                } else if (var2 != var17) {
//                    var2.addSuppressed(var17);
//                }
//
//                throw var2;
//            }
//        } catch (Throwable var18) {
//            var18.printStackTrace();
//        }
//
//        return arr;
//    }
//}
