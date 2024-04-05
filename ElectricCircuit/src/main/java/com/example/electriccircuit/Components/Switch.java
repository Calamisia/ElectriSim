package com.example.electriccircuit.Components;

import com.example.electriccircuit.Logic.BuilderMatrix;
import com.example.electriccircuit.Logic.Debug;
import javafx.scene.Node;
import javafx.scene.paint.Color;

public class Switch extends Wire{
    @Override
    public int getId(){
        return 10;
    }
    @Override
    public Color getColor(){
        return Color.PINK;
    }

    //If true, the switch is closed
    public boolean isClosed;

    public Switch(){
        this.color = Color.PINK;
        this.Id = 10;
        isClosed = true;
    }

    @Override
    public void interact(){
        node.setOnMousePressed(e -> { // When mouse pressed on the object
            if(isClosed){
                Debug.Info(super.getLocationRow() + " " + super.getLocationColumn());
                BuilderMatrix.setBoxID(super.getLocationRow(), super.getLocationColumn(), 11);
                isClosed = false;
            } else {
                BuilderMatrix.setBoxID(super.getLocationRow(), super.getLocationColumn(), 10);
                isClosed = true;
            }
        });
    }
}
