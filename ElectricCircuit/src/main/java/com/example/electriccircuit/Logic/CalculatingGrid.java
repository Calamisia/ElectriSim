package com.example.electriccircuit.Logic;

import com.example.electriccircuit.Components.Capacitors;
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
import java.util.concurrent.CountDownLatch;

public class CalculatingGrid {
    //Base stat variables
    static Ohm resistance = new Ohm(0);
    static Volt potential = new Volt(0);
    private Amp current = new Amp(0);
    static Capacitance farad = new Capacitance(0);
    private Charge coulomb = new Charge(0);
    private int capacitorLocation;
    public static Capacitors chargedCapacitor;

    GridPane dataGrid;

    public CalculatingGrid(int[][] grid){
        //New instance of sandbox matrix
        BuilderMatrix sandboxMatrix = new BuilderMatrix(grid);
        //if the sandbox matrix is closed
        if(sandboxMatrix.closedCircuit()) {
            resistance.setOhm(0);
            potential.setVolt(0);

            //the two main paths
            String circuitPath = sandboxMatrix.getCircuitPath();
            ArrayList<Component> objectPath = sandboxMatrix.getObjectPath();

            //calculates values for potential and resistance
            potential.setVolt(objectPath.get(0).getPassingVoltage());
            for(int i = 1; i < objectPath.size(); i++){
                Debug.Log("The possible connections of " + i + " is " + objectPath.get(i).getConnections()[0] + objectPath.get(i).getConnections()[1] + objectPath.get(i).getConnections()[2] + objectPath.get(i).getConnections()[3] + " and is at location " + objectPath.get(i).getLocationRow() + " " + objectPath.get(i).getLocationColumn());
                objectPath.get(i).setPassingVoltage(objectPath.get(i - 1).getPassingVoltage());
                resistance.setOhm(resistance.getOhm() + objectPath.get(i).getResistance());
            }

            //calculates capacitance
            for(int i = 1; i < objectPath.size(); i++){
                if(objectPath.get(i).getCapacitance() != 0){
                    capacitorLocation = i;
                    farad.setCapacitance(farad.getCapacitance() + ( 1 / objectPath.get(i).getCapacitance()));
                }
            }

            //calculates current
            current.setAmp(current.ohmsLaw(potential, resistance));

            //calculates charge
            if(farad.getCapacitance() != 0){
                farad.setCapacitance(1 / farad.getCapacitance());
            }
            coulomb.setCharge(farad.getCapacitance() * potential.getVolt());

            //Real time capacitor in RC charging
            if(farad.getCapacitance() != 0 && resistance.getOhm() != 0){
                Thread thread1 = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        double start = System.currentTimeMillis();
                        while (objectPath.get(capacitorLocation).getVoltage() != potential.getVolt()) {
                            objectPath.get(capacitorLocation).setVoltage(potential.getVolt() * (1 - Math.pow(Math.E,(-(System.currentTimeMillis() - start) / 1000 * HelloController.returnTimeSlider().getValue()) / (resistance.getOhm() * farad.getCapacitance()))));
                            if (objectPath.get(capacitorLocation).isDisplayed()) {
                                Platform.runLater(() -> {
                                    objectPath.get(capacitorLocation).refreshPersonalLabel();
                                });
                                try {
                                    Thread.sleep(20);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                });
                thread1.start();
                chargedCapacitor = ((Capacitors)objectPath.get(capacitorLocation));
            }

            //Sets menu
            HelloController.returnCalButton().setId("calculatetrue");
            HelloController.returnCalButton().setMouseTransparent(false);
            HelloController.returnCalButton().setOpacity(1);
            HelloController.returnTotalVoltLabel().setText(String.valueOf(potential.getVolt()));
            HelloController.returnTotalAmpLabel().setText(String.valueOf(current.getAmp()));
            HelloController.returnTotalOhmLabel().setText(String.valueOf(resistance.getOhm()));
            HelloController.returnTotalFaradsLabel().setText(String.valueOf(farad.getCapacitance()));
            HelloController.returnTotalChargeLabel().setText(String.valueOf(coulomb.getCharge()));

            //sets the display value for individual components
            for(int i = 1; i < objectPath.size(); i++){
                objectPath.get(i).setPassingCurrent(current.getAmp());
                if(objectPath.get(i).getResistance() != 0){
                    objectPath.get(i).setVoltage(current.getAmp() * objectPath.get(i).getResistance());
                } else if(objectPath.get(i).getCapacitance() != 0 && resistance.getOhm() == 0){
                        objectPath.get(i).setCharge(coulomb.getCharge());
                        objectPath.get(i).setVoltage(objectPath.get(i).getCharge() / objectPath.get(i).getCapacitance());
                } else{
                        objectPath.get(i).setVoltage(0);
                }



               if(objectPath.get(i).isDisplayed()){
                   objectPath.get(i).refreshPersonalLabel();
               }
            }
        }
        else{
            //resets display things
            HelloController.returnTotalVoltLabel().setText(String.valueOf(0));
            HelloController.returnTotalAmpLabel().setText(String.valueOf(0));
            HelloController.returnTotalOhmLabel().setText(String.valueOf(0));
            HelloController.returnTotalFaradsLabel().setText(String.valueOf(0));
            HelloController.returnTotalChargeLabel().setText(String.valueOf(0));
            HelloController.returnCalButton().setId("calculatefalse");
            HelloController.returnCalButton().setMouseTransparent(true);

            //Real time capacitor in RC discharging
            if(chargedCapacitor != null){
                Capacitors temporarycap = chargedCapacitor;
                Debug.Log("unload");
                Thread thread2 = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        //* HelloController.returnTimeSlider().getValue()
                        double start = System.currentTimeMillis();
                        while (temporarycap.getVoltage() != 0) {
                            temporarycap.setVoltage(potential.getVolt() * (Math.pow(Math.E,(-(System.currentTimeMillis() - start) / 1000 * HelloController.returnTimeSlider().getValue()) / (resistance.getOhm() * farad.getCapacitance()))));
                            if (temporarycap.isDisplayed()) {
                                Platform.runLater(() -> {
                                    temporarycap.refreshPersonalLabel();
                                });
                                try {
                                    Thread.sleep(20);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                });
                thread2.start();
                Debug.Log(temporarycap.getName() + " is at " + temporarycap.getVoltage());
                chargedCapacitor = null;
            }
        }
    }
}
