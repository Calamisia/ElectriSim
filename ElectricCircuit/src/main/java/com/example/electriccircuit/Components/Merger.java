package com.example.electriccircuit.Components;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.io.InputStream;

public class Merger extends Component{
    private static Wire inputWireOne;
    private static Wire inputWireTwo;
    private static Wire inputWireThree;
    private static Wire outputWire;
    private boolean isConnected;
    private static int nOfInputs;

    public Merger (){
        InputStream in = getClass().getResourceAsStream("/com/example/electriccircuit/FourWayMerger.png");
        assert in != null;
        this.image = new Image(in);
        this.Id = 5;
    } // no-arg constructor

    // constructor
    public Merger (Wire inputWireOne, Wire inputWireTwo, Wire inputWireThree){
        this.inputWireOne = inputWireOne;
        this.inputWireTwo = inputWireTwo;
        this.inputWireThree = inputWireThree;

        InputStream in = getClass().getResourceAsStream("/com/example/electriccircuit/FourWayMerger.png");
        assert in != null;
        this.image = new Image(in);
        this.Id = 5;
    }

    // setter
    public void setnOfInputs(int nOfInputs) {
        this.nOfInputs = nOfInputs;
    }

    // calculate the current, voltage and resistance through this section of the wire.
    public static void calculate (){
        switch (nOfInputs){
            case 1 : { // if there's only one input, the info between wires doesn't change
                outputWire.setPassingCurrent(inputWireOne.getPassingCurrent());
                outputWire.setPassingVoltage(inputWireOne.getPassingVoltage());
            } break;
            case 2 : { // if there's 2 inputs, the currents are added together
                outputWire.setPassingCurrent(inputWireOne.getPassingCurrent()+inputWireTwo.getPassingCurrent());
                outputWire.setPassingVoltage(inputWireOne.getPassingVoltage());
            } break;
            case 3 : { // if there's 3 inputs, the currents are added together
                outputWire.setPassingCurrent(inputWireOne.getPassingCurrent()+inputWireTwo.getPassingCurrent()+inputWireThree.getPassingCurrent());
                outputWire.setPassingVoltage(inputWireOne.getPassingVoltage());
            } break;

        }
    }

}
