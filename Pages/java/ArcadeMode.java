package com.example.yugandhara.dumbcharades;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.view.View.OnClickListener;


public class ArcadeMode extends Activity {

    String difficulty_level_java= "easy";
    String language_selected_java="hindi";

    RadioGroup language;
    RadioButton hindi;
    RadioButton english;
    RadioButton bhojpuri;
    RadioButton random;

    RadioGroup difficulty_level;
    RadioButton easy;
    RadioButton difficult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arcade_mode);

        addListenerLanguage();
        addListenerDifficulty();

    }

    private void addListenerDifficulty() {
        difficulty_level = (RadioGroup) findViewById(R.id.difficulty_level);
        easy = (RadioButton) findViewById(R.id.easy);
        difficult = (RadioButton) findViewById(R.id.difficult);
        easy.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                difficulty_level_java="easy";
            }
        });
        difficult.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                difficulty_level_java="difficult";
            }
        });
    }

    private void addListenerLanguage() {

        language = (RadioGroup) findViewById(R.id.language);
        english = (RadioButton) findViewById(R.id.english);
        hindi = (RadioButton) findViewById(R.id.hindi);
        bhojpuri = (RadioButton) findViewById(R.id.bhojpuri);
        random = (RadioButton) findViewById(R.id.random);

        english.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                language_selected_java = "english";
            }
        });
        hindi.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                language_selected_java="hindi";
            }
        });
        bhojpuri.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                language_selected_java="bhojpuri";
            }
        });
        random.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                language_selected_java = "random";
            }
        });
    }


    public void startGame(View v)
    {
        Intent in=new Intent(ArcadeMode.this,ArcadeMain.class);
        Bundle bundle = new Bundle();
        bundle.putString("difficulty_level", difficulty_level_java);
        bundle.putString("language_selected", language_selected_java);
        in.putExtras(bundle);
        startActivity(in);
        finish();
    }

}
