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
    Ohm resistance = new Ohm(0);
    Volt potential = new Volt(0);
    Amp current = new Amp(0);

    GridPane dataGrid;

    public CalculatingGrid(int[][] grid){
        BuilderMatrix sandboxMatrix = new BuilderMatrix(grid);
        if(sandboxMatrix.closedCircuit()) {
            String circuitPath = sandboxMatrix.getCircuitPath();
            ArrayList<Component> objectPath = sandboxMatrix.getObjectPath();
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
            HelloController.returnDataGrid().add(new Label(Double.toString(resistance.getOhm())), 0, 1);
            HelloController.returnDataGrid().add(new Label(Double.toString(potential.getVolt())), 1, 1);
            HelloController.returnDataGrid().add(new Label(Double.toString(current.getAmp())), 2, 1);
        }
        else{
            HelloController.returnCalButton().setId("calculatefalse");
            HelloController.returnCalButton().setMouseTransparent(true);
        }
    }
    /*
    public static void addLabel(int row){
        HelloController.returnDataGrid().getChildren().remove(row, 1);
        HelloController.returnDataGrid().getChildren().remove(row, 2);
        HelloController.returnDataGrid().getChildren().remove(row, 3);
        HelloController.returnDataGrid().addRow(row, new Label(Double.toString(resistance.getOhm())), new Label(Double.toString(potential.getVolt())),new Label(Double.toString(potential.getVolt())), new Label(Double.toString(current.getAmp())));
    }
    */
}
