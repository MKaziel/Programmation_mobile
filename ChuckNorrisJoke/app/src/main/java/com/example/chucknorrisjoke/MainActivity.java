package com.example.chucknorrisjoke;

import android.graphics.Bitmap;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    String joke = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setContentDescription("Bouton pour charger des blagues");
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Joke in progress", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                joke(view);

                jokePicture(view,"memes","memes everywhere");

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void joke(View v){

        Ion.with(this)
                .load("http://api.icndb.com/jokes/random")
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        joke = result.get("value").getAsJsonObject().get("joke").getAsString();
                        Log.d("Test joke :", joke);
                        TextView tv = findViewById(R.id.Text_joke);
                        tv.setText(joke);
                        tv.setContentDescription(joke);
                    }
                });
    }

    public void jokePicture(View v, final String txt1, final String txt2){
        String txt1_modif = txt1.replace(" ","_");
        String txt2_modif = txt2.replace(" ","_");

        Ion.with(this)
                .load("https://memegen.link/joker/"+txt1_modif+"/"+txt2_modif+"/goes_here.jpg?preview=true&watermark=none")
                .asBitmap()
                .setCallback(new FutureCallback<Bitmap>() {
                    @Override
                    public void onCompleted(Exception e, Bitmap result) {
                        if(e!=null){
                            TextView tv = findViewById(R.id.Text_joke);
                            tv.setText(e.getMessage());
                        }
                        else{
                            ImageView iv = findViewById(R.id.factImg);
                            iv.setImageBitmap(result);
                            iv.setContentDescription(txt1 + "                   " + txt2);
                        }
                    }
                });

    }



}
