package rip.revere.tournament.teams.data;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TeamData {
    private int teamScore = 0;
    private int teamGoals = 0;
    private int opponentGoals = 0;
    private int matchesPlayed = 0;

    /**
     * Calculate the goal difference for deciding winner upon draw.
     *
     * @return the goal difference
     */
    public int getGoalDifference() {
        return teamGoals - opponentGoals;
    }
}
