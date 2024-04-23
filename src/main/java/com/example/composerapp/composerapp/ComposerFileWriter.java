package com.example.composerapp.composerapp;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ComposerFileWriter {
    public static void writeComposersToFile(List<Composer> composers, File file) throws IOException {
        FileWriter writer = new FileWriter(file);

        for (Composer composer : composers) {
            writer.write(composer.getName() + " " + composer.getBirthYear() + " " + composer.getCountry() + "\n");
        }

        writer.close();
    }
}