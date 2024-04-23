package com.example.composerapp.composerapp;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ComposerSorter {
    public static void sortByNameCountryBirthYear(List<Composer> composers) {
        Collections.sort(composers, Comparator.comparing(Composer::getName)
                .thenComparing(Composer::getCountry)
                .thenComparingInt(Composer::getBirthYear));
    }

    public static void sortByBirthYearNameCountry(List<Composer> composers) {
        Collections.sort(composers, Comparator.comparingInt(Composer::getBirthYear)
                .thenComparing(Composer::getName)
                .thenComparing(Composer::getCountry));
    }

    public static void sortByNameBirthYearCountry(List<Composer> composers) {
        Collections.sort(composers, Comparator.comparing(Composer::getName)
                .thenComparingInt(Composer::getBirthYear)
                .thenComparing(Composer::getCountry));
    }
}