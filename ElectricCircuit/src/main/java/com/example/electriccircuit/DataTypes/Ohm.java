package com.example.electriccircuit.DataTypes;

import com.example.electriccircuit.Logic.Physics;

public class Ohm extends Physics {
    private double resistance;

    public Ohm(double resistance){
        this.resistance = resistance;
    }

    public Ohm(Volt potential, Amp current){
        this.resistance = ohmsLaw(potential, current);
    }

    public double getOhm(){
        return resistance;
    }

    public void setOhm(double resistance){
        this.resistance = resistance;
    }
}
