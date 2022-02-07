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
        setTeamPoints();
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
            team.setGamesPlayed(team.getMatches().size());
        });
    }

    private void setTeamPoints() {
        int win = 0;
        int draw = 0;
        int lose = 0;
        int goals = 0;
        for (Team team : teams) {
            for (Match match : team.getMatches()) {
                if (Objects.equals(team.getName(), match.getHomeTeamName()) || Objects.equals(team.getName(), match.getAwayTeamName())) {
                    if (Objects.equals(team.getName(), match.getHomeTeamName())) {
                        goals = goals + match.getHomeTeamGoals();
                        if (match.getHomeTeamGoals() > match.getAwayTeamGoals()) {
                            win = win + 1;
                        } else if (match.getHomeTeamGoals() == match.getAwayTeamGoals()) {
                            draw = draw + 1;
                        } else lose = lose + 1;
                    }
                    else if (Objects.equals(team.getName(), match.getAwayTeamName())) {
                        goals = goals + match.getAwayTeamGoals();
                        if (match.getAwayTeamGoals() > match.getHomeTeamGoals()) {
                            win = win + 1;
                        } else if (match.getAwayTeamGoals() == match.getHomeTeamGoals()) {
                            draw = draw + 1;
                        } else lose = lose + 1;
                    }
                }
                team.setWin(win);
                team.setDraw(draw);
                team.setLose(lose);
                team.setPoints();
                team.setGoals(goals);
            }
            win = 0;
            draw = 0;
            lose = 0;
            goals = 0;
        }
    }

}
