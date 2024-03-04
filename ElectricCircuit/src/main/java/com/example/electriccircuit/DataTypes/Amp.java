package com.example.electriccircuit.DataTypes;
import com.example.electriccircuit.Logic.Physics;

public class Amp extends Physics {
    private double current;

    public Amp(double current){
        this.current = current;
    } // constructer with value given

    public void setAmp(double current){
        this.current = current;
    } //setter

    public double getAmp(){
        return current;
    } //getter

}