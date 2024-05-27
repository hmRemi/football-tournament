package rip.revere.tournament.teams;

import lombok.Getter;
import lombok.Setter;
import rip.revere.tournament.teams.annotation.TeamMetaData;
import rip.revere.tournament.teams.data.TeamData;

@Getter
@Setter
public class AbstractTeam {
    private final String teamName;
    private final String teamDescription;
    private TeamData teamData;

    /**
     * Constructor for AbstractTeam.
     */
    public AbstractTeam() {
        this.teamName = getClass().getAnnotation(TeamMetaData.class).teamName();
        this.teamDescription = getClass().getAnnotation(TeamMetaData.class).teamDescription();
        this.teamData = new TeamData();
    }
}
