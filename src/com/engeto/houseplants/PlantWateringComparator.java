package com.engeto.houseplants;

import java.util.Comparator;

public class PlantWateringComparator implements Comparator<Plant> {
    @Override
    public int compare(Plant firstPlant, Plant secondPlant) {
        return firstPlant.getWatering().compareTo(secondPlant.getWatering());
    }
}
