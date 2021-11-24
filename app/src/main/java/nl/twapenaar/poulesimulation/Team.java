package nl.twapenaar.poulesimulation;

public abstract class Team {
    protected String name;
    protected int playedMatches = 0;
    protected int points = 0;
    protected int goalsMade = 0;
    protected int goalsGotten = 0;
    protected float strength = 1f;

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

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getGoalsMade() {
        return goalsMade;
    }

    public void setGoalsMade(int goalsMade) {
        this.goalsMade = goalsMade;
    }

    public int getGoalsGotten() {
        return goalsGotten;
    }

    public void setGoalsGotten(int goalsGotten) {
        this.goalsGotten = goalsGotten;
    }
}
