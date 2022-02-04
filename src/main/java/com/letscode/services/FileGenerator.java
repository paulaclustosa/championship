package com.letscode.services;

import com.letscode.Championship;
import com.letscode.entities.Match;
import com.letscode.entities.Team;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;


public class FileGenerator {

    public static void createFilePerTeam(Championship championship) {
        for (Team team : championship.getTeams()) {
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
