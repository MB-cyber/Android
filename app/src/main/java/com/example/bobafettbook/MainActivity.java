package com.example.bobafettbook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    EditText edlogin,edPassword;
    Button blogin;
    TextView tvregister;
    ////data
    Button bDatas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        setContentView(R.layout.activity_main);
        edlogin=(EditText) findViewById(R.id.edLogin);
        edPassword=(EditText) findViewById(R.id.edPassword);
        blogin=(Button) findViewById(R.id.bLogin);
        tvregister=(TextView) findViewById(R.id.tvRegister);
        //
        bDatas= findViewById(R.id.bdatas);
        //

        bDatas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(MainActivity.this,ADDDATA.class);
                startActivity(intent);
            }
        });


        //
        blogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                            mAuth.signInWithEmailAndPassword(edlogin.getText().toString(), edPassword.getText().toString())
                        .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Log.d("tag", "signInWithEmail:success");
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    OpenQuiz();
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Log.w("TAG", "signInWithEmail:failure", task.getException());
                                    Toast.makeText(MainActivity.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });


  }

        });

        tvregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(MainActivity.this,Register.class);
                startActivity(intent);
            }
        });

    }

    private void OpenQuiz() {
        Intent intent =new Intent(this,Q1.class);
        startActivity(intent);
    }


}