package com.example.electriccircuit.Components;

import javafx.scene.paint.Color;

public class Resistors {

    private int resistance;

    public void setResistance(int resistance) {
        this.resistance = resistance;
    } // setter

    public int getResistance() {
        return resistance;
    } // getter

    public int getId(){
        return 3;
    }
    public Color getColor(){
        return Color.GREEN;
    }
}
