package com.example.electriccircuit.Components;

import com.example.electriccircuit.DataTypes.*;
import com.example.electriccircuit.HelloController;
import com.example.electriccircuit.Logic.BuilderMatrix;
import com.example.electriccircuit.Logic.Debug;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Shape;

public class Component {
    Shape node;
    Image image[] = new Image[3];
    int Id;
    private int[] location = new int[2];
    public static Component[][] componentArray = new Component[35][20];

    private double passingVoltage;
    private double passingCurrent;
    private double resistance;
    private int[] connections = new int[4];

    Component(Shape node) {
        this.node = node;
    }

    Component() {
    }

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

    public void interact() {
    }
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

    public void setPassingVoltage(double passingVoltage){
        this.passingVoltage = passingVoltage;
    }
    public double getPassingVoltage(){
        return passingVoltage;
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

    public String toString(){
        return Id + "";
    }

}
