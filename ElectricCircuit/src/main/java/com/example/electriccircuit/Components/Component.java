package com.example.electriccircuit.Components;

import com.example.electriccircuit.HelloController;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class Component {
    Node node;
    Image image;
    int Id;
    private int[] location = new int[2];
    public static Component[][] componentArray = new Component[35][20];

    Component(Node node) {
        this.node = node;
    }

    Component() {
    }

    public Node getComponentNode() {
        return this.node;
    }

    public void setComponentNode(Node node) {
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

    public Image getImageTexture() {
        return this.image;
    }

    public void interact() {
    }


}
