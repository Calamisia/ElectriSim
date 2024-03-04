package com.example.electriccircuit.Logic;

import java.io.*;
import java.util.*;

public class SaveFiles {
    private static PrintWriter sandBoxSaver;
    private static Scanner sandBoxLoader;
    private PrintWriter achievementSaver;
    private Scanner achievementLoader;

    public static void main(String[] args) throws FileNotFoundException {
        int[][] test = new int[20][20];
        for (int i = 0; i < test.length; i++) {
            for (int j = 0; j < test[i].length; j++) {
                test[i][j] = (int) Math.floor(Math.random() * 5);
            }
        }

        saveSandbox(test);
        int[][] outputTest;
        outputTest = loadSandbox();

        for (int i = 0; i < outputTest.length; i++) {
            for (int j = 0; j < outputTest[i].length; j++) {
                System.out.print(outputTest[i][j] + " ");
            }
            System.out.println();
        }

        StringBuilder achievementBitString = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            achievementBitString.append((int) Math.floor(Math.random()*2));
        }
        saveAchievements(achievementBitString);
        String achievementOutputTest;
        achievementOutputTest = loadAchievements();
        System.out.println(achievementOutputTest);

    }



    public static void writeToFile(StringBuilder dataString, String filePath) throws FileNotFoundException {
        PrintWriter sandBoxSaver = new PrintWriter(filePath);
        sandBoxSaver.write(dataString.toString());
        sandBoxSaver.close();
    }

    public static String readFromFile(String filePath) throws FileNotFoundException {
        File file = new File(filePath);
        sandBoxLoader = new Scanner(file);
        String dataString = sandBoxLoader.nextLine();
        sandBoxLoader.close();
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


    public static void saveSandbox(int[][] test) {
        StringBuilder sandboxBitString = new StringBuilder(400);
        String filePath = "ElectricCircuit/src/main/resources/SaveFiles/sandboxSaveFile.txt";
        for (int i = 0; i < test.length; i++) {
            for (int j = 0; j < test[i].length; j++) {
                sandboxBitString.append(test[i][j]);
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