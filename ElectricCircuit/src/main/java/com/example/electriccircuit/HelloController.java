package com.example.electriccircuit;

import com.example.electriccircuit.Logic.BuilderMatrix;
import com.example.electriccircuit.Logic.draggable;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
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

    //start of title screen ids
    @FXML
    private SplitPane splitPane;

    @FXML
    private ImageView logotitle;

    @FXML
    private HBox logotitlehbox;
    //end of title screen ids

    //start of achievements screen ids
    @FXML
    private ImageView lockimage;
    @FXML
    private ImageView lockimage1;
    @FXML
    private ImageView lockimage2;
    @FXML
    private ImageView lockimage3;
    @FXML
    private ImageView lockimage4;
    @FXML
    private ImageView lockimage5;
    @FXML
    private ImageView lockimage6;
    @FXML
    private ImageView lockimage7;

    @FXML
    private VBox smallvbox;
    @FXML
    private VBox bigvbox;

    @FXML
    private HBox achievementshbox;
    @FXML
    private HBox achievementshbox1;
    @FXML
    private HBox achievementshbox2;
    @FXML
    private HBox achievementshbox3;
    @FXML
    private HBox achievementshbox4;
    @FXML
    private HBox achievementshbox5;
    @FXML
    private HBox achievementshbox6;
    @FXML
    private HBox achievementshbox7;

    @FXML
    private AnchorPane anchorpaneachievement;
    @FXML
    private ScrollPane scrollPaneachievement;

    // Set maximum and minimum font sizes
    private static final double MAX_FONT_SIZE = 24.0;
    private static final double MIN_FONT_SIZE = 12.0;

    @FXML
    private Label achievementlabel;

    @FXML
    private HBox labelhbox;

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
    public void Achievements(ActionEvent event) throws IOException{
        FXMLLoader loader1 = new FXMLLoader(getClass().getResource("Achievements screen.fxml"));
        Parent root = loader1.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setMaximized(false);
        stage.setMaximized(true);
        stage.show();

        HelloController controller1 = loader1.getController();

        AnchorPane anchorPane11 = controller1.getAnchorPaneAchievment();
        ScrollPane scrollPane11 = controller1.getScrollPaneachievement();

        ImageView lockImage11 = controller1.getLockImage();
        Label label11 = controller1.getAchievementLabel();
        HBox hbox11 = controller1.getLabelHBox();
        VBox smallvbox11 = controller1.getSmallVbox();
        VBox bigvbox11 = controller1.getBigVbox();

            anchorPane11.widthProperty().addListener((observable, oldValue, newValue) -> {
                // Calculate font size based on ScrollPane width
                double fontSize = newValue.doubleValue() / 20;

                // Ensure font size stays within the desired range
                if (fontSize > MAX_FONT_SIZE) {
                    fontSize = MAX_FONT_SIZE;
                } else if (fontSize < MIN_FONT_SIZE) {
                    fontSize = MIN_FONT_SIZE;
                }

                // Set the font size of the label
                label11.setStyle("-fx-font-size: " + fontSize + "px");
            });

            //make the big vbox be maximum height of scroll pane
            bigvbox11.setMaxHeight(scrollPane11.getMaxHeight());
            //make the small vbox be a maximum of half the big vbox
            smallvbox11.setMaxHeight(bigvbox11.getMaxHeight()/2);
            smallvbox11.setMinHeight(bigvbox11.getMaxHeight()/2);
            //make the hbox be a maximum of half the big vbox
            hbox11.setMaxHeight(bigvbox11.getMaxHeight()/2);
            hbox11.setMinHeight(bigvbox11.getMaxHeight()/2);

            // Bind the lock image to the size of the HBox
            lockImage11.setPreserveRatio(false); // Disable preserving aspect ratio
            //lockImage11.fitWidthProperty().bind(smallvbox11.widthProperty()); // Bind fitWidth to HBox width
            lockImage11.fitHeightProperty().bind(smallvbox11.heightProperty()); // Bind fitHeight to HBox height
    }


    /* getter methods for the achievements screen */
    public AnchorPane getAnchorPaneAchievment(){return this.anchorpaneachievement;}
    public ScrollPane getScrollPaneachievement(){return this.scrollPaneachievement;}

    public Label getAchievementLabel(){return this.achievementlabel;}
    public HBox getLabelHBox(){return this.achievementshbox;}
    public ImageView getLockImage(){return this.lockimage;}
    public VBox getSmallVbox(){return this.smallvbox;}
    public VBox getBigVbox(){return this.bigvbox;}


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
