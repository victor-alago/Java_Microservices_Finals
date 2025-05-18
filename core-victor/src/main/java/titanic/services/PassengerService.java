package titanic.services;


import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;
import titanic.datamodel.Passenger;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PassengerService {

    public List<Passenger> loadPassengers(String csvFile) {
        List<Passenger> passengers = new ArrayList<>();

        try (CSVReader csvReader = new CSVReaderBuilder(new FileReader(csvFile))
                .withCSVParser(new com.opencsv.CSVParserBuilder().withSeparator(';').build()) // Use semicolon as delimiter
                .build()) {

            List<String[]> records = csvReader.readAll();

            // Skip the header row
            for (int i = 1; i < records.size(); i++) {
                String[] record = records.get(i);

                // Ensure the record has the expected number of columns
                if (record.length < 5) {
                    System.err.println("Skipping malformed row: " + String.join(";", record));
                    continue;
                }

                try {
                    // Parse fields and handle missing or malformed data
                    String name = record[0].trim();
                    String pclass = record[1].trim();
                    String ageStr = record[2].trim();
                    String sex = record[3].trim();
                    String survivedStr = record[4].trim();

                    // Convert age to a double, default to -1 if missing
                    double age = ageStr.isEmpty() ? -1.0 : Double.parseDouble(ageStr);

                    // Convert survived to a boolean (1 -> true, 0 -> false)
                    boolean survived = survivedStr.equals("1");

                    // Create and add Passenger object
                    Passenger passenger = new Passenger(name, pclass, age, sex, survived);
                    passengers.add(passenger);

                } catch (NumberFormatException e) {
                    System.err.println("Skipping row with invalid number format: " + String.join(";", record));
                }
            }
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }

        return passengers;
    }

    // New method to load passengers from a directory
    public List<Passenger> loadPassengersFromDirectory(String directoryPath) {
        List<Passenger> allPassengers = new ArrayList<>();

        File directory = new File(directoryPath);
        if (!directory.isDirectory()) {
            System.err.println("Provided path is not a directory: " + directoryPath);
            return allPassengers;
        }

        File[] csvFiles = directory.listFiles((dir, name) -> name.endsWith(".csv"));
        if (csvFiles == null) {
            System.err.println("No CSV files found in directory: " + directoryPath);
            return allPassengers;
        }

        for (File csvFile : csvFiles) {
            System.out.println("Processing file: " + csvFile.getName());
            List<Passenger> passengersFromFile = loadPassengers(csvFile.getAbsolutePath());
            allPassengers.addAll(passengersFromFile);
        }

        return allPassengers;
    }
}