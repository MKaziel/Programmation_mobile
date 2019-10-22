package com.example.iut_orsay;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.widget.Button;

public class Quizz extends AppCompatActivity {

    int compteut_quizz=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quizz);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Vous n'avez pas trouver toutes les bonnes réponses", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                validation_quizz();
                compteut_quizz = 0;
            }
        });
    }

    public void verif_rep(View v){
            Button btn = findViewById(v.getId());
            String txt = btn.getText().toString();
            Log.d("test",txt);
            if(txt.equals("Sponge Bob") || txt.equals("Alors on attend pas patrick ?")){
                if(compteut_quizz < 3)
                {
                    compteut_quizz = compteut_quizz + 1;
                }
            }
    }

    public void  validation_quizz(){
        Log.d("score","your score : " + this.compteut_quizz);
        if(compteut_quizz == 2 || compteut_quizz == 1){
            Intent i = new Intent(this,Reponse_quizz.class);
            i.putExtra("score",String.valueOf(compteut_quizz));
            startActivity(i);
        }
    }

    public void verif_quizz(View v){

    }


}
