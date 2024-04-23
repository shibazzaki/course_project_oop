package com.example.composerapp.composerapp;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ComposerApp extends Application {
    private ComboBox<String> sortTypeComboBox;
    private TextArea composersTextArea;
    private List<Composer> germanComposers;
    private List<Composer> italianComposers;
    private List<Composer> otherComposers;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Composer App");

        germanComposers = new ArrayList<>();
        italianComposers = new ArrayList<>();
        otherComposers = new ArrayList<>();

        // Створення компонентів інтерфейсу
        sortTypeComboBox = new ComboBox<>();
        sortTypeComboBox.getItems().addAll("Name, Country, Birth Year", "Birth Year, Name, Country", "Name, Birth Year, Country");
        sortTypeComboBox.setValue("Name, Country, Birth Year");

        composersTextArea = new TextArea();
        composersTextArea.setEditable(false);

        Button loadFilesButton = new Button("Load Files");
        loadFilesButton.setOnAction(event -> loadFiles());

        Button sortComposersButton = new Button("Sort Composers");
        sortComposersButton.setOnAction(event -> sortComposers());

        Button writeFilesButton = new Button("Write Files");
        writeFilesButton.setOnAction(event -> writeFiles());

        VBox root = new VBox(10, sortTypeComboBox, composersTextArea, loadFilesButton, sortComposersButton, writeFilesButton);
        root.setPadding(new Insets(10));

        Scene scene = new Scene(root, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void loadFiles() {
        FileChooser fileChooser = new FileChooser();
        List<File> files = fileChooser.showOpenMultipleDialog(null);
        if (files != null) {
            try {
                germanComposers.clear();
                italianComposers.clear();
                otherComposers.clear();

                for (File file : files) {
                    List<Composer> composers = ComposerFileReader.readComposersFromFile(file);
                    for (Composer composer : composers) {
                        separateComposerByCountry(composer);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void separateComposerByCountry(Composer composer) {
        String country = composer.getCountry();
        if (country.equalsIgnoreCase("Germany")) {
            germanComposers.add(composer);
        } else if (country.equalsIgnoreCase("Italy")) {
            italianComposers.add(composer);
        } else {
            otherComposers.add(composer);
        }
    }

    private void sortComposers() {
        String sortType = sortTypeComboBox.getValue();
        switch (sortType) {
            case "Name, Country, Birth Year":
                ComposerSorter.sortByNameCountryBirthYear(germanComposers);
                ComposerSorter.sortByNameCountryBirthYear(italianComposers);
                ComposerSorter.sortByNameCountryBirthYear(otherComposers);
                break;
            case "Birth Year, Name, Country":
                ComposerSorter.sortByBirthYearNameCountry(germanComposers);
                ComposerSorter.sortByBirthYearNameCountry(italianComposers);
                ComposerSorter.sortByBirthYearNameCountry(otherComposers);
                break;
            case "Name, Birth Year, Country":
                ComposerSorter.sortByNameBirthYearCountry(germanComposers);
                ComposerSorter.sortByNameBirthYearCountry(italianComposers);
                ComposerSorter.sortByNameBirthYearCountry(otherComposers);
                break;
        }

        displayComposers();
    }

    private void displayComposers() {
        StringBuilder composersText = new StringBuilder();
        composersText.append("German Composers:\n");
        for (Composer composer : germanComposers) {
            composersText.append(composer.toString()).append("\n");
        }
        composersText.append("\nItalian Composers:\n");
        for (Composer composer : italianComposers) {
            composersText.append(composer.toString()).append("\n");
        }
        composersText.append("\nOther Composers:\n");
        for (Composer composer : otherComposers) {
            composersText.append(composer.toString()).append("\n");
        }

        composersTextArea.setText(composersText.toString());
    }

    private void writeFiles() {
        try {
            File outputFileGerman = new File("german_composers.txt");
            File outputFileItalian = new File("italian_composers.txt");
            File outputFileOther = new File("other_composers.txt");

            ComposerFileWriter.writeComposersToFile(germanComposers, outputFileGerman);
            ComposerFileWriter.writeComposersToFile(italianComposers, outputFileItalian);
            ComposerFileWriter.writeComposersToFile(otherComposers, outputFileOther);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}