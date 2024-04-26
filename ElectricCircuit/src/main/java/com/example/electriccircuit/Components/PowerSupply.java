package com.example.electriccircuit.Components;

import javafx.scene.image.Image;

import java.io.InputStream;

public class PowerSupply extends Component{
    public PowerSupply(){
        InputStream in = getClass().getResourceAsStream("/com/example/electriccircuit/power supply.png");
        assert in != null;
        this.image[0] = new Image(in);
        this.Id = 2;
        this.name = "Power Supply";
    }

    private double voltage;

    public void setVoltage(int voltage) {
        this.voltage = voltage;
        super.setPassingVoltage(voltage);
    } // setter

    public double getVoltage() {
        return voltage;
    } // getter
}
