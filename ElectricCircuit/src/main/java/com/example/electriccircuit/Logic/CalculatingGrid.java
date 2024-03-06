package com.example.electriccircuit.Logic;

public class CalculatingGrid {
    private double[][] calculatingGrid;
    public CalculatingGrid(){
        BuilderMatrix sandboxMatrix = new BuilderMatrix(BuilderMatrix.getGrid());
        if(sandboxMatrix.closedCircuit()) {
            String circuitPath = sandboxMatrix.getCircuitPath();
            System.out.println(circuitPath);
        }

    }

}
