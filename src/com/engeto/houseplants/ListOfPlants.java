package com.engeto.houseplants;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
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

    public static void exportToFile(List<Plant> data, String outputFile)
            throws PlantException {
        try (PrintWriter writer =
                     new PrintWriter(new FileWriter(outputFile))) {
            for (Plant plant : data) {
                writer.println(plant.exportToString("\t"));
            }
        } catch (IOException ioe) {
            throw new PlantException("File " + ioe.getMessage());
        }
    }

    public void addPlant(Plant plant) {
        listOfPlants.add(plant);
    }

    public static List<Plant> importFromFile(String inputFile,
                                             String delimiter)
            throws PlantException {
        List<Plant> plantsList = new ArrayList<>();
        File data = new File(inputFile);
        long line = 0L;
        try {
            Scanner scanner = new Scanner(data);
            while (scanner.hasNext()) {
                ++line;
                String record = scanner.nextLine();
                plantsList.add(Plant.parsePlant(record, delimiter));
            }
        } catch (FileNotFoundException fnfe) {
            throw new PlantException("File " + fnfe.getMessage());
        } catch (PlantException pe) {
            throw new PlantException(pe.getMessage() + ", line " + line);
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
