package com.example.electriccircuit.Components;

public class Splitter {
    private boolean inputDetected;
    private int nOfOutputs;
    private Wire inputWire;
    private Wire outputWireOne;
    private Wire outputWireTwo;
    private Wire outputWireThree;

    public Splitter(){}

    public Splitter(Wire inputWire){
        this.inputWire = inputWire;
        inputDetected = true;
    }

    public boolean isInputDetected() {
        return inputDetected;
    }

    public void setInputDetected(boolean inputDetected) {
        this.inputDetected = inputDetected;
    }

    public int getnOfOutputs() {
        return nOfOutputs;
    }

    public void setnOfOutputs(int nOfOutputs) {
        this.nOfOutputs = nOfOutputs;
    }

    public void calculate(){
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
