package com.example.electriccircuit.Logic;

import java.io.*;
import java.util.*;

public class SaveFiles {
    private static String filePath = "ElectricCircuit/src/main/resources/SaveFiles/SaveFile.txt";
    public static Scanner Loader = new Scanner(filePath);
    public static PrintWriter Saver;

    // method that saves files
    public static void saveGame(){

        // Create a printWriter. Creates a save file if one doesn't exist.
        try {
            Saver = new PrintWriter(filePath);
        } catch (FileNotFoundException ex) {
            try {
                File saveFile = new File(filePath);
                Saver = new PrintWriter(filePath);
            } catch (IOException e) {
                Debug.handleException(e);
            }
        }

        // gets the matrix, and creates a stringBuilder
        int[][] grid = BuilderMatrix.getGrid();
        StringBuilder sandboxBitString = new StringBuilder(700);

        // writes the matrix indices to the string
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                sandboxBitString.append(grid[i][j]);
            }
        }

        //saves the matrix
        Saver.write(sandboxBitString.toString() + "\n");

        //gets the achievement bit string
        Unlocks achievementSaver = new Unlocks();
        String achievementBitString = achievementSaver.getAchievementBitString().toString();

        //writes it to the second line of the save file
        Saver.write(achievementBitString);
        Saver.close();
    }


    // method that loads files
    public static void loadGame(){
        BuilderMatrix grid = new BuilderMatrix();
        String dataString = "";
        int[][] data = new int[35][20];
        int counter = 0;

        // reades the matrix in string form
        dataString = Loader.nextLine();

        // assigns values to the matrix "data" from the string
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[0].length; j++) {
                data[i][j] = Character.getNumericValue(dataString.charAt(counter));
                counter++;
            }
        }

        // set the matrixBuilder grid to "data".
        grid.setGrid(data);

        //reads the second line of the save file and sets it as the users achievements.
        Unlocks achievementLoader = new Unlocks();
        dataString = "";
        dataString = Loader.nextLine();
        achievementLoader.setAchievementBitString(dataString);
    }

}