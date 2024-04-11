package com.example.electriccircuit;

import com.example.electriccircuit.Components.*;
import com.example.electriccircuit.Logic.BuilderMatrix;
import com.example.electriccircuit.Logic.CalculatingGrid;
import com.example.electriccircuit.Logic.SaveFiles;
import static com.example.electriccircuit.Logic.SaveFiles.saveGame;
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
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static com.example.electriccircuit.Logic.SaveFiles.*;

public class HelloController implements Initializable {
    @FXML
    private Label welcomeText;
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
    private ImageView lockimage8;

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
    private Label achievementlabel8;

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
    @FXML
    private Label achievementdlabel8;

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

    //Start of main screen ids
    @FXML
    private HBox scrollhbox;
    @FXML
    private BorderPane borderPane;
    @FXML
    private GridPane togglegrid;
    @FXML
    private CheckBox checkGrid;
    @FXML
    private AnchorPane anchorpane;
    @FXML
    private AnchorPane smallanchorpane;
    @FXML
    public GridPane dataGrid;
    @FXML
    public Button cal;
    @FXML
    public ScrollPane pan;

    //components
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

    //Start of settings screen ids
    @FXML
    private VBox settingsvbox;

    //Non fxml variables
    private HelloApplication main;
    private Unlocks unlocked;
    public static double ancwidth;
    public static double ancheight;
    private int countee = 0;
    public static draggable draggableMaker = new draggable();
    BuilderMatrix sandboxMatrix = new BuilderMatrix();
    Label resistance = new Label("1");
    Label potential = new Label("2");
    Label current = new Label("3");


    //setters
    public void setMain(HelloApplication main){
        this.main = main;
    }
    public void setUnlocks(Unlocks unlocked) { this.unlocked = unlocked; }

    //initialize variables
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {}

    private static HelloController instance;
    public static HelloController getInstance() {
        return instance;
    }

    //initialize variables
    public HelloController() {}

    //method for first initialization
    public void titleinitialize(){
        // Replace current screen with the new one
        main.getMainContainer().getChildren().setAll(main.switchToTitle());
        for (Node child : main.getMainContainer().getChildren()) {
            child.setMouseTransparent(false);
        }
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
        for (Node child : main.getMainContainer().getChildren()) {
            child.setMouseTransparent(false);
        }
        titlemethod();
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

        //controller1.getAchievementtitlehbox().prefHeightProperty().bind(main.getMainContainer().heightProperty().multiply(0.25));

        //Bind the bighbox to twice the window size
        controller1.getBiggesthbox().prefWidthProperty().bind(main.getMainContainer().widthProperty().multiply(2.25));

        // Bind scroll pane height to maintain the desired ratio
        controller1.getScrollPaneachievement().prefHeightProperty().bind(main.getMainContainer().heightProperty().subtract(10));

        //container to base resizing on
        HBox hbox11 = controller1.getBaseHbox();

        //Hbox1

        //achieveunlock(controller1.getLockImage1(), new Image("lvl1.jpg"), 9);
        achievelock(controller1.getLockImage1(), hbox11, controller1.getAchievementLabel1(), controller1.getAchievementDLabel());

        //Hbox2

        //achieveunlock(controller1.getLockImage2(), new Image("lvl2.jpg"), 9);
        achievelock(controller1.getLockImage2(), hbox11, controller1.getAchievementLabel2(), controller1.getAchievementDLabel1());

        //Hbox3

        //achieveunlock(controller1.getLockImage3(), new Image("lvl3.jpg"), 9);
        achievelock(controller1.getLockImage3(), hbox11, controller1.getAchievementLabel3(), controller1.getAchievementDLabel2());

        //Hbox4

        //achieveunlock(controller1.getLockImage4(), new Image("lvl4.jpg"), 9);
        achievelock(controller1.getLockImage4(), hbox11, controller1.getAchievementLabel4(), controller1.getAchievementDLabel3());

        //Hbox5

        //achieveunlock(controller1.getLockImage5(), new Image("lvl5.jpg"), 9);
        achievelock(controller1.getLockImage5(), hbox11, controller1.getAchievementLabel5(), controller1.getAchievementDLabel4());

        //Hbox6

        //achieveunlock(controller1.getLockImage6(), new Image("lvl6.jpg"), 9);
        achievelock(controller1.getLockImage6(), hbox11, controller1.getAchievementLabel6(), controller1.getAchievementDLabel5());

        //Hbox7

        //achieveunlock(controller1.getLockImage7(), new Image("lvl7.jpg"), 9);
        achievelock(controller1.getLockImage7(), hbox11, controller1.getAchievementLabel7(), controller1.getAchievementDLabel6());

        //Hbox8

        //achieveunlock(controller1.getLockImage8(), new Image("lvl8.jpg"), 9);
        achievelock(controller1.getLockImage8(), hbox11, controller1.getAchievementLabel8(), controller1.getAchievementDLabel7());

        //Hbox9

        //achieveunlock(controller1.getLockImage9(), new Image("lvl9.jpg"), 9);
        achievelock(controller1.getLockImage9(), hbox11, controller1.getAchievementLabel9(), controller1.getAchievementDLabel8());

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

        //Hbox wants to be 1/5 of the screen
        controller1.getLeveltitlehbox().prefHeightProperty().bind(main.getMainContainer().heightProperty().multiply(0.2));

        //Other hbox wants to be 4/5 of the screen and 90% of the width
        controller1.getLevelselecthbox().prefHeightProperty().bind(main.getMainContainer().heightProperty().multiply(0.8));
        controller1.getLevelselecthbox().prefWidthProperty().bind(main.getMainContainer().widthProperty().multiply(0.89));

        //container to base resizing on
        HBox hbox11 = controller1.getSmallhboxlvl1();

        //Lvl1
        //Method for binding both, above this one
        levelimagelabel(hbox11, controller1.getImageviewlvl1(), controller1.getLabellvl1(),1);

        //Lvl2
        levelimagelabel(hbox11, controller1.getImageviewlvl2(), controller1.getLabellvl2(),2);

        //Lvl3
        levelimagelabel(hbox11, controller1.getImageviewlvl3(), controller1.getLabellvl3(),3);

        //Lvl4
        levelimagelabel(hbox11, controller1.getImageviewlvl4(), controller1.getLabellvl4(),4);

        //Lvl5
        levelimagelabel(hbox11, controller1.getImageviewlvl5(), controller1.getLabellvl5(),5);

        //Lvl6
        levelimagelabel(hbox11, controller1.getImageviewlvl6(), controller1.getLabellvl6(),6);

        //Lvl7
        levelimagelabel(hbox11, controller1.getImageviewlvl7(), controller1.getLabellvl7(),7);

        //Lvl8
        levelimagelabel(hbox11, controller1.getImageviewlvl8(), controller1.getLabellvl8(),8);

        //Lvl9
        levelimagelabel(hbox11, controller1.getImageviewlvl9(), controller1.getLabellvl9(),9);

        //Lvl10
        levelimagelabel(hbox11, controller1.getImageviewlvl10(), controller1.getLabellvl10(),10);


    }

