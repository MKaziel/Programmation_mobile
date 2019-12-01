package com.example.question_reponse;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class Reponse_quizz extends AppCompatActivity {

    String resultats;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reponse_quizz);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TextView tv = findViewById(R.id.textAnswerQuiz);

        Bundle bundle = getIntent().getExtras();

        if(bundle.getString("result") != null)
        {
            resultats = bundle.getString("result");

            Log.d("Test résult : ", resultats);

            tv.setText("Ton score est : " + resultats);
        }



    }

    }

