package com.example.electriccircuit;

import com.example.electriccircuit.Components.*;
import com.example.electriccircuit.Components.subcomponent.Switch;
import com.example.electriccircuit.Logic.BuilderMatrix;

import static com.example.electriccircuit.Components.Component.componentArray;
import com.example.electriccircuit.Logic.SaveFiles;
import static com.example.electriccircuit.Logic.SaveFiles.saveGame;
import com.example.electriccircuit.Logic.draggable;
import com.example.electriccircuit.Logic.*;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.UnsupportedAudioFileException;

import static com.example.electriccircuit.Components.Component.componentArray;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.util.Arrays;
import java.util.Objects;
import java.util.ResourceBundle;

import static com.example.electriccircuit.Logic.SaveFiles.*;
import static java.lang.Character.isDigit;

public class HelloController implements Initializable {

    //start of title screen ids
    @FXML
    private ImageView logotitle;

    @FXML
    private HBox logotitlehbox;

    @FXML
    private Button bt1, bt2, bt3, bt4;

    //start of achievements screen ids

    @FXML
    private ScrollPane scrollPaneachievement;
    @FXML
    private HBox biggesthbox, achievementtitlehbox;
    @FXML
    private ImageView lockimage, lockimage1, lockimage2, lockimage3, lockimage4, lockimage5, lockimage6, lockimage7, lockimage8;
    @FXML
    private HBox achievementshbox;
    @FXML
    private Label achievementlabel, achievementlabel1, achievementlabel2, achievementlabel3, achievementlabel4, achievementlabel5, achievementlabel6, achievementlabel7, achievementlabel8;

    @FXML
    private Label achievementdlabel, achievementdlabel1, achievementdlabel2, achievementdlabel3, achievementdlabel4, achievementdlabel5, achievementdlabel6, achievementdlabel7, achievementdlabel8;

    //Start of level selection ids
    @FXML
    private HBox leveltitlehbox, levelselecthbox;

    @FXML
    private HBox smallhboxlvl1, smallhboxlvl2, smallhboxlvl3, smallhboxlvl4, smallhboxlvl5, smallhboxlvl6, smallhboxlvl7, smallhboxlvl8, smallhboxlvl9, smallhboxlvl10;

    @FXML
    private VBox mainlevelvbox;

    @FXML
    private Label labellvl1, labellvl2, labellvl3, labellvl4, labellvl5, labellvl6, labellvl7, labellvl8, labellvl9, labellvl10;

    @FXML
    private ImageView imageviewlvl1, imageviewlvl2, imageviewlvl3, imageviewlvl4, imageviewlvl5, imageviewlvl6, imageviewlvl7, imageviewlvl8, imageviewlvl9, imageviewlvl10;

    //Start of main screen ids
    @FXML
    private HBox scrollhbox, rectanglebottom, hintbutton, hinthbox;
    @FXML
    private VBox retractableleft, rectangleleft, retractableright, rectangleright;
    @FXML
    private GridPane togglegrid, dataGrid;
    @FXML
    private Label totalVolt, totalAmp, totalOhms, priceBattery, voltTitle, resitanceTitle, faradTitle, totalFarads, totalCharge, hintlabel, budgetlabel, goallabel, givenbudgetlabel;
    @FXML
    private CheckBox checkGrid;
    @FXML
    private AnchorPane anchorpane, smallanchorpane, anchoringgrid;
    @FXML
    public Button cal;
    @FXML
    public ScrollPane pan, componentscroll, gridscroll;
    @FXML
    public TextField voltsofbattery, resistanceofresistor, faradsofcapacitor;
    @FXML
    private Slider timeSlider;

    //components
    @FXML
    private HBox wire, powerSupply, resistor, capacitor, merger, splitter, wireSwitch;

    //Start of settings screen ids
    @FXML
    private VBox settingsvbox;
    @FXML
    private CheckBox hintcheck;

    //Non fxml variables
    private HelloApplication main;
    private Unlocks unlocked;
    public static double ancwidth, ancheight;
    private int countee = 0;
    private static int level;
    public static draggable draggableMaker = new draggable();
    BuilderMatrix sandboxMatrix = new BuilderMatrix();
    private String voltstring, resistancestring, faradstring;
    private Label resistance = new Label(""), potential = new Label(""), current = new Label("");

    //setters
    public void setMain(HelloApplication main) {
        this.main = main;
    }

    public void setUnlocks(Unlocks unlocked) {
        this.unlocked = unlocked;
    }

    //initialize variables
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    private static HelloController instance;

    public static HelloController getInstance() {
        return instance;
    }

    //initialize variables
    public HelloController() {
    }

    //method for first initialization
    public void titleinitialize() {
        // Replace current screen with the new one
        main.getMainContainer().getChildren().setAll(main.switchToTitle());
        titlemethod();
    }

