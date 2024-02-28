package com.example.electriccircuit.DataTypes;

import com.example.electriccircuit.Logic.Physics;

public class Watt extends Physics {
    private double power;

    public Watt(double power){
        this.power = power;
    }

    public Watt(Volt potential, Amp current){
        this.power = powerLaw(potential, current);
    }

    public double getWatt(){
        return power;
    }

    public void setWatt(double power){
        this.power = power;
    }
}
