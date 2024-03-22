package com.example.electriccircuit;

import com.example.electriccircuit.Components.*;
import com.example.electriccircuit.Logic.BuilderMatrix;
import com.example.electriccircuit.Logic.CalculatingGrid;
import com.example.electriccircuit.Logic.SaveFiles;
import com.example.electriccircuit.Logic.draggable;
import javafx.animation.FadeTransition;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static com.example.electriccircuit.Logic.SaveFiles.*;

public class HelloController implements Initializable {
    private HelloApplication main;
    private Stage stage;
    private Scene scene1,scene2,scene3,scene4;
    private Parent root1,root2,root3,root4;
    private FXMLLoader loader1,loader2,loader3,loader4;
    BuilderMatrix sandboxMatrix = new BuilderMatrix();

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
    private ImageView logotitle;

    @FXML
    private HBox logotitlehbox;

    @FXML
    private Button bt1;
    @FXML
    private Button bt2;
    @FXML
    private Button bt3;
    @FXML
    private Button bt4;
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
    private HBox labelhbox;
    //end of achievements screen ids

    //Start of level selection ids
    @FXML
    private SplitPane splitlevel16;
    @FXML
    private SplitPane splitlevel27;
    @FXML
    private SplitPane splitlevel38;
    @FXML
    private SplitPane splitlevel49;
    @FXML
    private SplitPane splitlevel510;

    @FXML
    private HBox bighboxlvl1;
    @FXML
    private HBox bighboxlvl2;
    @FXML
    private HBox bighboxlvl3;
    @FXML
    private HBox bighboxlvl4;
    @FXML
    private HBox bighboxlvl5;
    @FXML
    private HBox bighboxlvl6;
    @FXML
    private HBox bighboxlvl7;
    @FXML
    private HBox bighboxlvl8;
    @FXML
    private HBox bighboxlvl9;
    @FXML
    private HBox bighboxlvl10;

    @FXML
    private Label labellvl1;
    @FXML
    private Label labellvl2;
    @FXML
    private Label labellvl3;
    @FXML
    private Label labellvl4;
    @FXML
    private Label labellvl5;
    @FXML
    private Label labellvl6;
    @FXML
    private Label labellvl7;
    @FXML
    private Label labellvl8;
    @FXML
    private Label labellvl9;
    @FXML
    private Label labellvl10;

    @FXML
    private HBox smallhboxlvl1;

    @FXML
    private ImageView imageviewlvl1;
    @FXML
    private ImageView imageviewlvl2;
    @FXML
    private ImageView imageviewlvl3;
    @FXML
    private ImageView imageviewlvl4;
    @FXML
    private ImageView imageviewlvl5;
    @FXML
    private ImageView imageviewlvl6;
    @FXML
    private ImageView imageviewlvl7;
    @FXML
    private ImageView imageviewlvl8;
    @FXML
    private ImageView imageviewlvl9;
    @FXML
    private ImageView imageviewlvl10;


    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    draggable draggableMaker = new draggable();

    public void setMain(HelloApplication main){
        this.main = main;
    }

    //initialize variables

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

    //initialize variables
    public HelloController() {
    }

    //bind the properties of imageview when launching title screen
    public void titleinitialize(){
        // Replace current screen with the new one
        main.getMainContainer().getChildren().setAll(main.switchToTitle());

        titlemethod();
    }

    @FXML
    public void titleScreen(ActionEvent event) {
        // Replace current screen with the new one
        main.getMainContainer().getChildren().setAll(main.switchToTitle());

        titlemethod();
    }

    public void titlemethod(){
        HelloController controller = main.TitleController();
        controller.getLogotitle().fitWidthProperty().bind(controller.getTitleHbox().widthProperty()); // Bind fitWidth to HBox width
        controller.getLogotitle().fitHeightProperty().bind(controller.getTitleHbox().heightProperty()); // Bind fitHeight to HBox height

        buttontext(controller.getBt1()); //invoke the method for all buttons on title screen
        buttontext(controller.getBt2());
        buttontext(controller.getBt3());
        buttontext(controller.getBt4());
    }

    //Getter methods for titlescreen
    public ImageView getLogotitle(){
        return logotitle;
    }
    public HBox getTitleHbox(){return logotitlehbox;}
    public Button getBt1(){return  bt1;}
    public Button getBt2(){return  bt2;}
    public Button getBt3(){return  bt3;}
    public Button getBt4(){return  bt4;}

