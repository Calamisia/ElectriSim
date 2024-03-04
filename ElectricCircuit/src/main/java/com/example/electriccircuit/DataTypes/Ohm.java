package com.example.electriccircuit.DataTypes;
import com.example.electriccircuit.Logic.Physics;

public class Ohm extends Physics {
    private double resistance;

    public Ohm(double resistance){
        this.resistance = resistance;
    } // constructor

    public void setOhm(double resistance){
        this.resistance = resistance;
    } // setter

    public double getOhm(){
        return resistance;
    } // getter

}