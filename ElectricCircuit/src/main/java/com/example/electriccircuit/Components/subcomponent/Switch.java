package com.example.electriccircuit.Components.subcomponent;

import com.example.electriccircuit.Components.Wire;
import com.example.electriccircuit.Logic.BuilderMatrix;
import com.example.electriccircuit.Logic.Debug;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.io.InputStream;

public class Switch extends Wire {

    //If true, the switch is closed
    public boolean isClosed;

    public Switch(){
        super(true);
        InputStream in = getClass().getResourceAsStream("/com/example/electriccircuit/openSwitch.png");
        assert in != null;
        setImageTexture(new Image(in));
        setId(7);
        isClosed = false;
    }

    @Override
    public void interact(){
        getComponentNode().setOnMousePressed(e -> { // When mouse pressed on the object
            if(isClosed){
                BuilderMatrix.setBoxID(super.getLocationRow(), super.getLocationColumn(), 8, this);
                InputStream in = getClass().getResourceAsStream("/com/example/electriccircuit/switchClosed.png");
                assert in != null;
                setImageTexture(new Image(in));
                ((Rectangle)getComponentNode()).setFill(new ImagePattern(getImageTexture()));
                isClosed = false;
            } else {
                BuilderMatrix.setBoxID(super.getLocationRow(), super.getLocationColumn(), 7, this);
                InputStream in = getClass().getResourceAsStream("/com/example/electriccircuit/openSwitch.png");
                assert in != null;
                setImageTexture(new Image(in));
                ((Rectangle)getComponentNode()).setFill(new ImagePattern(getImageTexture()));
                isClosed = true;
            }
        });
    }
}
