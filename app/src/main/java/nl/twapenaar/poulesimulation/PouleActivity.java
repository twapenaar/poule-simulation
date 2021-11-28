package nl.twapenaar.poulesimulation;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Debug;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.io.Serializable;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import nl.twapenaar.poulesimulation.entities.Match;
import nl.twapenaar.poulesimulation.entities.Poule;
import nl.twapenaar.poulesimulation.entities.Team;
import nl.twapenaar.poulesimulation.services.Utility;

public class PouleActivity extends AppCompatActivity {
    private Poule poule;
    private int matchIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poule);

        Intent intent = getIntent();
        this.poule = (Poule) intent.getSerializableExtra("poule");

        generateTeamsTable();
        generateMatchTable();
    }

    public void simulateMatches(View view){
        poule.SimulateAllMatches();

        generateTeamsTable();
        generateMatchTable();
    }

    public void simulateMatch(View view){
        List<Match> matches = (List<Match>) poule.getMatches();
        if (matchIndex < matches.size()){
            poule.SimulateMatch(matches.get(matchIndex));
            matchIndex++;

            generateTeamsTable();
            generateMatchTable();
        }
    }

    private void generateMatchTable() {
        TableLayout tableLayout = (TableLayout) findViewById(R.id.matchLayout);

        TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );

        tableLayout.removeAllViews();

        TableRow tr_head = new TableRow(this);
        tr_head.setId(1);
        tr_head.setLayoutParams(layoutParams);
        tr_head.setBackgroundResource(R.color.tableHeader);
        tr_head.setPadding(0, 5, 0, 5);

        TextView pointsHeader = Utility.genericTextView(2, "Versus", this);
        tr_head.addView(pointsHeader);

        TextView goalDiffHeader = Utility.genericTextView(3, "Goals", this);
        tr_head.addView(goalDiffHeader);

        tableLayout.addView(tr_head);

        List<Match> matches = (List<Match>) poule.getMatches();

        for (int i = 0; i < matches.size(); i++) {
            Match match = matches.get(i);
            tableLayout.addView(
                    generateMatchRow(match, layoutParams, i+1)
            );
        }
    }

    private TableRow generateMatchRow(Match match, TableRow.LayoutParams layoutParams, int rowIndex) {
        rowIndex *= 10;

        TableRow matchRow = new TableRow(this);
        matchRow.setId(rowIndex);
        matchRow.setLayoutParams(layoutParams);
        matchRow.setBackgroundResource(R.color.white);
        matchRow.setPadding(0, 5, 0, 5);

        String versusTxt = match.getTeamA().getName() + " - " + match.getTeamB().getName();
        TextView versusText = Utility.genericTextView(rowIndex+1, versusTxt, this);
        matchRow.addView(versusText);

        String goalTxt = match.getGoalsTeamA() + " - " + match.getGoalsTeamB();
        TextView goalsText = Utility.genericTextView(rowIndex+2, goalTxt, this);
        matchRow.addView(goalsText);

        return matchRow;
    }

    @SuppressLint("ResourceType")
    private void generateTeamsTable(){
        TableLayout tableLayout = (TableLayout) findViewById(R.id.teamScoreLayout);

        TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );

        tableLayout.removeAllViews();

        TableRow tr_head = new TableRow(this);
        tr_head.setId(1);
        tr_head.setLayoutParams(layoutParams);
        tr_head.setBackgroundResource(R.color.tableHeader);
        tr_head.setPadding(0, 5, 0, 5);

        TextView teamHeader = Utility.genericTextView(2, "", this);
        tr_head.addView(teamHeader);

        TextView gamesPlayedHeader = Utility.genericTextView(3, "GP", this);
        /*gamesPlayedHeader.setId(3);
        gamesPlayedHeader.setLayoutParams(layoutParams);
        gamesPlayedHeader.setTextColor(Color.BLACK);
        gamesPlayedHeader.setTextSize(14);
        gamesPlayedHeader.setText("GP");*/
        tr_head.addView(gamesPlayedHeader);

        TextView pointsHeader = Utility.genericTextView(4, "P", this);
        /*pointsHeader.setId(4);
        pointsHeader.setLayoutParams(layoutParams);
        pointsHeader.setTextColor(Color.BLACK);
        pointsHeader.setTextSize(14);
        pointsHeader.setText("P");*/
        tr_head.addView(pointsHeader);

        TextView goalDiffHeader = Utility.genericTextView(5, "GD", this);
        /*goalDiffHeader.setId(5);
        goalDiffHeader.setLayoutParams(layoutParams);
        goalDiffHeader.setTextColor(Color.BLACK);
        goalDiffHeader.setTextSize(14);
        goalDiffHeader.setText("GD");*/
        tr_head.addView(goalDiffHeader);

        tableLayout.addView(tr_head);

        List<Team> teams = poule.getTeams();
        teams.sort((t1, t2) -> {
            if (t1.getPoints() != t2.getPoints()){
                return t2.getPoints() - t1.getPoints();
            }

            return (t2.getGoalsMade() - t2.getGoalsGotten()) - (t1.getGoalsMade() - t1.getGoalsGotten());
        });

        for (int i = 0; i < teams.size(); i++) {
            Team team = teams.get(i);
            tableLayout.addView(
                    generateTeamRow(team, layoutParams, i+1)
            );
        }
    }

    private TableRow generateTeamRow(Team team, TableRow.LayoutParams layoutParams, int rowIndex){
        rowIndex *= 10;
        TableRow teamRow = new TableRow(this);
        teamRow.setId(rowIndex);
        teamRow.setLayoutParams(layoutParams);
        teamRow.setBackgroundResource(R.color.white);
        teamRow.setPadding(0, 5, 0, 5);

        int transparency = Math.round((255 * team.getStrength()));
        int teamBackground = Color.argb(transparency, 124, 252, 0 );

        TextView teamNameText = Utility.genericTextView(rowIndex+1, team.getName(), this, teamBackground);
        /*teamNameText.setId(rowIndex+1);
        teamNameText.setLayoutParams(layoutParams);
        teamNameText.setBackgroundColor(teamBackground);
        teamNameText.setTextColor(Color.BLACK);
        teamNameText.setTextSize(14);
        teamNameText.setText(team.getName());*/
        teamRow.addView(teamNameText);

        TextView gamesPlayedText = Utility.genericTextView(rowIndex+2, team.getPlayedMatches()+"", this);
        /*gamesPlayedText.setId(rowIndex+2);
        gamesPlayedText.setLayoutParams(layoutParams);
        gamesPlayedText.setTextColor(Color.BLACK);
        gamesPlayedText.setTextSize(14);
        gamesPlayedText.setText(team.getPlayedMatches()+"");*/
        teamRow.addView(gamesPlayedText);

        TextView pointsText = Utility.genericTextView(rowIndex+3, team.getPoints()+"", this);
        /*pointsText.setId(rowIndex+3);
        pointsText.setLayoutParams(layoutParams);
        pointsText.setTextColor(Color.BLACK);
        pointsText.setTextSize(14);
        pointsText.setText(team.getPoints()+"");*/
        teamRow.addView(pointsText);

        String goalDiff = team.getGoalsMade() + " - " + team.getGoalsGotten();
        TextView goalDifferenceText = Utility.genericTextView(rowIndex+4, goalDiff, this);
        /*goalDifferenceText.setId(rowIndex+4);
        goalDifferenceText.setLayoutParams(layoutParams);
        goalDifferenceText.setTextColor(Color.BLACK);
        goalDifferenceText.setTextSize(14);
        goalDifferenceText.setText(goalDiff);*/
        teamRow.addView(goalDifferenceText);

        return teamRow;
    }
}