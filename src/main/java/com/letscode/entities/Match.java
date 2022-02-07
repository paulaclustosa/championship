package com.letscode.entities;

import lombok.*;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;
import java.util.Comparator;

@Builder
@Getter
@Setter
@EqualsAndHashCode

public class Match implements Comparable<Match> {

    private LocalDate date;
    private String homeTeamName, awayTeamName;
    private int homeTeamGoals, awayTeamGoals;

    @Override
    public String toString() {
        return  date + ": " +
                homeTeamName + " " +
                homeTeamGoals + " x " +
                awayTeamGoals + " " +
                awayTeamName;
    }

    @Override
    public int compareTo(@NotNull Match match) {
        return Comparator.comparing(Match::getDate)
                .thenComparing(Match::getHomeTeamName)
                .thenComparing(Match::getAwayTeamName)
                .compare(this, match);
    }

}
