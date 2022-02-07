package com.letscode.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@EqualsAndHashCode

public class Team {

    private String name;
    private List<Match> matches;
    private int win;
    private int draw;
    private int lose;
    private int points;
    private int goals;
    private int gamesPlayed;

    public Team(String name) {
        this.name = name;
    }

    public void setPoints() {
        this.points = 3 * this.win + this.draw;
    }

    @Override
    public String toString() {
        return  name + ";" +
                gamesPlayed + ";" +
                win + ";" +
                draw + ";" +
                lose + ";" +
                points;
    }

}
