package rip.revere.tournament.match.util;

import rip.revere.tournament.teams.AbstractTeam;

import java.util.Collections;
import java.util.List;

public record TeamPicker(List<AbstractTeam> teams) {

    /**
     * Constructs a new TeamPicker.
     *
     * @param teams the teams to pick from
     */
    public TeamPicker {
    }

    /**
     * Shuffles the teams.
     *
     * @param teams the teams to shuffle
     */
    public void shuffleTeams(List<AbstractTeam> teams) {
        Collections.shuffle(teams);
    }

    /**
     * A record to store the goals of each team.
     */
    public record TeamGoalData(int team1Goals, int team2Goals) {
    }

    /**
     * Gets the goals of each team.
     *
     * @return the goals of each team
     */
    public TeamGoalData getTeamGoalData() {
        int team1Goals = (int) (Math.random() * 5);
        int team2Goals = (int) (Math.random() * 5);
        return new TeamGoalData(team1Goals, team2Goals);
    }

    /**
     * Calculates the score of the match.
     *
     * @param team1      the first team
     * @param team2      the second team
     * @param team1Goals the goals of the first team
     * @param team2Goals the goals of the second team
     */
    public void calculateScore(AbstractTeam team1, AbstractTeam team2, int team1Goals, int team2Goals) {
        if (team1Goals > team2Goals) {
            team1.getTeamData().setTeamScore(team1.getTeamData().getTeamScore() + 3);
        } else if (team1Goals < team2Goals) {
            team2.getTeamData().setTeamScore(team2.getTeamData().getTeamScore() + 3);
        } else {
            team1.getTeamData().setTeamScore(team1.getTeamData().getTeamScore() + 1);
            team2.getTeamData().setTeamScore(team2.getTeamData().getTeamScore() + 1);
        }
    }

    /**
     * Sets the goals scored by the opponent of each team.
     *
     * @param team1      the first team
     * @param team2      the second team
     * @param team2Goals the goals scored by the second team
     * @param team1Goals the goals scored by the first team
     */
    public void setOpponentGoals(AbstractTeam team1, AbstractTeam team2, int team2Goals, int team1Goals) {
        team1.getTeamData().setOpponentGoals(team1.getTeamData().getOpponentGoals() + team2Goals);
        team2.getTeamData().setOpponentGoals(team2.getTeamData().getOpponentGoals() + team1Goals);
    }

    /**
     * Sets the goals scored by each team.
     *
     * @param team1      the first team
     * @param team2      the second team
     * @param team1Goals the goals scored by the first team
     * @param team2Goals the goals scored by the second team
     */
    public void setTeamGoals(AbstractTeam team1, AbstractTeam team2, int team1Goals, int team2Goals) {
        team1.getTeamData().setTeamGoals(team1.getTeamData().getTeamGoals() + team1Goals);
        team2.getTeamData().setTeamGoals(team2.getTeamData().getTeamGoals() + team2Goals);
    }

    /**
     * Sets the number of matches played by each team.
     *
     * @param team1 the first team
     * @param team2 the second team
     */
    public void setMatchesPlayed(AbstractTeam team1, AbstractTeam team2) {
        team1.getTeamData().setMatchesPlayed(team1.getTeamData().getMatchesPlayed() + 1);
        team2.getTeamData().setMatchesPlayed(team2.getTeamData().getMatchesPlayed() + 1);
    }
}