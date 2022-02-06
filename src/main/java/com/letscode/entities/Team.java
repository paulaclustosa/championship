package com.letscode.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

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

    public Team(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return  name + ";" +
                win + ";" +
                draw + ";" +
                lose + ";" +
                points;
    }
}
