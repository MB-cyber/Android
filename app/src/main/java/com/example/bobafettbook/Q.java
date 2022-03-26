package com.example.bobafettbook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class Q extends AppCompatActivity {
    FirebaseStorage storage ;


    StorageReference storageRef ;
    String Q="q1";
    StorageReference pathReference;


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
        question = Questions.get(0);

        TVQuestion.setText(question.quest);
        RB1.setText(question.A1);
        RB2.setText(question.A2);
        RB3.setText(question.A3);
        RB4.setText(question.A4);








            next_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    RadioButton Rb = (RadioButton) findViewById(R_btn.getCheckedRadioButtonId());
                    Log.i("position ", String.valueOf(position));

                        if (Rb.getText().equals(Questions.get(position).TrueAnswer)) {

                            Scores+=1; ;

                        }
                        position+=1;
                    Log.i("Scores123 ", String.valueOf(Scores));
                    if(position<Questions.size()){
                        Log.i("Scores ", String.valueOf(Scores));
                        Question question;

                        question = Questions.get(position);

                        TVQuestion.setText(question.quest);
                        R_btn.clearCheck();
                        RB1.setText(question.A1);

                        RB2.setText(question.A2);

                        RB3.setText(question.A3);

                        RB4.setText(question.A4);

                        Log.i("position ", String.valueOf(position));
                    }else
                        OpenScores();






                }
            });
        }



    public void onBackPressed() {
        super.onBackPressed();
        Intent intent =new Intent(this,ADDDATA.class);
        startActivity(intent);

    }


    private void OpenScores() {

        Intent intent = new Intent(this, Score.class);
        intent.putExtra("Scores",Scores);
        intent.putExtra("position",position+1);
        startActivity(intent);
    }

    public void GetImages(){
  storage = FirebaseStorage.getInstance();
  storageRef= storage.getReference() ;String Q="q1";
  pathReference = storageRef.child("Questions/QSM1/"+Q+".jpg");


    }
}