    /* Switch to main screen and initialize*/
    @FXML
    private void MainScreen(ActionEvent event) {
        //Replace current screen with the new one
        main.getMainContainer().getChildren().setAll(main.switchToMainScreen());
        HelloController controller1 = main.MainController();

        if(countee == 0){
            main.maximise();
            countee++;
            ancwidth = main.getMainContainer().getWidth() - 309;
            ancheight = main.getMainContainer().getHeight() - 177;
            limiter(controller1.getSmallanchorpane());
            limiter(controller1.getTogglegrid());
            controller1.getDataGrid().addRow(1,resistance,potential,current);
        }
        // Fade in transition
        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(0.5), main.switchToMainScreen());
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);
        fadeTransition.play();

        for (Node child : main.getMainContainer().getChildren()) {
            child.setMouseTransparent(false);
        }
        controller1.getBorderPane().setMouseTransparent(true);
        if (controller1.getCal() != null && controller1.getCal().getId() != "calculatetrue") {
            controller1.getCal().setMouseTransparent(true);
            controller1.getCal().setOpacity(0.5);
        }
        controller1.getScrollhbox().prefWidthProperty().bind(main.getMainContainer().widthProperty().add(1199));
        loadGame();
        if(sandboxMatrix.closedCircuit()) {
            controller1.getCal().setId("calculatetrue");
            controller1.getCal().setMouseTransparent(false);
            controller1.getCal().setOpacity(1);
        }
       }

    /* Settings */
    @FXML
    private void settings(ActionEvent event) {
        //Add the settings to the stage
        for (Node child : main.getMainContainer().getChildren()) {
            child.setMouseTransparent(true);
        }
        main.getMainContainer().getChildren().add(main.settings());
        HelloController controller1 = main.settingsController();
        controller1.getSettingsvbox().setMinWidth(400);
        controller1.getSettingsvbox().setMaxWidth(400);
        controller1.getSettingsvbox().setMinHeight(350);
        controller1.getSettingsvbox().setMaxHeight(470);
    }
    /* exit settings */
    @FXML
    private void exitSettings(ActionEvent event) {
        //Remove the settings from the stage
        main.getMainContainer().getChildren().remove(main.settings());
        for (Node child : main.getMainContainer().getChildren()) {
            child.setMouseTransparent(false);
        }
        HelloController controller1 = main.AchievementsController();
        HelloController controller2 = main.MainController();
        if (controller1.getBorderPane() != null) controller1.getBorderPane().setMouseTransparent(true);
        if (controller2.getCal() != null && controller2.getCal().getId() != "calculatetrue") controller2.getCal().setMouseTransparent(true);
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
        Rectangle sprite = new Rectangle(HelloController.getAncwidth()/35,HelloController.getAncheight()/20);
        assert component != null;
        sprite.setFill(new ImagePattern(component.getImageTexture()));
        sprite.setOpacity(0);
        anchorpane.getChildren().add(sprite);
        smallanchorpane.getChildren().add(sprite);


        final boolean[] isEventEnabled = {true};

        /*On mouse movement, calibrates to small and large anchor panes */
        smallanchorpane.setOnMouseMoved(mouseEvent -> {
            sprite.setX(mouseEvent.getX() - sprite.getWidth() / 2);
            sprite.setY(mouseEvent.getY() - sprite.getHeight() / 2);
            if (!sprite.isFocused())
                sprite.setOpacity(100);
        });

        anchorpane.setOnMouseMoved(mouseEvent -> {
            sprite.setX((HelloController.getAncwidth() - (anchorpane.getWidth())) / 2  + mouseEvent.getX() - sprite.getWidth() / 2);
            sprite.setY(mouseEvent.getY() - sprite.getHeight() / 2);
            if (!sprite.isFocused())
                sprite.setOpacity(100);
        });

        pan.setOnMouseReleased(mouseEvent -> {
            if(isEventEnabled[0]) { //makes sure you can only release once
                //Creates the solid circle
                Rectangle solidSprite = new Rectangle(HelloController.getAncwidth()/35,HelloController.getAncheight()/20);
                component.setComponentNode(solidSprite);
                solidSprite.setFill(new ImagePattern(component.getImageTexture()));

                //draggableMaker.dragging(solidcircle, iD, smallanchorpane, dataGrid);
                double Hspacing = (HelloController.getAncheight()/ 20);
                double Wspacing = (HelloController.getAncwidth()/ 35);

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
                        solidSprite.setY(Hindex * (Hspacing));
                        solidSprite.setX(Windex * (Wspacing));
                        smallanchorpane.getChildren().add(solidSprite);
                        solidSprite.toFront();

                        //Sandbox Matrix creation
                        BuilderMatrix.setBoxID(Windex, Hindex, component.getId());
                        component.setLocation(Windex, Hindex);
                        component.interact();
                    }
                }
                if(!mouseEvent.isShiftDown()){
                    smallanchorpane.getChildren().remove(sprite);
                    anchorpane.getChildren().remove(sprite);

                    isEventEnabled[0] = false;
                }
            }
            System.out.println(HelloController.returnSmallAnchorPane().getWidth());
            System.out.println(HelloController.returnSmallAnchorPane().getHeight());
            System.out.println(ancwidth + " " + ancheight);
        });
    }

    @FXML
    public void exit(ActionEvent event){
        System.exit(0);
    }
    @FXML
    public void clearGrid(ActionEvent event){
        for(int i = 0; i < 20; i++){
            for (int j = 0; j < 35; j++){
                BuilderMatrix.removeBoxID(j,i);
            }
            saveGame();
        }
        smallanchorpane.getChildren().clear();
        //Should also clear the components!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! (this is a future Alex problem don't worry about it)
        HelloController controller1 = main.MainController();
        controller1.getCal().setId("calculatefalse");
        controller1.getCal().setMouseTransparent(true);
        controller1.getCal().setOpacity(0.5);
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

    //Method for level selection binding
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
        //imageView.setMouseTransparent(true);
        //if (unlocked.isLevelUnlocked(lvlasked) == true) imageView.setMouseTransparent(false);
    }

    //Method for achievement hbox
    public void achievelock(ImageView imageView, HBox hbox, Label label, Label dlabel){

        double wfs = main.getMainContainer().getWidth() / 4;
        double hfs = (main.getMainContainer().getHeight() / 4)*2.35;

        // Choose the smaller font size to ensure it fits both width and height
        double fs = Math.min(wfs, hfs) / 10;

        // Set the font size of the label
        if (fs != 0) {
            label.setStyle("-fx-font-size: " + fs + "px");
            dlabel.setStyle("-fx-font-size: " + fs + "px");
        }


        /* start of changing font size and label size */
        main.getMainContainer().widthProperty().addListener((observable, oldValue, newWidth) -> {
            double widthFontSize = newWidth.doubleValue() / 4;
            double heightFontSize = (main.getMainContainer().getHeight() / 4.5)*2.30;

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
            double heightFontSize = (newHeight.doubleValue() / 4.5)*2.30;

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
    //method for changing achievement image if unlocked
    public void achieveunlock(ImageView imageview, Image image, int num){
        HelloController controller1 = main.AchievementsController();
        //Check if the achievement is unlocked
        //if (unlocked.isAchievementUnlocked(num)) imageview.setImage(image);
    }

    //Method for showing grid in main
    @FXML
    public void showGrid(ActionEvent event){
        if (checkGrid.isSelected())

            togglegrid.setOpacity(0.5);

        else

            togglegrid.setOpacity(0);
    }

    //Method to bind the breadboard to a certainsize
    public void limiter(Region region){
        region.setMaxWidth(ancwidth);
        region.setMinWidth(ancwidth);
        region.setMaxHeight(ancheight);
        region.setMinHeight(ancheight);
    }


    //Getters for static getters
    public static double getAncwidth(){
        return ancwidth;
    }
    public static double getAncheight(){
        return ancheight;
    }
    public Button getCal(){
        return cal;
    }
    public GridPane getDataGrid(){
        return dataGrid;
    }

    //Static getters
    public static GridPane returnDataGrid(){
        HelloController controllerx = HelloApplication.statMainController();
        return controllerx.getDataGrid();
    }

    public static AnchorPane returnSmallAnchorPane(){
        HelloController controllerx = HelloApplication.statMainController();
        return controllerx.getSmallanchorpane();
    }

    public static Button returnCalButton(){
        HelloController controllerx = HelloApplication.statMainController();
        return controllerx.getCal();
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

    /* getter methods for the achievements screen */
    public ScrollPane getScrollPaneachievement(){return this.scrollPaneachievement;}
    public HBox getBiggesthbox(){return this.biggesthbox;}
    public HBox getAchievementtitlehbox(){return this.achievementtitlehbox;}
    public HBox getBaseHbox(){return this.achievementshbox;}

    //Hbox1
    public Label getAchievementLabel1(){return this.achievementlabel;}
    public ImageView getLockImage1(){return this.lockimage;}
    public Label getAchievementDLabel(){return this.achievementdlabel;}

    //Hbox2
    public Label getAchievementLabel2(){return this.achievementlabel1;}
    public ImageView getLockImage2(){return this.lockimage1;}
    public Label getAchievementDLabel1(){return this.achievementdlabel1;}

    //Hbox3
    public Label getAchievementLabel3(){return this.achievementlabel2;}
    public ImageView getLockImage3(){return this.lockimage2;}
    public Label getAchievementDLabel2(){return this.achievementdlabel2;}

    //Hbox4
    public Label getAchievementLabel4(){return this.achievementlabel3;}
    public ImageView getLockImage4(){return this.lockimage3;}
    public Label getAchievementDLabel3(){return this.achievementdlabel3;}

    //Hbox5
    public Label getAchievementLabel5(){return this.achievementlabel4;}
    public ImageView getLockImage5(){return this.lockimage4;}
    public Label getAchievementDLabel4(){return this.achievementdlabel4;}

    //Hbox6
    public Label getAchievementLabel6(){return this.achievementlabel5;}
    public ImageView getLockImage6(){return this.lockimage5;}
    public Label getAchievementDLabel5(){return this.achievementdlabel5;}

    //Hbox7
    public Label getAchievementLabel7(){return this.achievementlabel6;}
    public ImageView getLockImage7(){return this.lockimage6;}
    public Label getAchievementDLabel6(){return this.achievementdlabel6;}

    //Hbox8
    public Label getAchievementLabel8(){return this.achievementlabel7;}
    public ImageView getLockImage8(){return this.lockimage7;}
    public Label getAchievementDLabel7(){return this.achievementdlabel7;}

    //Hbox9
    public Label getAchievementLabel9(){return this.achievementlabel8;}
    public ImageView getLockImage9(){return this.lockimage8;}
    public Label getAchievementDLabel8(){return this.achievementdlabel8;}

    //main screen getters
    public HBox getScrollhbox(){return this.scrollhbox;}
    public BorderPane getBorderPane(){return this.borderPane;}
    public AnchorPane getAnchorpane(){return this.anchorpane;}
    public AnchorPane getSmallanchorpane(){return this.smallanchorpane;}
    public GridPane getTogglegrid(){return this.togglegrid;}

    //settings getters
    public VBox getSettingsvbox(){return this.settingsvbox;}

    //Random getters
    public HelloController getMainController(){
        return main.MainController();
    }
}

