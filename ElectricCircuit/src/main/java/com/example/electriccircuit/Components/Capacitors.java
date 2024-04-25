package com.example.electriccircuit.Components;

import javafx.scene.image.Image;

import java.io.InputStream;

public class Capacitors extends Component{

    public Capacitors(){
        InputStream in = getClass().getResourceAsStream("/com/example/electriccircuit/capacitor.png");
        assert in != null;
        this.image[0] = new Image(in);
        this.Id = 4;
    }
}