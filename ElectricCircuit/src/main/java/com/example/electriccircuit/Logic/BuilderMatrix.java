package com.example.electriccircuit.Logic;

import com.example.electriccircuit.Components.Component;

import java.util.ArrayList;
import java.util.Arrays;

import static com.example.electriccircuit.Components.Component.componentArray;
public class BuilderMatrix {

    public static int[][] grid = new int[35][20];
    private StringBuilder circuitPath = new StringBuilder(); // used for calculation grid
    private ArrayList<Component> objectPath = new ArrayList<Component>();

    public BuilderMatrix(){
        // initialize every value to zero
        for (int[] ints : grid) {
            Arrays.fill(ints, 0);
        }
    }
    public BuilderMatrix(int[][] matrix){
        // initialize every value to given value
        for (int i = 0; i < grid.length; i++) {
            System.arraycopy(matrix[i], 0, grid[i], 0, grid[i].length);
        }
    }

    // Used to add a component Id to a box
    public static void setBoxID(int row, int column, int iD) {
        if(grid[row][column] != 0){
            Debug.Log("Set box ID thinks row is " + row + " column is " + column);
            componentArray[row][column].removeComponentNode();
        }
        grid[row][column] = iD;
        new CalculatingGrid(getGrid());
    }
    // Used to clear a grid space
    public static void removeBoxID(int row, int column) {
        grid[row][column] = 0;
    }
    // Used to get the component at a certain box in the matrix
    public int getBoxID(int row, int column) {
        return grid[row][column];
    }
    // used to get the matrix (Used to save the sandbox)
    public static int[][] getGrid(){
        return grid;
    }
    // used to set the matrix (used to load the sandbox)
    public static void setGrid(int[][] grid) {
        BuilderMatrix.grid = grid;
        new CalculatingGrid(getGrid());
    }
    // get the circuit path, used for calculating grid
    public String getCircuitPath() {
        return circuitPath.toString();
    }

    public ArrayList<Component> getObjectPath() {
        return objectPath;
    }

