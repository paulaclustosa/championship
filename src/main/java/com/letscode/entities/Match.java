package com.letscode.entities;

import lombok.*;

import java.time.LocalDate;

@Builder
@Getter
@Setter
@EqualsAndHashCode

public class Match implements Comparable<Match> {

    private LocalDate date;
    private String homeTeam, awayTeam;
    private int goalsHomeTeam, goalsAwayTeam;

    @Override
    public String toString() {
        return  date + ": " +
                homeTeam + " " +
                goalsHomeTeam + " x " +
                goalsAwayTeam + " " +
                awayTeam;
    }

    @Override
    public int compareTo(Match match) {
        return date.compareTo(match.date);
    }

}
