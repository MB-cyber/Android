package com.example.bobafettbook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ADDDATA extends AppCompatActivity {
    Button bData,getbData;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    List<Question> quest=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adddata);


        bData= findViewById(R.id.bdata);
        getbData=findViewById(R.id.bgetdata);


        bData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create a new user with a first, middle, and last name

                Question Q1=new Question(1,"is boba feet ?",
                        0,
                        "Mandalorian",
                        "Bounty Hunter",
                        "both","Non",
                        "Non");


                Question Q2=new Question(2,"Was boba feet ?",
                        0,
                        "Mandalorian",
                        "Bounty Hunter",
                        "both","Hunter",
                        "both");

                Question Q3=new Question(3,"What animal eats Boba Fett? ?",
                        0,
                        "Mandalorian",
                        "Bounty Hunter",
                        "Sarlacc","Non",
                        "Sarlacc");

// Add a new document with a generated ID
                db.collection("Questions").document("Q1")
                        .set(Q1);
                db.collection("Questions").document("Q2")
                        .set(Q2);
                db.collection("Questions").document("Q3")
                        .set(Q3);

            }
        });

        getbData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Question.setQuestion(new DAO() {
                    @Override
                    public void onCallback(List<Question> QuestionList) {

                        quest=QuestionList;
                        OpenScores(quest);
                        Log.d("TAG5555", ""+QuestionList);
                    }
                });






            }
        });

    }

    private void OpenScores(List<Question> quest) {

        Intent intent = new Intent(this, Q.class);
        Log.d("openscore", ""+quest);
        intent.putExtra("quest", (Serializable) quest);


        startActivity(intent);
    }
}