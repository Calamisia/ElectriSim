package com.example.electriccircuit.Logic;

import javafx.scene.Node;
import javafx.scene.shape.Circle;

public class draggable {
    private double mouseAnchorX;
    private double mouseAnchorY;

    public void dragging(Node node){
        node.setOnMousePressed(e -> {
            mouseAnchorX = e.getX();
            mouseAnchorY = e.getY();
        });

        node.setOnMouseDragged(e -> {
            node.setLayoutX(e.getSceneX() - mouseAnchorX);
            node.setLayoutY(e.getSceneY() - mouseAnchorY);
        });
    }


}
