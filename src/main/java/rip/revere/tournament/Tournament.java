package rip.revere.tournament;

import lombok.Getter;
import lombok.Setter;
import rip.revere.tournament.match.Match;
import rip.revere.tournament.match.util.TeamPicker;
import rip.revere.tournament.teams.TeamRepository;
import rip.revere.tournament.util.TournamentLogger;

@Getter
@Setter
public class Tournament {

    @Getter
    private final static TournamentLogger logger = new TournamentLogger("Tournament");
    private TeamRepository teamRepository;
    private TeamPicker teamPicker;
    private Match match;

    /**
     * The tournament constructor
     *
     * @param args the command line arguments
     */
    public Tournament(String[] args) {
    }

    /**
     * Launches the tournament.
     */
    public void launch() {
        this.teamRepository = new TeamRepository();
        this.teamPicker = new TeamPicker(teamRepository.getTeams());
        this.match = new Match(teamPicker, teamRepository);
        this.match.processTeamsAndPlay();
    }
}

