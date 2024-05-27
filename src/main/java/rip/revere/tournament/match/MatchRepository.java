package rip.revere.tournament.match;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class MatchRepository {
    private final List<Match> matches = new ArrayList<>();

    /**
     * Adds a match to the repository.
     *
     * @param match the match to add
     */
    public void addMatch(Match match) {
        matches.add(match);
    }
}
