package com.example.electriccircuit.Logic;

import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import com.example.electriccircuit.HelloController;

import static com.example.electriccircuit.HelloController.draggableMaker;

/***
 * Any object that passes through a draggable object instance can be dragged
 */
public class draggable {
    // Mouse position X and Y
    private double mouseAnchorX;
    private double mouseAnchorY;
    final boolean[] isEventEnabled = {false};

    public void dragging(Node node, int iD, AnchorPane smallanchorpane, GridPane dataGrid, int[] indexArray) {
        node.setOnMousePressed(e -> { // When mouse pressed on the object
            mouseAnchorX = e.getSceneX() - node.getLayoutX();
            mouseAnchorY = e.getSceneY() - node.getLayoutY();
            System.out.println(iD);
            isEventEnabled[0] = true;
            BuilderMatrix.setBoxID(indexArray[0], indexArray[1], 0);
        });

        node.setOnMouseDragged(e -> { // On mouse drag with object held, make object follow mouse
            node.setLayoutX(e.getSceneX() - mouseAnchorX);
            node.setLayoutY(e.getSceneY() - mouseAnchorY);
        });

        smallanchorpane.setOnMouseReleased(e -> {
            smallanchorpane.getChildren().remove(node);
            if (isEventEnabled[0]) {
                double Hspacing = (smallanchorpane.getHeight() / 20);
                double Wspacing = (smallanchorpane.getWidth() / 35);

                Circle solidcircle = new Circle(20);

                solidcircle.setFill(Color.BLACK);
                System.out.println(Hspacing + " " + Wspacing);

                int Hindex = (int) Math.round((e.getY() - Hspacing / 2) / (Hspacing));
                int Windex = (int) Math.round((e.getX() - Wspacing / 2) / (Wspacing));
                indexArray[0] = Hindex;
                indexArray[1] = Windex;
                draggableMaker.dragging(solidcircle, iD, smallanchorpane, dataGrid, indexArray);
                System.out.println(Hindex + " " + Windex);

                /* more fluid input */
                if (Hindex == 20) {
                    Hindex = 19;
                }
                if (Windex == -1) {
                    {
                        Windex = 0;
                    }
                }
                if (Windex == 35) {
                    Windex = 34;
                }

                if (Hindex < 20 && Hindex >= 0) { //if within bound of small anchor
                    if (Windex < 35 && Windex >= 0) {
                        //snaps to grid
                        solidcircle.setCenterY(Hindex * (Hspacing) + Hspacing / 2);
                        solidcircle.setCenterX(Windex * (Wspacing) + Wspacing / 2);

                        smallanchorpane.getChildren().add(solidcircle);
                        solidcircle.toFront();
                        //Sandbox Matrix creation
                        BuilderMatrix.setBoxID(Windex, Hindex, iD);
                    }
                }
                isEventEnabled[0] = false;
            }
        });
    }
}