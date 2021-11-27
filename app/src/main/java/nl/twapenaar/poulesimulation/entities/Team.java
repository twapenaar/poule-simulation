package nl.twapenaar.poulesimulation.entities;

import java.io.Serializable;

public class Team implements Serializable {
    private String name;
    private int playedMatches = 0;
    private int points = 0;
    private int goalsMade = 0;
    private int goalsGotten = 0;
    private float strength = 1f;

    /**
     * @param name|string
     * @param strength|float a random float number between 0 and 1 to determine the teams strength
     */
    public Team(String name, float strength) {
        this.name = name;
        this.strength = strength;
    }

    public String getName() {
        return name;
    }

    public float getStrength(){
        return strength;
    }

    public int getPlayedMatches() {
        return playedMatches;
    }

    public void setPlayedMatches(int playedMatches) {
        this.playedMatches = playedMatches;
    }

    public void addPlayedMatches(int playedMatches) {
        this.playedMatches += playedMatches;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public void addPoints(int points) {
        this.points += points;
    }

    public int getGoalsMade() {
        return goalsMade;
    }

    public void addGoalsMade(int goalsMade) {
        this.goalsMade += goalsMade;
    }

    public int getGoalsGotten() {
        return goalsGotten;
    }

    public void addGoalsGotten(int goalsGotten) {
        this.goalsGotten += goalsGotten;
    }
}
