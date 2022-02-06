package com.letscode;

import com.letscode.entities.Championship;
import com.letscode.services.FileGenerator;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        String fileName = "matchesResult.csv";
        Championship championship = new Championship(fileName);
        FileGenerator.createFilePerTeam(championship);
        FileGenerator.createChampionshipResultFile(championship);

    }
}
