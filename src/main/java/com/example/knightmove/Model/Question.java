package com.example.knightmove.Model;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class Question {
    private static int idCounter = 1;
    private Integer id;
    private String question;
    private ArrayList<String> answers;
    private Integer correctAnswer;
    private Integer level;
    private String team;

    private String rightAnswer;

    public Question(String question, ArrayList<String> answers, Integer correctAnswer, Integer level, String team) {
        this.id=idCounter++;
        this.question = question;
        this.answers = answers;
        this.correctAnswer = correctAnswer;
        this.level = level;
        this.team = team;
        theRightAnswer();
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        question = question;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setRightAnswer(String rightAnswer) {
        this.rightAnswer = rightAnswer;
    }

    public String getRightAnswer() {
        return rightAnswer;
    }

    public void theRightAnswer(){
        String ans = new String ();
        switch (this.correctAnswer){
            case 1:
                this.rightAnswer = answers.get(0);
                break;
            case 2:
                this.rightAnswer = answers.get(1);
                break;
            case 3:
                this.rightAnswer = answers.get(2);
                break;
            case 4:
                this.rightAnswer = answers.get(3);
                break;
        }

    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", question='" + question + '\'' +
                ", answers=" + answers +
                ", correctAnswer=" + correctAnswer +
                ", level=" + level +
                ", team='" + team + '\'' +
                ", rightAnswer='" + rightAnswer + '\'' +
                '}';
    }
}
