package com.example.iut_orsay;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reponse_quizz);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TextView tv = findViewById(R.id.textAnswerQuiz);

        Bundle bundle = getIntent().getExtras();

        if(bundle.getString("score") != null)
        {
            int index = Integer.valueOf(bundle.getString("score"));
            if(index == 2)tv.setText("Vous êtes extraordinaire ! Vous avez trouvez toutes les réponses");
            else if(index == 1)tv.setText("Bravo ! Vous avez validé une des deux bonnes réponses");

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    }
}