    // set the circuit path
    public void setCircuitPath(StringBuilder circuitPath) {
        this.circuitPath = circuitPath;
    }
    // this method determines the index of the first power supply
    public ArrayList foundPower(){
        boolean foundPower = false;
        ArrayList powerSupplyLocation = new ArrayList(3);
        outerloop:
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 2){
                    powerSupplyLocation.add(true);
                    powerSupplyLocation.add(i);
                    powerSupplyLocation.add(j);
                    grid[i][j] = 9;
                    circuitPath.append(9);
                    foundPower = true;
                    System.out.println("Found index of powersupply " + i + " " + j);
                    break outerloop;
                }
            }
        }
        if (!foundPower) {
            // if no powerSupply was found, the circuit is, by deduction, not closed.
            powerSupplyLocation.add(false);
        }
        // it returns [boolean (if it found one), row, grid] of the power supply)
        return powerSupplyLocation;
    }
    // this method is used to check if the surrounding component fits the criteria
    public boolean isValid(int row, int column, int prevRow, int prevCol){
        if (row < 0 || row >= grid.length || column < 0 || column >= grid[0].length)
            return false; //outofbounds searching excluded
        else if(column == prevCol && row == prevRow || grid[row][column] == 0)
            return false; //repeat component excluded and empty boxes
        else
            return true;
    }
    // this method checks surrounding cases around a given case, to see if there is a component in one of them.
    public ArrayList surrounding(int row, int column, int prevRow, int prevCol) {
        ArrayList arraylist = new ArrayList(5);

        //check below component
        if (isValid(row-1, column, prevRow, prevCol)){
            arraylist.add(true);
            arraylist.add(row - 1);
            arraylist.add(column);
            arraylist.add(row);
            arraylist.add(column);
            return arraylist;
        }
        //check to the left of component
        else if (isValid(row, column-1, prevRow, prevCol)){
            arraylist.add(true);
            arraylist.add(row);
            arraylist.add(column-1);
            arraylist.add(row);
            arraylist.add(column);
            return arraylist;
        }
        //check to the right of the component
        else if (isValid(row, column+1, prevRow, prevCol)){
            arraylist.add(true);
            arraylist.add(row);
            arraylist.add(column+1);
            arraylist.add(row);
            arraylist.add(column);
            return arraylist;
        }
        //check above component
        else if(isValid(row+1, column, prevRow, prevCol)){
            arraylist.add(true);
            arraylist.add(row+1);
            arraylist.add(column);
            arraylist.add(row);
            arraylist.add(column);
            return arraylist;
        }
        //no valid surrounding component
        else {
            arraylist.add(false);
            arraylist.add(999);
            arraylist.add(999);
            arraylist.add(999);
            arraylist.add(999);
        }
        return arraylist;
        // it returns an ArrayList with the first index being a boolean, representing whether there is a surrounding ID.
        // If there is a surroudning ID, the second and third entries in the ArrayList are its row and column coordinates.
        // the third and fourth entries serve to mark the previous component, so it can't be found again.
    }

    public static int[] surroundingCheck(int row, int column) {
        int[] surrounding = new int[4];

        if (grid[row - 1][column] != 0){
            surrounding[0] = 1;
        } if (grid[row][column - 1] != 0){
            surrounding[1] = 1;
        } if (grid[row + 1][column] != 0){
            surrounding[2] = 1;
        } if (grid[row][column + 1] != 0){
            surrounding[3] = 1;
        }
        return surrounding;
    }


    // this method is used to check if the circuit is closed, before doing any calculations.
    public boolean closedCircuit() {
        // attempt to find power supply
        ArrayList powerSupplyLocation = foundPower();
        if (!(boolean) powerSupplyLocation.get(0))
            return false;

        // assign starting place
        int[] componentIndex = new int[4]; // indexes for a certain component
        componentIndex[0] = (int) powerSupplyLocation.get(1);
        componentIndex[1] = (int) powerSupplyLocation.get(2);
        componentIndex[2] = 999;
        componentIndex[3] = 999;

        int desiredEndID = 9; // our original powerSupply
        return closedLoop(componentIndex, desiredEndID);
    }
    // checks if a given part is closed. Appends to circuitPath
    public boolean closedLoop(int[] componentIndex, int desiredEndID){
        ArrayList surroundingInfo = new ArrayList(5);
        while (true) {
            surroundingInfo = surrounding(componentIndex[0], componentIndex[1], componentIndex[2], componentIndex[3]);
            if ((boolean) (surroundingInfo.get(0))) {
                if (grid[(int) surroundingInfo.get(1)][(int) surroundingInfo.get(2)] == desiredEndID) {
                    circuitPath.append(desiredEndID); // if it found the original battery, return true
                    System.out.println(circuitPath.toString());
                    return true;
                }
                // found a splitter
                else if (grid[(int) surroundingInfo.get(1)][(int) surroundingInfo.get(2)] == 6) {
                    circuitPath.append(6);
                    // call the method for the path branching to the right
                    if (isValid(componentIndex[0], componentIndex[1]+1, componentIndex[2], componentIndex[3])){
                        componentIndex[3] = componentIndex[1];
                        componentIndex[1] = componentIndex[1] + 1;
                        circuitPath.append(grid[(int) componentIndex[0]][(int) componentIndex[1]]);
                        closedLoop(componentIndex, 5);
                    }
                    // call the method for the path branching to the lft
                    if (isValid(componentIndex[0], componentIndex[1]-1, componentIndex[2], componentIndex[3])){
                        componentIndex[3] = componentIndex[1];
                        componentIndex[1] = componentIndex[1] - 1;
                        circuitPath.append(grid[(int) componentIndex[0]][(int) componentIndex[1]]);
                        closedLoop(componentIndex, 5);
                    }
                    // call the method for the path branching above
                    if (isValid(componentIndex[0]+1, componentIndex[1], componentIndex[2], componentIndex[3])){
                        componentIndex[2] = componentIndex[0];
                        componentIndex[0] = componentIndex[0] + 1;
                        circuitPath.append(grid[(int) componentIndex[0]][(int) componentIndex[1]]);
                        closedLoop(componentIndex, 5);
                    }
                    // call the method for the path branching below
                    if (isValid(componentIndex[0]-1, componentIndex[1], componentIndex[2], componentIndex[3])){
                        componentIndex[2] = componentIndex[0];
                        componentIndex[0] = componentIndex[0] - 1;
                        circuitPath.append(grid[(int) componentIndex[0]][(int) componentIndex[1]]);
                        closedLoop(componentIndex, 5);
                    }
                }
                else {
                        // if the surrounding component isn't the battery, make that component the new center component.
                        componentIndex[0] = (int) surroundingInfo.get(1);
                        componentIndex[1] = (int) surroundingInfo.get(2);
                        componentIndex[2] = (int) surroundingInfo.get(3);
                        componentIndex[3] = (int) surroundingInfo.get(4);
                        circuitPath.append(grid[(int) surroundingInfo.get(1)][(int) surroundingInfo.get(2)]);
                    }
            }
            else // no component was found, and we aren't back at powerSupply
                return false;
        }
    }

}