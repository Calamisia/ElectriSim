package com.example.electriccircuit.Components;

import com.example.electriccircuit.Logic.BuilderMatrix;
import com.example.electriccircuit.Logic.Debug;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;

import java.io.InputStream;


public class Wire extends Component{
    public Wire(){
        InputStream in = getClass().getResourceAsStream("/com/example/electriccircuit/wire.png");
        assert in != null;
        this.image[0] = new Image(in);

        InputStream in2 = getClass().getResourceAsStream("/com/example/electriccircuit/angleWire.png");
        assert in2 != null;
        this.image[1] = new Image(in2);

        this.Id = 1;
        this.name = "Wire";
    }

    public Wire(boolean empty){

    }/*
    private int length;
    private Color wireColor;
    private boolean isConnected;
    private double passingCurrent;
    private double passingVoltage;
    private Resistors connectedResistor;
    private Capacitors connectedCapacitor;
    private PowerSupply connectedPowerSupply;
    private double connectedResistance;
    private double connectedVoltage;
    private double connectedCapacitance;


    public void setLength(int length) {
        this.length = length;
    } // setter

    public int getLength() {
        return length;
    } // getter

    public void setPassingCurrent(double passingCurrent) {
        this.passingCurrent = passingCurrent;
    } // setter

    public double getPassingCurrent() {
        return passingCurrent;
    } // getter

    public void setWireColor(Color color){
        this.wireColor = color;
    } // setter

    public Color getWireColor() {
        return wireColor;
    } // getter

    public void setPassingVoltage(double passingVoltage) {
        this.passingVoltage = passingVoltage;
    } // setter

    public double getPassingVoltage() {
        return passingVoltage;
    } // getter

    public void setConnectedResistance(double connectedResistance) {
        this.connectedResistance = connectedResistance;
    } // setter

    public double getConnectedResistance() {
        return connectedResistance;
    } // getter

    public void setConnectedCapacitance(double connectedCapacitance) {
        this.connectedCapacitance = connectedCapacitance;
    } // setter

    public double getConnectedCapacitance() {
        return connectedCapacitance;
    } // getter

    public void checkIfConnected(){
        // check surrounding matrix grids if there is a component there, if so, then connected
        if (isConnected) {
            connectedResistance = connectedResistor.getResistance();
            connectedCapacitance = connectedCapacitor.getCapacitance();
            connectedVoltage = connectedPowerSupply.getVoltage();
            passingVoltage = connectedVoltage;
        } // the wire is transferring the data through components
        else {
            connectedResistance = 0;
            connectedCapacitance = 0;
            connectedVoltage = 0;
            passingVoltage = 0;
        } // if it isn't connected, there's no data to transfer.
    } */
    @Override
    public void refreshComponent(){
        //Debug.Log(BuilderMatrix.surroundingCheck(Windex, Hindex)[0] + " " + BuilderMatrix.surroundingCheck(Windex, Hindex)[1] + " " +
        //BuilderMatrix.surroundingCheck(Windex, Hindex)[2] + " " + BuilderMatrix.surroundingCheck(Windex, Hindex)[3] + " ");
        Debug.Log("refreshes " + BuilderMatrix.surroundingCheck(getLocationRow(),getLocationColumn())[0] + BuilderMatrix.surroundingCheck(getLocationRow(),getLocationColumn())[1] + BuilderMatrix.surroundingCheck(getLocationRow(),getLocationColumn())[2] + BuilderMatrix.surroundingCheck(getLocationRow(),getLocationColumn())[3]);
        //Switch row and columns !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! :(
        if(BuilderMatrix.surroundingCheck(getLocationRow(), getLocationColumn())[0] == 1 && BuilderMatrix.surroundingCheck(getLocationRow(), getLocationColumn())[1] == 1){
            node.setFill(new ImagePattern(getImageTexture(1)));
            node.setRotate(90);
            Debug.Log("refreshes 1");
            setConnections(1,0,0,1);
        } else if (BuilderMatrix.surroundingCheck(getLocationRow(), getLocationColumn())[0] == 1 && BuilderMatrix.surroundingCheck(getLocationRow(), getLocationColumn())[2] == 1){
            node.setFill(new ImagePattern(getImageTexture(0)));
            Debug.Log("refreshes 2");
            setConnections(0,1,0,1);
        } else if (BuilderMatrix.surroundingCheck(getLocationRow(), getLocationColumn())[0] == 1 && BuilderMatrix.surroundingCheck(getLocationRow(), getLocationColumn())[3] == 1){
            node.setFill(new ImagePattern(getImageTexture(1)));
            node.setRotate(360);
            Debug.Log("refreshes 3");
            setConnections(0,0,1,1);
        } else if (BuilderMatrix.surroundingCheck(getLocationRow(), getLocationColumn())[1] == 1 && BuilderMatrix.surroundingCheck(getLocationRow(), getLocationColumn())[2] == 1){
            node.setFill(new ImagePattern(getImageTexture(1)));
            node.setRotate(180);
            Debug.Log("refreshes 4");
            setConnections(1,1,0,0);
        } else if (BuilderMatrix.surroundingCheck(getLocationRow(), getLocationColumn())[1] == 1 && BuilderMatrix.surroundingCheck(getLocationRow(), getLocationColumn())[3] == 1){
            node.setFill(new ImagePattern(getImageTexture(0)));
            node.setRotate(90);
            Debug.Log("refreshes 5");
            setConnections(1,0,1,0);
        } else if (BuilderMatrix.surroundingCheck(getLocationRow(), getLocationColumn())[2] == 1 && BuilderMatrix.surroundingCheck(getLocationRow(), getLocationColumn())[3] == 1){
            node.setFill(new ImagePattern(getImageTexture(1)));
            node.setRotate(-90);
            Debug.Log("refreshes 6");
            setConnections(0,1,1,0);
        } else if (BuilderMatrix.surroundingCheck(getLocationRow(), getLocationColumn())[0] == 1){
            node.setFill(new ImagePattern(getImageTexture(0)));
            Debug.Log("refreshes 7");
            setConnections(0,0,0,1);
        } else if (BuilderMatrix.surroundingCheck(getLocationRow(), getLocationColumn())[1] == 1){
            node.setFill(new ImagePattern(getImageTexture(0)));
            node.setRotate(90);
            Debug.Log("refreshes 18");
            setConnections(0,0,1,0);
        } else if (BuilderMatrix.surroundingCheck(getLocationRow(), getLocationColumn())[2] == 1){
            node.setFill(new ImagePattern(getImageTexture(0)));
            Debug.Log("refreshes 19");
            setConnections(0,1,0,0);
        } else if (BuilderMatrix.surroundingCheck(getLocationRow(), getLocationColumn())[3] == 1){
            node.setFill(new ImagePattern(getImageTexture(0)));
            node.setRotate(90);
            Debug.Log("refreshes 110");
            setConnections(1,0,0,0);
        } else{
            node.setFill(new ImagePattern(getImageTexture(0)));
            Debug.Log("refreshes 111");
        }
    }
    @Override
    public void mainRefreshComponent(){
        Debug.Log("got here");
        if(BuilderMatrix.surroundingCheck(getLocationRow(), getLocationColumn())[0] == 1 && BuilderMatrix.surroundingCheck(getLocationRow(), getLocationColumn())[1] == 1){
            node.setFill(new ImagePattern(getImageTexture(1)));
            node.setRotate(90);
            componentArray[getLocationRow() - 1][getLocationColumn()].refreshComponent();
            componentArray[getLocationRow()][getLocationColumn() - 1].refreshComponent();
            setConnections(1,0,0,1);
        } else if (BuilderMatrix.surroundingCheck(getLocationRow(), getLocationColumn())[0] == 1 && BuilderMatrix.surroundingCheck(getLocationRow(), getLocationColumn())[2] == 1){
            node.setFill(new ImagePattern(getImageTexture(0)));
            componentArray[getLocationRow() - 1][getLocationColumn()].refreshComponent();
            componentArray[getLocationRow() + 1][getLocationColumn()].refreshComponent();
            setConnections(0,1,0,1);
        } else if (BuilderMatrix.surroundingCheck(getLocationRow(), getLocationColumn())[0] == 1 && BuilderMatrix.surroundingCheck(getLocationRow(), getLocationColumn())[3] == 1){
            node.setFill(new ImagePattern(getImageTexture(1)));
            componentArray[getLocationRow() - 1][getLocationColumn()].refreshComponent();
            componentArray[getLocationRow()][getLocationColumn() + 1].refreshComponent();
            setConnections(0,0,1,1);
        } else if (BuilderMatrix.surroundingCheck(getLocationRow(), getLocationColumn())[1] == 1 && BuilderMatrix.surroundingCheck(getLocationRow(), getLocationColumn())[2] == 1){
            node.setFill(new ImagePattern(getImageTexture(1)));
            node.setRotate(180);
            componentArray[getLocationRow()][getLocationColumn() - 1].refreshComponent();
            componentArray[getLocationRow() + 1][getLocationColumn()].refreshComponent();
            setConnections(1,1,0,0);
        } else if (BuilderMatrix.surroundingCheck(getLocationRow(), getLocationColumn())[1] == 1 && BuilderMatrix.surroundingCheck(getLocationRow(), getLocationColumn())[3] == 1){
            node.setFill(new ImagePattern(getImageTexture(0)));
            node.setRotate(90);
            componentArray[getLocationRow()][getLocationColumn() - 1].refreshComponent();
            componentArray[getLocationRow()][getLocationColumn() + 1].refreshComponent();
            setConnections(1,0,1,0);
        } else if (BuilderMatrix.surroundingCheck(getLocationRow(), getLocationColumn())[2] == 1 && BuilderMatrix.surroundingCheck(getLocationRow(), getLocationColumn())[3] == 1){
            node.setFill(new ImagePattern(getImageTexture(1)));
            node.setRotate(-90);
            componentArray[getLocationRow() + 1][getLocationColumn()].refreshComponent();
            componentArray[getLocationRow()][getLocationColumn() + 1].refreshComponent();
            setConnections(0,1,1,0);
        } else if (BuilderMatrix.surroundingCheck(getLocationRow(), getLocationColumn())[0] == 1){
            node.setFill(new ImagePattern(getImageTexture(0)));
            componentArray[getLocationRow() - 1][getLocationColumn()].refreshComponent();
            Debug.Log("refreshes7");
            setConnections(0,0,0,1);
        } else if (BuilderMatrix.surroundingCheck(getLocationRow(), getLocationColumn())[1] == 1){
            node.setFill(new ImagePattern(getImageTexture(0)));
            node.setRotate(90);
            componentArray[getLocationRow()][getLocationColumn() - 1].refreshComponent();
            Debug.Log("refreshes8");
            setConnections(0,0,1,0);
        } else if (BuilderMatrix.surroundingCheck(getLocationRow(), getLocationColumn())[2] == 1){
            node.setFill(new ImagePattern(getImageTexture(0)));
            componentArray[getLocationRow() + 1][getLocationColumn()].refreshComponent();
            Debug.Log("refreshes9");
            setConnections(0,1,0,0);
        } else if (BuilderMatrix.surroundingCheck(getLocationRow(), getLocationColumn())[3] == 1){
            node.setFill(new ImagePattern(getImageTexture(0)));
            node.setRotate(90);
            componentArray[getLocationRow()][getLocationColumn() + 1].refreshComponent();
            Debug.Log("refreshes10");
            setConnections(1,0,0,0);
        } else{
            node.setFill(new ImagePattern(getImageTexture(0)));
        }
    }
}
