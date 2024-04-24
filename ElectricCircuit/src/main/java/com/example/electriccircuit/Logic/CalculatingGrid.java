package com.example.electriccircuit.Logic;

import com.example.electriccircuit.Components.Component;
import com.example.electriccircuit.Components.PowerSupply;
import com.example.electriccircuit.Components.Resistors;
import com.example.electriccircuit.DataTypes.*;

import com.example.electriccircuit.HelloApplication;
import com.example.electriccircuit.HelloController;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;

public class CalculatingGrid {
    private Ohm resistance = new Ohm(0);
    private Volt potential = new Volt(0);
    private Amp current = new Amp(0);

    GridPane dataGrid;

    public CalculatingGrid(int[][] grid){
        Debug.Log("Calculating Grid called");
        BuilderMatrix sandboxMatrix = new BuilderMatrix(grid);
        if(sandboxMatrix.closedCircuit()) {
            String circuitPath = sandboxMatrix.getCircuitPath();
            ArrayList<Component> objectPath = sandboxMatrix.getObjectPath();
            Debug.Log(objectPath.get(0) + " is object " + 0 + " and voltage is " + objectPath.get(0).getPassingVoltage());
            potential.setVolt(objectPath.get(0).getPassingVoltage());
            for(int i = 1; i < objectPath.size(); i++){
                System.out.println(objectPath.get(i) + " is component " + i);
                objectPath.get(i).setPassingVoltage(objectPath.get(i - 1).getPassingVoltage());
                Debug.Log(objectPath.get(i).getPassingVoltage() + " is passing component voltage of " + i);
                resistance.setOhm(resistance.getOhm() + objectPath.get(i).getResistance());
            }
            current.setAmp(current.ohmsLaw(potential, resistance));
            HelloController.returnCalButton().setId("calculatetrue");
            HelloController.returnCalButton().setMouseTransparent(false);
            HelloController.returnCalButton().setOpacity(1);
            HelloController.returnTotalVoltLabel().setText(String.valueOf(potential.getVolt()));
            HelloController.returnTotalAmpLabel().setText(String.valueOf(current.getAmp()));
            HelloController.returnTotalOhmLabel().setText(String.valueOf(resistance.getOhm()));
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
