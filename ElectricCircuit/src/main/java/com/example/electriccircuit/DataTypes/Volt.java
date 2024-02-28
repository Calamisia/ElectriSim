package com.example.electriccircuit.DataTypes;

import com.example.electriccircuit.Logic.Physics;

public class Volt extends Physics {
    private double potential;

    public Volt(double potential){
        this.potential = potential;
    }

    public Volt(Amp current, Ohm resistance){
        this.potential = ohmsLaw(current, resistance);
    }

    public Volt(Watt power, Amp current){
        this.potential = powerLaw(power, current);
    }

    public Volt(Charge coulomb, Capacitance farad){
        this.potential = capacitanceLaw(coulomb, farad);
    }

    public double getVolt(){
        return potential;
    }

    public void setVolt(double potential){
        this.potential = potential;
    }
}
