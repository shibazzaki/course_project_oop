package com.example.composerapp.composerapp;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ComposerFileReader {
    public static List<Composer> readComposersFromFile(File file) throws FileNotFoundException {
        List<Composer> composers = new ArrayList<>();
        Scanner scanner = new Scanner(file);

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] parts = line.split(" ");

            if (parts.length == 4) {
                String name = parts[1];
                int birthYear = Integer.parseInt(parts[2]);
                String country = parts[3];
                composers.add(new Composer(name, birthYear, country));
            }
        }

        scanner.close();
        return composers;
    }
}