package com.letscode.services;

import com.letscode.entities.Match;
import com.letscode.entities.Team;
import lombok.Getter;

import java.io.File;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Getter

public class ChampionshipHandler {

    private SortedSet<Match> matches;
    private List<Team> teams = new ArrayList<>();

    public ChampionshipHandler(File file, DateTimeFormatter formatter) {
        this.matches = ChampionshipFileReader.readFile(file, formatter);
        setTeams();
        setTeamMatches();
        ChampionshipFileGenerator.createFilePerTeam(teams);
    }

    public void setMatches(File file, DateTimeFormatter formatter) {
        this.matches = ChampionshipFileReader.readFile(file, formatter);
        setTeams();
        setTeamMatches();
    }

    private void setTeams() {
        List<String> teamsNames = new ArrayList<>();
        matches.forEach(match -> {
            if (!teamsNames.contains(match.getHomeTeamName())) {
                teamsNames.add(match.getHomeTeamName());
            } else if (!teamsNames.contains(match.getAwayTeamName())) {
                teamsNames.add(match.getAwayTeamName());
            }
        });
        teamsNames.forEach(name -> teams.add(new Team(name)));
    }

    private void setTeamMatches() {
        for (Team team : teams) {
            List<Match> matchesPerTeam = matches.stream()
                    .filter(match ->
                            Objects.equals(match.getHomeTeamName(), team.getName())
                                    || Objects.equals(match.getAwayTeamName(), team.getName()))
                    .collect(Collectors.toList());
            team.setMatches(matchesPerTeam);
        }
    }

}
