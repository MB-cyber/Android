package com.example.bobafettbook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Q extends AppCompatActivity {
    public void setScores(int scores) {
        Scores = scores;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    Bundle bundle ;
    int Scores=0;
    int position=0;
    List<Question> Questions ;
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

        bundle = getIntent().getExtras();
//get data
        Questions= (ArrayList<Question>) getIntent().getSerializableExtra("quest");


          Log.i("Questions are ",Questions.toString());



        Question question;
        question = Questions.get(position);

        TVQuestion.setText(question.quest);
        RB1.setText(question.A1);
        RB2.setText(question.A2);
        RB3.setText(question.A3);
        RB4.setText(question.A4);








            next_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    RadioButton Rb = (RadioButton) findViewById(R_btn.getCheckedRadioButtonId());


                    if (Rb.getText().equals(question.TrueAnswer)) {

                       setScores(Scores ++); ;

                    }
                    setPosition(position++);




                }
            });
        }



    public void onBackPressed() {
        super.onBackPressed();
        Intent intent =new Intent(this,ADDDATA.class);
        startActivity(intent);

    }


    private void OpenScores(){

        Intent intent =new Intent(this,Score.class);
        intent.putExtra("Scores",Scores);
        intent.putExtra("position", position+1);

        startActivity(intent);
    }
}