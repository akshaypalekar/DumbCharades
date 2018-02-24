package com.example.yugandhara.dumbcharades;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;


public class GameMain extends Activity {

    String difficulty_level_java=null;
    String language_selected_java=null;
    String game_time_java=null;
    Integer no_of_teams_java;
    Integer time_details_java;
    String[] team_names_java= new String[4];
    String checked_ans_java=null;
    int[] team_scores_java= new int[4];
    Boolean go_next=true; //go-> true--- next->false

    ImageButton fetch_movie_java;
    ImageButton change_language_java;

    RadioGroup language_java;
    RadioButton hindi_java;
    RadioButton english_java;
    RadioButton bhojpuri_java;
    RadioButton random_java;

    RadioGroup answer_java;
    RadioButton right_java;
    RadioButton wrong_java;


    TextView playing_team_java;
    TextView score_java;
    TextView timer;
    TextView display_movie;
    Integer movieId;
    CountDownTimer count_down_timer;

    Integer current_team=0;
    String score_card="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_main);

        fetch_movie_java = (ImageButton) findViewById(R.id.fetch_movie);
        change_language_java = (ImageButton) findViewById(R.id.change_language);
        language_java = (RadioGroup) findViewById(R.id.language);
        english_java = (RadioButton) findViewById(R.id.english);
        hindi_java = (RadioButton) findViewById(R.id.hindi);
        bhojpuri_java = (RadioButton) findViewById(R.id.bhojpuri);
        random_java = (RadioButton) findViewById(R.id.random);

        timer = (TextView) findViewById(R.id.timer);
        display_movie = (TextView) findViewById(R.id.display_movie);
        score_java = (TextView) findViewById(R.id.score);
        playing_team_java = (TextView) findViewById(R.id.playing_team);


        Bundle bundle = getIntent().getExtras();
        //Extract the dataÖ
        difficulty_level_java = bundle.getString("difficulty_level");
        language_selected_java= bundle.getString("language");
        team_names_java[0] = bundle.getString("team_one");
        team_names_java[1]= bundle.getString("team_two");
        team_names_java[2] = bundle.getString("team_three");
        team_names_java[3]= bundle.getString("team_four");
        game_time_java= bundle.getString("game_time");
        no_of_teams_java= bundle.getInt("no_of_teams");
        time_details_java= bundle.getInt("time_details");

        addListenerAnswer();
        updateScore();
        updateTeam();



