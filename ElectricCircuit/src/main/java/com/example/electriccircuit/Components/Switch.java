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
        return 1;
    }

    //If true, the switch is closed
    public boolean isClosed;

    public Switch(){
        InputStream in = getClass().getResourceAsStream("/com/example/electriccircuit/openSwitch.png");
        assert in != null;
        this.image = new Image(in);
        this.Id = 10;
        isClosed = true;
    }

    @Override
    public void interact(){
        node.setOnMousePressed(e -> { // When mouse pressed on the object
            if(isClosed){
                BuilderMatrix.setBoxID(super.getLocationRow(), super.getLocationColumn(), 0);
                InputStream in = getClass().getResourceAsStream("/com/example/electriccircuit/openSwitch.png");
                assert in != null;
                this.image = new Image(in);
                Debug.Info(super.getLocationRow() + " " + super.getLocationColumn());
                BuilderMatrix.setBoxID(super.getLocationRow(), super.getLocationColumn(), 11);
                isClosed = false;
            } else {
                BuilderMatrix.setBoxID(super.getLocationRow(), super.getLocationColumn(), 1);
                InputStream in = getClass().getResourceAsStream("/com/example/electriccircuit/switchClosed.png");
                assert in != null;
                this.image = new Image(in);
                BuilderMatrix.setBoxID(super.getLocationRow(), super.getLocationColumn(), 10);
                isClosed = true;
            }
        });
    }
}
