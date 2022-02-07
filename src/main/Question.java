package main;

import java.util.ArrayList;

public class Question {

    private String question;
    private int correctIndex;
    private ArrayList<String> choices;

    public Question(String question, int correctIndex, ArrayList<String> choices) {
        this.question = question;
        this.correctIndex = correctIndex;
        this.choices = choices;
    }

    public Question(){
        // empty constructor
    }

    @Override
    public String toString() {
        return "Question{" +
                "question='" + question + '\'' +
                ", correctChoice='" + correctIndex + '\'' +
                ", choices=" + choices +
                '}';
    }

    // setters and getters

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public ArrayList<String> getChoices() {
        return choices;
    }

    public void setChoices(ArrayList<String> choices) {
        this.choices = choices;
    }

    public int getCorrectIndex() {
        return correctIndex;
    }

    public void setCorrectIndex(int correctIndex) {
        this.correctIndex = correctIndex;
    }
}
