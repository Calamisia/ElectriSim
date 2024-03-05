package com.example.electriccircuit.Logic;

import java.io.*;
import java.util.*;

public class SaveFiles {
    private static PrintWriter Saver;
    private static Scanner Loader;

    public static void main(String[] args) throws FileNotFoundException {

        saveGame();
        loadGame();

    }

    public static void saveGame(){
        int[][] grid = BuilderMatrix.getGrid();
        saveSandbox(grid);

        StringBuilder achievementBitString = new StringBuilder(Unlocks.getAchievementBitString());
        saveAchievements(achievementBitString);
    }

    public static void loadGame(){
        BuilderMatrix grid = new BuilderMatrix();
        grid.setGrid(loadSandbox());

        Unlocks achievementLoader = new Unlocks();
        achievementLoader.setAchievementBitString(loadAchievements());
        // set the achievementloader string = loadAchievements();
    }


    public static void writeToFile(StringBuilder dataString, String filePath) throws FileNotFoundException {
        Saver = new PrintWriter(filePath);
        Saver.write(dataString.toString());
        Saver.close();
    }

    public static String readFromFile(String filePath) throws FileNotFoundException {
        File file = new File(filePath);
        Loader = new Scanner(file);
        String dataString = Loader.nextLine();
        Loader.close();
        return dataString;
    }

    public static void saveAchievements(StringBuilder achievementBitString){
        String filePath = "ElectricCircuit/src/main/resources/SaveFiles/achievementSaveFile.txt";
        try {
            writeToFile(achievementBitString, filePath);
        } catch (FileNotFoundException ex) {
            try {
                File saveFile = new File(filePath);
                writeToFile(achievementBitString, filePath);
            } catch (FileNotFoundException ignored) {
            }
        }
    }

    public static String loadAchievements(){
        String filePath = "ElectricCircuit/src/main/resources/SaveFiles/achievementSaveFile.txt";
        String dataString = "";
        int counter = 0;

        try {
            dataString = readFromFile(filePath);
        } catch (FileNotFoundException ignored) {}

        return dataString;
    }


    public static void saveSandbox(int[][] grid) {
        StringBuilder sandboxBitString = new StringBuilder(400);
        String filePath = "ElectricCircuit/src/main/resources/SaveFiles/sandboxSaveFile.txt";
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                sandboxBitString.append(grid[i][j]);
            }
        }
        try {
            writeToFile(sandboxBitString, filePath);
        } catch (FileNotFoundException ex) {
            try {
                File saveFile = new File(filePath);
                writeToFile(sandboxBitString, filePath);
            } catch (FileNotFoundException ignored) {
            }
        }
    }
    public static int[][] loadSandbox() {
        String filePath = "ElectricCircuit/src/main/resources/SaveFiles/sandboxSaveFile.txt";
        String dataString = "";
        int[][] data = new int[20][20];
        int counter = 0;

        try {
            dataString = readFromFile(filePath);
        } catch (FileNotFoundException ignored) {}

        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[0].length; j++) {
                data[i][j] = Character.getNumericValue(dataString.charAt(counter));
                counter++;
            }
        }

        return data;
    }

}