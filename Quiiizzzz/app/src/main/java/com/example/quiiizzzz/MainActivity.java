package com.example.quiiizzzz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbInit();
        dbQuery();
    }


    private static final String DBNAME = "questiondb";


    public void onClick_quizz(View view){
        Intent i = new Intent(this, com.example.quiiizzzz.Quizzz.class);
        startActivity(i);

    }


    public void dbInit() {
            SQLiteDatabase db = openOrCreateDatabase(DBNAME, MODE_PRIVATE, null);

            db.execSQL("DROP TABLE IF EXISTS questions");


            db.execSQL("CREATE TABLE questions (" +
                    "    id      INTEGER             NOT NULL PRIMARY KEY AUTOINCREMENT," +
                    "    question  VARCHAR(40)     NOT NULL," +
                    "    rep1  VARCHAR(40)     NOT NULL," +
                    "    rep2  VARCHAR(40)     NOT NULL," +
                    "    rep3   VARCHAR(40)     NOT NULL," +
                    "    answer INEGER(10)     NOT NULL)");


            ContentValues vals = new ContentValues();
            vals.put("question", "1 & 1 = ?");
            vals.put("rep1", "1");
            vals.put("rep2", "2");
            vals.put("rep3", "11");
            vals.put("answer","11");

            long res = db.insert("questions", null, vals);

            vals.put("question", "Que faire dans cette situation ?");
            vals.put("rep1", "J'accélère");
            vals.put("rep2", "Je m'arrète");
            vals.put("rep3", "Je klaxonne");
            vals.put("answer","Je klaxonne");

            long res2 = db.insert("questions", null, vals);

            vals.put("question", "Qu'est-ce qui est jaune et qui vit dans un ananas dans la mer?");
            vals.put("rep1", "Zakia ?");
            vals.put("rep2", "Bob le bricoleur?");
            vals.put("rep3", "Bob l'éponge?");
            vals.put("answer","Bob l'éponge?");
            long res3 = db.insert("questions", null, vals);


            Log.d("dbInit", "insert: " + res);
            Log.d("dbInit", "insert: " + res2);
            Log.d("dbInit", "insert: " + res3);

            Log.d("dbInit", "initialized");

            TableLayout tab = findViewById(R.id.table);
            tab.removeAllViews();


        }


        public void dbQuery() {
            Log.d("dbQuery", "query start");

            SQLiteDatabase db = openOrCreateDatabase(DBNAME, MODE_PRIVATE, null);
            TableLayout tab = findViewById(R.id.table);

            // remove rows from previous query
            tab.removeAllViews();



            // execute SQL query
            Cursor cur;
            try {
                cur = db.rawQuery("Select * from questions", null);
            } catch(SQLiteException e) {
                Toast.makeText(this, "SQL error", Toast.LENGTH_LONG).show();
                Log.d("dbQuery", "SQL error: " + e);
                return;
            }

            // first row of column names
            TableRow colNameRow = new TableRow(this);
            for(String colName : cur.getColumnNames()) {
                Log.d("dbQuery","column name: " + colName);

                TextView colNameView = new TextView(this);
                colNameView.setText(colName);
                colNameView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 16);
                colNameView.setTypeface(null, Typeface.BOLD);
                colNameView.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, TableRow.LayoutParams.FILL_PARENT, 1));
                colNameRow.addView(colNameView);
            }
            tab.addView(colNameRow);

            // data rows
            boolean gray = true;
            if(cur.moveToFirst()) {
                do {
                    Log.d("dbQuery", "start new row");
                    TableRow dataRow = new TableRow(this);

                    for(String colName : cur.getColumnNames()) {
                        Log.d("dbQuery", "value: " + cur.getString(cur.getColumnIndex(colName)));

                        TextView dataView = new TextView(this);
                        dataView.setText(cur.getString(cur.getColumnIndex(colName)));
                        dataView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 16);
                        dataView.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, TableRow.LayoutParams.FILL_PARENT, 1));
                        dataRow.addView(dataView);
                    }
                    if(gray)
                        dataRow.setBackgroundColor(Color.LTGRAY);
                    gray = !gray;
                    tab.addView(dataRow);
                } while(cur.moveToNext());
                cur.close();
            }

            Log.d("dbQuery", "query end");
        }
    }
