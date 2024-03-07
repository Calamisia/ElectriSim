package com.example.electriccircuit;

import com.example.electriccircuit.Logic.BuilderMatrix;
import com.example.electriccircuit.Logic.draggable;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Control;
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
    private ScrollPane scrollPaneachievement;
    @FXML
    private HBox biggesthbox;
    @FXML
    private HBox achievementtitlehbox;
    @FXML
    private SplitPane globalSplitPane;

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

    // Set maximum and minimum font sizes
    private static final double MAX_FONT_SIZE = 48.0;
    private static final double MIN_FONT_SIZE = 20.0;

    @FXML
    private Label achievementlabel;
    @FXML
    private Label achievementlabel1;
    @FXML
    private Label achievementlabel2;
    @FXML
    private Label achievementlabel3;
    @FXML
    private Label achievementlabel4;
    @FXML
    private Label achievementlabel5;
    @FXML
    private Label achievementlabel6;
    @FXML
    private Label achievementlabel7;

    @FXML
    private Label descriptionlabel;
    @FXML
    private Label descriptionlabel1;
    @FXML
    private Label descriptionlabel2;
    @FXML
    private Label descriptionlabel3;
    @FXML
    private Label descriptionlabel4;
    @FXML
    private Label descriptionlabel5;
    @FXML
    private Label descriptionlabel6;
    @FXML
    private Label descriptionlabel7;

    @FXML
    private HBox labelhbox;
    //end of achievements screen ids

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

    //Method for achievement hbox
    public void achievelock(ScrollPane scroll, ImageView imageView, HBox hbox, Label label, Label descriptionlabel){
        /* start of changing font size and label size */
        scroll.widthProperty().addListener((observable, oldValue, newWidth) -> {
            // Calculate font size based on ScrollPane width
            double widthFontSize = newWidth.doubleValue() / 36;

            // Calculate font size based on ScrollPane height
            double heightFontSize = scroll.getHeight() / 18;

            // Choose the smaller font size to ensure it fits both width and height
            double fontSize = Math.min(widthFontSize, heightFontSize);

            // Ensure font size stays within the desired range
            if (fontSize > MAX_FONT_SIZE) {
                fontSize = MAX_FONT_SIZE;
            } else if (fontSize < MIN_FONT_SIZE) {
                fontSize = MIN_FONT_SIZE;
            }

            // Set the font size of the label
            label.setStyle("-fx-font-size: " + (int)fontSize + "px");

            // Adjust label's size to fit its content
            label.setPrefWidth(Control.USE_COMPUTED_SIZE);
            label.setPrefHeight(Control.USE_COMPUTED_SIZE);
            label.autosize();

        });

        scroll.heightProperty().addListener((observable, oldValue, newHeight) -> {
            // Calculate font size based on ScrollPane width
            double widthFontSize = scroll.getWidth() / 36;

            // Calculate font size based on ScrollPane height
            double heightFontSize = newHeight.doubleValue() / 18;

            // Choose the smaller font size to ensure it fits both width and height
            double fontSize = Math.min(widthFontSize, heightFontSize);

            // Ensure font size stays within the desired range
            if (fontSize > MAX_FONT_SIZE) {
                fontSize = MAX_FONT_SIZE;
            } else if (fontSize < MIN_FONT_SIZE) {
                fontSize = MIN_FONT_SIZE;
            }

            // Set the font size of the label
            label.setStyle("-fx-font-size: " + (int)fontSize + "px");

            // Adjust label's size to fit its content
            label.setPrefWidth(Control.USE_COMPUTED_SIZE);
            label.setPrefHeight(Control.USE_COMPUTED_SIZE);
            label.autosize();

        });

        // Bind the lock image to the size of the HBox
        imageView.setPreserveRatio(true); // Disable preserving aspect ratio
        imageView.fitWidthProperty().bind(hbox.widthProperty()); // Bind fitWidth to HBox width
        imageView.fitHeightProperty().bind(hbox.heightProperty()); // Bind fitHeight to HBox height
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

        //get the necessary variables for dynamic resizing
        ScrollPane scrollPane11 = controller1.getScrollPaneachievement();
        HBox bighbox11 = controller1.getBiggesthbox();
        HBox achievementtitlehbox11 = controller1.getAchievementtitlehbox();
        SplitPane splitPane11 = controller1.getSplitPane();

        bighbox11.setMaxWidth(splitPane11.getWidth()*2);
        scrollPane11.setMinHeight(splitPane11.getHeight()*((double) 7 /10));
        achievementtitlehbox11.setMinHeight(splitPane11.getHeight()*((double) 3 /10));

        scrollPane11.widthProperty().addListener((observable, oldValue, newWidth) -> {
            //set size of biggest hbox to twice the scrollpane
            bighbox11.setMaxWidth(newWidth.doubleValue()*2);
        });

        splitPane11.heightProperty().addListener((observable, oldValue, newHeight) -> {
            //scrollPane11.setMaxHeight(newHeight.doubleValue()*((double) 7 /10));
            scrollPane11.setMinHeight(newHeight.doubleValue()*((double) 7 /10));
            achievementtitlehbox11.setMinHeight(splitPane11.getHeight()*((double) 3 /10));
        });

        //Hbox1

        //get the necessary variables for dynamic resizing
        ImageView lockImage11 = controller1.getLockImage1();
        HBox hbox11 = controller1.getLabelHBox1();

        Label label11 = controller1.getAchievementLabel1();
        Label descriptionlabel11 = controller1.getDescriptionLabel1();

        achievelock(scrollPane11,lockImage11,hbox11,label11,descriptionlabel11);

        //Hbox2
        //get the necessary variables for dynamic resizing
        ImageView lockImage21 = controller1.getLockImage2();
        HBox hbox21 = controller1.getLabelHBox2();

        Label label21 = controller1.getAchievementLabel2();
        Label descriptionlabel21 = controller1.getDescriptionLabel2();

        achievelock(scrollPane11,lockImage21,hbox21,label21,descriptionlabel21);

        //Hbox3
        //get the necessary variables for dynamic resizing
        ImageView lockImage31 = controller1.getLockImage3();
        HBox hbox31 = controller1.getLabelHBox3();

        Label label31 = controller1.getAchievementLabel3();
        Label descriptionlabel31 = controller1.getDescriptionLabel3();

        achievelock(scrollPane11,lockImage31,hbox31,label31,descriptionlabel31);

        //Hbox4
        //get the necessary variables for dynamic resizing
        ImageView lockImage41 = controller1.getLockImage4();
        HBox hbox41 = controller1.getLabelHBox4();

        Label label41 = controller1.getAchievementLabel4();
        Label descriptionlabel41 = controller1.getDescriptionLabel4();

        achievelock(scrollPane11,lockImage41,hbox41,label41,descriptionlabel41);
        //Hbox5
        //get the necessary variables for dynamic resizing
        ImageView lockImage51 = controller1.getLockImage5();
        HBox hbox51 = controller1.getLabelHBox5();

        Label label51 = controller1.getAchievementLabel5();
        Label descriptionlabel51 = controller1.getDescriptionLabel5();

        achievelock(scrollPane11,lockImage51,hbox51,label51,descriptionlabel51);

        //Hbox6
        //get the necessary variables for dynamic resizing
        ImageView lockImage61 = controller1.getLockImage6();
        HBox hbox61 = controller1.getLabelHBox6();

        Label label61 = controller1.getAchievementLabel6();
        Label descriptionlabel61 = controller1.getDescriptionLabel6();

        achievelock(scrollPane11,lockImage61,hbox61,label61,descriptionlabel61);

        //Hbox7
        //get the necessary variables for dynamic resizing
        ImageView lockImage71 = controller1.getLockImage7();
        HBox hbox71 = controller1.getLabelHBox7();

        Label label71 = controller1.getAchievementLabel7();
        Label descriptionlabel71 = controller1.getDescriptionLabel7();

        achievelock(scrollPane11,lockImage71,hbox71,label71,descriptionlabel71);

        //Hbox8
        //get the necessary variables for dynamic resizing
        ImageView lockImage81 = controller1.getLockImage8();
        HBox hbox81 = controller1.getLabelHBox8();

        Label label81 = controller1.getAchievementLabel8();
        Label descriptionlabel81 = controller1.getDescriptionLabel8();

        achievelock(scrollPane11,lockImage81,hbox81,label81,descriptionlabel81);

    }

    /* getter methods for the achievements screen (global containers)*/
    public ScrollPane getScrollPaneachievement(){return this.scrollPaneachievement;}
    public HBox getBiggesthbox(){return this.biggesthbox;}
    public HBox getAchievementtitlehbox(){return this.achievementtitlehbox;}
    public SplitPane getSplitPane(){return this.globalSplitPane;}

    /* getter methods for the achievements screen (Hbox1)*/
    public Label getAchievementLabel1(){return this.achievementlabel;}
    public Label getDescriptionLabel1(){return this.descriptionlabel;}

    public HBox getLabelHBox1(){return this.achievementshbox;}
    public ImageView getLockImage1(){return this.lockimage;}

    /* getter methods for the achievements screen (Hbox2)*/
    public Label getAchievementLabel2(){return this.achievementlabel1;}
    public Label getDescriptionLabel2(){return this.descriptionlabel1;}

    public HBox getLabelHBox2(){return this.achievementshbox1;}
    public ImageView getLockImage2(){return this.lockimage1;}

    /* getter methods for the achievements screen (Hbox3)*/
    public Label getAchievementLabel3(){return this.achievementlabel2;}
    public Label getDescriptionLabel3(){return this.descriptionlabel2;}

    public HBox getLabelHBox3(){return this.achievementshbox2;}
    public ImageView getLockImage3(){return this.lockimage2;}

    /* getter methods for the achievements screen (Hbox4)*/
    public Label getAchievementLabel4(){return this.achievementlabel3;}
    public Label getDescriptionLabel4(){return this.descriptionlabel3;}

    public HBox getLabelHBox4(){return this.achievementshbox3;}
    public ImageView getLockImage4(){return this.lockimage3;}

    /* getter methods for the achievements screen (Hbox5)*/
    public Label getAchievementLabel5(){return this.achievementlabel4;}
    public Label getDescriptionLabel5(){return this.descriptionlabel4;}

    public HBox getLabelHBox5(){return this.achievementshbox4;}
    public ImageView getLockImage5(){return this.lockimage4;}

    /* getter methods for the achievements screen (Hbox6)*/
    public Label getAchievementLabel6(){return this.achievementlabel5;}
    public Label getDescriptionLabel6(){return this.descriptionlabel5;}

    public HBox getLabelHBox6(){return this.achievementshbox5;}
    public ImageView getLockImage6(){return this.lockimage5;}

    /* getter methods for the achievements screen (Hbox7)*/
    public Label getAchievementLabel7(){return this.achievementlabel6;}
    public Label getDescriptionLabel7(){return this.descriptionlabel6;}

    public HBox getLabelHBox7(){return this.achievementshbox6;}
    public ImageView getLockImage7(){return this.lockimage6;}

    /* getter methods for the achievements screen (Hbox8)*/
    public Label getAchievementLabel8(){return this.achievementlabel7;}
    public Label getDescriptionLabel8(){return this.descriptionlabel7;}

    public HBox getLabelHBox8(){return this.achievementshbox7;}
    public ImageView getLockImage8(){return this.lockimage7;}

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
