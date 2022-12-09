package com.example.knightmove.Model;

import com.example.knightmove.HelloApplication;

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
            this.questions.add(q);
            return true;
        } else{
            return false;
        }
    }

    public boolean removeQuestion(Question q){
        if (q != null ) {
            this.questions.remove(q);
            return true;
        } else {
            return false;
        }
    }

    public boolean editQuestion(Question q, Question eq){
        if (q!=null){
            for (Question question: this.questions){
                if (question.getId()==q.getId()){
                    q.setQuestion(eq.getQuestion());
                    q.setAnswers(eq.getAnswers());
                    q.setLevel(eq.getLevel());
                    q.setCorrectAnswer(eq.getCorrectAnswer());
                    return true;
                }
            }

            return false;
        }
         return false;
    }

    public Question getQuestionById(Question q){
        for (Question u:questions){
            if (u.getId()==q.getId())
                return u;
        }
        return null;
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
