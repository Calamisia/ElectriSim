package com.example.electriccircuit.Components;

import javafx.scene.Node;

public class Component {
    private Node node;
    private int[] location = new int[2];
    Component(Node node){
        this.node = node;
    }
    Component(){}

    public Node getComponentNode(){
        return this.node;
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


}
