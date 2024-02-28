package com.example.electriccircuit.DataTypes;


import com.example.electriccircuit.Logic.Physics;

public class Amp extends Physics {
    private double current;

    /* constructor directly with the value given */
    public Amp(double current){
        this.current = current;
    }
    /* using ohms law to get the current from the volts and ohms */
    public Amp(Volt potential, Ohm resistance) {
        this.current = ohmsLaw(potential,resistance);
    }
    /* using the power law to get the current from the power and the potential */
    public Amp(Watt power, Volt potential) {
        this.current = powerLaw(potential,power);
    }

    /* setters */
    public void setAmp(double current){
        this.current = current;
    }

    /* getters */
    public double getAmp(){
        return current;
    }
}
