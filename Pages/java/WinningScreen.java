package com.example.yugandhara.dumbcharades;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;



public class WinningScreen extends Activity {

    TextView winner;
    TextView score_java;

    Integer no_of_teams_java;
    String[] team_names= new String[4];
    String score_card="";

    int[] team_scores= new int[4];
    int max=0;
    int max_pos=0;
    boolean tie=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_winning_screen);

        winner = (TextView) findViewById(R.id.winner);
        score_java = (TextView) findViewById(R.id.scores);

        Bundle bundle = getIntent().getExtras();

        //Extract the dataÖ
        team_names[0] = bundle.getString("team_one");
        team_names[1] = bundle.getString("team_two");
        team_names[2] = bundle.getString("team_three");
        team_names[3] = bundle.getString("team_four");
        no_of_teams_java = bundle.getInt("no_of_teams");
        team_scores[0] = bundle.getInt("score_team_one");
        team_scores[1] = bundle.getInt("score_team_two");
        team_scores[2] = bundle.getInt("score_team_three");
        team_scores[3] = bundle.getInt("score_team_four");



        for (int i=0; i<no_of_teams_java; i++)
        {
            score_card= score_card.concat(team_names[i]+": " + team_scores[i] + " ");
        }

        score_java.setText(score_card);

        //Gives Max score
        for (int counter = 0; counter < team_scores.length; counter++)
        {
            if (team_scores[counter] >= max)
            {
                max = team_scores[counter];
                max_pos=counter;
            }
        }

        //If all Team scores are 0
        if(max==0)
        {
            winner.setText("No Team Won");
        }
        else
        {
            //if there is a tie
            for (int counter = 0; counter < team_scores.length; counter++)
            {
                if (max == team_scores[counter] && counter!=max_pos)
                {
                    winner.setText("There is a Tie. No Team Won");
                    tie=true;
                }
            }
            //Winning team
            if(!tie)
            {
                winner.setText("Congratulations. " + team_names[max_pos] + " Won");
            }
        }


    }


    public void mainMenu(View v)
    {
        Intent in=new Intent(WinningScreen.this,HomeScreen.class);
        in.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(in);
        finish();
    }

    public void exit(View v)
    {
        Intent intent = new Intent(getApplicationContext(), HomeScreen.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("EXIT", true);
        startActivity(intent);
    }

    public void onBackPressed() {

        DBAdapter db =  new DBAdapter(this);  // calls the constructor > which calls the onCreate()
        db.open();
        db.updateMovie();
        db.close();
        Intent in=new Intent(WinningScreen.this,HomeScreen.class);
        in.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(in);
        finish();

    }

}
