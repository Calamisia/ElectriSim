package com.example.electriccircuit.Logic;

import com.example.electriccircuit.DataTypes.*;

import com.example.electriccircuit.HelloApplication;
import com.example.electriccircuit.HelloController;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class CalculatingGrid {
    private Ohm resistance = new Ohm(0);
    private Volt potential = new Volt(0);
    private Amp current = new Amp(0);

    GridPane dataGrid;

    public CalculatingGrid(int[][] grid){
        BuilderMatrix sandboxMatrix = new BuilderMatrix(grid);
        Debug.printGrid(grid);
        if(sandboxMatrix.closedCircuit()) {
            String circuitPath = sandboxMatrix.getCircuitPath();
            for(int i = 0; i < circuitPath.length(); i++){
                if(circuitPath.charAt(i) == '2' || circuitPath.charAt(i) == '9'){
                    potential.setVolt(10);
                } else if (circuitPath.charAt(i) == '3'){
                    resistance.setOhm(resistance.getOhm() + 50);
                }
            }
            current.setAmp(current.ohmsLaw(potential, resistance));
            HelloController.returnCalButton().setId("calculatetrue");
            HelloController.returnCalButton().setMouseTransparent(false);
            HelloController.returnCalButton().setOpacity(1);
            addLabel(1);
        }
        else{
            HelloController.returnCalButton().setId("calculatefalse");
            HelloController.returnCalButton().setMouseTransparent(true);
        }
    }

    public void addLabel(int row){
        HelloController.returnDataGrid().getChildren().remove(row, 1);
        HelloController.returnDataGrid().getChildren().remove(row, 2);
        HelloController.returnDataGrid().getChildren().remove(row, 3);
        HelloController.returnDataGrid().addRow(row,new Label("R" + row), new Label(Double.toString(resistance.getOhm())), new Label(Double.toString(potential.getVolt())),new Label(Double.toString(potential.getVolt())), new Label(Double.toString(current.getAmp())));
    }

}
