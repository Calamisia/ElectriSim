package com.example.electriccircuit.DataTypes;
import com.example.electriccircuit.Logic.Physics;

public class Capacitance extends Physics {
    private double farad;

    public Capacitance(double farad){
        this.farad = farad;
    } // constructor

    public void setCapacitance(double farad){
        this.farad = farad;
    } // setter

    public double getCapacitance(){
        return farad;
    } // getter

}