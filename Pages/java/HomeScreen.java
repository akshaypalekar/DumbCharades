package com.example.yugandhara.dumbcharades;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


public class HomeScreen extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Exits app if the exit button is clicked
        if (getIntent().getBooleanExtra("EXIT", false)) {
            finish();
        }
        setContentView(R.layout.activity_home_screen);

        //---Clears USED column in DB---
        DBAdapter db =  new DBAdapter(this);  // calls the constructor > which calls the onCreate()
        db.open();
        db.updateMovie();
        db.close();



    }


    public void gameMode(View v)
    {
        Intent in=new Intent(HomeScreen.this,GameMode.class);
        startActivity(in);
    }

    public void arcadeMode(View v)
    {
        Intent in=new Intent(HomeScreen.this,ArcadeMode.class);
        startActivity(in);
    }


}
