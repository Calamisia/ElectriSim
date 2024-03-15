package com.example.electriccircuit.Logic;

import com.example.electriccircuit.DataTypes.*;

public class CalculatingGrid {
    private double[][] calculatingGrid;
    Ohm resistance = new Ohm(0);
    Volt potential = new Volt(0);
    Amp current = new Amp(0);
    public CalculatingGrid(){
        BuilderMatrix sandboxMatrix = new BuilderMatrix(BuilderMatrix.getGrid());
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

            System.out.println("circuit resistance: " + resistance.getOhm());
            System.out.println("circuit voltage: " + potential.getVolt());
            System.out.println("circuit current: " + current.getAmp());
        }


    }

}
