package com.letscode.services;

import com.letscode.entities.Match;
import com.letscode.entities.Team;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;


public class ChampionshipFileGenerator {

    public static void createFilePerTeam(List<Team> teams) {
        for (Team team : teams) {
            try {
                BufferedWriter bw = new BufferedWriter(new FileWriter(team.getName() + ".txt"));
                for (Match match : team.getMatches()) {
                    bw.write(match + "\n");
                }
                bw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
