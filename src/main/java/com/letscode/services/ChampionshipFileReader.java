package com.letscode.services;

import com.letscode.entities.Match;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.SortedSet;
import java.util.TreeSet;

public class ChampionshipFileReader {

    public static SortedSet<Match> readFile(File file, DateTimeFormatter formatter) {
        SortedSet<Match> matches = new TreeSet<>();
        try {
            BufferedReader br = new BufferedReader(new java.io.FileReader(file));
            String line;
            while ((line = br.readLine()) != null) {
                String[] dataLine = line.split(";");

                Match match = Match.builder()
                        .date(LocalDate.parse(dataLine[4], formatter))
                        .homeTeamName(dataLine[0])
                        .awayTeamName(dataLine[1])
                        .goalsHomeTeam(Integer.parseInt(dataLine[2]))
                        .goalsAwayTeam(Integer.parseInt(dataLine[3]))
                        .build();

                matches.add(match);
            }
            br.close();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        return matches;
    }

}