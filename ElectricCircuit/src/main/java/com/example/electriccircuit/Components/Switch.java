package com.example.electriccircuit.Components;

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
}