    //Method for button resizing
    public void buttontext(Button button){
        button.setStyle("-fx-font-size: " + button.getWidth()/10 + "px;");
        button.widthProperty().addListener((obs, oldVal, newVal) -> {
            double width = newVal.doubleValue();
            double height = button.getHeight()*5;

            // Adjust the font size based on the aspect ratio
            double fontSize = Math.min(width, height) / 10; // Adjust the factor as needed
            button.setStyle("-fx-font-size: " + fontSize + "px;");
        });

        button.heightProperty().addListener((obs, oldVal, newVal) -> {
            double height = newVal.doubleValue()*5;
            double width = button.getWidth();

            // Adjust the font size based on the aspect ratio
            double fontSize = Math.min(width, height) / 10; // Adjust the factor as needed
            button.setStyle("-fx-font-size: " + fontSize + "px;");
        });
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
    public void Achievements(ActionEvent event){

        main.getMainContainer().getChildren().setAll(main.switchToAchievements());

        HelloController controller1 = main.AchievementsController();



      /*  //get the necessary variables for dynamic resizing
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

        //container to base resizing on
        HBox hbox11 = this.achievementshbox;

        //Hbox1

        //get the necessary variables for dynamic resizing
        ImageView lockImage11 = controller1.getLockImage1();
        Label label11 = controller1.getAchievementLabel1();

        achievelock(scrollPane11,lockimage,hbox11,label11);

        //Hbox2
        //get the necessary variables for dynamic resizing
        ImageView lockImage21 = controller1.getLockImage2();
        Label label21 = controller1.getAchievementLabel2();

        achievelock(scrollPane11,lockimage1,hbox11,label21);

        //Hbox3
        //get the necessary variables for dynamic resizing
        ImageView lockImage31 = controller1.getLockImage3();
        Label label31 = controller1.getAchievementLabel3();

        achievelock(scrollPane11,lockimage2,hbox11,label31);

        //Hbox4
        //get the necessary variables for dynamic resizing
        ImageView lockImage41 = controller1.getLockImage4();
        Label label41 = controller1.getAchievementLabel4();

        achievelock(scrollPane11,lockImage41,hbox11,label41);
        //Hbox5
        //get the necessary variables for dynamic resizing
        ImageView lockImage51 = controller1.getLockImage5();
        Label label51 = controller1.getAchievementLabel5();

        achievelock(scrollPane11,lockImage51,hbox11,label51);

        //Hbox6
        //get the necessary variables for dynamic resizing
        ImageView lockImage61 = controller1.getLockImage6();
        Label label61 = controller1.getAchievementLabel6();

        achievelock(scrollPane11,lockImage61,hbox11,label61);

        //Hbox7
        //get the necessary variables for dynamic resizing
        ImageView lockImage71 = controller1.getLockImage7();
        Label label71 = controller1.getAchievementLabel7();

        achievelock(scrollPane11,lockImage71,hbox11,label71);

        //Hbox8
        //get the necessary variables for dynamic resizing
        ImageView lockImage81 = controller1.getLockImage8();
        Label label81 = controller1.getAchievementLabel8();

        achievelock(scrollPane11,lockImage81,hbox11,label81);
*/
    }

    /* getter methods for the achievements screen (global containers)*/
    public ScrollPane getScrollPaneachievement(){return this.scrollPaneachievement;}
    public HBox getBiggesthbox(){return this.biggesthbox;}
    public HBox getAchievementtitlehbox(){return this.achievementtitlehbox;}
    public SplitPane getSplitPane(){return this.globalSplitPane;}

    /* getter methods for the achievements screen (Hbox1)*/
    public Label getAchievementLabel1(){return this.achievementlabel;}

    public ImageView getLockImage1(){return this.lockimage;}

    /* getter methods for the achievements screen (Hbox2)*/
    public Label getAchievementLabel2(){return this.achievementlabel1;}
    public ImageView getLockImage2(){return this.lockimage1;}

    /* getter methods for the achievements screen (Hbox3)*/
    public Label getAchievementLabel3(){return this.achievementlabel2;}
    public ImageView getLockImage3(){return this.lockimage2;}

    /* getter methods for the achievements screen (Hbox4)*/
    public Label getAchievementLabel4(){return this.achievementlabel3;}
    public ImageView getLockImage4(){return this.lockimage3;}

    /* getter methods for the achievements screen (Hbox5)*/
    public Label getAchievementLabel5(){return this.achievementlabel4;}
    public ImageView getLockImage5(){return this.lockimage4;}

    /* getter methods for the achievements screen (Hbox6)*/
    public Label getAchievementLabel6(){return this.achievementlabel5;}
    public ImageView getLockImage6(){return this.lockimage5;}

    /* getter methods for the achievements screen (Hbox7)*/
    public Label getAchievementLabel7(){return this.achievementlabel6;}
    public ImageView getLockImage7(){return this.lockimage6;}

    /* getter methods for the achievements screen (Hbox8)*/
    public Label getAchievementLabel8(){return this.achievementlabel7;}
    public ImageView getLockImage8(){return this.lockimage7;}

    //Method for achievement hbox
    public void levelimagelabel(HBox bighbox, ImageView imageView, HBox hbox, Label label){
        /* start of changing font size and label size */
        bighbox.widthProperty().addListener((observable, oldValue, newWidth) -> {
            // Calculate font size based on ScrollPane width
            double widthFontSize = newWidth.doubleValue() / 36;

            // Calculate font size based on ScrollPane height
            double heightFontSize = bighbox.getHeight() / 18;

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

        bighbox.heightProperty().addListener((observable, oldValue, newHeight) -> {
            // Calculate font size based on ScrollPane width
            double widthFontSize = bighbox.getWidth() / 36;

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

    /* Switch to level select screen and initialize*/
    @FXML
    public void LevelSelect(ActionEvent event) {
        // Replace current screen with the new one
        main.getMainContainer().getChildren().setAll(main.switchToLevelSelect());

      /*  //get the necessary variables for dynamic resizing
        ScrollPane scrollPane11 = controller1.getScrollPaneachievement();
        HBox bighbox11 = controller1.getBiggesthbox();
        HBox achievementtitlehbox11 = controller1.getAchievementtitlehbox();
        SplitPane splitPane11 = controller1.getSplitPane();

        scrollPane11.widthProperty().addListener((observable, oldValue, newWidth) -> {
            //set size of biggest hbox to twice the scrollpane
            bighbox11.setMaxWidth(newWidth.doubleValue() * 2);
        });

        splitPane11.heightProperty().addListener((observable, oldValue, newHeight) -> {
            //scrollPane11.setMaxHeight(newHeight.doubleValue()*((double) 7 /10));
            scrollPane11.setMinHeight(newHeight.doubleValue() * ((double) 7 / 10));
            achievementtitlehbox11.setMinHeight(splitPane11.getHeight() * ((double) 3 / 10));
        }); */
    }

    /* Switch to main screen and initialize*/
    @FXML
    private void MainScreen(ActionEvent event) {
        // Fade in transition
       /* FadeTransition fadeTransition = new FadeTransition(Duration.seconds(0.5), main.switchToMainScreen());
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);
        fadeTransition.play(); */
        //Replace current screen with the new one
        main.getMainContainer().getChildren().setAll(main.switchToMainScreen());
       }

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
                draggableMaker.dragging(solidcircle);
                double Hspacing = (smallanchorpane.getHeight() / 20);
                double Wspacing = (smallanchorpane.getWidth() / 35);

                int Hindex = (int)Math.round((mouseEvent.getY() - Hspacing / 2) / (Hspacing));
                int Windex = (int)Math.round((mouseEvent.getX() - Wspacing / 2) / (Wspacing));

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
                        solidcircle.setCenterY(Hindex * (Hspacing) + Hspacing / 2);
                        solidcircle.setCenterX(Windex * (Wspacing) + Wspacing / 2);
                        smallanchorpane.getChildren().add(solidcircle);
                        solidcircle.toFront();

                        //Sandbox Matrix creation
                        BuilderMatrix.setBoxID(Windex, Hindex, iD);
                    }
                }
                if(!mouseEvent.isShiftDown()){
                    smallanchorpane.getChildren().remove(circle);
                    anchorpane.getChildren().remove(circle);

                    isEventEnabled[0] = false;
                }
            }
        });
    }


}

