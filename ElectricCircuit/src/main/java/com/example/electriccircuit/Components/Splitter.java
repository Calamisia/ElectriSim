package com.example.electriccircuit.Components;

public class Splitter {
    private boolean inputDetected;
    private static int nOfOutputs;
    private static Wire inputWire;
    private static Wire outputWireOne;
    private static Wire outputWireTwo;
    private static Wire outputWireThree;

    public Splitter(){}

    public Splitter(Wire inputWire){
        this.inputWire = inputWire;
        inputDetected = true;
    }

    public void setInputDetected(boolean inputDetected) {
        this.inputDetected = inputDetected;
    }


    public void setnOfOutputs(int nOfOutputs) {
        this.nOfOutputs = nOfOutputs;
    }

    public static void calculate(){
        switch (nOfOutputs) {
           case 1 : {
               outputWireOne.setPassingCurrent(inputWire.getPassingCurrent());
               outputWireOne.setPassingVoltage(inputWire.getPassingVoltage());
           } break;
           case 2 : {
                outputWireOne.setPassingVoltage(inputWire.getPassingVoltage());
                outputWireTwo.setPassingVoltage(inputWire.getPassingVoltage());
                outputWireOne.setPassingCurrent(inputWire.getPassingVoltage() / outputWireOne.getConnectedResistance());
                outputWireTwo.setPassingCurrent(inputWire.getPassingCurrent() / outputWireTwo.getConnectedResistance());
           } break;
           case 3 : {
               outputWireOne.setPassingVoltage(inputWire.getPassingVoltage());
               outputWireTwo.setPassingVoltage(inputWire.getPassingVoltage());
               outputWireThree.setPassingVoltage(inputWire.getPassingVoltage());
               outputWireOne.setPassingCurrent(inputWire.getPassingCurrent() / outputWireOne.getConnectedResistance());
               outputWireTwo.setPassingCurrent(inputWire.getPassingCurrent() / outputWireTwo.getConnectedResistance());
               outputWireThree.setPassingCurrent(inputWire.getPassingCurrent() / outputWireThree.getConnectedResistance());
           } break;
        }


    }



}
