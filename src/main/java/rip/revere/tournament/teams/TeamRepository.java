package rip.revere.tournament.teams;

import lombok.Getter;
import lombok.Setter;
import org.reflections.Reflections;
import rip.revere.tournament.Tournament;
import rip.revere.tournament.teams.annotation.TeamMetaData;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class TeamRepository {

    private final List<AbstractTeam> teams = new ArrayList<>();

    public TeamRepository() {
        Reflections reflections = new Reflections("rip.revere.tournament.teams.impl");
        for (Class<?> clazz : reflections.getTypesAnnotatedWith(TeamMetaData.class)) {
            try {
                addTeam((AbstractTeam) clazz.newInstance());
                Tournament.getLogger().info("Added team: " + clazz.getSimpleName());
            } catch (InstantiationException | IllegalAccessException e) {
                Tournament.getLogger().error("Failed to instantiate team: " + clazz.getSimpleName());
            }
        }
    }

    /**
     * Adds a team to the repository.
     *
     * @param team the team to add
     */
    public void addTeam(AbstractTeam team) {
        teams.add(team);
    }
}
