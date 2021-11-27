package nl.twapenaar.poulesimulation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.*;

import nl.twapenaar.poulesimulation.entities.Match;
import nl.twapenaar.poulesimulation.entities.Poule;
import nl.twapenaar.poulesimulation.entities.Team;
import nl.twapenaar.poulesimulation.services.Utility;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Random random = new Random();

        Team team_one = new Team("Team 1", random.nextFloat());
        Team team_two = new Team("Team 2", random.nextFloat());
        Team team_three = new Team("Team 3", random.nextFloat());
        Team team_four = new Team("Team 4", random.nextFloat());

        Collection<Team> teams = new ArrayList<>();
        teams.add(team_one);
        teams.add(team_two);
        teams.add(team_three);
        teams.add(team_four);

        Poule poule = new Poule(teams, true);

        ArrayList<Match> matches = poule.GenerateMatches();

        poule.SimulateMatch(matches.get(0));
    }
}