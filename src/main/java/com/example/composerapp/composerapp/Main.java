package com.example.composerapp.composerapp;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Шляхи до вхідних файлів
        File file1 = new File("D:\\prog_shit\\ComposerApp\\src\\main\\resources\\input_files\\file1.txt");
        File file2 = new File("D:\\prog_shit\\ComposerApp\\src\\main\\resources\\input_files\\file2.txt");
        File file3 = new File("D:\\prog_shit\\ComposerApp\\src\\main\\resources\\input_files\\file3.txt");

        // Створення списків композиторів
        List<Composer> germanComposers = new ArrayList<>();
        List<Composer> italianComposers = new ArrayList<>();
        List<Composer> otherComposers = new ArrayList<>();

        try {
            // Читання даних з файлів
            List<Composer> composers1 = ComposerFileReader.readComposersFromFile(file1);
            List<Composer> composers2 = ComposerFileReader.readComposersFromFile(file2);
            List<Composer> composers3 = ComposerFileReader.readComposersFromFile(file3);

            // Розподіл композиторів за країнами
            for (Composer composer : composers1) {
                separateComposerByCountry(composer, germanComposers, italianComposers, otherComposers);
            }
            for (Composer composer : composers2) {
                separateComposerByCountry(composer, germanComposers, italianComposers, otherComposers);
            }
            for (Composer composer : composers3) {
                separateComposerByCountry(composer, germanComposers, italianComposers, otherComposers);
            }

            // Сортування списків композиторів (наприклад, за іменем, роком народження та країною)
            ComposerSorter.sortByNameCountryBirthYear(germanComposers);
            ComposerSorter.sortByNameCountryBirthYear(italianComposers);
            ComposerSorter.sortByNameCountryBirthYear(otherComposers);

            // Запис відсортованих даних у вихідні файли
            File outputFileGerman = new File("D:\\prog_shit\\ComposerApp\\src\\main\\resources\\output_files\\german_composers.txt");
            File outputFileItalian = new File("D:\\prog_shit\\ComposerApp\\src\\main\\resources\\output_files\\italian_composers.txt");
            File outputFileOther = new File("D:\\prog_shit\\ComposerApp\\src\\main\\resources\\output_files\\other_composers.txt");

            ComposerFileWriter.writeComposersToFile(germanComposers, outputFileGerman);
            ComposerFileWriter.writeComposersToFile(italianComposers, outputFileItalian);
            ComposerFileWriter.writeComposersToFile(otherComposers, outputFileOther);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void separateComposerByCountry(Composer composer, List<Composer> germanComposers,
                                                  List<Composer> italianComposers, List<Composer> otherComposers) {
        String country = composer.getCountry();
        if (country.equalsIgnoreCase("Germany")) {
            germanComposers.add(composer);
        } else if (country.equalsIgnoreCase("Italy")) {
            italianComposers.add(composer);
        } else {
            otherComposers.add(composer);
        }
    }
}