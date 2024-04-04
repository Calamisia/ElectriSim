package com.example.electriccircuit.Components;

import javafx.scene.paint.Color;

public class Resistors extends Component{

    public Resistors(){
        this.color = Color.GREEN;
        this.Id = 3;
    }

    private int resistance;

    public void setResistance(int resistance) {
        this.resistance = resistance;
    } // setter

    public int getResistance() {
        return resistance;
    } // getter
}
