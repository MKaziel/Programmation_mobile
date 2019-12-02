package com.example.quiiizzzz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class Reponse_quizzz extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reponse_quizzz);

        TextView tv = findViewById(R.id.textAnswerQuiz);

        Bundle bundle = getIntent().getExtras();

        if(bundle.getString("result") != null)
        {
           String resultats = bundle.getString("result");

            Log.d("Test résult : ", resultats);

            tv.setText("Ton score est : " + resultats);
        }
    }


}
