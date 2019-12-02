package com.example.question_reponse;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Quizz extends AppCompatActivity {



    Integer resultat_question = 0;
    int compteut_quizz=0;
    TextView question;
    Button rep1;
    Button rep2;
    Button rep3;
    String reponse;
    int nbquestions;
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
        setResultat_question(1);

        Log.d("Cpt", String.valueOf(compteut_quizz));
        Log.d("nbQuestions", String.valueOf(nbquestions));
        Log.d("Create", "Création");
        dbQuery();
        Log.d("TEST","Après DBQUERY");
        i = new Intent(this, Reponse_quizz.class);
    }

    public void onClickQuizz(View v) {
        Button btn = findViewById(v.getId());
        String txt = btn.getText().toString();

        if(txt == reponse && compteut_quizz <= nbquestions){
            compteut_quizz = compteut_quizz + 1;
            int i = getResultat_question() + 1;
            setResultat_question(i);
            dbQuery();
            Log.d("POINT",String.valueOf(resultat_question));
            Log.d("Rep","OK1");
        } else if(txt != reponse && compteut_quizz <= nbquestions){
            compteut_quizz = compteut_quizz + 1;
            Log.d("POINT",String.valueOf(resultat_question));
            Log.d("Rep","PAS OK1");
            dbQuery();
        } if(compteut_quizz > nbquestions){
            if(resultat_question!=0){
                Log.d("Test résult : ", String.valueOf(resultat_question-1));
                i.putExtra("result",String.valueOf(resultat_question-1));
                startActivity(i);
            }

        }
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

    public void setResultat_question(int resultat_question) {
        this.resultat_question = resultat_question;
    }

    public int getResultat_question() {
        return resultat_question;
    }

}
