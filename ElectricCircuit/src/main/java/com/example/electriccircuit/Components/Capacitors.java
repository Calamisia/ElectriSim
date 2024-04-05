package com.example.electriccircuit.Components;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.io.InputStream;

public class Capacitors extends Component{

    public Capacitors(){
        InputStream in = getClass().getResourceAsStream("com/example/electriccircuit/capacitor.png");
        assert in != null;
        this.image = new Image(in);
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