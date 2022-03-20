package com.example.bobafettbook;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class Question {

    int id;
    String quest;

    public void setId(int id) {
        this.id = id;
    }

    public void setQuest(String quest) {
        this.quest = quest;
    }

    public void setTrueAnswer(String trueAnswer) {
        TrueAnswer = trueAnswer;
    }

    public void setImage(int image) {
        Image = image;
    }

    public void setA1(String a1) {
        A1 = a1;
    }

    public void setA2(String a2) {
        A2 = a2;
    }

    public void setA3(String a3) {
        A3 = a3;
    }

    public void setA4(String a4) {
        A4 = a4;
    }

    public int getId() {
        return id;
    }

    public String getQuest() {
        return quest;
    }

    public String getTrueAnswer() {
        return TrueAnswer;
    }

    public int getImage() {
        return Image;
    }

    public String getA1() {
        return A1;
    }

    public String getA2() {
        return A2;
    }

    public String getA3() {
        return A3;
    }

    public String getA4() {
        return A4;
    }

    String TrueAnswer;

    int Image;
    String A1,A2,A3,A4;


    public Question(){};
    public Question(int id, String quest, int image, String a1, String a2, String a3, String a4, String trueAnswer) {
        this.id = id;
        this.quest = quest;
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
                ", question='" + quest + '\'' +
                ", TrueAnswer=" + TrueAnswer +
                ", Image=" + Image +
                ", A1='" + A1 + '\'' +
                ", A2='" + A2 + '\'' +
                ", A3='" + A3 + '\'' +
                ", A4='" + A4 + '\'' +
                '}';
    }
    public static void setQuestion(DAO Dao) {

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("Questions")

                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {

                       task.getResult().toObjects(Question.class);
                            List<Question> QuestionList = new ArrayList<>();
                            for(QueryDocumentSnapshot doc : task.getResult()) {
                                Question q = doc.toObject(Question.class);

                                QuestionList.add(q);
                            }
                            Dao.onCallback(QuestionList);


                        } else {
                            Log.i("TAG", "Error getting documents: ", task.getException());
                        }
                    }
                });

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
