package com.letscode.entities;

import com.letscode.services.InputFileHandler;
import lombok.Getter;

import java.util.*;
import java.util.stream.Collectors;

@Getter

public class Championship {

    private SortedSet<Match> matches;
    private List<Team> teams;

    public Championship(String fileName) {
        this.matches = InputFileHandler.readFile(fileName);
        this.teams = setTeams();
        setTeamMatches();
        setPoints();
    }

    private List<Team> setTeams() {
        List<String> teamsNames = new ArrayList<>();
        matches.forEach(match -> {
                    if (!teamsNames.contains(match.getHomeTeamName()) || !teamsNames.contains(match.getAwayTeamName())) {
                        if (!teamsNames.contains(match.getHomeTeamName())) teamsNames.add(match.getHomeTeamName());
                        else if (!teamsNames.contains(match.getAwayTeamName())) teamsNames.add(match.getAwayTeamName());
                    }
                });

        return teamsNames.stream().map(Team::new).collect(Collectors.toList());
    }

    private void setTeamMatches() {
        teams.forEach(team -> {
            List<Match> teamMatchesHistory = matches
                    .stream()
                    .filter(match -> Objects.equals(match.getHomeTeamName(), team.getName())
                            || Objects.equals(match.getAwayTeamName(), team.getName()))
                    .collect(Collectors.toList());

            team.setMatches(teamMatchesHistory);
        });
    }

    private void setPoints() {
        int points = 0;
        int win = 0;
        int draw = 0;
        int lose = 0;
        for (Team team : teams) {
            for (Match match : team.getMatches()) {
                if (Objects.equals(team.getName(), match.getHomeTeamName()) || Objects.equals(team.getName(), match.getAwayTeamName())) {
                    if (Objects.equals(team.getName(), match.getHomeTeamName())) {
                        if (match.getGoalsHomeTeam() > match.getGoalsAwayTeam()) {
                            team.setPoints(points = points + 3);
                            team.setWin(win = win + 1);
                        } else if (match.getGoalsHomeTeam() == match.getGoalsAwayTeam()) {
                            team.setPoints(points = points + 1);
                            team.setDraw(draw = draw + 1);
                        } else team.setLose(lose = lose + 1);
                    }
                    else if (Objects.equals(team.getName(), match.getAwayTeamName())) {
                        if (match.getGoalsAwayTeam() > match.getGoalsHomeTeam()) {
                            team.setPoints(points = points + 3);
                            team.setWin(win = win + 1);
                        } else if (match.getGoalsAwayTeam() == match.getGoalsHomeTeam()) {
                            team.setPoints(points = points + 1);
                            team.setDraw(draw = draw + 1);
                        } else team.setLose(lose = lose + 1);
                    }
                }
            }

            points = 0;
            win = 0;
            draw = 0;
            lose = 0;
        }
    }

}
