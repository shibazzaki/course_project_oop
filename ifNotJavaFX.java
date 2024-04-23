import java.io.*;
import java.util.*;

class Composer implements Comparable<Composer> {
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

public class ComposerApp {
    public static void main(String[] args) {
        List<Composer> germanComposers = new ArrayList<>();
        List<Composer> italianComposers = new ArrayList<>();
        List<Composer> otherComposers = new ArrayList<>();

        // Читання даних з вхідних файлів
        readComposersFromFiles(germanComposers, italianComposers, otherComposers);

        // Сортування композиторів за замовчуванням (ім'я, рік народження, країна)
        Collections.sort(germanComposers);
        Collections.sort(italianComposers);
        Collections.sort(otherComposers);

        // Запис відсортованих даних у вихідні файли
        writeComposersToFiles(germanComposers, italianComposers, otherComposers);
    }

    private static void readComposersFromFiles(List<Composer> germanComposers, List<Composer> italianComposers, List<Composer> otherComposers) {
        try {
            readComposersFromFile("input_file1.txt", germanComposers, italianComposers, otherComposers);
            readComposersFromFile("input_file2.txt", germanComposers, italianComposers, otherComposers);
            readComposersFromFile("input_file3.txt", germanComposers, italianComposers, otherComposers);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void readComposersFromFile(String fileName, List<Composer> germanComposers, List<Composer> italianComposers, List<Composer> otherComposers) throws IOException {
        File file = new File(fileName);
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line;

        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(" ");
            if (parts.length == 4) {
                String name = parts[1];
                int birthYear = Integer.parseInt(parts[2]);
                String country = parts[3];
                Composer composer = new Composer(name, birthYear, country);

                if (country.equalsIgnoreCase("Germany")) {
                    germanComposers.add(composer);
                } else if (country.equalsIgnoreCase("Italy")) {
                    italianComposers.add(composer);
                } else {
                    otherComposers.add(composer);
                }
            }
        }

        reader.close();
    }

    private static void writeComposersToFiles(List<Composer> germanComposers, List<Composer> italianComposers, List<Composer> otherComposers) {
        try {
            writeComposersToFile("german_composers.txt", germanComposers);
            writeComposersToFile("italian_composers.txt", italianComposers);
            writeComposersToFile("other_composers.txt", otherComposers);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void writeComposersToFile(String fileName, List<Composer> composers) throws IOException {
        File file = new File(fileName);
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));

        for (Composer composer : composers) {
            writer.write(composer.toString());
            writer.newLine();
        }

        writer.close();
    }
}
