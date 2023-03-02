package com.engeto.houseplants;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class Houseplants {
    public static void main(String[] args) {
        ListOfPlants listOfPlants = new ListOfPlants();
        Logger logger = Logger.getLogger(Houseplants.class.getName());
        Scanner scanner = null;
        try {
            List<Plant> loadedList = ListOfPlants
                    .importFromFile(Settings.getFile(), "\t");
            listOfPlants.setListOfPlants(loadedList);
            List<Plant> copyOfListOfPlants =
                    new ArrayList<>(listOfPlants.getListOfPlants());
            Collections.sort(copyOfListOfPlants);

            System.out.println("Print watering info sorted by name for each "
                    + "plant (imported from file \"" + Settings.getFile()
                    + "\" into a list):");
            for (Plant plant : copyOfListOfPlants) {
                System.out.println(plant.getWateringInfo());
            }
            System.out.println("---");

            Plant plant1 = new Plant("Růže");
            listOfPlants.addPlant(plant1);
            Plant plant2 = new Plant("Tulipán", "pěkná květina",
                    LocalDate.of(2021, 1, 1),
                    LocalDate.of(2021, 1, 6), 5);
            listOfPlants.addPlant(plant2);
            listOfPlants.removePlantAtIndex(1);

            copyOfListOfPlants.clear();
            copyOfListOfPlants.addAll(listOfPlants.getListOfPlants());
            Collections.sort(copyOfListOfPlants,
                    new PlantWateringComparator());
            ListOfPlants.exportToFile(copyOfListOfPlants,
                    Settings.getFileNew());
            System.out.println("Print contents of file sorted by watering \""
                    + Settings.getFileNew()
                    + "\" (2 plants added, 1 removed):");
            scanner = new Scanner(new File(Settings.getFileNew()));
            while (scanner.hasNext()) {
                System.out.println(scanner.nextLine());
            }
            System.out.println("---");

            System.out.println("Find out and print dates planted "
                    + "(print once if more plants planted on the same date):");
            copyOfListOfPlants.stream()
                    .map(Plant::getPlanted)
                    .collect(Collectors.toSet())
                    .forEach(System.out::println);
            System.out.println("---");

            System.out.println("Find out and print dates planted, "
                    + "consider last month only "
                    + "(print once if more plants planted on the same date):");
            copyOfListOfPlants.stream()
                    .map(Plant::getPlanted)
                    .filter(planted -> ChronoUnit.MONTHS
                            .between(planted, LocalDate.now()) < 1)
                    .collect(Collectors.toSet())
                    .forEach(System.out::println);

            /*
            System.out.println("Import of file with wrong datum\n"
                    + "(comment out this import in source code to see "
                    + "the other one in action):");
            List<Plant> loadedListWrongDatum = ListOfPlants.importFromFile(
                    Settings.getFileWrongDatum(), "\t");

            System.out.println("Import of file with wrong frequency of "
                    + "Watering:");
            List<Plant> loadedListWrongFrequency = ListOfPlants.importFromFile(
                    Settings.getFileWrongFrequency(), "\t");
            */
        } catch(FileNotFoundException | PlantException e) {
            logger.log(Level.WARNING, e.getClass().getName() + " - "
                    + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}
