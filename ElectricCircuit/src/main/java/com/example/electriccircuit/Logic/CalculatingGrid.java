package com.example.electriccircuit.Logic;

import com.example.electriccircuit.DataTypes.*;

import com.example.electriccircuit.HelloController;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class CalculatingGrid {
    private double[][] calculatingGrid;
    Ohm resistance = new Ohm(0);
    Volt potential = new Volt(0);
    Amp current = new Amp(0);
    public CalculatingGrid(int[][] grid, GridPane dataGrid){
        for(int i = 0; i < grid[i].length; i++){
            for(int j = 0; j < grid.length; j++){
                System.out.print(grid[j][i] + " ");
            }
            System.out.println();
        }
        BuilderMatrix sandboxMatrix = new BuilderMatrix(grid);
        if(sandboxMatrix.closedCircuit()) {
            String circuitPath = sandboxMatrix.getCircuitPath();
            System.out.println(circuitPath);
            for(int i = 0; i < circuitPath.length(); i++){
                System.out.println(circuitPath.charAt(i));
                if(circuitPath.charAt(i) == '2'){
                    potential.setVolt(10);
                    System.out.println("volt found");
                } else if (circuitPath.charAt(i) == '3'){
                    resistance.setOhm(resistance.getOhm() + 50);
                    System.out.println("resistance found");
                }
            }
            current.setAmp(current.ohmsLaw(potential, resistance));

            dataGrid.add(new Label(Double.toString(resistance.getOhm())), 0, 1);
            dataGrid.add(new Label(Double.toString(potential.getVolt())), 1, 1);
            dataGrid.add(new Label(Double.toString(current.getAmp())), 2, 1);
            System.out.println("circuit resistance: " + resistance.getOhm());
            System.out.println("circuit voltage: " + potential.getVolt());
            System.out.println("circuit current: " + current.getAmp());
        }
        else{
            System.out.println("not closed");
        }


    }

}
