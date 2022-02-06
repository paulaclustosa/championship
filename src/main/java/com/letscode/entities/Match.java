package com.letscode.entities;

import lombok.*;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Comparator;

@Builder
@Getter
@Setter
@EqualsAndHashCode

public class Match implements Comparable<Match>, Serializable {

    private LocalDate date;
    private String homeTeamName, awayTeamName;
    private int goalsHomeTeam, goalsAwayTeam;

    @Override
    public String toString() {
        return  date + ": " +
                homeTeamName + " " +
                goalsHomeTeam + " x " +
                goalsAwayTeam + " " +
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
