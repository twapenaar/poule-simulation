package nl.twapenaar.poulesimulation.entities;

public class Match {
    private Team teamA;
    private Team teamB;
    private int goalsTeamA;
    private int goalsTeamB;

    public Match(Team teamA, Team teamB) {
        this.teamA = teamA;
        this.teamB = teamB;
    }

    public Team getTeamA() {
        return teamA;
    }

    public Team getTeamB() {
        return teamB;
    }

    public int getGoalsTeamA() {
        return goalsTeamA;
    }

    public void setGoalsTeamA(int goalsTeamA) {
        this.goalsTeamA = goalsTeamA;
        this.teamA.addGoalsMade(goalsTeamA);
        this.teamB.addGoalsGotten(goalsTeamA);
    }

    public int getGoalsTeamB() {
        return goalsTeamB;
    }

    public void setGoalsTeamB(int goalsTeamB) {
        this.goalsTeamB = goalsTeamB;
        this.teamB.addGoalsMade(goalsTeamB);
        this.teamA.addGoalsGotten(goalsTeamB);
    }
    public void setScore(int goalsTeamA, int goalsTeamB){
        setScore(goalsTeamA, goalsTeamB, true);
    }

    public void setScore(int goalsTeamA, int goalsTeamB, boolean finishedMatch){
        this.setGoalsTeamA(goalsTeamA);
        this.setGoalsTeamB(goalsTeamB);

        if (finishedMatch){
            this.teamA.addPlayedMatches(1);
            this.teamB.addPlayedMatches(1);
        }

        /*this.goalsTeamA = goalsTeamA;
        this.teamA.addGoalsMade(goalsTeamA);
        this.teamA.addGoalsGotten(goalsTeamB);*/

        /*this.goalsTeamB = goalsTeamB;
        this.teamB.addGoalsMade(goalsTeamB);
        this.teamB.addGoalsGotten(goalsTeamA);*/
    }

    @Override
    public String toString() {
        return teamA.getName() + " vs " + teamB.getName();
    }
}
