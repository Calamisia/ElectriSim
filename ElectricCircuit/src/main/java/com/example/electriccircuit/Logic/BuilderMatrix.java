package com.example.electriccircuit.Logic;

import com.example.electriccircuit.HelloController;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;

import static com.example.electriccircuit.Logic.SaveFiles.saveGame;

public class BuilderMatrix {

    private static int[][] grid = new int[35][20];
    private final int WIREID = 1;
    private final int POWERSUPPLYID = 2;
    private final int RESISTORID = 3;
    private final int CAPACITORID = 4;
    private final int MERGERID = 5;
    private final int SPLITTERID = 6;

    private final int SWITCH = 10;
    private boolean isClosedCircuit;
    private StringBuilder circuitPath = new StringBuilder(); // used for calculation grid

    public BuilderMatrix(){
        // initialize every value to zero
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                grid[i][j] = 0;
            }
        }
    }
    public BuilderMatrix(int[][] matrix){
        // initialize every value to given value
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                grid[i][j] = matrix[i][j];
            }
        }
    }

    // Used to add a component Id to a box
    public static void setBoxID(int row, int column, int iD) {
        grid[row][column] = iD;
        new CalculatingGrid(getGrid());
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
    }

    // get the circuit path, used for calculating grid
    public String getCircuitPath() {
        return circuitPath.toString();
    }
    // set the circuit path
    public void setCircuitPath(StringBuilder circuitPath) {
        this.circuitPath = circuitPath;
    }

    // this method checks all 8 surrounding cases around a given case, to see if there is an ID in one of them.
    // it returns an ArrayList with the first index being a boolean, representing whether or not there is a surrounding ID.
    // If there is a surroudning ID, the second and third entries in the ArrayList are its row and column coordinates.
    public ArrayList surrounding(int row, int column, int prevRow, int prevCol) {
        ArrayList arraylist = new ArrayList(5);
        int rownegbuffer = 0;
        int columnnegbuffer = 0; // these buffers prevent indexing negative values
        if (row - 1 < 0)
            rownegbuffer = 1;
        if (column - 1 < 0)
            columnnegbuffer = 1;
        int rowtopbuffer = 0;
        int columntopbuffer = 0; // these buffers prevent indexing out of bounds
        if (row + 1 == 35)
            rowtopbuffer = 1;
        if (column + 1 == 20)
            columntopbuffer = 1;

        if (grid[row - 1 + rownegbuffer][column] != 0 && ((row - 1 + rownegbuffer)!= prevRow || (column)!= prevCol)){
            arraylist.add(true);
            arraylist.add(row - 1 + rownegbuffer);
            arraylist.add(column);
            arraylist.add(row);
            arraylist.add(column);
            return arraylist;
        }
        else if (grid[row][column - 1 + columnnegbuffer] != 0 && ((row)!= prevRow || (column - 1 + columnnegbuffer)!= prevCol)){
            arraylist.add(true);
            arraylist.add(row);
            arraylist.add(column-1 + columnnegbuffer);
            arraylist.add(row);
            arraylist.add(column);
            return arraylist;
        }
        else if (grid[row][column + 1 - columntopbuffer] != 0 && ((row)!= prevRow || (column + 1 - columntopbuffer)!= prevCol)){
            arraylist.add(true);
            arraylist.add(row);
            arraylist.add(column+1 - columntopbuffer);
            arraylist.add(row);
            arraylist.add(column);
            return arraylist;
        }
        else if (grid[row + 1 - rowtopbuffer][column] != 0 && ((row + 1 - rowtopbuffer)!= prevRow || (column)!= prevCol)){
            arraylist.add(true);
            arraylist.add(row+1 - rowtopbuffer);
            arraylist.add(column);
            arraylist.add(row);
            arraylist.add(column);
            return arraylist;
        }
        else {
            arraylist.add(false);
            arraylist.add(999);
            arraylist.add(999);
            arraylist.add(999);
            arraylist.add(999);
        }

        return arraylist;
    }


    // this method is used to check if the circuit is closed, before doing any calculations.
    public boolean closedCircuit() {
        int[] componentIndex = new int[4]; // indexes for a certain component
        boolean foundPower = false;
        ArrayList surroundingInfo = new ArrayList();

        outerloop:
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                // this checks every single box on the grid if for surrounding components
                surroundingInfo = surrounding(i, j, 999, 999);
                if ((boolean) (surroundingInfo.get(0))) {
                    // if there is a surrounding component, check if it's a powerSupply
                    if (grid[(int) surroundingInfo.get(1)][(int) surroundingInfo.get(2)] == 2 || grid[(int) surroundingInfo.get(1)][(int) surroundingInfo.get(2)] == 9) {
                        //if it is a power supply, make that the componentIndex, and start the circuitPath from there
                        componentIndex[0] = (int) surroundingInfo.get(1);
                        componentIndex[1] = (int) surroundingInfo.get(2);
                        componentIndex[2] = (int) surroundingInfo.get(3);
                        componentIndex[3] = (int) surroundingInfo.get(4);
                        grid[(int) surroundingInfo.get(1)][(int) surroundingInfo.get(2)] = 9;
                        circuitPath.append(2);
                        foundPower = true;
                        System.out.println("Found index of powersupply " + i + " " + j);
                        break outerloop;
                        // we can break this loop, since we found the start of the circuit
                    }
                }
            }
        }
        if (foundPower == false) {
            // if no powerSupply was found, the circuit is, by deduction, not closed.
            return false;
        }

        // now that we have identified where the power supply is, we run surrounding and create
        // a circuitpath. Every time a new component is found, surrounding is called on that component
        // until we get back to the power supply.

        while (true) {
            surroundingInfo = surrounding(componentIndex[0], componentIndex[1], componentIndex[2], componentIndex[3]);
            if ((boolean) (surroundingInfo.get(0))) {
                if (grid[(int) surroundingInfo.get(1)][(int) surroundingInfo.get(2)] == 9) {
                    circuitPath.append(2); // if it found the battery, return true
                    return true;
                } else {
                    // if the surrounding component isn't the battery, make that component the new center component.
                    componentIndex[0] = (int) surroundingInfo.get(1);
                    componentIndex[1] = (int) surroundingInfo.get(2);
                    componentIndex[2] = (int) surroundingInfo.get(3);
                    componentIndex[3] = (int) surroundingInfo.get(4);
                    circuitPath.append(grid[(int) surroundingInfo.get(1)][(int) surroundingInfo.get(2)]);
                }
            } else
                return false;
        }
    }
}

// HAVE TO RECURSIVELY CALL THE METHOD ON SPLITTERS AND MERGERS
// ALSO HAVE TO SPLIT THE CIRCUIT PATH STRING WHEN FINDING SPLITTERS AND MERGERS