package com.example.knightmove.Model;

import java.util.HashMap;

public class SysData {
    private HashMap <Integer, Question> questions = new HashMap<>();


    public HashMap<Integer, Question> getQuestions() {
        return questions;
    }

    public void setQuestions(HashMap<Integer, Question> questions) {
        this.questions = questions;
    }

    public boolean addQuestion(Question q){
        if (q!=null&&this.getQuestions().containsKey(q.getId())){
            return this.getQuestions().put(q.getId(),q) == null;
        } else{
            return false;
        }
    }

    public boolean removeQuestion(Question q){
        if (q != null && this.getQuestions().containsKey(q.getId())) {
            return this.getQuestions().remove(q.getId()) != null;
        } else {
            return false;
        }
    }

}
