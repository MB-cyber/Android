package com.example.bobafettbook;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;


public class Score extends AppCompatActivity {
    Bundle bundle ;
    int position;
    int Scores ;
    String Total;
    TextView score;
    ProgressBar progress;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        progress=findViewById(R.id.ProgressBar);
        score= findViewById(R.id.tv_score);

        bundle = getIntent().getExtras();
        position=bundle.getInt("position");
        Scores = bundle.getInt("Scores");
        Total=Scores+"/3";
        progress.setProgress(Scores);

        score.setText(Total);




       // Scores=getIntent().getExtras().getInt("Scores");


    }
    public void onBackPressed() {


        super.onBackPressed();
        Intent intent =new Intent(this,ADDDATA.class);
        startActivity(intent);

    }
}
