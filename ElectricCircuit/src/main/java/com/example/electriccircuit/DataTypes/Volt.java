package com.example.electriccircuit.DataTypes;
import com.example.electriccircuit.Logic.Physics;

public class Volt extends Physics {
    private double potential;

    public Volt(double potential){
        this.potential = potential;
    } // constructor

    public void setVolt(double potential){
        this.potential = potential;
    } // setter

    public double getVolt(){
        return potential;
    } // getter

}