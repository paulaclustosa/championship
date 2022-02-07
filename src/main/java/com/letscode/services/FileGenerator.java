package com.letscode.services;

import com.letscode.entities.Championship;
import com.letscode.entities.Match;
import com.letscode.entities.Team;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Comparator;

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

    public static void createChampionshipResultFile(Championship championship) throws IOException {
        championship.getTeams().sort(Comparator.comparing(Team::getPoints).reversed()
                .thenComparing(Comparator.comparing(Team::getWin).reversed())
                .thenComparing(Comparator.comparing(Team::getGoals).reversed()));
        String header = "Team; Games Played (GP); Win (W); Draw (D); Lose (L); Points (PTS)";

        Writer writer = new FileWriter("ChampionshipResult.csv");
        try (CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.EXCEL)) {
            csvPrinter.printRecord(header);
            for (Team team : championship.getTeams()) {
                csvPrinter.printRecord(team);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}


