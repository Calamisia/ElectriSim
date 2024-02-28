package com.example.electriccircuit.DataTypes;

import com.example.electriccircuit.Logic.Physics;

public class Capacitance extends Physics {
    private double farad;

    public Capacitance(double farad){
        this.farad = farad;
    }

    public Capacitance(Charge coulomb, Volt potential){
        this.farad = capacitanceLaw(coulomb, potential);
    }

    public double getCapacitance(){
        return farad;
    }

    public void setCapacitance(double farad){
        this.farad = farad;
    }
}
