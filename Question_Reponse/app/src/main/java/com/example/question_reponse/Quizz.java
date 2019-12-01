package com.example.question_reponse;

import android.content.Intent;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class Quizz extends AppCompatActivity {

    int compteut_quizz=0;
    TextView question;
    Button rep1;
    Button rep2;
    Button rep3;
    String reponse;
    int nbquestions;
    int point = 0;
    Intent i;

    private static final String DBNAME = "questiondb";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quizz);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        question = findViewById(R.id.textView);
        rep1 = findViewById(R.id.button);
        rep2 = findViewById(R.id.button2);
        rep3 = findViewById(R.id.button3);
        compteut_quizz = compteut_quizz + 1;
        Log.d("Cpt", String.valueOf(compteut_quizz));
        Log.d("nbQuestions", String.valueOf(nbquestions));
        Log.d("Create", "Création");
        dbQuery();
        Log.d("TEST","Après DBQUERY");
        i = new Intent(this, Reponse_quizz.class);

       rep1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(rep1.getText() == reponse && compteut_quizz <= nbquestions){
                    compteut_quizz = compteut_quizz + 1;
                    point += 1;
                    dbQuery();
                    Log.d("POINT",String.valueOf(point));
                    Log.d("Rep","OK1");
                } else if(rep1.getText() != reponse && compteut_quizz <= nbquestions){
                    compteut_quizz = compteut_quizz + 1;
                    Log.d("POINT",String.valueOf(point));
                    Log.d("Rep","PAS OK1");
                    dbQuery();
                } if(compteut_quizz > nbquestions){
                    Log.d("Test résult : ", String.valueOf(point));
                    i.putExtra("result",point);
                    startActivity(i);
                }
            }
        });
       rep2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Button 1", "Clique sur le bouton "+ rep2.getText() + reponse + compteut_quizz + nbquestions);
                if(rep2.equals(reponse) && compteut_quizz <= nbquestions){
                    compteut_quizz = compteut_quizz + 1;
                    point += + 1;
                    dbQuery();
                    Log.d("POINT",String.valueOf(point));
                    Log.d("Rep","OK2");
                } else if(!rep2.equals(reponse) && compteut_quizz <= nbquestions){
                    compteut_quizz = compteut_quizz + 1;
                    Log.d("POINT",String.valueOf(point));
                    Log.d("Rep","PAS OK2");
                    dbQuery();
                } if(compteut_quizz > nbquestions){
                    Log.d("POINT",String.valueOf(point));
                    i.putExtra("result",point);
                    startActivity(i);
                }
            }
        });
       rep3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Button 1", "Clique sur le bouton "+ rep3.getText() + reponse + compteut_quizz + nbquestions);
                if(rep3.getText() == reponse && compteut_quizz <= nbquestions){
                    compteut_quizz = compteut_quizz + 1;
                    point += 1;
                    dbQuery();
                    Log.d("POINT", String.valueOf(point));
                    Log.d("Rep","OK3");
                } else if(rep3.getText() != reponse && compteut_quizz <= nbquestions){
                    compteut_quizz = compteut_quizz + 1;
                    Log.d("POINT",String.valueOf(point));
                    Log.d("Rep","PAS OK3");
                    dbQuery();
                } if(compteut_quizz > nbquestions){
                    if(point!=0)
                    {
                        Log.d("POINT",String.valueOf(point));
                        i.putExtra("result",point);
                        startActivity(i);
                    }
                }
            }
        });



    }

    public void dbQuery() {

        SQLiteDatabase db = openOrCreateDatabase(DBNAME, MODE_PRIVATE, null);

        Log.d("dbQuery", "query start");



        // execute SQL query
        Cursor cur_question;
        Cursor cur_rep1;
        Cursor cur_rep2;
        Cursor cur_rep3;
        Cursor cur_answer;
        Cursor cur_nbquestions;

        try {
            cur_question = db.rawQuery("Select question from questions where id = "+compteut_quizz, null);
            cur_rep1 = db.rawQuery("Select rep1 from questions where id = "+compteut_quizz, null);
            cur_rep2 = db.rawQuery("Select rep2 from questions where id ="+ compteut_quizz, null);
            cur_rep3 = db.rawQuery("Select rep3 from questions where id ="+ compteut_quizz, null);
            cur_answer = db.rawQuery("Select answer from questions where id ="+ compteut_quizz, null);
            cur_nbquestions = db.rawQuery("Select Count(*) from questions", null);
        } catch(SQLiteException e) {
            Toast.makeText(this, "SQL error", Toast.LENGTH_LONG).show();
            Log.d("dbQuery", "SQL error: " + e);
            return;
        }

        if(cur_question.moveToFirst()) {
            question.setText(cur_question.getString(cur_question.getColumnIndex("question")));
        }
        if(cur_rep1.moveToFirst()) {
            rep1.setText(cur_rep1.getString(cur_rep1.getColumnIndex("rep1")));
        }
        if(cur_rep2.moveToFirst()) {
            rep2.setText(cur_rep2.getString(cur_rep2.getColumnIndex("rep2")));
        }
        if(cur_rep3.moveToFirst()) {
            rep3.setText(cur_rep3.getString(cur_rep3.getColumnIndex("rep3")));
        }
        if(cur_answer.moveToFirst()) {
            reponse = cur_answer.getString(cur_answer.getColumnIndex("answer"));
        }
        cur_nbquestions.moveToFirst();
        nbquestions = cur_nbquestions.getInt(0);






        Log.d("dbQuery", "query end");
    }



}
