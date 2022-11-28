package com.example.knightmove.Model;

import java.util.HashSet;

public class SysData {
    private HashSet<Question> questions;

    public SysData() {
        this.questions = Json.readFromJSON();
    }

    public HashSet<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(HashSet<Question> questions) {
        this.questions = questions;
    }

    public boolean addQuestion(Question q){
        if (q!=null){
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

    public HashSet<Question> getQuestionsByLevel(Integer level){ //grouping the questions by levels
        HashSet <Question> questionsByLevel = new HashSet<>();
        for (Question q: questions){
            if (q.getLevel()==level)
                questionsByLevel.add(q);
        }
        return questionsByLevel;
    }
}