    public void FadeTransition(Node node) {
        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(0.5), node);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);
        fadeTransition.play();
    }

    @FXML
    public void titleScreen(ActionEvent event) {
        //save screen
        saveGame();
        // Fade in transition
        FadeTransition(main.switchToTitle());
        popSound();
        // Replace current screen with the new one
        main.getMainContainer().getChildren().setAll(main.switchToTitle());
        titlemethod();
    }

    /* Switch to achievements screen and initialize*/
    @FXML
    public void Achievements(ActionEvent event) {

        // Fade in transition
        FadeTransition(main.switchToAchievements());
        popSound();

        main.getMainContainer().getChildren().setAll(main.switchToAchievements());
        HelloController controller1 = main.AchievementsController();

        //controller1.getAchievementtitlehbox().prefHeightProperty().bind(main.getMainContainer().heightProperty().multiply(0.25));

        //Bind the bighbox to 2.25 times the window size
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
        FadeTransition(main.switchToLevelSelect());
        popSound();

        main.getMainContainer().getChildren().setAll(main.switchToLevelSelect());
        HelloController controller1 = main.LevelSelectController();

        //Other hbox wants to be 4/5 of the screen and 90% of the width
        controller1.getLevelselecthbox().prefWidthProperty().bind(main.getMainContainer().widthProperty().multiply(0.90));
        //container to base resizing on
        HBox hbox11 = controller1.getSmallhboxlvl1();

        levelhboxbind();

        controller1.getImageviewlvl1().fitWidthProperty().bind(hbox11.widthProperty().multiply(0.9));
        controller1.getImageviewlvl1().fitHeightProperty().bind(hbox11.heightProperty());

        //Bindings
        //Lvl1
        levelimagelabel(controller1.getImageviewlvl1(), controller1.getImageviewlvl1(), controller1.getLabellvl1(), 1);
        //Lvl2
        levelimagelabel(controller1.getImageviewlvl1(), controller1.getImageviewlvl2(), controller1.getLabellvl2(), 2);
        //Lvl3
        levelimagelabel(controller1.getImageviewlvl1(), controller1.getImageviewlvl3(), controller1.getLabellvl3(), 3);
        //Lvl4
        levelimagelabel(controller1.getImageviewlvl1(), controller1.getImageviewlvl4(), controller1.getLabellvl4(), 4);
        //Lvl5
        levelimagelabel(controller1.getImageviewlvl1(), controller1.getImageviewlvl5(), controller1.getLabellvl5(), 5);
        //Lvl6
        levelimagelabel(controller1.getImageviewlvl1(), controller1.getImageviewlvl6(), controller1.getLabellvl6(), 6);
        //Lvl7
        levelimagelabel(controller1.getImageviewlvl1(), controller1.getImageviewlvl7(), controller1.getLabellvl7(), 7);
        //Lvl8
        levelimagelabel(controller1.getImageviewlvl1(), controller1.getImageviewlvl8(), controller1.getLabellvl8(), 8);
        //Lvl9
        levelimagelabel(controller1.getImageviewlvl1(), controller1.getImageviewlvl9(), controller1.getLabellvl9(), 9);
        //Lvl10
        levelimagelabel(controller1.getImageviewlvl1(), controller1.getImageviewlvl10(), controller1.getLabellvl10(), 10);

    }

    /* Switch to main screen and initialize*/
    private void MainScreen() {
        //Replace current screen with the new one
        FadeTransition(main.switchToMainScreen());
        popSound();
        main.getMainContainer().getChildren().setAll(main.switchToMainScreen());
        HelloController controller1 = main.MainController();

        if (countee == 0) {
            controller1.getDataGrid().addRow(1, resistance, potential, current);
            ancwidth = (Math.ceil((float) main.getScreenWidth() / 35)) * 35;
            ancheight = (float) (main.getScreenWidth() * 20) / 35;

            limiter(controller1.getSmallanchorpane());
            limiter(controller1.getTogglegrid());
            limiter(controller1.getAnchoringGrid());
            System.out.println("done");
            countee++;
        }

        if (controller1.getCal() != null && controller1.getCal().getId() != "calculatetrue") {
            controller1.getCal().setMouseTransparent(true);
            controller1.getCal().setOpacity(0.5);
        }

        levelmaking(level);
        controller1.getScrollhbox().prefWidthProperty().bind(main.getMainContainer().widthProperty().add(main.getScreenWidth() * 0.6));
        if(level == 0) {
            loadGame();
            System.out.println("Loaded");
        }

        if (sandboxMatrix.closedCircuit()) {
            controller1.getCal().setId("calculatetrue");
            controller1.getCal().setMouseTransparent(false);
            controller1.getCal().setOpacity(1);
        }
    }

    /* Settings */
    @FXML
    private void settings(ActionEvent event) {
        //Add the settings to the stage
        main.getMainContainer().getChildren().add(main.settings());
        popSound();
        HelloController controller1 = main.settingsController();

        controller1.getSettingsvbox().setMinWidth(400);
        controller1.getSettingsvbox().setMaxWidth(400);
        controller1.getSettingsvbox().setMinHeight(370);
        controller1.getSettingsvbox().setMaxHeight(470);
    }

    /* exit settings */
    @FXML
    private void exitSettings(ActionEvent event) {
        //Remove the settings from the stage
        main.getMainContainer().getChildren().remove(main.settings());
        popSound();
    }

    private double contentX;
    private double contentY;
    private double budget;

    @FXML
    public void spawn(MouseEvent e) {
        final Component[] component = new Component[1];
        System.out.println("Worked");
        if (((HBox) e.getSource()).getId().equals(wire.getId())) {
            component[0] = new Wire();
        } else if(((HBox) e.getSource()).getId().equals(powerSupply.getId())){
            component[0] = new PowerSupply();
            if (voltsofbattery.getText().isBlank())
                voltsofbattery.setText("0");
            ((PowerSupply) component[0]).setVoltage(Integer.parseInt(String.valueOf(voltsofbattery.getText())));
            Debug.Log(((PowerSupply) component[0]).getVoltage() + " is voltage ");
        } else if (((HBox) e.getSource()).getId().equals(resistor.getId())) {
            component[0] = new Resistors();
            if (resistanceofresistor.getText().isBlank())
                resistanceofresistor.setText("0");
            component[0].setResistance(Integer.parseInt(String.valueOf(resistanceofresistor.getText())));
        } else if(((HBox) e.getSource()).getId().equals(capacitor.getId())){
            component[0] = new Capacitors();
            if (faradsofcapacitor.getText().isBlank())
                faradsofcapacitor.setText("0");
            component[0].setCapacitance(Double.parseDouble(String.valueOf(faradsofcapacitor.getText())));
        } else if(((HBox) e.getSource()).getId().equals(merger.getId())){
            component[0] = new Merger();
        } else if (((HBox) e.getSource()).getId().equals(splitter.getId())) {
            component[0] = new Splitter();
        } else if (((HBox) e.getSource()).getId().equals(wireSwitch.getId())) {
            component[0] = new Switch();
            Debug.Info("wireSwitch found");
        } else {
            component[0] = null;
            Debug.Error("Invalid spawn component");
        }
        if (component[0] instanceof PowerSupply) {
            component[0].setConnections(0, 1, 0, 0);
        } else {
            component[0].setConnections(0, 1, 0, 1);
        }

        // Get the position of the content
        pan.viewportBoundsProperty().addListener((observable, oldValue, newValue) -> {
            Bounds contentBounds = anchoringgrid.getBoundsInParent();
            contentX = contentBounds.getMinX() + newValue.getMinX() + 0.5;
            contentY = contentBounds.getMinY() + newValue.getMinY() + 0.5;
        });
        //System.out.println("Content position: (" + contentX + ", " + contentY + ")");

        //Creates the object
        Rectangle sprite = new Rectangle(HelloController.getAncwidth() / 35, HelloController.getAncheight() / 20);
        assert component[0] != null;
        sprite.setFill(new ImagePattern(component[0].getImageTexture()));
        sprite.setOpacity(0);
        anchorpane.getChildren().add(sprite);
        smallanchorpane.getChildren().add(sprite);

        main.getMainContainer().setOnKeyPressed(event -> {
            if (event.getCode().toString().equals("R")) {
                rotateComponent(sprite);
                if (component[0] instanceof PowerSupply) {
                    if (Arrays.equals(component[0].getConnections(), new int[]{0, 1, 0, 0})) {
                        component[0].setConnections(0, 0, 1, 0);
                    } else if (Arrays.equals(component[0].getConnections(), new int[]{0, 0, 1, 0})) {
                        component[0].setConnections(0, 0, 0, 1);
                    } else if (Arrays.equals(component[0].getConnections(), new int[]{0, 0, 0, 1})) {
                        component[0].setConnections(1, 0, 0, 0);
                    } else if (Arrays.equals(component[0].getConnections(), new int[]{1, 0, 0, 0})) {
                        component[0].setConnections(0, 1, 0, 0);
                    } else {
                        Debug.Error("well this is weird");
                    }
                } else {
                    for (int i = 0; i < component[0].getConnections().length; i++) {
                        if (component[0].getConnections()[i] == 0) {
                            component[0].getConnections()[i] = 1;
                        } else {
                            component[0].getConnections()[i] = 0;
                        }
                    }
                }
            }
        });


        final boolean[] isEventEnabled = {true};

        /*On mouse movement, calibrates to small and large anchor panes */
        smallanchorpane.setOnMouseMoved(mouseEvent -> {
            if (sprite.getRotate() + 360 % 180 == 0) {
                sprite.setX((mouseEvent.getX() - contentX - sprite.getWidth() / 2));
                sprite.setY((mouseEvent.getY() - contentY - sprite.getHeight() / 2));
            } else {
                sprite.setX((mouseEvent.getX() - contentX - sprite.getHeight() / 2));
                sprite.setY((mouseEvent.getY() - contentY - sprite.getWidth() / 2));
            }
            if (!sprite.isFocused())
                sprite.setOpacity(100);
        });

        anchorpane.setOnMouseMoved(mouseEvent -> {
            if (sprite.getRotate() + 360 % 180 == 0) {
                sprite.setX((mouseEvent.getX() - contentX - sprite.getWidth() / 2));
                sprite.setY((mouseEvent.getY() - contentY - sprite.getHeight() / 2));
            }
            if (!sprite.isFocused())
                sprite.setOpacity(100);
        });

        pan.setOnMouseReleased(mouseEvent -> {
            if (isEventEnabled[0]) { //makes sure you can only release once
                double Hspacing = (Math.round(HelloController.getAncheight() / 20));
                double Wspacing = (Math.round(HelloController.getAncwidth() / 35));
                popSound();

                switch(component[0].getId()){
                    case 1: budget = budget + 1; break;
                    case 2: budget = budget + Double.parseDouble(priceBattery.getText()); break;
                    case 3: budget = budget + 10; break;
                    case 4: budget = budget + 10; break;
                    case 7: budget = budget + 10; break;
                    default:
                        budget = budget; break;
                }
                budgetlabel.setText(String.valueOf(budget) + "$");


                int Hindex = (int) Math.round(((mouseEvent.getY() - contentY - Hspacing / 2) / (Hspacing)));
                int Windex = (int) Math.round(((mouseEvent.getX() - contentX - Wspacing / 2) / (Wspacing)));
                /* more fluid input */
                if (Hindex == 20) {
                    Hindex = 19;
                }
                if (Windex == -1) {
                    Windex = 0;
                }
                if (Windex == 35) {
                    Windex = 34;
                }

                if (Hindex < 20 && Hindex >= 0) { //if within bound of small anchor
                    if (Windex < 35 && Windex >= 0) {
                        placeSolidSprite(component[0], Windex, Hindex, sprite, Hspacing, Wspacing);
                    }
                }
                if (!mouseEvent.isShiftDown()) {
                    smallanchorpane.getChildren().remove(sprite);
                    anchorpane.getChildren().remove(sprite);

                    isEventEnabled[0] = false;
                } else {
                    try {
                        int[] tempConnections = Arrays.copyOf(component[0].getConnections(), component[0].getConnections().length);
                        component[0] = component[0].getClass().newInstance();
                        component[0].setConnections(tempConnections);
                    } catch (InstantiationException ex) {
                        throw new RuntimeException(ex);
                    } catch (IllegalAccessException ex) {
                        throw new RuntimeException(ex);
                    }

                }

            }
        });
    }

    public void placeSolidSprite(Component component, int Windex, int Hindex, Rectangle sprite, double Hspacing, double Wspacing) {
        Rectangle solidSprite = new Rectangle(HelloController.getAncwidth() / 35, HelloController.getAncheight() / 20);
        component.setComponentNode(solidSprite);
        component.setLocation(Windex, Hindex);
        component.interact();


        //snaps to grid
        solidSprite.setY((Hindex * (Hspacing)));
        solidSprite.setX((Windex * (Wspacing)));
        smallanchorpane.getChildren().add(solidSprite);
        solidSprite.toFront();

        //Sandbox Matrix creation

        BuilderMatrix.setBoxID(Windex, Hindex, component.getId(), component);
        component.setLocation(Windex, Hindex);
        component.interact();
        if (component instanceof Wire) {
            component.mainRefreshComponent();
        } else {
            component.mainRefreshComponent();
            solidSprite.setFill(new ImagePattern(component.getImageTexture()));
            solidSprite.setRotate(sprite.getRotate());
        }
        new CalculatingGrid(BuilderMatrix.getGrid());
        Debug.Log("column is actually " + Windex + " and row is " + Hindex);
    }

    @FXML
    public void exit(ActionEvent event) {
        popSound();
        System.exit(0);
    }

    @FXML
    public void clearGrid() {
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 35; j++) {
                BuilderMatrix.removeBoxID(j, i);
                componentArray[j][i] = null;
            }
            saveGame();
        }
        CalculatingGrid.chargedCapacitor = new Capacitors();
        CalculatingGrid.chargedCapacitor = null;

        //Should also clear the components!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! (this is a future Alex problem don't worry about it)
        HelloController controller1 = main.MainController();
        controller1.getSmallanchorpane().getChildren().clear();
        controller1.getCal().setId("calculatefalse");
        controller1.getCal().setMouseTransparent(true);
        controller1.getCal().setOpacity(0.5);
        wooshSound();
        controller1.getBudgetlabel().setText("0$");
        budget = 0;
        for(int i = 2; i < controller1.getDataGrid().getRowCount();i++) {
            int index = i;
            HelloController.returnDataGrid().getChildren().removeIf(node -> GridPane.getRowIndex(node) != null && GridPane.getRowIndex(node) == index);
        }
        new CalculatingGrid(BuilderMatrix.getGrid());
    }

    private void rotateComponent(Rectangle rectangle) {
        rectangle.setRotate(rectangle.getRotate() + 90);
    }


    public void titlemethod() {
        HelloController controller = main.TitleController();
        controller.getLogotitle().fitWidthProperty().bind(controller.getTitleHbox().widthProperty()); // Bind fitWidth to HBox width
        controller.getLogotitle().fitHeightProperty().bind(controller.getTitleHbox().heightProperty()); // Bind fitHeight to HBox height

        buttontext(controller.getBt1()); //invoke the method for all buttons on title screen
        buttontext(controller.getBt2());
        buttontext(controller.getBt3());
        buttontext(controller.getBt4());
    }

    //Method for button resizing
    public void buttontext(Button button) {
        button.setStyle("-fx-font-size: " + button.getWidth() / 10 + "px;");
        button.widthProperty().addListener((obs, oldVal, newVal) -> {
            double width = newVal.doubleValue();
            double height = button.getHeight() * 5;

            // Adjust the font size based on the aspect ratio
            double fontSize = Math.min(width, height) / 10; // Adjust the factor as needed
            button.setStyle("-fx-font-size: " + fontSize + "px;");
        });

        button.heightProperty().addListener((obs, oldVal, newVal) -> {
            double height = newVal.doubleValue() * 5;
            double width = button.getWidth();

            // Adjust the font size based on the aspect ratio
            double fontSize = Math.min(width, height) / 10; // Adjust the factor as needed
            button.setStyle("-fx-font-size: " + fontSize + "px;");
        });
    }

    //Method for level selection binding
    public void levelimagelabel(ImageView image, ImageView imageView, Label label, int lvlasked) {
        HelloController controller1 = main.LevelSelectController();
        /* start of changing font size and label size */
        main.getMainContainer().widthProperty().addListener((observable, oldValue, newWidth) -> {
            double widthFontSize = newWidth.doubleValue() / 4;
            double heightFontSize = (main.getMainContainer().getHeight() / 4) * 2.35;

            // Choose the smaller font size to ensure it fits both width and height
            double fontSize = Math.min(widthFontSize, heightFontSize) / 10;

            // Set the font size of the label
            if (fontSize != 0) {
                label.setStyle("-fx-font-size: " + fontSize + "px");
            }
        });

        main.getMainContainer().heightProperty().addListener((observable, oldValue, newHeight) -> {
            double widthFontSize = main.getMainContainer().getWidth() / 4;
            double heightFontSize = (newHeight.doubleValue() / 4) * 2.35;

            // Choose the smaller font size to ensure it fits both width and height
            double fontSize = Math.min(widthFontSize, heightFontSize) / 10;

            // Set the font size of the label
            if (fontSize != 0) {
                label.setStyle("-fx-font-size: " + fontSize + "px");
            }
        });

        // Bind the lock image to the size of the HBox
        imageView.setPreserveRatio(true); // Disable preserving aspect ratio
        if (image != imageView) {
            imageView.fitWidthProperty().bind(image.fitWidthProperty()); // Bind fitWidth to HBox width
            imageView.fitHeightProperty().bind(image.fitHeightProperty()); // Bind fitHeight to HBox height
        }

        //Check if the level can be unlocked
        //imageView.setMouseTransparent(true);
        //if (unlocked.isLevelUnlocked(lvlasked) == true) imageView.setMouseTransparent(false);
    }

    //Method for binding hbox
    public void levelhboxbind() {
        HelloController controller1 = main.LevelSelectController();
        controller1.getSmallhboxlvl1().prefWidthProperty().bind(main.getMainContainer().widthProperty().subtract(Math.round((float) ((main.getMainContainer().getWidth() - 30) * 4) / 5)));
        controller1.getSmallhboxlvl2().prefWidthProperty().bind(main.getMainContainer().widthProperty().subtract(Math.round((float) ((main.getMainContainer().getWidth() - 30) * 4) / 5)));
        controller1.getSmallhboxlvl3().prefWidthProperty().bind(main.getMainContainer().widthProperty().subtract(Math.round((float) ((main.getMainContainer().getWidth() - 30) * 4) / 5)));
        controller1.getSmallhboxlvl4().prefWidthProperty().bind(main.getMainContainer().widthProperty().subtract(Math.round((float) ((main.getMainContainer().getWidth() - 30) * 4) / 5)));
        controller1.getSmallhboxlvl5().prefWidthProperty().bind(main.getMainContainer().widthProperty().subtract(Math.round((float) ((main.getMainContainer().getWidth() - 30) * 4) / 5)));
        controller1.getSmallhboxlvl6().prefWidthProperty().bind(main.getMainContainer().widthProperty().subtract(Math.round((float) ((main.getMainContainer().getWidth() - 30) * 4) / 5)));
        controller1.getSmallhboxlvl7().prefWidthProperty().bind(main.getMainContainer().widthProperty().subtract(Math.round((float) ((main.getMainContainer().getWidth() - 30) * 4) / 5)));
        controller1.getSmallhboxlvl8().prefWidthProperty().bind(main.getMainContainer().widthProperty().subtract(Math.round((float) ((main.getMainContainer().getWidth() - 30) * 4) / 5)));
        controller1.getSmallhboxlvl9().prefWidthProperty().bind(main.getMainContainer().widthProperty().subtract(Math.round((float) ((main.getMainContainer().getWidth() - 30) * 4) / 5)));
        controller1.getSmallhboxlvl10().prefWidthProperty().bind(main.getMainContainer().widthProperty().subtract(Math.round((float) ((main.getMainContainer().getWidth() - 30) * 4) / 5)));
    }

    //Method for achievement hbox
    public void achievelock(ImageView imageView, HBox hbox, Label label, Label dlabel) {

        double wfs = main.getMainContainer().getWidth() / 4;
        double hfs = (main.getMainContainer().getHeight() / 4) * 2.35;

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
            double heightFontSize = (main.getMainContainer().getHeight() / 4.5) * 2.30;

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
            double heightFontSize = (newHeight.doubleValue() / 4.5) * 2.30;

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
    public void achieveunlock(ImageView imageview, Image image, int num) {
        HelloController controller1 = main.AchievementsController();
        //Check if the achievement is unlocked
        //if (unlocked.isAchievementUnlocked(num)) imageview.setImage(image);
    }

    //Method for showing grid in main
    @FXML
    public void showGrid(ActionEvent event) {
        if (checkGrid.isSelected())
            togglegrid.setOpacity(0.5);
        else
            togglegrid.setOpacity(0);
    }

    //Method to bind the breadboard to a ancwidth / ancheight
    public void limiter(Region region) {
        region.setMaxWidth(ancwidth);
        region.setMinWidth(ancwidth);
        region.setMaxHeight(ancheight);
        region.setMinHeight(ancheight);
        region.setPrefWidth(ancwidth);
        region.setPrefHeight(ancheight);
    }

    @FXML
    public void gridlimit() {
        HelloController controller = main.MainController();
        //controller.getDataGrid().setTranslateX(((controller.getDataGrid().getWidth()/(-2))));
        //controller.getDataGrid().setTranslateY(controller.getDataGrid().getHeight()/2);
        controller.getGridscroll().setTranslateX(((controller.getGridscroll().getWidth() / (-2))));
        controller.getGridscroll().setTranslateY(((controller.getGridscroll().getHeight() / (2))));
    }

    @FXML
    public void gridrestore() {
        HelloController controller = main.MainController();
        //controller.getDataGrid().setTranslateX(0);
        // controller.getDataGrid().setTranslateY(0);
        controller.getGridscroll().setTranslateX(0);
        controller.getGridscroll().setTranslateY(0);
    }

    @FXML
    public void retractRight() {
        HelloController controller = main.MainController();
        if (controller.getRetractableright().getTranslateX() == 0) {
            controller.getRetractableright().setTranslateX(280);
            controller.getRectangleright().setTranslateX(280);
        } else {
            controller.getRetractableright().setTranslateX(0);
            controller.getRectangleright().setTranslateX(0);
        }
    }

    @FXML
    public void retractLeft() {
        HelloController controller = main.MainController();
        if (controller.getRetractableleft().getTranslateX() == 0) {
            controller.getRetractableleft().setTranslateX(-190);
            controller.getRectangleleft().setTranslateX(-190);
        } else {
            controller.getRetractableleft().setTranslateX(0);
            controller.getRectangleleft().setTranslateX(0);
        }
    }

    @FXML
    public void retractBottom() {
        HelloController controller = main.MainController();
        if (controller.getComponentscroll().getTranslateY() == 0) {
            controller.getComponentscroll().setTranslateY(200);
            controller.getRectanglebottom().setTranslateY(200);
        } else {
            controller.getComponentscroll().setTranslateY(0);
            controller.getRectanglebottom().setTranslateY(0);
        }
    }

    @FXML
    public void writeVolt() {
        HelloController controller = main.MainController();
        voltstring = "";
        for (int i = 0; i < controller.getVoltsofbattery().getLength(); i++) {
            if (isDigit(controller.getVoltsofbattery().getText().charAt(i))) {
                voltstring = voltstring + controller.getVoltsofbattery().getText().charAt(i);
            }
        }
        if (voltstring.isEmpty() || Double.parseDouble(voltstring) < 0) {
            voltstring = "0";
            controller.getPriceBattery().setText("0");
            controller.getVoltsofbattery().setText("0");
        } else if (Double.parseDouble(voltstring) * Double.parseDouble(voltstring) * 0.2 > 2147483647) {
            controller.getVoltsofbattery().setText("103621");
            voltstring = "103621";
            controller.getPriceBattery().setText("2147483647");
        } else {
            controller.getPriceBattery().setText(String.valueOf(round(Double.parseDouble(voltstring) * Double.parseDouble(voltstring) * 0.2, 2)));
        }

        controller.getVoltTitle().setText("Power Supply (" + voltstring + " V)");

    }

    @FXML
    public void writeOhms() {
        HelloController controller = main.MainController();
        resistancestring = "";
        for (int i = 0; i < controller.getResistanceofresistor().getLength(); i++) {
            if (isDigit(controller.getResistanceofresistor().getText().charAt(i))) {
                resistancestring = resistancestring + controller.getResistanceofresistor().getText().charAt(i);
            }
        }
        if (resistancestring.isEmpty() || Double.parseDouble(resistancestring) < 0) {
            resistancestring = "0";
            controller.getResistanceofresistor().setText("0");
        } else if (Double.parseDouble(resistancestring) > 2147483647) {
            resistancestring = "2147483647";
            controller.getResistanceofresistor().setText(resistancestring);
        }

        controller.getResitanceTitle().setText("Resistor (" + resistancestring + " Ω)");

    }

    @FXML
    public void writeFarads() {
        HelloController controller = main.MainController();
        faradstring = "";
        for (int i = 0; i < controller.getFaradsofcapacitor().getLength(); i++) {
            if (isDigit(controller.getFaradsofcapacitor().getText().charAt(i))) {
                faradstring = faradstring + controller.getFaradsofcapacitor().getText().charAt(i);
            }
        }
        if (faradstring.isEmpty() || Double.parseDouble(faradstring) < 0) {
            faradstring = "0";
            controller.getFaradsofcapacitor().setText("0");
        } else if (Double.parseDouble(faradstring) > 2147483647) {
            faradstring = "2147483647";
            controller.getResistanceofresistor().setText(faradstring);
        }

        controller.getFaradTitle().setText("Capacitor (" + faradstring + " F)");
    }

    public double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    @FXML
    public void hint() {
        HelloController controller = main.MainController();
        if (controller.getHintlabel().getOpacity() != 0) {
            controller.getHintlabel().setOpacity(0);
            controller.getHintlabel().setMaxHeight(0);
            controller.getHintlabel().setMinHeight(0);
        } else {
            controller.getHintlabel().setOpacity(100);
            controller.getHintlabel().setMaxHeight(Region.USE_COMPUTED_SIZE);
            controller.getHintlabel().setMinHeight(Region.USE_COMPUTED_SIZE);
        }
    }

    @FXML
    public void seeHint() {
        HelloController controller = main.MainController();
        HelloController controller1 = main.settingsController();
        if (controller1.getHintcheck().isSelected()) {
            controller.getHinthbox().setOpacity(100);
            controller.getHinthbox().setMaxHeight(Region.USE_COMPUTED_SIZE);
            controller.getHinthbox().setMinHeight(Region.USE_COMPUTED_SIZE);
            if (controller.getHintlabel().getOpacity() != 0) {
                controller.getHintlabel().setMaxHeight(Region.USE_COMPUTED_SIZE);
                controller.getHintlabel().setMinHeight(Region.USE_COMPUTED_SIZE);
            }
        } else {
            controller.getHinthbox().setOpacity(0);
            controller.getHinthbox().setMaxHeight(0);
            controller.getHinthbox().setMinHeight(0);
            controller.getHintlabel().setMaxHeight(0);
            controller.getHintlabel().setMinHeight(0);
        }
    }

    public void levelmaking(int levelmethod){
        HelloController controller1 = main.MainController();
        switch (levelmethod){
            case 1:
                controller1.getGivenBudgetLabel().setText("50$");
                controller1.getGoalLabel().setText("Make the total circuit current 1 amp");
                controller1.getHintlabel().setText("Think about Ohm's law V = IR");
                break;
            case 2:
                controller1.getGivenBudgetLabel().setText("50$");
                controller1.getGoalLabel().setText("Keep voltage in a resistor that's not connected to a power supply");
                controller1.getHintlabel().setText("Use a capacitor");
                break;
            case 3:
                controller1.getGivenBudgetLabel().setText("50$");
                controller1.getGoalLabel().setText("Unknown");
                controller1.getHintlabel().setText("How can I help?");
                break;
            case 4:
                controller1.getGivenBudgetLabel().setText("50$");
                controller1.getGoalLabel().setText("Unknown");
                controller1.getHintlabel().setText("How can I help?");
                break;
            case 5:
                controller1.getGivenBudgetLabel().setText("50$");
                controller1.getGoalLabel().setText("Unknown");
                controller1.getHintlabel().setText("How can I help?");
                break;
            case 6:
                controller1.getGivenBudgetLabel().setText("50$");
                controller1.getGoalLabel().setText("Unknown");
                controller1.getHintlabel().setText("How can I help?");
                break;
            case 7:
                controller1.getGivenBudgetLabel().setText("50$");
                controller1.getGoalLabel().setText("Unknown");
                controller1.getHintlabel().setText("How can I help?");
                break;
            case 8:
                controller1.getGivenBudgetLabel().setText("50$");
                controller1.getGoalLabel().setText("Unknown");
                controller1.getHintlabel().setText("How can I help?");
                break;
            case 9:
                controller1.getGivenBudgetLabel().setText("50$");
                controller1.getGoalLabel().setText("Unknown");
                controller1.getHintlabel().setText("How can I help?");
                break;
            case 10:
                controller1.getGivenBudgetLabel().setText("50$");
                controller1.getGoalLabel().setText("Unknown");
                controller1.getHintlabel().setText("How can I help?");
                break;
            default:
                break;
        }
    }

    @FXML
    public void selectLevel1(){level = 1; MainScreen();}
    @FXML
    public void selectLevel2(){level = 2; MainScreen();}
    @FXML
    public void selectLevel3(){level = 3; MainScreen();}
    @FXML
    public void selectLevel4(){level = 4; MainScreen();}
    @FXML
    public void selectLevel5(){level = 5; MainScreen();}
    @FXML
    public void selectLevel6(){level = 6; MainScreen();}
    @FXML
    public void selectLevel7(){level = 7; MainScreen();}
    @FXML
    public void selectLevel8(){level = 8; MainScreen();}
    @FXML
    public void selectLevel9(){level = 9; MainScreen();}
    @FXML
    public void selectLevel10(){level = 10; MainScreen();}
    @FXML
    public void selectSandbox(){level = 0; MainScreen();}

    @FXML
    public void submit(){
        HelloController controller1 = main.MainController();
        String budgettext = "";
        String givenbudgettext = "";
        for (int i = 0; i < controller1.getBudgetlabel().getText().length(); i++) {
            if(controller1.getBudgetlabel().getText().charAt(i) == '.')
                break;
            else if (isDigit(controller1.getBudgetlabel().getText().charAt(i))) {
                budgettext = budgettext + controller1.getBudgetlabel().getText().charAt(i);
            }
        }

        for (int i = 0; i < controller1.getGivenBudgetLabel().getText().length(); i++) {
            if (isDigit(controller1.getGivenBudgetLabel().getText().charAt(i))) {
                givenbudgettext = givenbudgettext + controller1.getGivenBudgetLabel().getText().charAt(i);
            }
        }
        switch (level) {
            case 1:
                System.out.println(Integer.parseInt(budgettext) + " " + Integer.parseInt(givenbudgettext));
                if(round(Integer.parseInt(budgettext),0) < Integer.parseInt(givenbudgettext)){
                   if(Double.parseDouble(controller1.getTotalAmpLabel().getText()) == 1) {
                       correctSound();
                   }
                   else{
                       controller1.getBudgetlabel().setId("normal");
                       controller1.getGoalLabel().setId("nope");
                   }
                }
                else{
                    controller1.getBudgetlabel().setId("nope");
                    controller1.getGoalLabel().setId("normal");
                }
                break;
            case 2:
                controller1.getGivenBudgetLabel().setText("50$");
                controller1.getGoalLabel().setText("Keep voltage in a resistor that's not connected to a power supply");
                controller1.getHintlabel().setText("Use a capacitor");
                break;
            case 3:
                controller1.getGivenBudgetLabel().setText("50$");
                controller1.getGoalLabel().setText("Unknown");
                controller1.getHintlabel().setText("How can I help?");
                break;
            case 4:
                controller1.getGivenBudgetLabel().setText("50$");
                controller1.getGoalLabel().setText("Unknown");
                controller1.getHintlabel().setText("How can I help?");
                break;
            case 5:
                controller1.getGivenBudgetLabel().setText("50$");
                controller1.getGoalLabel().setText("Unknown");
                controller1.getHintlabel().setText("How can I help?");
                break;
            case 6:
                controller1.getGivenBudgetLabel().setText("50$");
                controller1.getGoalLabel().setText("Unknown");
                controller1.getHintlabel().setText("How can I help?");
                break;
            case 7:
                controller1.getGivenBudgetLabel().setText("50$");
                controller1.getGoalLabel().setText("Unknown");
                controller1.getHintlabel().setText("How can I help?");
                break;
            case 8:
                controller1.getGivenBudgetLabel().setText("50$");
                controller1.getGoalLabel().setText("Unknown");
                controller1.getHintlabel().setText("How can I help?");
                break;
            case 9:
                controller1.getGivenBudgetLabel().setText("50$");
                controller1.getGoalLabel().setText("Unknown");
                controller1.getHintlabel().setText("How can I help?");
                break;
            case 10:
                controller1.getGivenBudgetLabel().setText("50$");
                controller1.getGoalLabel().setText("Unknown");
                controller1.getHintlabel().setText("How can I help?");
                break;
            default:
                break;
        }

    }

    public static void removefromBudget(int id){
        HelloController controller = HelloApplication.statMainController();
        double statbudget = 0;
        switch (id){
            case 1: statbudget = statbudget + 1; break;
            case 2: statbudget = statbudget + Double.parseDouble(controller.getPriceBattery().getText()); break;
            case 3: statbudget = statbudget + 10; break;
            case 4: statbudget = statbudget + 10; break;
            case 7: statbudget = statbudget + 10; break;
            default:
                statbudget = statbudget; break;
        }
        controller.updatebudget(statbudget);
    }

    public void updatebudget(double decrease){
        HelloController controller = main.MainController();
        budget = budget - decrease;
        controller.getBudgetlabel().setText(String.valueOf(budget) + "$");
    }

    // SOUND EFFECTS
    public void popSound() {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(this.getClass().getResource("pop.wav"));
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void correctSound() {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(this.getClass().getResource("correct.wav"));
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void wooshSound() {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(this.getClass().getResource("/com/example/electriccircuit/woosh.wav"));
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
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
    public Label getTotalVoltLabel(){
        return totalVolt;
    }
    public Label getTotalAmpLabel(){
        return totalAmp;
    }
    public Label getTotalOhmLabel(){
        return totalOhms;
    }
    public Label getTotalFaradsLabel(){
        return totalFarads;
    }
    public Label getTotalChargeLabel(){
        return totalCharge;
    }
    public Slider getTimeSlider(){
        return timeSlider;
    }

    //Static getters
    public static GridPane returnDataGrid(){
        HelloController controllerx = HelloApplication.statMainController();
        return controllerx.getDataGrid();
    }
    public static Label returnTotalVoltLabel(){
        HelloController controllerx = HelloApplication.statMainController();
        return controllerx.getTotalVoltLabel();
    }
    public static Label returnTotalAmpLabel(){
        HelloController controllerx = HelloApplication.statMainController();
        return controllerx.getTotalAmpLabel();
    }
    public static Label returnTotalOhmLabel(){
        HelloController controllerx = HelloApplication.statMainController();
        return controllerx.getTotalOhmLabel();
    }
    public static Label returnTotalFaradsLabel(){
        HelloController controllerx = HelloApplication.statMainController();
        return controllerx.getTotalFaradsLabel();
    }
    public static Label returnTotalChargeLabel(){
        HelloController controllerx = HelloApplication.statMainController();
        return controllerx.getTotalChargeLabel();
    }
    public static Slider returnTimeSlider(){
        HelloController controllerx = HelloApplication.statMainController();
        return controllerx.getTimeSlider();
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
    public HBox getSmallhboxlvl2(){return this.smallhboxlvl2;}
    public HBox getSmallhboxlvl3(){return this.smallhboxlvl3;}
    public HBox getSmallhboxlvl4(){return this.smallhboxlvl4;}
    public HBox getSmallhboxlvl5(){return this.smallhboxlvl5;}
    public HBox getSmallhboxlvl6(){return this.smallhboxlvl6;}
    public HBox getSmallhboxlvl7(){return this.smallhboxlvl7;}
    public HBox getSmallhboxlvl8(){return this.smallhboxlvl8;}
    public HBox getSmallhboxlvl9(){return this.smallhboxlvl9;}
    public HBox getSmallhboxlvl10(){return this.smallhboxlvl10;}

    public VBox getMainlevelvbox(){return this.mainlevelvbox;}

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
    public HBox getRectanglebottom(){return this.rectanglebottom;}
    public AnchorPane getAnchorpane(){return this.anchorpane;}
    public AnchorPane getSmallanchorpane(){return this.smallanchorpane;}
    public GridPane getTogglegrid(){return this.togglegrid;}
    public ScrollPane getPan(){return this.pan;}
    public ScrollPane getComponentscroll(){return this.componentscroll;}
    public AnchorPane getAnchoringGrid(){return this.anchoringgrid;}

    public TextField getVoltsofbattery(){return voltsofbattery;}
    public TextField getResistanceofresistor(){return resistanceofresistor;}
    public TextField getFaradsofcapacitor(){return faradsofcapacitor;}

    public Label getPriceBattery(){return this.priceBattery;}
    public Label getVoltTitle(){return this.voltTitle;}
    public Label getResitanceTitle(){return this.resitanceTitle;}
    public Label getFaradTitle(){return this.faradTitle;}
    public Label getGoalLabel(){return this.goallabel;}
    public Label getGivenBudgetLabel(){return  this.givenbudgetlabel;}

    public Label getBudgetlabel(){return this.budgetlabel;}

    public VBox getRetractableright(){
        return retractableright;
    }
    public VBox getRectangleright(){
        return rectangleright;
    }
    public VBox getRetractableleft(){
        return retractableleft;
    }
    public VBox getRectangleleft(){
        return rectangleleft;
    }

    public HBox getHinthbox(){
        return hinthbox;
    }

    public ScrollPane getGridscroll(){
        return gridscroll;
    }

    public Label getHintlabel(){return this.hintlabel;}

    //settings getters
    public VBox getSettingsvbox(){return this.settingsvbox;}

    public CheckBox getHintcheck(){return this.hintcheck;}

    //Random getters

}