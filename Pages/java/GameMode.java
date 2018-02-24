package com.example.yugandhara.dumbcharades;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.view.View.OnClickListener;


public class GameMode extends Activity {

    RadioGroup no_of_teams;
    RadioButton two_teams;
    RadioButton three_teams;
    RadioButton four_teams;

    RadioGroup game_time;
    RadioButton timed;
    RadioButton not_timed;

    RadioGroup time_details;
    RadioButton two_mins;
    RadioButton three_mins;
    RadioButton five_mins;

    RadioGroup language;
    RadioButton hindi;
    RadioButton english;
    RadioButton bhojpuri;
    RadioButton random;

    RadioGroup difficulty_level;
    RadioButton easy;
    RadioButton difficult;


    EditText team_one;
    EditText team_two;
    EditText team_three;
    EditText team_four;
    TextView select_time;

    String difficulty_level_java="easy";
    String language_selected_java="hindi";
    String game_time_java="notime";
    Integer no_of_teams_java=2;
    Integer time_details_java=120000;
    String team_one_java="Team A";
    String team_two_java="Team B";
    String team_three_java="Team C";
    String team_four_java="Team D";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_mode);

        addListenerNoOfTeams();
        addListenerGameTime();
        addListenerLanguage();
        addListenerDifficulty();

        time_details = (RadioGroup) findViewById(R.id.time_details);

        team_one = (EditText) findViewById(R.id.team_one);
        team_two = (EditText) findViewById(R.id.team_two);
        team_three = (EditText) findViewById(R.id.team_three);
        team_four = (EditText) findViewById(R.id.team_four);

    }

    private void addListenerDifficulty() {
        difficulty_level = (RadioGroup) findViewById(R.id.difficulty_level);
        easy = (RadioButton) findViewById(R.id.easy);
        difficult = (RadioButton) findViewById(R.id.difficult);
        easy.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                difficulty_level_java = "easy";
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

    private void addListenerNoOfTeams() {

        no_of_teams = (RadioGroup) findViewById(R.id.no_of_teams);
        two_teams = (RadioButton) findViewById(R.id.two_teams);
        three_teams = (RadioButton) findViewById(R.id.three_teams);
        four_teams = (RadioButton) findViewById(R.id.four_teams);


        two_teams.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                no_of_teams_java=2;
                team_three.setVisibility(View.GONE);
                team_four.setVisibility(View.GONE);
            }
        });
        three_teams.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                no_of_teams_java=3;
                team_three.setVisibility(View.VISIBLE);
                team_four.setVisibility(View.GONE);
            }
        });
        four_teams.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                no_of_teams_java=4;
                team_three.setVisibility(View.VISIBLE);
                team_four.setVisibility(View.VISIBLE);
            }
        });

    }


    private void addListenerGameTime() {

        game_time = (RadioGroup) findViewById(R.id.game_time);
        timed = (RadioButton) findViewById(R.id.timed);
        not_timed = (RadioButton) findViewById(R.id.not_timed);

        select_time = (TextView) findViewById(R.id.select_time);

        timed.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                game_time_java = "time";
                time_details.setVisibility(View.VISIBLE);
                select_time.setVisibility(View.VISIBLE);

                addListenerTimeDetails();

            }
        });
        not_timed.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                game_time_java="notime";
                select_time.setVisibility(View.GONE);
                time_details.setVisibility(View.GONE);
            }
        });

    }



    private void addListenerTimeDetails() {

        two_mins = (RadioButton) findViewById(R.id.two_mins);
        three_mins = (RadioButton) findViewById(R.id.three_mins);
        five_mins = (RadioButton) findViewById(R.id.five_mins);

        two_mins.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                time_details_java=120000;
            }
        });
        three_mins.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                time_details_java=180000;
            }
        });
        five_mins.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                time_details_java=300000;
            }
        });

    }


    public void startGame(View v)
    {

        team_one_java=team_one.getText().toString();
        team_two_java=team_two.getText().toString();
        team_three_java=team_three.getText().toString();
        team_four_java=team_four.getText().toString();

        if(team_one_java.trim().equals(""))
        {
            team_one_java="Team A";
        }
        if(team_two_java.trim().equals(""))
        {
            team_two_java="Team B";
        }
        if(team_three_java.trim().equals(""))
        {
            team_three_java="Team C";
        }
        if(team_four_java.trim().equals(""))
        {
            team_four_java="Team D";
        }

        Intent in=new Intent(GameMode.this,GameMain.class);
        Bundle bundle = new Bundle();
        bundle.putInt("no_of_teams", no_of_teams_java);
        bundle.putString("team_one", team_one_java);
        bundle.putString("team_two", team_two_java);
        bundle.putString("team_three", team_three_java);
        bundle.putString("team_four", team_four_java);
        bundle.putString("game_time", game_time_java);
        bundle.putInt("time_details", time_details_java);
        bundle.putString("language", language_selected_java);
        bundle.putString("difficulty_level", difficulty_level_java);
        in.putExtras(bundle);
        startActivity(in);
        finish();
    }

}