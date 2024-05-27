package rip.revere.tournament.match;

import lombok.Getter;
import lombok.Setter;
import rip.revere.tournament.Tournament;
import rip.revere.tournament.match.util.TeamPicker;
import rip.revere.tournament.teams.AbstractTeam;
import rip.revere.tournament.teams.TeamRepository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Getter
@Setter
public class Match {
    private MatchRepository matchRepository;
    private TeamRepository teamRepository;
    private TeamPicker teamPicker;

    /**
     * Constructs a new Match.
     *
     * @param teamPicker     the team picker
     * @param teamRepository the team repository
     */
    public Match(TeamPicker teamPicker, TeamRepository teamRepository) {
        this.teamPicker = teamPicker;
        this.teamRepository = teamRepository;
        this.matchRepository = new MatchRepository();
    }

    /**
     * Plays a match between two teams and updates the scores.
     *
     * @param team1 the first team
     * @param team2 the second team
     */
    private AbstractTeam playMatch(AbstractTeam team1, AbstractTeam team2) {
        Tournament.getLogger().infoNewLine("Playing match " + matchRepository.getMatches().size() + " between " + team1.getTeamName() + " and " + team2.getTeamName() + "...");

        // Set the match data for the teams and calculate the score
        TeamPicker.TeamGoalData teamGoalData = teamPicker.getTeamGoalData();
        teamPicker.setMatchesPlayed(team1, team2);
        teamPicker.setTeamGoals(team1, team2, teamGoalData.team1Goals(), teamGoalData.team2Goals());
        teamPicker.setOpponentGoals(team1, team2, teamGoalData.team2Goals(), teamGoalData.team1Goals());
        teamPicker.calculateScore(team1, team2, teamGoalData.team1Goals(), teamGoalData.team2Goals());

        // Process the match results
        return processMatchResults(team1, team2, teamGoalData.team1Goals(), teamGoalData.team2Goals());
    }

    /**
     * Processes the match results.
     *
     * @param team1      the first team
     * @param team2      the second team
     * @param team1Goals the goals of the first team
     * @param team2Goals the goals of the second team
     */
    private AbstractTeam processMatchResults(AbstractTeam team1, AbstractTeam team2, int team1Goals, int team2Goals) {
        if (team1Goals > team2Goals) {
            Tournament.getLogger().info(team1.getTeamName() + " won against " + team2.getTeamName() + " with a score of " + team1Goals + " - " + team2Goals);
            return team1;
        } else if (team1Goals < team2Goals) {
            Tournament.getLogger().info(team2.getTeamName() + " won against " + team1.getTeamName() + " with a score of " + team2Goals + " - " + team1Goals);
            return team2;
        } else {
            Tournament.getLogger().info(team1.getTeamName() + " drew against " + team2.getTeamName() + " with a score of " + team1Goals + " - " + team2Goals);
            Tournament.getLogger().info("The match will be decided by goal difference...");
            return team1.getTeamData().getGoalDifference() > team2.getTeamData().getGoalDifference() ? team1 : team2;
        }
    }

    /**
     * Shuffle the teams and play the tournament.
     */
    public void processTeamsAndPlay() {
        List<AbstractTeam> teams = teamRepository.getTeams();
        this.teamPicker.shuffleTeams(teams);

        while (teams.size() > 1) {
            List<AbstractTeam> nextRoundTeams = new ArrayList<>();
            this.matchRepository.addMatch(this);

            for (int i = 0; i < teams.size(); i += 2) {
                AbstractTeam team1 = teams.get(i);
                AbstractTeam team2 = (i + 1 < teams.size()) ? teams.get(i + 1) : null;

                if (team2 != null) {
                    AbstractTeam winner = this.playMatch(team1, team2);
                    if (winner != null) {
                        // Add the winner to the next round
                        nextRoundTeams.add(winner);
                    }
                } else {
                    // Add the skipped team to the next round
                    nextRoundTeams.add(team1);
                }
            }

            teams = nextRoundTeams;
        }

        this.logTournamentResults(teams);
    }

    /**
     * Logs the tournament results.
     *
     * @param teams the teams
     */
    private void logTournamentResults(List<AbstractTeam> teams) {
        List<AbstractTeam> sortedTeams = new ArrayList<>(teamRepository.getTeams());
        sortedTeams.sort(Comparator.comparingInt((AbstractTeam team) -> team.getTeamData().getTeamScore()).reversed());

        Tournament.getLogger().infoNewLine("The tournament has ended and consists of " + matchRepository.getMatches().size() + " matches played.");
        Tournament.getLogger().info("The tournament results are as follows:");
        Tournament.getLogger().info("=====================================");

        for (AbstractTeam team : sortedTeams) {
            Tournament.getLogger().info(team.getTeamName() + " scored " + team.getTeamData().getTeamScore() + " points with " + team.getTeamData().getMatchesPlayed() + " matches played");
        }

        Tournament.getLogger().info("=====================================");
        Tournament.getLogger().info(!teams.isEmpty() ? "The winner of the tournament is " + teams.get(0).getTeamName() : "The tournament ended in a draw");
    }
}
