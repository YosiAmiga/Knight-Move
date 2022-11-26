package com.example.knightmove.Model;

import java.util.HashMap;
import java.util.HashSet;

public class SysData {
    private HashSet<Question> questions = new HashSet<>();


    public HashSet<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(HashSet<Question> questions) {
        this.questions = questions;
    }

    public boolean addQuestion(Question q){
        if (q!=null){
            Json.writeToJson(q);
            this.getQuestions().add(q);
            return true;
        } else{
            return false;
        }
    }

    public boolean removeQuestion(Question q){
        if (q != null ) {
            this.getQuestions().remove(q);
            return true;
        } else {
            return false;
        }
    }

}
