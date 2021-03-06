package com.example.bobafettbook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;

import java.util.List;

public class Q1 extends AppCompatActivity {
int Scores=0;
int position=1;
    List<Question> Questions = Question.getQuestion();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q1);
        TextView TVQuestion =(TextView) findViewById(R.id.TVQuestion);
        RadioGroup R_btn= (RadioGroup) findViewById(R.id.RG);
        Button next_btn= (Button) findViewById(R.id.Bnext);
        RadioButton RB1 = (RadioButton) findViewById(R.id.RB1);
        RadioButton RB2 = (RadioButton) findViewById(R.id.RB2);
        RadioButton RB3 = (RadioButton) findViewById(R.id.RB3);
        RadioButton RB4 = (RadioButton) findViewById(R.id.RB4);


//get data


        //   Log.i("Questions are ",Questions.toString());


        int question_id;
        Question question;

        question = Questions.get(position-1);
        question_id=question.id;
        TVQuestion.setText(question.quest);
        RB1.setText(question.A1);
        RB2.setText(question.A2);
        RB3.setText(question.A3);
        RB4.setText(question.A4);


        Log.i("Questions are ",question.toString());





        next_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RadioButton Rb = (RadioButton) findViewById(R_btn.getCheckedRadioButtonId());

                if (Rb.getText().equals(question.TrueAnswer) && question_id == position) {

                    Scores += 1;

                }


                OpenScores();


            }
        });

    }

    public void onBackPressed() {
        super.onBackPressed();
        Intent intent =new Intent(this,Q1.class);
        startActivity(intent);

    }


    private void OpenScores(){

        Intent intent =new Intent(this,Q2.class);
        intent.putExtra("Scores",Scores);
        intent.putExtra("position", position+1);

        startActivity(intent);
    }
}