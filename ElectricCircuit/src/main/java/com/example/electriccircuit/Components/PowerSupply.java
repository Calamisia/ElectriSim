package com.example.electriccircuit.Components;

import javafx.scene.paint.Color;

public class PowerSupply {

    private int voltage;

    public void setVoltage(int voltage) {
        this.voltage = voltage;
    } // setter

    public int getVoltage() {
        return voltage;
    } // getter

    public int getId(){
        return 2;
    }
    public Color getColor(){
        return Color.BLUE;
    }
}
