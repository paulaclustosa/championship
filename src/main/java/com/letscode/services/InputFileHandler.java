package com.letscode.services;

import com.letscode.entities.Match;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Stream;

public class InputFileHandler {

    public static SortedSet<Match> readFile(String fileName) {
        SortedSet<Match> matches = new TreeSet<>();
        try {
            FileReader fr = new FileReader(fileName);
            BufferedReader br = new BufferedReader(fr);

            Stream<String> lines = br.lines().skip(1);

            lines.forEachOrdered(line -> {
                String[] data = line.split(";");
                Match match = Match.builder()
                        .date(LocalDate.parse(data[4], DateTimeFormatter.ofPattern("yyyy-MM-d")))
                        .homeTeamName(data[0])
                        .awayTeamName(data[1])
                        .goalsHomeTeam(Integer.parseInt(data[2]))
                        .goalsAwayTeam(Integer.parseInt(data[3]))
                        .build();
                matches.add(match);
            });

            br.close();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        return matches;
    }

}
