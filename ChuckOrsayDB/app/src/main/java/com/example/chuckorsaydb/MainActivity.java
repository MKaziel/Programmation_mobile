package com.example.chuckorsaydb;

import android.content.ContentValues;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.util.Log;
import android.util.TypedValue;
import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;

    private static final String DBNAME = "ZakiaDb";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow,
                R.id.nav_tools, R.id.nav_share, R.id.nav_send)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        dbInit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    public void dbInit() {
        SQLiteDatabase db = openOrCreateDatabase(DBNAME, MODE_PRIVATE, null);

        db.execSQL("DROP TABLE IF EXISTS Questions");
        db.execSQL("DROP TABLE IF EXISTS Reponse");


        db.execSQL("CREATE TABLE Questions (" +
                "    id      INTEGER             NOT NULL PRIMARY KEY AUTOINCREMENT," +
                "    texte_question  VARCHAR2(100)     NOT NULL)");

        db.execSQL("CREATE TABLE Reponse (" +
                "    id      INTEGER             NOT NULL PRIMARY KEY AUTOINCREMENT," +
                "    id_question  INTEGER     NOT NULL," +
                "    texte_reponse   VARCHAR2(100)     NOT NULL)");

        ContentValues vals = new ContentValues();
        vals.put("texte_question", "mmmmmmm?");
        db.insert("Questions", null, vals);

        vals.clear();
        vals.put("id_question",1);
        vals.put("texte_reponse","tttttttt!");
        db.insert("Reponse",null,vals);

        vals.clear();
        vals.put("id_question",1);
        vals.put("texte_reponse","tttttttt");
        db.insert("Reponse",null,vals);

        vals.clear();
        vals.put("texte_question", "ttttttttt?");
        db.insert("Questions", null, vals);

        vals.clear();
        vals.put("id_question",2);
        vals.put("texte_reponse","mmmmmmm");
        db.insert("Reponse",null,vals);

        vals.clear();
        vals.put("id_question",2);
        vals.put("texte_reponse","mmmmmmm!");
        db.insert("Reponse",null,vals);

        Log.d("dbInit", "initialized");

        TableLayout tab = findViewById(R.id.table);
        tab.removeAllViews();

        EditText queryText = findViewById(R.id.queryText);
        queryText.setText("SELECT * FROM Questions");
    }

    public void dbQuery(View v) {
        Log.d("dbQuery", "query start");

        SQLiteDatabase db = openOrCreateDatabase(DBNAME, MODE_PRIVATE, null);
        TableLayout tab = findViewById(R.id.table);

        // remove rows from previous query
        tab.removeAllViews();

        // fetch query string
        EditText queryText = findViewById(R.id.queryText);
        String query = queryText.getText().toString();

        // execute SQL query
        Cursor cur;
        try {
            cur = db.rawQuery(query, null);
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

    protected void onPause() {
        super.onPause();

        Switch switch1 = findViewById(R.id.switch1);
        Log.d("switch1", "paused: " + switch1.isChecked());

        SharedPreferences prefs = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor ed = prefs.edit();
        ed.putBoolean("switch1", switch1.isChecked());
        ed.apply();
    }
}
