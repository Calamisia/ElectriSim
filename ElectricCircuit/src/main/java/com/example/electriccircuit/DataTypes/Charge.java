package com.example.electriccircuit.DataTypes;

import com.example.electriccircuit.Logic.Physics;

public class Charge extends Physics {
    private double coulomb;

    public Charge(double coulomb){
        this.coulomb = coulomb;
    }

    public Charge(Capacitance farad, Volt potential){
        this.coulomb = capacitanceLaw(farad, potential);
    }

    public double getCharge(){
        return coulomb;
    }

    public void setCharge(double coulomb){
    this.coulomb = coulomb;
    }
}