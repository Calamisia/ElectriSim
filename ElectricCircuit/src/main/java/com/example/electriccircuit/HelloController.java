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
import javafx.scene.control.SplitPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
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
    private SplitPane splitPane;

    @FXML
    private ImageView logotitle;

    @FXML
    private HBox logotitlehbox;

    @FXML
    private ImageView lockimage;

    @FXML
    private VBox achievementsvbox;

    @FXML
    private HBox wideachievementshbox;

    @FXML
    private HBox achievementshbox;

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

    //initialize variables
    public HelloController() {
    }

    //bind the properties of imageview when launching title screen
    @FXML
    public void titleinitialize(){
        //set the divider position to the middle of the screen
        splitPane.setDividerPositions(0.5);

        //Set max size of hbox to half of splitpane
        logotitlehbox.setMaxWidth(splitPane.getWidth()/2);

        logotitle.setPreserveRatio(true); // Disable preserving aspect ratio
        logotitle.fitWidthProperty().bind(logotitlehbox.widthProperty()); // Bind fitWidth to HBox width
        logotitle.fitHeightProperty().bind(logotitlehbox.heightProperty()); // Bind fitHeight to HBox height

    }

    /* Switch to achievements screen and initialize*/
    @FXML
    private void Achievements(ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("Achievements screen.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setMaximized(false);
        stage.setMaximized(true);

        lockimage.setPreserveRatio(true); // Disable preserving aspect ratio
        lockimage.fitWidthProperty().bind(achievementshbox.widthProperty()); // Bind fitWidth to HBox width
        lockimage.fitHeightProperty().bind(achievementshbox.heightProperty()); // Bind fitHeight to HBox height

        stage.show();;}

    /* Switch to main screen and initialize*/
    @FXML
    private void MainScreen(ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setMaximized(false);
        stage.setMaximized(true);
        stage.show();;}

    @FXML
    public void spawn(MouseEvent e) {
        //Creates the object
        Circle circle = new Circle(20);
        anchorpane.getChildren().add(circle);
        smallanchorpane.getChildren().add(circle);
        circle.toFront();

        final boolean[] isEventEnabled = {true};

        /*On mouse movement, calibrates to small and large anchor panes */
        smallanchorpane.setOnMouseMoved(mouseEvent -> {
            circle.setCenterX(mouseEvent.getX());
            circle.setCenterY(mouseEvent.getY());
        });

        anchorpane.setOnMouseMoved(mouseEvent -> {
            circle.setCenterX((smallanchorpane.getWidth() - anchorpane.getWidth()) / 2  + mouseEvent.getX());
            circle.setCenterY(mouseEvent.getY());
        });

        smallanchorpane.setOnMouseReleased(mouseEvent -> {
            if(isEventEnabled[0]) { //makes sure you can only release once
                //Creates the solid circle
                Circle solidcircle = new Circle(20);

                double Hspacing = (smallanchorpane.getHeight() / 20);
                double Wspacing = (smallanchorpane.getWidth() / 20);
                int Hindex = (int)Math.round(mouseEvent.getY() / (Hspacing)); //13
                int Windex = (int)Math.round(mouseEvent.getX() / (Wspacing)); //7

                /* more fluid input */
                if(Hindex == 20){
                    Hindex = 19;
                } if(Windex == -1){{
                    Windex = 0;
                }} if(Windex == 20){
                    Windex = 19;
                }

                if(Hindex < 20 && Hindex >= 0) { //if within bound of small anchor
                    if (Windex < 20 && Windex >= 0) {
                        //snaps to grid
                        solidcircle.setCenterY(Hindex * (Hspacing) + (Hspacing / 2));
                        solidcircle.setCenterX(Windex * (Wspacing) + (Wspacing / 2));
                        smallanchorpane.getChildren().add(solidcircle);
                        solidcircle.toFront();

                        //Sandbox Matrix creation
                        sandboxMatrix.setBoxID(Windex, Hindex, 1);
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
