package com.example.electriccircuit.DataTypes;
import com.example.electriccircuit.Logic.Physics;

public class Charge extends Physics {
    private double coulomb;

    public Charge(double coulomb){
        this.coulomb = coulomb;
    } // constructor

    public void setCharge(double coulomb){
        this.coulomb = coulomb;
    } // setter

    public double getCharge(){
        return coulomb;
    } // getter

}