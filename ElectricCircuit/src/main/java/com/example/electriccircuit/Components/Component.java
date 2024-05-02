package com.example.electriccircuit.Components;

import com.example.electriccircuit.DataTypes.*;
import com.example.electriccircuit.HelloController;
import com.example.electriccircuit.Logic.BuilderMatrix;
import com.example.electriccircuit.Logic.Debug;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Shape;

import java.io.InputStream;
import java.util.Arrays;

public class Component {
    //Variables
    String name;
    Shape node;
    Image image[] = new Image[4];
    int Id;
    private int[] location = new int[2];
    public static Component[][] componentArray = new Component[35][20];

    private boolean isDisplayed = false;

    private double passingVoltage;
    private double voltage;
    private double passingCurrent;
    private double resistance;
    private int displayrow;
    private double capacitance;
    private double charge;
    private int[] connections = new int[4];
    public Label labelName = new Label();
    public Label labelResistance = new Label();
    public Label labelPotential = new Label();
    public Label labelCurrent = new Label();
    public Label labelCapacitance = new Label();
    public Label labelCharge = new Label();

    Component(Shape node) {
        this.node = node;
    }

    Component() {
    }

    //Setters and getters
    public Node getComponentNode() {
        return this.node;
    }

    public void setComponentNode(Shape node) {
        this.node = node;
    }

    public void removeComponentNode(){
        HelloController.returnSmallAnchorPane().getChildren().remove(node);
    }

    public void setLocation(int row, int column) {
        location[0] = row;
        location[1] = column;
    }

    public void setLocation(int[] location) {
        this.location = location;
    }

    public int[] getLocation() {
        return location;
    }

    public int getLocationRow() {
        return location[0];
    }

    public int getLocationColumn() {
        return location[1];
    }

    public int getId() {
        return this.Id;
    }

    public void setId(int Id){
        this.Id = Id;
    }

    public Image getImageTexture() {
        return this.image[0];
    }
    public Image getImageTexture(int i) {
        return this.image[i];
    }

    public void setImageTexture(Image image){
        this.image[0] = image;
    }

    public void setImageTexture(Image image, int i){
        this.image[i] = image;
    }

    //Interact module which is triggered on component click
    public void interact() {
        getComponentNode().setOnMousePressed(e -> { // When mouse pressed on the object
            labelName.setText(name + " " + HelloController.returnDataGrid().getRowCount());
            refreshPersonalLabel();
            System.out.println(isDisplayed);
            if (isDisplayed) {
                HelloController.returnDataGrid().getChildren().removeIf(node -> GridPane.getRowIndex(node) != null && GridPane.getRowIndex(node) == displayrow);
                isDisplayed = false;
                HelloController.returnDataGrid().getChildren().forEach(node -> {
                    Integer rowIndex = GridPane.getRowIndex(node);
                    if (rowIndex != null && rowIndex > displayrow) {
                        GridPane.setRowIndex(node, rowIndex - 1);
                    }
                });
            }
            else if(!isDisplayed){
                isDisplayed = true;
                displayrow = HelloController.returnDataGrid().getRowCount();
                HelloController.returnDataGrid().addRow(
                        HelloController.returnDataGrid().getRowCount(),
                        labelName, labelResistance, labelPotential, labelCurrent, labelCapacitance, labelCharge
                );
            }
        });
    }

    public int getDisplayrow(){return this.displayrow;}

