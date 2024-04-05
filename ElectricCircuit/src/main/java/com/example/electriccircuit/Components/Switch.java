package com.example.electriccircuit.Components;

import com.example.electriccircuit.Logic.BuilderMatrix;
import com.example.electriccircuit.Logic.Debug;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.io.InputStream;

public class Switch extends Wire{
    @Override
    public int getId(){
        return 10;
    }

    //If true, the switch is closed
    public boolean isClosed;

    public Switch(){
        InputStream in = getClass().getResourceAsStream("/com/example/electriccircuit/switch.png");
        assert in != null;
        this.image = new Image(in);
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
