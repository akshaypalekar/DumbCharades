package com.example.yugandhara.dumbcharades;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.app.AlertDialog;

public class ArcadeMain extends Activity {

    String difficulty_level_java=null;
    String language_selected_java= null;
    ImageButton go;
    TextView display_movie;
    Integer movieId;

    RadioGroup language_java;
    RadioButton hindi_java;
    RadioButton english_java;
    RadioButton bhojpuri_java;
    RadioButton random_java;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arcade_main);


        go = (ImageButton) findViewById(R.id.fetch_movie);
        display_movie = (TextView) findViewById(R.id.display_movie);

        Bundle bundle = getIntent().getExtras();
        //Extract the dataÖ
        difficulty_level_java = bundle.getString("difficulty_level");
        language_selected_java= bundle.getString("language_selected");

        language_java = (RadioGroup) findViewById(R.id.language);
        english_java = (RadioButton) findViewById(R.id.english);
        hindi_java = (RadioButton) findViewById(R.id.hindi);
        bhojpuri_java = (RadioButton) findViewById(R.id.bhojpuri);
        random_java = (RadioButton) findViewById(R.id.random);

    }

    public void fetchMovie(View v)
    {
        language_java.setVisibility(View.GONE);

        DBAdapter db =  new DBAdapter(this);  // calls the constructor > which calls the onCreate()

        //---get a random movie ID---
        db.open();
        movieId = db.getMovieId(language_selected_java, difficulty_level_java);
        db.close();
        go.setBackgroundResource(R.drawable.nextmovie);


        //---get the movie by ID---
        db.open();
        Cursor c = db.getMovie(movieId);
        if (c.moveToFirst())
        {
            display_movie.setText(c.getString(0));
        }
        db.close();

    }


    public void changeLanguage(View v)
    {

        hindi_java.setChecked(false);
        english_java.setChecked(false);
        bhojpuri_java.setChecked(false);
        random_java.setChecked(false);
        language_java.setVisibility(View.VISIBLE);
        addListenerLanguage();
        go.setBackgroundResource(R.drawable.go);
    }

    private void addListenerLanguage() {

        english_java.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                language_selected_java = "english";
                hindi_java.setChecked(false);
                bhojpuri_java.setChecked(false);
                random_java.setChecked(false);
            }
        });
        hindi_java.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                language_selected_java = "hindi";
                english_java.setChecked(false);
                bhojpuri_java.setChecked(false);
                random_java.setChecked(false);
            }
        });
        bhojpuri_java.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                language_selected_java = "bhojpuri";
                hindi_java.setChecked(false);
                english_java.setChecked(false);
                random_java.setChecked(false);
            }
        });
        random_java.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                language_selected_java = "random";
                hindi_java.setChecked(false);
                english_java.setChecked(false);
                bhojpuri_java.setChecked(false);
            }
        });

    }

    public void mainMenu(View v)
    {

        new AlertDialog.Builder(this)
                .setIcon(R.drawable.appiconsmall)
                        // OR alertDialog.setIcon(R.drawable.delete);
                .setTitle("Main Menu")
                .setMessage("Are you sure?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Intent in=new Intent(ArcadeMain.this,HomeScreen.class);
                        in.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(in);
                        finish();
                    }

                })
                .setNegativeButton("No", null)
                .show();

    }

    public void exit(View v)
    {

        new AlertDialog.Builder(this)
                .setIcon(R.drawable.appiconsmall)
                        // OR alertDialog.setIcon(R.drawable.delete);
                .setTitle("Exit")
                .setMessage("Are you sure?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(getApplicationContext(), HomeScreen.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent.putExtra("EXIT", true);
                        startActivity(intent);
                    }

                })
                .setNegativeButton("No", null)
                .show();

    }

}