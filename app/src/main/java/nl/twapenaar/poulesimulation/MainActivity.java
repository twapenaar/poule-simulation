package nl.twapenaar.poulesimulation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.*;

import nl.twapenaar.poulesimulation.entities.Match;
import nl.twapenaar.poulesimulation.entities.Poule;
import nl.twapenaar.poulesimulation.entities.Team;
import nl.twapenaar.poulesimulation.services.Utility;

public class MainActivity extends AppCompatActivity {

    private List<Team> teams;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.teams = new ArrayList<>();
    }

    /**
     *  Adds new teams to the poule
     */
    public void addTeam(View view){
        Random random = new Random();

        int teamNumber = teams.size() + 1;
        Team newTeam = new Team("Team " + teamNumber, random.nextFloat());
        teams.add(newTeam);

        LinearLayout teamLayout = (LinearLayout) findViewById(R.id.teams);

        TextView teamText = new TextView(this);
        teamText.setText(newTeam.getName());
        teamText.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        ));
        teamText.setEms(7);
        teamText.setTextColor(Color.BLACK);
        teamText.setTextSize(20);
        teamText.setPadding(8, 8, 8, 8);


        teamLayout.addView(teamText);
    }

    /**
     * Starts the poule with the added teams
     */
    public void startPoule(View view){
        CheckBox halfOfMatches = (CheckBox) findViewById(R.id.halfOfMatches);

        Poule poule = new Poule(teams, halfOfMatches.isChecked());
        poule.GenerateMatches();

        Intent intent = new Intent(this, PouleActivity.class);
        intent.putExtra("poule", poule);

        startActivity(intent);
    }
}