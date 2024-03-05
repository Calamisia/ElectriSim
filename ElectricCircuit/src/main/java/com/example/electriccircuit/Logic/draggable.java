package com.example.electriccircuit.Logic;

import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;

/***
 * Any object that passes through a draggable object instance can be dragged
 */
public class draggable {
    private double mouseAnchorX;
    private double mouseAnchorY;

    public void dragging(Node node){
        node.setOnMousePressed(e -> { //When mouse pressed on the object
            mouseAnchorX = e.getX();
            mouseAnchorY = e.getY();
        });

        node.setOnMouseDragged(e -> { // On mouse drag with object held, make object follow mouse
                node.setLayoutX(e.getSceneX() - mouseAnchorX);
                node.setLayoutY(e.getSceneY() - mouseAnchorY);
        });
    }
}
