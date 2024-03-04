package com.example.electriccircuit.Components;

public class Merger {
    private static Wire inputWireOne;
    private static Wire inputWireTwo;
    private static Wire inputWireThree;
    private static Wire outputWire;
    private boolean isConnected;
    private static int nOfInputs;

    public Merger (){}

    public Merger (Wire inputWireOne, Wire inputWireTwo, Wire inputWireThree){
        this.inputWireOne = inputWireOne;
        this.inputWireTwo = inputWireTwo;
        this.inputWireThree = inputWireThree;
    }

    public void setnOfInputs(int nOfInputs) {
        this.nOfInputs = nOfInputs;
    }

    public static void calculate (){
        switch (nOfInputs){
            case 1 : {
                outputWire.setPassingCurrent(inputWireOne.getPassingCurrent());
                outputWire.setPassingVoltage(inputWireOne.getPassingVoltage());
            } break;
            case 2 : {
                outputWire.setPassingCurrent(inputWireOne.getPassingCurrent()+inputWireTwo.getPassingCurrent());
                outputWire.setPassingVoltage(inputWireOne.getPassingVoltage());
            } break;
            case 3 : {
                outputWire.setPassingCurrent(inputWireOne.getPassingCurrent()+inputWireTwo.getPassingCurrent()+inputWireThree.getPassingCurrent());
                outputWire.setPassingVoltage(inputWireOne.getPassingVoltage());
            } break;

        }
    }

}
