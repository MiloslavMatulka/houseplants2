package com.engeto.houseplants;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Plant implements Comparable<Plant> {
    //region Attributes
    private int frequencyOfWatering;
    private String name;
    private String notes;
    private LocalDate planted;
    private LocalDate watering;
    //endregion

    //region Constructors
    public Plant(String name, String notes, LocalDate planted,
                 LocalDate watering, int frequencyOfWatering)
            throws PlantException {
        this.name = name;
        this.notes = notes;
        this.planted = planted;
        setWatering(watering);
        setFrequencyOfWatering(frequencyOfWatering);
    }

    public Plant(String name, LocalDate planted, int frequencyOfWatering)
            throws PlantException {
        this(name, "", planted, LocalDate.now(), frequencyOfWatering);
    }

    public Plant(String name) throws PlantException {
        this(name, LocalDate.now(), Settings.getDefaultWatering());
    }
    //endregion

    //region Getters and Setters
    public int getFrequencyOfWatering() {
        return frequencyOfWatering;
    }

    public void setFrequencyOfWatering(int frequencyOfWatering)
            throws PlantException {
        if (frequencyOfWatering <= 0) {
            throw new PlantException("Frequency of watering must be "
                    + "greater than 0.");
        }
        this.frequencyOfWatering = frequencyOfWatering;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public LocalDate getPlanted() {
        return planted;
    }

    public void setPlanted(LocalDate planted) {
        this.planted = planted;
    }

    public LocalDate getWatering() {
        return watering;
    }

    public void setWatering(LocalDate watering) throws PlantException {
        if (watering.isBefore(getPlanted())) {
            throw new PlantException("Watering cannot be older than "
                    + "planting.");
        }
        this.watering = watering;
    }
    //endregion

    @Override
    public int compareTo(Plant secondPlant) {
        return this.name.compareTo(secondPlant.name);
    }

    public String getWateringInfo() {
        return "Plant name: " + getName() + ", watering: " + getWatering()
                + ", recommended next watering: "
                + getWatering().plusDays(Settings.getDefaultWatering());
    }

    public static Plant parsePlant(String data, String delimiter)
            throws NumberFormatException, DateTimeParseException,
            PlantException {
        String[] items = data.split(delimiter);

        String name = items[0];
        String notes = items[1];
        int frequencyOfWatering = Integer.parseInt(items[2]);
        LocalDate watering = LocalDate.parse(items[3]);
        LocalDate planted = LocalDate.parse(items[4]);

        return new Plant(name, notes, planted, watering, frequencyOfWatering);
    }

    public String exportToString(String delimiter) {
        return getName() + delimiter + getNotes() + delimiter
                + getFrequencyOfWatering() + delimiter + getWatering()
                + delimiter + getPlanted();
    }
}
