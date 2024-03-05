package com.example.electriccircuit.Logic;

import java.util.ArrayList;

public class BuilderMatrix {

    private static int[][] grid = new int[20][20];
    private final int WIREID = 1;
    private final int POWERSUPPLYID = 2;
    private final int RESISTORID = 3;
    private final int CAPACITORID = 4;
    private final int SPLITTERID = 5;
    private final int MERGERID = 6;
    private boolean isClosedCircuit;
    private StringBuilder circuitPath = new StringBuilder();

    public BuilderMatrix(){
        // initialize every value to zero
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                grid[i][j] = 0;
            }
        }

    }

    public void setBoxID(int row, int column, int index) {
        grid[row][column] = index;
    }

    public int getBoxID(int row, int column) {
        return grid[row][column];
    }

    public static int[][] getGrid(){
        return grid;
    }

    public void setGrid(int[][] grid) {
        this.grid = grid;
    }

    public ArrayList surrounding(int row, int column) {
        ArrayList arraylist = new ArrayList(3);
        if (grid[row - 1][column - 1] != 0){
            arraylist.add(true);
            arraylist.add(row-1);
            arraylist.add(column-1);
        }
        else if (grid[row - 1][column] != 0){
            arraylist.add(true);
            arraylist.add(row-1);
            arraylist.add(column);
        }
        else if (grid[row - 1][column + 1] != 0){
            arraylist.add(true);
            arraylist.add(row-1);
            arraylist.add(column+1);
        }
        else if (grid[row][column - 1] != 0){
            arraylist.add(true);
            arraylist.add(row);
            arraylist.add(column-1);
        }
        else if (grid[row][column] != 0){
            arraylist.add(true);
            arraylist.add(row);
            arraylist.add(column);
        }
        else if (grid[row][column + 1] != 0){
            arraylist.add(true);
            arraylist.add(row);
            arraylist.add(column+1);
        }
        else if (grid[row + 1][column - 1] != 0){
            arraylist.add(true);
            arraylist.add(row+1);
            arraylist.add(column-1);
        }
        else if (grid[row + 1][column] != 0){
            arraylist.add(true);
            arraylist.add(row+1);
            arraylist.add(column);
        }
        else if (grid[row + 1][column + 1] != 0){
            arraylist.add(true);
            arraylist.add(row+1);
            arraylist.add(column+1);
        }
        else arraylist.add(false);

       return arraylist;
    }

    public boolean closedCircuit(){
        int[] componentIndex = new int[2];


        ArrayList surroundingInfo;
        outerloop:
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                surroundingInfo = surrounding(i, j);
                if ((boolean) (surroundingInfo.get(0))) {
                    // this means that there is surrounding objects
                    if (grid[(int) surroundingInfo.get(1)][(int) surroundingInfo.get(2)] == 2) {
                        componentIndex[0] = i;
                        componentIndex[1] = j;
                        circuitPath.append(2);
                        break outerloop;
                    }
                }
                return false;
            }
        }
        while (true) {
            surroundingInfo = surrounding(componentIndex[0], componentIndex[1]);
            if ((boolean) (surroundingInfo.get(0))) {
                if (grid[(int) surroundingInfo.get(1)][(int) surroundingInfo.get(2)] == 2) {
                    circuitPath.append(2);
                    return true;
                }
                else {
                    componentIndex[0] = (int) surroundingInfo.get(1);
                    componentIndex[1] = (int) surroundingInfo.get(2);
                    circuitPath.append(grid[(int) surroundingInfo.get(1)][(int) surroundingInfo.get(2)]);
                }
            } else break;
        }
        return false;
    }

    public void refresh() {
        boolean doesItWork = closedCircuit();
        // make a copy of circuit path string
        // modify the grid values using anchor panes
        // check if circuitPath = old circuit path. If true, do nothing (since the circuit didn't change)
        // if false, the circuit changed. Therefore, run closedCircuit to see if it works well.
    }

}

// SURROUNDING RETURNS TRUE FROM THE PREVIOUS COMPONENT SO IT DOESN'T WORK
// HAVE TO RECURSIVELY CALL THE METHOD ON SPLITTERS AND MERGERS
// ALSO HAVE TO SPLIT THE CIRCUIT PATH STRING WHEN FINDING SPLITTERS AND MERGERS
// MAKE SURE NO NEGATIVE INDEXES WHEN SUBTRACTING INDEXES