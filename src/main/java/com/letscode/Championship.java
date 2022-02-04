package com.letscode;

import com.letscode.entities.Match;
import com.letscode.entities.Team;
import com.letscode.utils.Reader;
import lombok.Getter;

import java.io.File;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.SortedSet;
import java.util.stream.Collectors;

@Getter

public class Championship {

    private SortedSet<Match> matches;
    private List<Team> teams = new ArrayList<>();

    public Championship(File results, DateTimeFormatter formatter) {
        this.matches = Reader.readFile(results, formatter);
        setTeams();
        setTeamMatches();
    }

    public void setMatches(File results, DateTimeFormatter formatter) {
        this.matches = Reader.readFile(results, formatter);
        setTeams();
        setTeamMatches();
    }

    private void setTeams() {
        matches.forEach(match -> {
            if (!teams.contains(match.getHomeTeam())) {
                teams.add(new Team(match.getHomeTeam()));
            } else if (!teams.contains(match.getAwayTeam())) {
                teams.add(new Team(match.getAwayTeam()));
            }
        });
    }

    private void setTeamMatches() {
        for (Team team : teams) {
            List<Match> matchesPerTeam = matches.stream()
                    .filter(match ->
                            Objects.equals(match.getHomeTeam(), team.getName()) || Objects.equals(match.getAwayTeam(), team.getName()))
                    .collect(Collectors.toList());
            assert team != null;
            team.setMatches(matchesPerTeam);
        }
    }
}
