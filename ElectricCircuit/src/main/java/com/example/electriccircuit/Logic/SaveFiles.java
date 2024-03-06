package com.example.electriccircuit.Logic;

import java.io.*;
import java.util.*;

public class SaveFiles {
    private static PrintWriter Saver;
    private static Scanner Loader;

    public static void main(String[] args) throws FileNotFoundException { // TO BE DELETED
        // CHECKING IF THEY WORK
        saveGame();
        loadGame();

    }

    // method that saves files, both achievements and sandbox
    public static void saveGame(){
        int[][] grid = BuilderMatrix.getGrid();
        saveSandbox(grid);

        Unlocks achievementSaver = new Unlocks();
        StringBuilder achievementBitString = new StringBuilder(achievementSaver.getAchievementBitString());
        saveAchievements(achievementBitString);
    }

    // method that loads files, both achievements and sandbox
    public static void loadGame(){
        BuilderMatrix grid = new BuilderMatrix();
        grid.setGrid(loadSandbox());

        Unlocks achievementLoader = new Unlocks();
        achievementLoader.setAchievementBitString(loadAchievements());
    }

    // this is the method that writes a string of data to a given file, used for saving
    public static void writeToFile(StringBuilder dataString, String filePath) throws FileNotFoundException {
        Saver = new PrintWriter(filePath);
        Saver.write(dataString.toString());
        Saver.close();
    }

    // this is the method that reads a string of datato a given file, used for loading
    public static String readFromFile(String filePath) throws FileNotFoundException {
        File file = new File(filePath);
        Loader = new Scanner(file);
        String dataString = Loader.nextLine();
        Loader.close();
        return dataString;
    }

    //this attmepts to write to a file. If it doesn't exist, it creates the file, and then calls itself to try again.
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

    //this attmepts to read from a file. If it doesn't exist, it does nothing
    public static String loadAchievements(){
        String filePath = "ElectricCircuit/src/main/resources/SaveFiles/achievementSaveFile.txt";
        String dataString = "";
        int counter = 0;

        try {
            dataString = readFromFile(filePath);
        } catch (FileNotFoundException ignored) {}

        return dataString;
    }

    //this attmepts to write to a file. If it doesn't exist, it creates the file, and then calls itself to try again.
    // it creates a string out of a matrix
    public static void saveSandbox(int[][] grid) {
        StringBuilder sandboxBitString = new StringBuilder(700);
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

    //this attmepts to read from a file. If it doesn't exist, it does nothing
    //it assigns the values from a string to a matrix following the same pattern as it was read
    public static int[][] loadSandbox() {
        String filePath = "ElectricCircuit/src/main/resources/SaveFiles/sandboxSaveFile.txt";
        String dataString = "";
        int[][] data = new int[35][20];
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