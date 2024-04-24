package com.example.electriccircuit.Logic;

import com.example.electriccircuit.Components.*;
import com.example.electriccircuit.Components.subcomponent.Switch;
import com.example.electriccircuit.HelloController;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.io.*;
import java.util.*;

import static com.example.electriccircuit.Components.Component.componentArray;

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
        File file = new File(filePath);
        try (Scanner loader = new Scanner(file)) {
            String dataString = loader.next();
            Debug.Log(dataString);
            int[][] grid = new int[35][20];
            int counter = 0;
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[i].length; j++) {
                    grid[i][j] = (dataString.charAt(counter) - 48);
                    if(grid[i][j] != 0) {
                        //handle visuals
                    }
                    counter++;
                }
            }
            Debug.printGrid(grid);
            handleUI(grid);
            BuilderMatrix.setGrid(grid);
        } catch (FileNotFoundException e) {
            Debug.handleException(e);
        }
    }

    public static void handleUI(int[][] grid){
        for(int i = 0; i < grid[i].length; i++){
            for(int j = 0; j < grid.length; j++){
                if(grid[j][i] > 0){
                    Component component;
                    if(grid[j][i] == 1){
                        component = new Wire();
                    } else if(grid[j][i] == 2 || grid[j][i] == 9){
                        component = new PowerSupply();
                        ((PowerSupply) component).setVoltage(20);
                    } else if(grid[j][i] == 3){
                        component = new Resistors();
                        component.setResistance(50);
                    } else if(grid[j][i] == 4){
                        component = new Capacitors();
                    } else if(grid[j][i] == 5){
                        component = new Merger();
                    } else if(grid[j][i] == 6){
                        component = new Splitter();
                    } else if(grid[j][i] == 10){
                        component = new Switch();
                        Debug.Info("wireSwitch found");
                    } else {
                        component = null;
                        Debug.Error("Invalid spawn component" + grid[j][i]);
                    }
                    Rectangle solidSprite = new Rectangle(HelloController.getAncwidth()/35,HelloController.getAncheight()/20);
                    component.setComponentNode(solidSprite);
                    solidSprite.setFill(new ImagePattern(component.getImageTexture()));

                    //draggableMaker.dragging(solidcircle, iD, smallanchorpane, dataGrid);
                    double Hspacing = (HelloController.getAncheight()/ 20);
                    double Wspacing = (HelloController.getAncwidth()/ 35);

                    int Hindex = i;
                    int Windex = j;

                    if(Hindex < 20 && Hindex >= 0) { //if within bound of small anchor
                        if (Windex < 35 && Windex >= 0) {
                            component.setLocation(Windex, Hindex);
                            //snaps to grid
                            solidSprite.setY(Hindex * (Hspacing));
                            solidSprite.setX(Windex * (Wspacing));
                            HelloController.returnSmallAnchorPane().getChildren().add(solidSprite);
                            solidSprite.toFront();

                            //Sandbox Matrix creation
                            component.interact();
                            componentArray[Windex][Hindex] = component;
                        }
                    }
                }
            }
            System.out.println();
        }
    }

}