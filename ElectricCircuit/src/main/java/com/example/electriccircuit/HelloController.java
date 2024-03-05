package com.example.electriccircuit;

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
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;
    BuilderMatrix sandboxMatrix = new BuilderMatrix();

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

    }

    @FXML
    public void spawn(MouseEvent e) {
        System.out.println("pls");
        Circle circle = new Circle(20);
        anchorpane.getChildren().add(circle);
        smallanchorpane.getChildren().add(circle);
        circle.toFront();
        final boolean[] isEventEnabled = {true};

        smallanchorpane.setOnMouseMoved(mouseEvent -> {
            circle.setCenterX(mouseEvent.getX());
            circle.setCenterY(mouseEvent.getY());
        });

        anchorpane.setOnMouseMoved(mouseEvent -> {
            circle.setCenterX((smallanchorpane.getWidth() - anchorpane.getWidth()) / 2  + mouseEvent.getX());
            circle.setCenterY(mouseEvent.getY());
        });

        smallanchorpane.setOnMouseReleased(mouseEvent -> {
            if(isEventEnabled[0]) {
                System.out.println(mouseEvent.getX());
                Circle solidcircle = new Circle(20);
                solidcircle.setCenterY(Math.round(mouseEvent.getY() / (smallanchorpane.getHeight() / 20)) * (smallanchorpane.getHeight() / 20));
                solidcircle.setCenterX(Math.round(mouseEvent.getX() / (smallanchorpane.getWidth() / 20)) * (smallanchorpane.getWidth() / 20));
                smallanchorpane.getChildren().add(solidcircle);
                solidcircle.toFront();
                smallanchorpane.getChildren().remove(circle);
                anchorpane.getChildren().remove(circle);

                sandboxMatrix.setBoxID((int) Math.round(mouseEvent.getX() / (smallanchorpane.getWidth() / 20)), (int) Math.round(mouseEvent.getY() / (smallanchorpane.getHeight() / 20)), 1);
                System.out.println(sandboxMatrix.getBoxID((int) Math.round(mouseEvent.getX() / (smallanchorpane.getWidth() / 20)), (int) Math.round(mouseEvent.getY() / (smallanchorpane.getHeight() / 20))));

                isEventEnabled[0] = false;
            }
        });
    }
}
