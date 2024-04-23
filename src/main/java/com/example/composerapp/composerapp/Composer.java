package com.example.composerapp.composerapp;

public class Composer implements Comparable<Composer> {
    private String name;
    private int birthYear;
    private String country;

    public Composer(String name, int birthYear, String country) {
        this.name = name;
        this.birthYear = birthYear;
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public String getCountry() {
        return country;
    }

    @Override
    public int compareTo(Composer other) {
        // Порівняння за іменем, роком народження та країною за замовчуванням
        int nameCompare = name.compareTo(other.name);
        if (nameCompare != 0) {
            return nameCompare;
        }

        int birthYearCompare = Integer.compare(birthYear, other.birthYear);
        if (birthYearCompare != 0) {
            return birthYearCompare;
        }

        return country.compareTo(other.country);
    }

    @Override
    public String toString() {
        return name + " (" + birthYear + ") - " + country;
    }
}