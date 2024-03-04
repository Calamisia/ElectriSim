package com.example.electriccircuit.Components;

import javafx.scene.paint.Color;

public class Wire {
    private int length;
    private Color wireColor;
    private boolean isConnected;
    private double passingCurrent;
    private double passingVoltage;
    private Resistors connectedResistor;
    private Capacitors connectedCapacitor;
    private PowerSupply connectedPowerSupply;
    private double connectedResistance;
    private double connectedVoltage;
    private double connectedCapacitance;


    public void setLength(int length) {
        this.length = length;
    } // setter

    public int getLength() {
        return length;
    } // getter

    public void setPassingCurrent(double passingCurrent) {
        this.passingCurrent = passingCurrent;
    }

    public double getPassingCurrent() {
        return passingCurrent;
    }

    public void setWireColor(Color color){
        this.wireColor = color;
    }

    public Color getWireColor() {
        return wireColor;
    }

    public void setPassingVoltage(double passingVoltage) {
        this.passingVoltage = passingVoltage;
    }

    public double getPassingVoltage() {
        return passingVoltage;
    }

    public void setConnectedResistance(double connectedResistance) {
        this.connectedResistance = connectedResistance;
    }

    public double getConnectedResistance() {
        return connectedResistance;
    }

    public void setConnectedCapacitance(double connectedCapacitance) {
        this.connectedCapacitance = connectedCapacitance;
    }

    public double getConnectedCapacitance() {
        return connectedCapacitance;
    }

    public void checkIfConnected(){
        // check surrounding matrix grids if there is a component there, if so, then connected
        if (isConnected) {
            connectedResistance = connectedResistor.getResistance();
            connectedCapacitance = connectedCapacitor.getCapacitance();
            connectedVoltage = connectedPowerSupply.getVoltage();
            passingVoltage = connectedVoltage;
        }
        else {
            connectedResistance = 0;
            connectedCapacitance = 0;
            connectedVoltage = 0;
            passingVoltage = 0;
        }
    }
}


// ADD COLORS