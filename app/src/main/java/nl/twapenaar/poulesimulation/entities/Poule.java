package nl.twapenaar.poulesimulation.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import nl.twapenaar.poulesimulation.services.Utility;

public class Poule  implements Serializable {
    private List<Team> teams;
    private Collection<Match> matches;
    private boolean halfOfTheMatches = false;

    public Poule(Collection<Team> teams, boolean halfOfTheMatches) {
        this.teams = (List<Team>) teams;
        this.halfOfTheMatches = halfOfTheMatches;
    }

    /**
     * Generates who is against who
     * if the boolean halfOfTheMatches is true the teams only have single match against each other
     */
    public void GenerateMatches(){
        ArrayList<Team> tempTeams = new ArrayList<>(teams); // clones the teams, because some languages point back to original array when you edit the array
        ArrayList<Match> matches = new ArrayList<>();

        for (Team teamA : teams) {
            for (Team teamB : tempTeams){
                if (teamB == teamA){continue;}
                Match match = new Match(teamA, teamB);
                matches.add(match);
            }

            if (halfOfTheMatches){
                tempTeams.remove(teamA);
            }
        }

        Collections.shuffle(matches);
        this.matches = matches;
    }

    /**
     *  Simulates all matches
     *  using the Simulate one match function
     */
    public void SimulateAllMatches(){
        ArrayList<Match> playedMatches = new ArrayList<>();

        for (Match match: matches) {
            playedMatches.add(SimulateMatch(match));
        }

        this.matches = playedMatches;
    }

    /**
     * Simulates a match between two teams.
     * Scores are based on RNG (random number generation)
     * @param match|Match
     * @return Match
     */
    public Match SimulateMatch(Match match){
        int matchIndex = ((List<Match>)matches).indexOf(match);
        Team teamA = match.getTeamA();
        int teamAScore = 0;
        Team teamB = match.getTeamB();
        int teamBScore = 0;

        int teamAModifier = calculateModifier(teamA.getStrength());
        int teamBModifier = calculateModifier(teamB.getStrength());


        int minutes = 90;
        int speed = 10;
        while (minutes > 0){
            int rollToBeat = Utility.RandomInt(15, 20);
            int teamARoll = Utility.RandomInt(20) + teamAModifier;
            int teamBRoll = Utility.RandomInt(20) + teamBModifier;

            boolean teamAScored = false;
            boolean teamBScored = false;

            if (teamARoll >= rollToBeat){
                teamAScored = true;
            }

            if (teamBRoll >= rollToBeat){
                teamBScored = true;
            }

            if (teamAScored && !teamBScored){
                teamAScore++;
            }else if(!teamAScored && teamBScored){
                teamBScore++;
            }

            minutes -= speed;
        }

        Utility.Log("Team A "+teamAScore);
        Utility.Log("Team B "+teamBScore);

        Utility.Log("Team A mod " + teamAModifier);
        Utility.Log("Team A str " + teamA.getStrength());
        Utility.Log("Team B mod " + teamBModifier);
        Utility.Log("Team A str " + teamB.getStrength());

        match.setScore(teamAScore, teamBScore);

        ((List<Match>) matches).set(matchIndex, match);
        return match;
    }

    /**
     * calculates the bonus a team gets based on their strength
     */
    private int calculateModifier(float strength) {
        int maxStat = 20;
        float teamStat = maxStat*strength;

        return Math.round(teamStat-10)/2;
    }


    public List<Team> getTeams() {
        return teams;
    }

    public Collection<Match> getMatches() {
        return matches;
    }

    public boolean isHalfOfTheMatches() {
        return halfOfTheMatches;
    }
}
