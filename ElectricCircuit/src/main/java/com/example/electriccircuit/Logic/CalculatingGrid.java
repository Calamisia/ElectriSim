package com.example.electriccircuit.Logic;

import com.example.electriccircuit.Components.Component;
import com.example.electriccircuit.Components.PowerSupply;
import com.example.electriccircuit.Components.Resistors;
import com.example.electriccircuit.DataTypes.*;

import com.example.electriccircuit.HelloApplication;
import com.example.electriccircuit.HelloController;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;

public class CalculatingGrid {
    private Ohm resistance = new Ohm(0);
    private Volt potential = new Volt(0);
    private Amp current = new Amp(0);
    private Capacitance farad = new Capacitance(0);
    private Charge coulomb = new Charge(0);
    private int capacitorLocation;

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
                Debug.Log("The possible connections of " + i + " is " + objectPath.get(i).getConnections()[0] + objectPath.get(i).getConnections()[1] + objectPath.get(i).getConnections()[2] + objectPath.get(i).getConnections()[3] + " and is at location " + objectPath.get(i).getLocationRow() + " " + objectPath.get(i).getLocationColumn());
                System.out.println(objectPath.get(i) + " is component " + i);
                objectPath.get(i).setPassingVoltage(objectPath.get(i - 1).getPassingVoltage());
                Debug.Log(objectPath.get(i).getPassingVoltage() + " is passing component voltage of " + i);
                resistance.setOhm(resistance.getOhm() + objectPath.get(i).getResistance());

            }
            for(int i = 1; i < objectPath.size(); i++){
                Debug.Log("The possible connections of " + i + " is " + objectPath.get(i).getConnections()[0] + objectPath.get(i).getConnections()[1] + objectPath.get(i).getConnections()[2] + objectPath.get(i).getConnections()[3] + " and is at location " + objectPath.get(i).getLocationRow() + " " + objectPath.get(i).getLocationColumn());
                System.out.println(objectPath.get(i) + " is component " + i);
                objectPath.get(i).setPassingVoltage(objectPath.get(i - 1).getPassingVoltage());
                Debug.Log(objectPath.get(i).getPassingVoltage() + " is passing component voltage of " + i);
                resistance.setOhm(resistance.getOhm() + objectPath.get(i).getResistance());
                if(objectPath.get(i).getCapacitance() != 0){
                    capacitorLocation = i;
                    farad.setCapacitance(farad.getCapacitance() + ( 1 / objectPath.get(i).getCapacitance()));
                    Debug.Log(objectPath.get(i).getCapacitance() + " is passing component capacitance of " + i + " and farad is " + farad.getCapacitance());
                }
            }
            Debug.Log(potential.getVolt() + " ");
            current.setAmp(current.ohmsLaw(potential, resistance));
            if(farad.getCapacitance() != 0){
                farad.setCapacitance(1 / farad.getCapacitance());
            }
            Debug.Log(farad.getCapacitance() + " is capapdnaskld");
            coulomb.setCharge(farad.getCapacitance() * potential.getVolt());

            if(farad.getCapacitance() != 0 && resistance.getOhm() != 0){
                Thread thread1 = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("Task 1 is running");
                        double start = System.currentTimeMillis();
                        while (objectPath.get(capacitorLocation).getVoltage() != potential.getVolt()) {
                            objectPath.get(capacitorLocation).setVoltage(potential.getVolt() * (1 - Math.pow(Math.E,(-(System.currentTimeMillis() - start) / 1000 * HelloController.returnTimeSlider().getValue()) / (resistance.getOhm() * farad.getCapacitance()))));
                            if (objectPath.get(capacitorLocation).isDisplayed()) {
                                Platform.runLater(() -> {
                                    objectPath.get(capacitorLocation).refreshPersonalLabel();
                                });
                                try {
                                    Thread.sleep(500); // Sleep for 0.5 seconds
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                            Debug.Log(objectPath.get(capacitorLocation).getVoltage() + " is voltage stuff");
                        }
                        Debug.Log("we out");
                    }
                });
                thread1.start();
            }

            HelloController.returnCalButton().setId("calculatetrue");
            HelloController.returnCalButton().setMouseTransparent(false);
            HelloController.returnCalButton().setOpacity(1);
            HelloController.returnTotalVoltLabel().setText(String.valueOf(potential.getVolt()));
            HelloController.returnTotalAmpLabel().setText(String.valueOf(current.getAmp()));
            HelloController.returnTotalOhmLabel().setText(String.valueOf(resistance.getOhm()));
            HelloController.returnTotalFaradsLabel().setText(String.valueOf(farad.getCapacitance()));
            HelloController.returnTotalChargeLabel().setText(String.valueOf(coulomb.getCharge()));

            for(int i = 1; i < objectPath.size(); i++){
                objectPath.get(i).setPassingCurrent(current.getAmp());
                if(objectPath.get(i).getResistance() != 0){
                    objectPath.get(i).setCharge(coulomb.getCharge());
                } else if(objectPath.get(i).getCapacitance() != 0 && resistance.getOhm() == 0){
                    objectPath.get(i).setCharge(coulomb.getCharge());
                    objectPath.get(i).setVoltage(objectPath.get(i).getCharge() / objectPath.get(i).getCapacitance());
                    Debug.Log(objectPath.get(i).getCharge() / objectPath.get(i).getCapacitance() + " should be the voltage");
                } else{
                    objectPath.get(i).setVoltage(0);
                }


               if(objectPath.get(i).isDisplayed()){
                   objectPath.get(i).refreshPersonalLabel();
               }
            }
        }
        else{
            HelloController.returnTotalVoltLabel().setText(String.valueOf(0));
            HelloController.returnTotalAmpLabel().setText(String.valueOf(0));
            HelloController.returnTotalOhmLabel().setText(String.valueOf(0));
            HelloController.returnTotalFaradsLabel().setText(String.valueOf(0));
            HelloController.returnTotalChargeLabel().setText(String.valueOf(0));
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
