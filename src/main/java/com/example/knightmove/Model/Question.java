package com.example.knightmove.Model;


import java.io.FileReader;
import java.util.ArrayList;

public class Question {

    private String Question;
    private ArrayList<String> answers;
    private Integer correctAnswer;
    private Integer level;
    private String team;

    public Question(String question, ArrayList<String> answers, Integer correctAnswer, Integer level, String team) {
        this.Question = question;
        this.answers = answers;
        this.correctAnswer = correctAnswer;
        this.level = level;
        this.team = team;
    }

    public String getQuestion() {
        return Question;
    }

    public void setQuestion(String question) {
        Question = question;
    }

    public ArrayList<String> getAnswers() {
        return answers;
    }

    public void setAnswers(ArrayList<String> answers) {
        this.answers = answers;
    }

    public Integer getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(Integer correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

}