    //Refresh system for wire and switch
    public void refreshComponent(){

    }
    public void mainRefreshComponent(){
            if(BuilderMatrix.surroundingCheck(getLocationRow(), getLocationColumn())[0] == 1 && BuilderMatrix.surroundingCheck(getLocationRow(), getLocationColumn())[1] == 1){
                componentArray[getLocationRow() - 1][getLocationColumn()].refreshComponent();
                componentArray[getLocationRow()][getLocationColumn() - 1].refreshComponent();
            } else if (BuilderMatrix.surroundingCheck(getLocationRow(), getLocationColumn())[0] == 1 && BuilderMatrix.surroundingCheck(getLocationRow(), getLocationColumn())[2] == 1){
                componentArray[getLocationRow() - 1][getLocationColumn()].refreshComponent();
                componentArray[getLocationRow() + 1][getLocationColumn()].refreshComponent();
            } else if (BuilderMatrix.surroundingCheck(getLocationRow(), getLocationColumn())[0] == 1 && BuilderMatrix.surroundingCheck(getLocationRow(), getLocationColumn())[3] == 1){
                componentArray[getLocationRow() - 1][getLocationColumn()].refreshComponent();
                componentArray[getLocationRow()][getLocationColumn() + 1].refreshComponent();
            } else if (BuilderMatrix.surroundingCheck(getLocationRow(), getLocationColumn())[1] == 1 && BuilderMatrix.surroundingCheck(getLocationRow(), getLocationColumn())[2] == 1){
                componentArray[getLocationRow()][getLocationColumn() - 1].refreshComponent();
                componentArray[getLocationRow() + 1][getLocationColumn()].refreshComponent();
            } else if (BuilderMatrix.surroundingCheck(getLocationRow(), getLocationColumn())[1] == 1 && BuilderMatrix.surroundingCheck(getLocationRow(), getLocationColumn())[3] == 1){
                componentArray[getLocationRow()][getLocationColumn() - 1].refreshComponent();
                componentArray[getLocationRow()][getLocationColumn() + 1].refreshComponent();
            } else if (BuilderMatrix.surroundingCheck(getLocationRow(), getLocationColumn())[2] == 1 && BuilderMatrix.surroundingCheck(getLocationRow(), getLocationColumn())[3] == 1){
                componentArray[getLocationRow() + 1][getLocationColumn()].refreshComponent();
                componentArray[getLocationRow()][getLocationColumn() + 1].refreshComponent();
            } else if (BuilderMatrix.surroundingCheck(getLocationRow(), getLocationColumn())[0] == 1){
                componentArray[getLocationRow() - 1][getLocationColumn()].refreshComponent();
            } else if (BuilderMatrix.surroundingCheck(getLocationRow(), getLocationColumn())[1] == 1){
                componentArray[getLocationRow()][getLocationColumn() - 1].refreshComponent();
            } else if (BuilderMatrix.surroundingCheck(getLocationRow(), getLocationColumn())[2] == 1){
                componentArray[getLocationRow() + 1][getLocationColumn()].refreshComponent();
            } else if (BuilderMatrix.surroundingCheck(getLocationRow(), getLocationColumn())[3] == 1){
                componentArray[getLocationRow()][getLocationColumn() + 1].refreshComponent();
            }
    }

    //Setters and getters for stats
    public void setPassingVoltage(double passingVoltage){
        this.passingVoltage = passingVoltage;
    }
    public double getPassingVoltage(){
        return passingVoltage;
    }
    public void setVoltage(double voltage){
        this.voltage = voltage;
    }
    public double getVoltage(){
        return voltage;
    }
    public void setPassingCurrent(double passingCurrent){
        this.passingCurrent = passingCurrent;
    }
    public double getPassingCurrent(){
        return passingCurrent;
    }
    public void setResistance(double resistance){
        this.resistance = resistance;
    }
    public double getResistance(){
        return resistance;
    }


    public void setCapacitance(double capacitance) {
        this.capacitance = capacitance;
    } // setter

    public double getCapacitance() {
        return capacitance;
    } // getter

    public void setCharge(double charge) {
        this.charge = charge;
    } // setter

    public double getCharge() {
        return charge;
    } // getter

    //Connections is the possible places where a component can connect to
    public int[] getConnections(){
        return connections;
    }
    public void setConnections(int[] connections){
        this.connections = connections;
    }
    public void setConnections(int connection1, int connection2, int connection3, int connection4){
        this.connections[0] = connection1;
        this.connections[1] = connection2;
        this.connections[2] = connection3;
        this.connections[3] = connection4;
    }
    public void setConnections(boolean horizontal){
        if(horizontal){
            this.connections[0] = 0;
            this.connections[1] = 1;
            this.connections[2] = 0;
            this.connections[3] = 1;
        } else{
            this.connections[0] = 1;
            this.connections[1] = 0;
            this.connections[2] = 1;
            this.connections[3] = 0;
        }
    }

    ///displayed stats
    public void setIsDisplayed(boolean isDisplayed){
        this.isDisplayed = isDisplayed;
    }
    public boolean isDisplayed(){
        return isDisplayed;
    }
    public String getName(){
        return name;
    }

    public void refreshPersonalLabel(){
        labelResistance.setText(String.valueOf(resistance));
        labelPotential.setText((String.valueOf(voltage)));
        labelCurrent.setText(String.valueOf(passingCurrent));
        labelCapacitance.setText(String.valueOf(capacitance));
        labelCharge.setText(String.valueOf(charge));
    }
    public void closedCircuitRefreshPersonalLabel(){
        labelResistance.setText(String.valueOf(0));
        labelPotential.setText((String.valueOf(0)));
        labelCurrent.setText(String.valueOf(0));
        labelCapacitance.setText(String.valueOf(0));
        labelCharge.setText(String.valueOf(0));
    }

    public String toString(){
        return Id + "";
    }

}
