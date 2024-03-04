package com.example.electriccircuit.DataTypes;
import com.example.electriccircuit.Logic.Physics;

public class Watt extends Physics {
    private double power;

    public Watt(double power){
        this.power = power;
    } // constructor

    public void setWatt(double power){
        this.power = power;
    } // setter

    public double getWatt(){
        return power;
    } // getter

}