package com.engeto.houseplants;

public class Settings {
    private static final int DEFAULT_WATERING = 7;
    private static final String FILE = "resources/kvetiny.txt";
    private static final String FILE_NEW = "resources/kvetiny_new.txt";
    private static final String FILE_WRONG_DATUM =
            "resources/kvetiny-spatne-datum.txt";
    private static final String FILE_WRONG_FREQUENCY =
            "resources/kvetiny-spatne-frekvence.txt";

    public static int getDefaultWatering() {
        return DEFAULT_WATERING;
    }

    public static String getFile() {
        return FILE;
    }

    public static String getFileNew() {
        return FILE_NEW;
    }

    public static String getFileWrongDatum() {
        return FILE_WRONG_DATUM;
    }

    public static String getFileWrongFrequency() {
        return FILE_WRONG_FREQUENCY;
    }
}
