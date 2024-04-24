package com.example.electriccircuit.Components;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.io.InputStream;

public class Resistors extends Component{

    public Resistors(){
        InputStream in = getClass().getResourceAsStream("/com/example/electriccircuit/resistor.png");
        assert in != null;
        this.image[0] = new Image(in);
        this.Id = 3;
    }
}
