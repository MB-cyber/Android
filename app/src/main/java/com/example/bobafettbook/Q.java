package com.example.bobafettbook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Q extends AppCompatActivity {
    FirebaseStorage storage = FirebaseStorage.getInstance();
    StorageReference storageRef = storage.getReference();
    String Q="q1";
    StorageReference pathReference;
    ImageView Images;

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
        Images= findViewById(R.id.imgQuestion);

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
        setImages(question.id,Images);








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
                        setImages(question.id,Images);

                        Log.i("position ", String.valueOf(position));
                    }else

                        OpenScores();






                }
            });
        }



    public void onBackPressed() {
        super.onBackPressed();
        Intent intent =new Intent(this,MainActivity.class);
        startActivity(intent);

    }


    private void OpenScores() {

        Intent intent = new Intent(this, Score.class);
        intent.putExtra("Scores",Scores);
        intent.putExtra("position",position+1);
        startActivity(intent);
    }


    public void setImages(int id, ImageView imgview){
        File localFile;
        try {
            pathReference = storageRef.child("/Questions/QSM1/q"+id+".jpg");
            localFile = File.createTempFile("imgsq1","jpg");

            pathReference.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                    // Local temp file has been created
                    Picasso.get().load(localFile).into(imgview);
                    Log.i("imageerrr ", "well done");
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception exception) {
                    Log.i("imageeee ", "well bad");                }
            });


        } catch (IOException e) {
            e.printStackTrace();
        }



    }
}