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

        // gets the matrix, and creates a stringBuilder
        StringBuilder rotationBitString = new StringBuilder(700);

        // writes the matrix indices to the string
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if(grid[i][j] != 0 && grid[i][j] != 1){
                    if(componentArray[i][j].getConnections()[0] == 1 && componentArray[i][j].getConnections()[2] == 1){
                        rotationBitString.append(1);
                    } else if(componentArray[i][j].getConnections()[1] == 1 && componentArray[i][j].getConnections()[3] == 1){
                        rotationBitString.append(0);
                    } else if(componentArray[i][j].getConnections()[0] == 1){
                        rotationBitString.append(2);
                    } else if(componentArray[i][j].getConnections()[1] == 1){
                        rotationBitString.append(3);
                    } else if(componentArray[i][j].getConnections()[2] == 1){
                        rotationBitString.append(4);
                    } else if(componentArray[i][j].getConnections()[3] == 1){
                        rotationBitString.append(5);
                    } else{
                        rotationBitString.append(0);
                    }
                }
            }
        }

        //saves the matrix
        Saver.write(sandboxBitString.toString() + "\n");

        Saver.write(rotationBitString.toString() + "\n");

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
            String rotationString = loader.next();
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
            BuilderMatrix builderMatrix = new BuilderMatrix();
            Debug.printGrid(grid);
            handleUI(grid, rotationString);
        } catch (FileNotFoundException e) {
            Debug.handleException(e);
        }
    }

    public static void handleUI(int[][] grid, String rotationString){
        Debug.Log("rotation string is " + rotationString);
        int rotationCounter = 0;
        BuilderMatrix builderMatrix = new BuilderMatrix();
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[i].length; j++){
                if(grid[i][j] > 0){
                    Component component;
                    if(grid[i][j] == 1){
                        component = new Wire();
                    } else if(grid[i][j] == 2 || grid[i][j] == 9){
                        component = new PowerSupply();
                        ((PowerSupply) component).setVoltage(20);
                    } else if(grid[i][j] == 3){
                        component = new Resistors();
                        component.setResistance(50);
                    } else if(grid[i][j] == 4){
                        component = new Capacitors();
                        component.setCapacitance(1E-6);
                    } else if(grid[i][j] == 5){
                        component = new Merger();
                    } else if(grid[i][j] == 6){
                        component = new Splitter();
                    } else if(grid[i][j] == 7 ||grid[i][j] == 8){
                        component = new Switch();
                    } else {
                        component = null;
                        Debug.Error("Invalid spawn component" + grid[i][j]);
                    }
                    builderMatrix.setBoxID(i,j,component.getId(),component);
                    Rectangle solidSprite = new Rectangle(HelloController.getAncwidth()/35,HelloController.getAncheight()/20);
                    component.setComponentNode(solidSprite);
                    solidSprite.setFill(new ImagePattern(component.getImageTexture()));


                    //draggableMaker.dragging(solidcircle, iD, smallanchorpane, dataGrid);
                    double Hspacing = (HelloController.getAncheight()/ 20);
                    double Wspacing = (HelloController.getAncwidth()/ 35);

                    int Hindex = j;
                    int Windex = i;

                    if(Hindex < 20 && Hindex >= 0) { //if within bound of small anchor
                        if (Windex < 35 && Windex >= 0) {
                            component.setLocation(Windex, Hindex);
                            //snaps to grid
                            solidSprite.setY(Hindex * (Hspacing));
                            solidSprite.setX(Windex * (Wspacing));
                            HelloController.returnSmallAnchorPane().getChildren().add(solidSprite);
                            solidSprite.toFront();
                            if(component.getId() != 0 && component.getId() != 1){
                                if(rotationString.charAt(rotationCounter) == '1'){
                                    component.getComponentNode().setRotate(90);
                                    component.setConnections(1, 0, 1, 0);
                                } else if(rotationString.charAt(rotationCounter) == '0'){
                                    component.setConnections(0,1,0,1);
                                }else if(rotationString.charAt(rotationCounter) == '2'){
                                    component.getComponentNode().setRotate(-90);
                                    component.setConnections(1, 0, 0, 0);
                                } else if(rotationString.charAt(rotationCounter) == '3'){
                                    component.setConnections(0, 1, 0, 0);
                                } else if(rotationString.charAt(rotationCounter) == '4'){
                                    component.getComponentNode().setRotate(90);
                                    component.setConnections(0, 0, 1, 0);
                                } else if(rotationString.charAt(rotationCounter) == '5'){
                                    component.getComponentNode().setRotate(180);
                                    component.setConnections(0, 0, 0, 1);
                                }else{
                                    component.setConnections(0,1,0,1);
                                }
                                rotationCounter++;
                            }

                            //Sandbox Matrix creation
                            component.interact();
                            componentArray[Windex][Hindex] = component;

                            component.mainRefreshComponent();
                            new CalculatingGrid(BuilderMatrix.getGrid());
                            Debug.Log("orientation is " + component.getConnections()[0] + component.getConnections()[1] + component.getConnections()[2] + component.getConnections()[3]);
                        }
                    }
                }
            }
        }
    }

}