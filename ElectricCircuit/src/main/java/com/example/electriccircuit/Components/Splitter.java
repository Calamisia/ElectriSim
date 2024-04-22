package com.example.electriccircuit.Components;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.io.InputStream;

public class Splitter extends Component{
    private boolean inputDetected;
    private static int nOfOutputs;
    private static Wire inputWire;
    private static Wire outputWireOne;
    private static Wire outputWireTwo;
    private static Wire outputWireThree;

    public Splitter(){
        InputStream in = getClass().getResourceAsStream("/com/example/electriccircuit/wire.png");
        assert in != null;
        this.image[0] = new Image(in);
        this.Id = 6;
    } // no-arg constructor

    public Splitter(Wire inputWire){
        this.inputWire = inputWire;
        inputDetected = true;
    } // constructor

    public void setInputDetected(boolean inputDetected) {
        this.inputDetected = inputDetected;
    } // setter


    public void setnOfOutputs(int nOfOutputs) {
        this.nOfOutputs = nOfOutputs;
    } // setter

    // calculate the current, voltage and resistance through this section of the wire.
    public static void calculate(){
        switch (nOfOutputs) {
           case 1 : { // if there's only one output, the info between wires doesn't change
               outputWireOne.setPassingCurrent(inputWire.getPassingCurrent());
               outputWireOne.setPassingVoltage(inputWire.getPassingVoltage());
           } break;
           case 2 : { // if there's 2 outputs, the voltage is passed to both of them, and the current passed to each wire is calculated using Ohm's law.
                outputWireOne.setPassingVoltage(inputWire.getPassingVoltage());
                outputWireTwo.setPassingVoltage(inputWire.getPassingVoltage());
                outputWireOne.setPassingCurrent(inputWire.getPassingVoltage() / outputWireOne.getConnectedResistance());
                outputWireTwo.setPassingCurrent(inputWire.getPassingCurrent() / outputWireTwo.getConnectedResistance());
           } break;
           case 3 : { // if there's 3 outputs, the voltage is passed all of them, and the current passed to each wire is calculated using Ohm's law.
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
