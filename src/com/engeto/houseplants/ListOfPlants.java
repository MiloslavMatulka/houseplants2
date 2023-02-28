package com.engeto.houseplants;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ListOfPlants {
    private List<Plant> listOfPlants = new ArrayList<>();

    public List<Plant> getListOfPlants() {
        return new ArrayList<>(listOfPlants);
    }

    public void setListOfPlants(List<Plant> listOfPlants) {
        this.listOfPlants = listOfPlants;
    }

    public static void exportToFile(List<Plant> data, String outputFile) {
        try (PrintWriter writer =
                     new PrintWriter(new FileWriter(outputFile))) {
            for (Plant plant : data) {
                writer.println(plant.exportToString("\t"));
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public void addPlant(Plant plant) {
        listOfPlants.add(plant);
    }

    public static List<Plant> importFromFile(String inputFile,
                                             String delimiter)
            throws FileNotFoundException, NumberFormatException,
            DateTimeParseException, PlantException {
        List<Plant> plantsList = new ArrayList<>();
        File data = new File(inputFile);
        Scanner scanner = new Scanner(data);
        while (scanner.hasNext()) {
            String record = scanner.nextLine();
            plantsList.add(Plant.parsePlant(record, delimiter));
        }
        return plantsList;
    }

    public void removePlant(Plant plant) {
        listOfPlants.remove(plant);
    }

    public void removePlantAtIndex(int index) {
        listOfPlants.remove(index);
    }
}
