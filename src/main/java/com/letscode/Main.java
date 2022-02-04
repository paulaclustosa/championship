package com.letscode;

import com.letscode.entities.Team;
import com.letscode.services.FileGenerator;

import java.io.*;

import java.time.format.DateTimeFormatter;


public class Main {

    public static void main(String[] args) {

        final File FILE = new File("C:\\Users\\paula\\Downloads\\PlanilhaJogos.csv");
        final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        Championship championship = new Championship(FILE, FORMATTER);
        FileGenerator.createFilePerTeam(championship);

    }
}
