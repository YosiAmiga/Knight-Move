package com.example.knightmove.Model;

import com.example.knightmove.HelloApplication;
import com.example.knightmove.controllers.QuestionsController;

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

    public boolean editQuestion(Question eq){
        if (eq!=null){
            for (Question question: questions){
                if (question.getId()== QuestionsController.selectedQuestion.getId()){
                    question.setQuestion(eq.getQuestion());
                    question.setAnswers(eq.getAnswers());
                    question.setLevel(eq.getLevel());
                    question.setCorrectAnswer(eq.getCorrectAnswer());
                    return true;
                }
                return false;
            }
        }
         return false;
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
