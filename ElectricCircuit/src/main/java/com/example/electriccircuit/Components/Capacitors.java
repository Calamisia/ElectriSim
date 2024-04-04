package com.example.electriccircuit.Components;

import javafx.scene.paint.Color;

public class Capacitors extends Component{

    public Capacitors(){
        this.color = Color.ORANGE;
        this.Id = 4;
    }
    private int capacitance;

    public void setCapacitance(int capacitance) {
        this.capacitance = capacitance;
    } // setter

    public int getCapacitance() {
        return capacitance;
    } // getter
}