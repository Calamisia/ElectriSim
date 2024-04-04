package com.example.electriccircuit.Components;

import javafx.scene.Node;
import javafx.scene.paint.Color;

public class Component {
    Node node;
    Color color;
    int Id;
    private int[] location = new int[2];
    Component(Node node){
        this.node = node;
    }
    Component(){}

    public Node getComponentNode(){
        return this.node;
    }
    public void setComponentNode(Node node){
        this.node = node;
    }
    public void setLocation(int row, int column){
        location[0] = row;
        location[1] = column;
    }
    public void setLocation(int[] location){
        this.location = location;
    }

    public int[] getLocation(){
        return location;
    }
    public int getLocationRow(){
        return location[0];
    }
    public int getLocationColumn(){
        return location[1];
    }

    public int getId(){
        return this.Id;
    }
    public Color getColor(){
        return this.color;
    }

    public void interact(){}


}