        count_down_timer= new CountDownTimer(time_details_java, 1000) {

            public void onTick(long millisUntilFinished) {
                timer.setText("seconds remaining: " + millisUntilFinished / 1000);
            }

            public void onFinish() {
                timer.setText("Time Over!");
                wrong_java.setChecked(true);
                checked_ans_java="wrong_java";
                fetch_movie_java.setClickable(true);
                fetch_movie_java.setBackgroundResource(R.drawable.nextteam);
                right_java.setClickable(false);
            }
        };



    }

    private void updateScore()
    {
        score_card="";
        for (int i=0; i<no_of_teams_java; i++)
        {
            score_card= score_card.concat(team_names_java[i]+": " + team_scores_java[i] + " ");
        }
        score_java.setText(score_card);

    }

    //Shows which team in playing
    private void updateTeam()
    {

        playing_team_java.setText(team_names_java[current_team] + " Playing");

    }


    public void endGame(View v)
    {
        new AlertDialog.Builder(this)
                .setIcon(R.drawable.appiconsmall)
                        // OR alertDialog.setIcon(R.drawable.delete);
                .setTitle("End Game")
                .setMessage("Are you sure?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent in=new Intent(GameMain.this,WinningScreen.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("team_one", team_names_java[0]);
                        bundle.putString("team_two", team_names_java[1]);
                        bundle.putString("team_three", team_names_java[2]);
                        bundle.putString("team_four", team_names_java[3]);
                        bundle.putInt("no_of_teams", no_of_teams_java);
                        bundle.putInt("score_team_one", team_scores_java[0]);
                        bundle.putInt("score_team_two", team_scores_java[1]);
                        bundle.putInt("score_team_three", team_scores_java[2]);
                        bundle.putInt("score_team_four", team_scores_java[3]);
                        in.putExtras(bundle);
                        in.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(in);
                        finish();
                    }

                })
                .setNegativeButton("No", null)
                .show();

    }

    public void changeLanguage(View v)
    {
        hindi_java.setChecked(false);
        english_java.setChecked(false);
        bhojpuri_java.setChecked(false);
        random_java.setChecked(false);
        language_java.setVisibility(View.VISIBLE);
        addListenerLanguage();
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

    private void addListenerAnswer() {
        answer_java = (RadioGroup) findViewById(R.id.answer);
        right_java = (RadioButton) findViewById(R.id.right);
        wrong_java = (RadioButton) findViewById(R.id.wrong);

        right_java.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checked_ans_java="right_java";
                fetch_movie_java.setClickable(true);
                fetch_movie_java.setBackgroundResource(R.drawable.nextteam);
            }
        });
        wrong_java.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checked_ans_java="wrong_java";
                fetch_movie_java.setClickable(true);
                fetch_movie_java.setBackgroundResource(R.drawable.nextteam);
            }
        });
    }



    public void fetchMovie(View v)
    {
        if(go_next) //Code for GO Button
        {
            fetch_movie_java.setClickable(false);
            answer_java.setVisibility(View.VISIBLE);
            fetch_movie_java.setBackgroundResource(R.drawable.tickright);
            go_next=false;
            change_language_java.setClickable(false);
            language_java.setVisibility(View.GONE);

            if(game_time_java.equalsIgnoreCase("time"))
            {
                timer.setVisibility(View.VISIBLE);
                count_down_timer= new CountDownTimer(time_details_java, 1000) {

                    public void onTick(long millisUntilFinished) {
                        timer.setText("Time Remaining: "+ millisUntilFinished / 1000 +" Seconds" );
                    }

                    public void onFinish() {
                        timer.setText("Time Over!");
                        wrong_java.setChecked(true);
                        checked_ans_java="wrong_java";
                        fetch_movie_java.setClickable(true);
                        fetch_movie_java.setBackgroundResource(R.drawable.nextteam);
                        right_java.setClickable(false);
                    }
                };
                count_down_timer.start();
            }


            //Displays Movie
            DBAdapter db =  new DBAdapter(this);  // calls the constructor > which calls the onCreate()

            //---get a random movie ID---
            db.open();
            movieId = db.getMovieId(language_selected_java, difficulty_level_java);
            db.close();

            //---get the movie by ID---
            db.open();
            Cursor c = db.getMovie(movieId);
            if (c.moveToFirst())
            {
                display_movie.setText(c.getString(0));
            }
            db.close();

            updateScore();

        }
        else   //Code for Next Team Button
        {
            fetch_movie_java.setClickable(true);
            answer_java.setVisibility(View.GONE);
            fetch_movie_java.setBackgroundResource(R.drawable.go);
            change_language_java.setClickable(true);
            go_next= true;
            answer_java.clearCheck();
            right_java.setClickable(true);
            count_down_timer.cancel();
            timer.setVisibility(View.GONE);
            display_movie.setText("");

            //Adding marks to current team score on right answer
            if(checked_ans_java.equalsIgnoreCase("right_java"))
            {
                team_scores_java[current_team]++;
            }

            current_team++;
            if(current_team>=no_of_teams_java)
            {
                current_team=0;
            }

            updateScore();
            updateTeam();
        }
    }

    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setIcon(R.drawable.appiconsmall)
                .setTitle("Exit")
                .setMessage("All Progress will be Lost. Are you sure?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Intent in=new Intent(GameMain.this,HomeScreen.class);
                        in.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(in);
                        finish();
                    }

                })
                .setNegativeButton("No", null)
                .show();
    }


}
