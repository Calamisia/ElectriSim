package com.example.electriccircuit;

import com.example.electriccircuit.Components.*;
import com.example.electriccircuit.Logic.BuilderMatrix;
import com.example.electriccircuit.Logic.CalculatingGrid;
import com.example.electriccircuit.Logic.SaveFiles;
import com.example.electriccircuit.Logic.draggable;
import com.example.electriccircuit.Logic.*;
import javafx.animation.FadeTransition;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.layout.HBox;
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
    private Unlocks unlocked;
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
    private Label achievementdlabel;
    @FXML
    private Label achievementdlabel1;
    @FXML
    private Label achievementdlabel2;
    @FXML
    private Label achievementdlabel3;
    @FXML
    private Label achievementdlabel4;
    @FXML
    private Label achievementdlabel5;
    @FXML
    private Label achievementdlabel6;
    @FXML
    private Label achievementdlabel7;
    //end of achievements screen ids

    //Start of level selection ids
    @FXML
    private HBox leveltitlehbox;
    @FXML
    private HBox levelselecthbox;
    @FXML
    private HBox smallhboxlvl1;

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

    //End of level selection ids

    public static draggable draggableMaker = new draggable();

    public void setMain(HelloApplication main){
        this.main = main;
    }

    public void setUnlocks(Unlocks unlocked) { this.unlocked = unlocked; }

    //initialize variables

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

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
    private HBox wireSwitch;


    @FXML
    public GridPane dataGrid;

    //initialize variables
    public HelloController() {}

    //bind the properties of imageview when launching title screen
    public void titleinitialize(){
        // Replace current screen with the new one
        main.getMainContainer().getChildren().setAll(main.switchToTitle());

        titlemethod();
    }

    @FXML
    public void titleScreen(ActionEvent event) {
        // Fade in transition
        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(0.5), main.switchToMainScreen());
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);
        fadeTransition.play();
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
    public void achievelock(ImageView imageView, HBox hbox, Label label, Label dlabel){

        /* start of changing font size and label size */
        main.getMainContainer().widthProperty().addListener((observable, oldValue, newWidth) -> {
            double widthFontSize = newWidth.doubleValue() / 4;
            double heightFontSize = (main.getMainContainer().getHeight() / 4)*2.35;

            // Choose the smaller font size to ensure it fits both width and height
            double fontSize = Math.min(widthFontSize, heightFontSize) / 10;

            // Set the font size of the label
            if (fontSize != 0) {
                label.setStyle("-fx-font-size: " + fontSize + "px");
                dlabel.setStyle("-fx-font-size: " + fontSize + "px");
            }
        });

        main.getMainContainer().heightProperty().addListener((observable, oldValue, newHeight) -> {
               double widthFontSize = main.getMainContainer().getWidth() / 4;
               double heightFontSize = (newHeight.doubleValue() / 4)*2.35;

               // Choose the smaller font size to ensure it fits both width and height
               double fontSize = Math.min(widthFontSize, heightFontSize) / 10;

               // Set the font size of the label
               if (fontSize != 0) {
                    label.setStyle("-fx-font-size: " + fontSize + "px");
                    dlabel.setStyle("-fx-font-size: " + fontSize + "px");
               }
        });

        // Bind the lock image to the size of the HBox
        imageView.setPreserveRatio(true); // Disable preserving aspect ratio
        imageView.fitWidthProperty().bind(hbox.widthProperty()); // Bind fitWidth to HBox width
        imageView.fitHeightProperty().bind(hbox.heightProperty()); // Bind fitHeight to HBox height
    }

    /* Switch to achievements screen and initialize*/
    @FXML
    public void Achievements(ActionEvent event){

        // Fade in transition
        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(0.5), main.switchToAchievements());
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);
        fadeTransition.play();
        main.getMainContainer().getChildren().setAll(main.switchToAchievements());

        HelloController controller1 = main.AchievementsController();

        controller1.getAchievementtitlehbox().prefHeightProperty().bind(main.getMainContainer().heightProperty().multiply(0.25));

        //Bind the bighbox to twice the window size
        controller1.getBiggesthbox().prefWidthProperty().bind(main.getMainContainer().widthProperty().multiply(2));

        // Bind scroll pane height to maintain the desired ratio
        controller1.getScrollPaneachievement().prefHeightProperty().bind(main.getMainContainer().heightProperty().multiply(0.75));

        //container to base resizing on
        HBox hbox11 = controller1.getBaseHbox();

        //Hbox1

        Image image = new Image("lvl1.jpg");
        //Check if the achievement is unlocked
        if (unlocked.isAchievementUnlocked(1)) controller1.getLockImage1().setImage(image);

        //Method for binding both, above this one
        achievelock(controller1.getLockImage1(), hbox11, controller1.getAchievementLabel1(), controller1.getAchievementDLabel());

        //Hbox2

        image = new Image("lvl2.jpg");
        //Check if the achievement is unlocked
        if (unlocked.isAchievementUnlocked(1)) controller1.getLockImage1().setImage(image);

        //Method for binding both, above this one
        achievelock(controller1.getLockImage2(), hbox11, controller1.getAchievementLabel2(), controller1.getAchievementDLabel1());

        //Hbox3

        image = new Image("lvl3.jpg");
        //Check if the achievement is unlocked
        if (unlocked.isAchievementUnlocked(1)) controller1.getLockImage1().setImage(image);

        //Method for binding both, above this one
        achievelock(controller1.getLockImage3(), hbox11, controller1.getAchievementLabel3(), controller1.getAchievementDLabel2());

        //Hbox4

        image = new Image("lvl4.jpg");
        //Check if the achievement is unlocked
        if (unlocked.isAchievementUnlocked(1)) controller1.getLockImage1().setImage(image);

        //Method for binding both, above this one
        achievelock(controller1.getLockImage4(), hbox11, controller1.getAchievementLabel4(), controller1.getAchievementDLabel3());

        //Hbox5

        image = new Image("lvl5.jpg");
        //Check if the achievement is unlocked
        if (unlocked.isAchievementUnlocked(1)) controller1.getLockImage1().setImage(image);

        //Method for binding both, above this one
        achievelock(controller1.getLockImage5(), hbox11, controller1.getAchievementLabel5(), controller1.getAchievementDLabel4());

        //Hbox6

        image = new Image("lvl6.jpg");
        //Check if the achievement is unlocked
        if (unlocked.isAchievementUnlocked(1)) controller1.getLockImage1().setImage(image);

        //Method for binding both, above this one
        achievelock(controller1.getLockImage6(), hbox11, controller1.getAchievementLabel6(), controller1.getAchievementDLabel5());

        //Hbox7

        image = new Image("lvl7.jpg");
        //Check if the achievement is unlocked
        if (unlocked.isAchievementUnlocked(1)) controller1.getLockImage1().setImage(image);

        //Method for binding both, above this one
        achievelock(controller1.getLockImage7(), hbox11, controller1.getAchievementLabel7(), controller1.getAchievementDLabel6());

        //Hbox8

        image = new Image("lvl8.jpg");
        //Check if the achievement is unlocked
        if (unlocked.isAchievementUnlocked(1)) controller1.getLockImage1().setImage(image);

        //Method for binding both, above this one
        achievelock(controller1.getLockImage8(), hbox11, controller1.getAchievementLabel8(), controller1.getAchievementDLabel7());
    }

    /* getter methods for the achievements screen (global containers)*/
    public ScrollPane getScrollPaneachievement(){return this.scrollPaneachievement;}
    public HBox getBiggesthbox(){return this.biggesthbox;}
    public HBox getAchievementtitlehbox(){return this.achievementtitlehbox;}
    public HBox getBaseHbox(){return this.achievementshbox;}

    /* getter methods for the achievements screen (Hbox1)*/
    public Label getAchievementLabel1(){return this.achievementlabel;}
    public ImageView getLockImage1(){return this.lockimage;}
    public Label getAchievementDLabel(){return this.achievementdlabel;}

    /* getter methods for the achievements screen (Hbox2)*/
    public Label getAchievementLabel2(){return this.achievementlabel1;}
    public ImageView getLockImage2(){return this.lockimage1;}
    public Label getAchievementDLabel1(){return this.achievementdlabel1;}

    /* getter methods for the achievements screen (Hbox3)*/
    public Label getAchievementLabel3(){return this.achievementlabel2;}
    public ImageView getLockImage3(){return this.lockimage2;}
    public Label getAchievementDLabel2(){return this.achievementdlabel2;}

    /* getter methods for the achievements screen (Hbox4)*/
    public Label getAchievementLabel4(){return this.achievementlabel3;}
    public ImageView getLockImage4(){return this.lockimage3;}
    public Label getAchievementDLabel3(){return this.achievementdlabel3;}

    /* getter methods for the achievements screen (Hbox5)*/
    public Label getAchievementLabel5(){return this.achievementlabel4;}
    public ImageView getLockImage5(){return this.lockimage4;}
    public Label getAchievementDLabel4(){return this.achievementdlabel4;}

    /* getter methods for the achievements screen (Hbox6)*/
    public Label getAchievementLabel6(){return this.achievementlabel5;}
    public ImageView getLockImage6(){return this.lockimage5;}
    public Label getAchievementDLabel5(){return this.achievementdlabel5;}

    /* getter methods for the achievements screen (Hbox7)*/
    public Label getAchievementLabel7(){return this.achievementlabel6;}
    public ImageView getLockImage7(){return this.lockimage6;}
    public Label getAchievementDLabel6(){return this.achievementdlabel6;}

    /* getter methods for the achievements screen (Hbox8)*/
    public Label getAchievementLabel8(){return this.achievementlabel7;}
    public ImageView getLockImage8(){return this.lockimage7;}
    public Label getAchievementDLabel7(){return this.achievementdlabel7;}


    public void levelimagelabel(HBox hbox, ImageView imageView, Label label, int lvlasked){
        /* start of changing font size and label size */
        main.getMainContainer().widthProperty().addListener((observable, oldValue, newWidth) -> {
            double widthFontSize = newWidth.doubleValue() / 4;
            double heightFontSize = (main.getMainContainer().getHeight() / 4)*2.35;

            // Choose the smaller font size to ensure it fits both width and height
            double fontSize = Math.min(widthFontSize, heightFontSize) / 10;

            // Set the font size of the label
            if (fontSize != 0) {
                label.setStyle("-fx-font-size: " + fontSize + "px");
            }
        });

        main.getMainContainer().heightProperty().addListener((observable, oldValue, newHeight) -> {
            double widthFontSize = main.getMainContainer().getWidth() / 4;
            double heightFontSize = (newHeight.doubleValue() / 4)*2.35;

            // Choose the smaller font size to ensure it fits both width and height
            double fontSize = Math.min(widthFontSize, heightFontSize) / 10;

            // Set the font size of the label
            if (fontSize != 0) {
                label.setStyle("-fx-font-size: " + fontSize + "px");
            }
        });

        // Bind the lock image to the size of the HBox
        imageView.setPreserveRatio(true); // Disable preserving aspect ratio
        imageView.fitWidthProperty().bind(hbox.widthProperty()); // Bind fitWidth to HBox width
        imageView.fitHeightProperty().bind(hbox.heightProperty()); // Bind fitHeight to HBox height

        //Check if the level can be unlocked
        imageView.setMouseTransparent(true);
        if (unlocked.isLevelUnlocked(lvlasked) == true) imageView.setMouseTransparent(false);
    }

    /* Switch to level select screen and initialize*/
    @FXML
    public void LevelSelect(ActionEvent event) {
        // Fade in transition
        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(0.5), main.switchToLevelSelect());
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);
        fadeTransition.play();
        main.getMainContainer().getChildren().setAll(main.switchToLevelSelect());

        HelloController controller1 = main.LevelSelectController();

        //Hbox wants to be 1/6 of the screen
        controller1.getLeveltitlehbox().prefHeightProperty().bind(main.getMainContainer().heightProperty().multiply(0.2));

        //Other hbox wants to be 5/6 of the screen and 90% of the width
        controller1.getLevelselecthbox().prefHeightProperty().bind(main.getMainContainer().heightProperty().multiply(0.8));

        controller1.getLevelselecthbox().prefWidthProperty().bind(main.getMainContainer().widthProperty().multiply(0.9));


        //container to base resizing on
        HBox hbox11 = controller1.getSmallhboxlvl1();

        //Lvl1

        //Method for binding both, above this one
        levelimagelabel(hbox11, controller1.getImageviewlvl1(), controller1.getLabellvl1(),1);

        //Lvl2

        //Method for binding both, above this one
        levelimagelabel(hbox11, controller1.getImageviewlvl2(), controller1.getLabellvl2(),2);

        //Lvl3

        //Method for binding both, above this one
        levelimagelabel(hbox11, controller1.getImageviewlvl3(), controller1.getLabellvl3(),3);

        //Lvl4

        //Method for binding both, above this one
        levelimagelabel(hbox11, controller1.getImageviewlvl4(), controller1.getLabellvl4(),4);

        //Lvl5

        //Method for binding both, above this one
        levelimagelabel(hbox11, controller1.getImageviewlvl5(), controller1.getLabellvl5(),5);

        //Lvl6

        //Method for binding both, above this one
        levelimagelabel(hbox11, controller1.getImageviewlvl6(), controller1.getLabellvl6(),6);

        //Lvl7

        //Method for binding both, above this one
        levelimagelabel(hbox11, controller1.getImageviewlvl7(), controller1.getLabellvl7(),7);

        //Lvl8

        //Method for binding both, above this one
        levelimagelabel(hbox11, controller1.getImageviewlvl8(), controller1.getLabellvl8(),8);

        //Lvl9

        //Method for binding both, above this one
        levelimagelabel(hbox11, controller1.getImageviewlvl9(), controller1.getLabellvl9(),9);

        //Lvl10

        //Method for binding both, above this one
        levelimagelabel(hbox11, controller1.getImageviewlvl10(), controller1.getLabellvl10(),10);
        

    }

    //Level selection screen getters

    //Hbox for the top part of screen
    public HBox getLeveltitlehbox(){return this.leveltitlehbox;}

    //Hbox for rest of the screen
    public HBox getLevelselecthbox(){return this.levelselecthbox;}

    //Hbox for resizing images
    public HBox getSmallhboxlvl1(){return this.smallhboxlvl1;}


    //Level 1
    public ImageView getImageviewlvl1(){return this.imageviewlvl1;}
    public Label getLabellvl1(){return this.labellvl1;}

    //Level 2
    public ImageView getImageviewlvl2(){return this.imageviewlvl2;}
    public Label getLabellvl2(){return this.labellvl2;}

    //Level 3
    public ImageView getImageviewlvl3(){return this.imageviewlvl3;}
    public Label getLabellvl3(){return this.labellvl3;}

    //Level 4
    public ImageView getImageviewlvl4(){return this.imageviewlvl4;}
    public Label getLabellvl4(){return this.labellvl4;}

    //Level 5
    public ImageView getImageviewlvl5(){return this.imageviewlvl5;}
    public Label getLabellvl5(){return this.labellvl5;}

    //Level 6
    public ImageView getImageviewlvl6(){return this.imageviewlvl6;}
    public Label getLabellvl6(){return this.labellvl6;}

    //Level 7
    public ImageView getImageviewlvl7(){return this.imageviewlvl7;}
    public Label getLabellvl7(){return this.labellvl7;}

    //Level 8
    public ImageView getImageviewlvl8(){return this.imageviewlvl8;}
    public Label getLabellvl8(){return this.labellvl8;}

    //Level 9
    public ImageView getImageviewlvl9(){return this.imageviewlvl9;}
    public Label getLabellvl9(){return this.labellvl9;}

    //Level 10
    public ImageView getImageviewlvl10(){return this.imageviewlvl10;}
    public Label getLabellvl10(){return this.labellvl10;}

    /* Switch to main screen and initialize*/
    @FXML
    private void MainScreen(ActionEvent event) {
        // Fade in transition
        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(0.5), main.switchToMainScreen());
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);
        fadeTransition.play();
        //Replace current screen with the new one
        main.getMainContainer().getChildren().setAll(main.switchToMainScreen());
       }

    @FXML
    public void spawn(MouseEvent e) {
        Component component;
        if(((HBox) e.getSource()).getId().equals(wire.getId())){
            component = new Wire();
        } else if(((HBox) e.getSource()).getId().equals(powerSupply.getId())){
            component = new PowerSupply();
        } else if(((HBox) e.getSource()).getId().equals(resistor.getId())){
            component = new Resistors();
        } else if(((HBox) e.getSource()).getId().equals(capacitor.getId())){
            component = new Capacitors();
        } else if(((HBox) e.getSource()).getId().equals(merger.getId())){
            component = new Merger();
        } else if(((HBox) e.getSource()).getId().equals(splitter.getId())){
            component = new Splitter();
        } else if(((HBox) e.getSource()).getId().equals(wireSwitch.getId())){
            component = new Switch();
            Debug.Info("wireSwitch found");
        } else {
            component = null;
            Debug.Error("Invalid spawn component");
        }

        //Creates the object
        Circle circle = new Circle(20);
        circle.setFill(component.getColor());
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
                component.setComponentNode(solidcircle);
                solidcircle.setFill(component.getColor());

                //draggableMaker.dragging(solidcircle, iD, smallanchorpane, dataGrid);
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
                        component.setLocation(Windex, Hindex);
                        //snaps to grid
                        solidcircle.setCenterY(Hindex * (Hspacing) + Hspacing / 2);
                        solidcircle.setCenterX(Windex * (Wspacing) + Wspacing / 2);
                        smallanchorpane.getChildren().add(solidcircle);
                        solidcircle.toFront();

                        //Sandbox Matrix creation
                        BuilderMatrix.setBoxID(Windex, Hindex, component.getId());
                        component.interact();
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

