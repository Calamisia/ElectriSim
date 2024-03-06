package com.example.electriccircuit;

import com.example.electriccircuit.Components.*;
import com.example.electriccircuit.Logic.BuilderMatrix;
import com.example.electriccircuit.Logic.draggable;
import com.example.electriccircuit.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static com.example.electriccircuit.Logic.SaveFiles.*;

public class HelloController implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;
    BuilderMatrix sandboxMatrix = new BuilderMatrix();

    //allows scene switching from scene 2 to 1
    public void switchtoScene1(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("scene1.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /* Switch to achievements screen */
    @FXML
    private void Achievements(ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("Achievements screen.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setMaximized(false);
        stage.setMaximized(true);
        stage.show();;}

    public void switchtoScene2(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("scene2.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private AnchorPane anchorpane;

    @FXML
    private AnchorPane smallanchorpane;

    @FXML
    private Label welcomeText;

    @FXML
    private Circle Circle2;

    @FXML
    private ImageView imgv;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    draggable draggableMaker = new draggable();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //draggableMaker.dragging(Circle2);
        //FIGURE THIS OUT SOON
    }

    @FXML
    private HBox wire;
    @FXML
    private HBox powerSupply;
    @FXML
    private HBox resistor;
    @FXML
    private HBox capacitor;
    @FXML
    private HBox merger;
    @FXML
    private HBox splitter;

    @FXML
    public void spawn(MouseEvent e) {
        int iD;
        Color color;
        if(((HBox) e.getSource()).getId().equals(wire.getId())){
            Wire carry = new Wire();
            iD = carry.getId();
            color = carry.getColor();
            System.out.println("Wire selected");
        } else if(((HBox) e.getSource()).getId().equals(powerSupply.getId())){
            PowerSupply carry = new PowerSupply();
            iD = carry.getId();
            color = carry.getColor();
        } else if(((HBox) e.getSource()).getId().equals(resistor.getId())){
            Resistors carry = new Resistors();
            iD = carry.getId();
            color = carry.getColor();
        } else if(((HBox) e.getSource()).getId().equals(capacitor.getId())){
            Capacitors carry = new Capacitors();
            iD = carry.getId();
            color = carry.getColor();
        } else if(((HBox) e.getSource()).getId().equals(merger.getId())){
            Merger carry = new Merger();
            iD = carry.getId();
            color = carry.getColor();
        } else if(((HBox) e.getSource()).getId().equals(splitter.getId())){
            Splitter carry = new Splitter();
            iD = carry.getId();
            color = carry.getColor();
        }else {
            System.out.println("HELP");
            color = null;
            iD = -1;
        }

        //Creates the object
        Circle circle = new Circle(20);
        circle.setFill(color);
        circle.setOpacity(0);
        anchorpane.getChildren().add(circle);
        smallanchorpane.getChildren().add(circle);


        final boolean[] isEventEnabled = {true};

        /*On mouse movement, calibrates to small and large anchor panes */
        smallanchorpane.setOnMouseMoved(mouseEvent -> {
            circle.setCenterX(mouseEvent.getX());
            circle.setCenterY(mouseEvent.getY());
            if (!circle.isFocused())
                circle.setOpacity(100);
        });

        anchorpane.setOnMouseMoved(mouseEvent -> {
            circle.setCenterX((smallanchorpane.getWidth() - anchorpane.getWidth()) / 2  + mouseEvent.getX());
            circle.setCenterY(mouseEvent.getY());
            if (!circle.isFocused())
                circle.setOpacity(100);
        });

        smallanchorpane.setOnMouseReleased(mouseEvent -> {
            if(isEventEnabled[0]) { //makes sure you can only release once
                //Creates the solid circle
                Circle solidcircle = new Circle(20);
                solidcircle.setFill(color);

                double Hspacing = (smallanchorpane.getHeight() / 20);
                double Wspacing = (smallanchorpane.getWidth() / 35);
                int Hindex = (int)Math.round(mouseEvent.getY() / (Hspacing)); //13
                int Windex = (int)Math.round(mouseEvent.getX() / (Wspacing)); //7

                /* more fluid input */
                if(Hindex == 20){
                    Hindex = 19;
                } if(Windex == -1){{
                    Windex = 0;
                }} if(Windex == 35){
                    Windex = 34;
                }

                if(Hindex < 20 && Hindex >= 0) { //if within bound of small anchor
                    if (Windex < 35 && Windex >= 0) {
                        //snaps to grid
                        solidcircle.setCenterY(Hindex * (Hspacing) + (Hspacing / 2));
                        solidcircle.setCenterX(Windex * (Wspacing) + (Wspacing / 2));
                        smallanchorpane.getChildren().add(solidcircle);
                        solidcircle.toFront();

                        //Sandbox Matrix creation
                        sandboxMatrix.setBoxID(Windex, Hindex, iD);
                    }
                }
                //removes carrying component
                smallanchorpane.getChildren().remove(circle);
                anchorpane.getChildren().remove(circle);

                isEventEnabled[0] = false;
                //saves game
                saveGame();//DELETE THIS

            }
        });
    }
}

