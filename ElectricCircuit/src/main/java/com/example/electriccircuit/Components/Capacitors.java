package com.example.electriccircuit.Components;

import javafx.scene.paint.Color;

public class Capacitors {

    private int capacitance;

    public void setCapacitance(int capacitance) {
        this.capacitance = capacitance;
    } // setter

    public int getCapacitance() {
        return capacitance;
    } // getter

    public int getId(){
        return 4;
    }
    public Color getColor(){
        return Color.ORANGE;
    }

}