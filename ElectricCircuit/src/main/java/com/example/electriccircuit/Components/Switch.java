package com.example.electriccircuit.Components;

import com.example.electriccircuit.Logic.BuilderMatrix;
import javafx.scene.Node;
import javafx.scene.paint.Color;

public class Switch extends Wire{
    Node node;
    Switch(Node node){
        this.node = node;
    }
    @Override
    public int getId(){
        return 10;
    }
    @Override
    public Color getColor(){
        return Color.PINK;
    }

    public void interact(){
        node.setOnMousePressed(e -> { // When mouse pressed on the object
            BuilderMatrix.setBoxID(indexArray[0], indexArray[1], 0, dataGrid);
        });
    }
}
