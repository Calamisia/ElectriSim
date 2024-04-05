package com.example.electriccircuit.Components;

import javafx.scene.paint.Color;

public class PowerSupply extends Component{
    public PowerSupply(){
        this.color = Color.BLUE;
        this.Id = 2;
    }

    private int voltage;

    public void setVoltage(int voltage) {
        this.voltage = voltage;
    } // setter

    public int getVoltage() {
        return voltage;
    } // getter
}
