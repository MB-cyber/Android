package com.example.bobafettbook;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Question {
    int id;
    String  question ;
    String TrueAnswer;
    int Image;
    String A1,A2,A3,A4;

    public Question(int id, String question, int image, String a1, String a2, String a3, String a4, String trueAnswer) {
        this.id = id;
        this.question = question;
        this.TrueAnswer = trueAnswer;
        Image = image;
        A1 = a1;
        A2 = a2;
        A3 = a3;
        A4 = a4;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", question='" + question + '\'' +
                ", TrueAnswer=" + TrueAnswer +
                ", Image=" + Image +
                ", A1='" + A1 + '\'' +
                ", A2='" + A2 + '\'' +
                ", A3='" + A3 + '\'' +
                ", A4='" + A4 + '\'' +
                '}';
    }
    public static ArrayList<Question>  getQuestion() {
        ArrayList<Question> quest = new ArrayList<>();
        Question Q1=new Question(1,"is boba feet ?",
                0,
                "Mandalorian",
                "Bounty Hunter",
                "both","Non",
                "Non");

        quest.add(Q1);
        Question Q2=new Question(2,"Was boba feet ?",
                0,
                "Mandalorian",
                "Bounty Hunter",
                "both","Hunter",
                "both");
        quest.add(Q2);
        Question Q3=new Question(3,"What animal eats Boba Fett? ?",
                0,
                "Mandalorian",
                "Bounty Hunter",
                "Sarlacc","Non",
                "Sarlacc");

        quest.add(Q3);

        return quest;
    }
}
