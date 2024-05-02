package com.example.electriccircuit;

import com.example.electriccircuit.Logic.CalculatingGrid;
import com.example.electriccircuit.Logic.Unlocks;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;

import static com.example.electriccircuit.Logic.Debug.Logger;
import static com.example.electriccircuit.Logic.SaveFiles.loadGame;
import static com.example.electriccircuit.Logic.SaveFiles.saveGame;

public class HelloApplication extends Application {

    private Stage primaryStage;
    private Unlocks unlocked = new Unlocks();
    private Scene scene;
    private Node scene1,scene2,scene3,scene4,scene5;
    //private FXMLLoader fxmlLoader1,fxmlLoader2,fxmlLoader3,fxmlLoader4;
    private HelloController controller1,controller2,controller3,controller4,controller5;
    private static HelloController controllerx;
    private static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    public static int screenWidth = (int) Math.ceil(screenSize.getWidth());
    public static int screenHeight = (int) Math.ceil(screenSize.getHeight());


    //No arg constructor
    public HelloApplication(){};

    // Add mainContainer
    private StackPane mainContainer = new StackPane();

    @Override
    public void start(Stage primaryStage) throws IOException {
        this.primaryStage = primaryStage;
        primaryStage.setMinHeight(350);
        primaryStage.setMinWidth(675);

        // Initialize mainContainer
        mainContainer = new StackPane();
        mainContainer.setAlignment(Pos.CENTER);

        scene = new Scene(mainContainer);
        scene.getStylesheets().add(getClass().getResource("styleSheet.css").toExternalForm());

        FXMLLoader fxmlLoader1 = new FXMLLoader(HelloApplication.class.getResource("title.fxml"));
        scene1 = fxmlLoader1.load();
        controller1 = fxmlLoader1.getController();

        FXMLLoader fxmlLoader2 = new FXMLLoader(HelloApplication.class.getResource("LevelSelection.fxml"));
        scene2 = fxmlLoader2.load();
        controller2 = fxmlLoader2.getController();

        FXMLLoader fxmlLoader3 = new FXMLLoader(HelloApplication.class.getResource("Achievements screen.fxml"));
        scene3 = fxmlLoader3.load();
        controller3 = fxmlLoader3.getController();

        FXMLLoader fxmlLoader4 = new FXMLLoader(HelloApplication.class.getResource("MainScreen.fxml"));
        scene4 = fxmlLoader4.load();
        controller4 = fxmlLoader4.getController();
        controllerx = fxmlLoader4.getController();

        FXMLLoader fxmlLoader5 = new FXMLLoader(HelloApplication.class.getResource("Settings screen.fxml"));
        scene5 = fxmlLoader5.load();
        controller5 = fxmlLoader5.getController();

        //set the main for every screen
        controller1.setMain(this);
        controller2.setMain(this);
        controller3.setMain(this);
        controller4.setMain(this);
        controller5.setMain(this);

        //set unlocked for the level select screen and achievements screen
        controller2.setUnlocks(this.unlocked);
        controller3.setUnlocks(this.unlocked);

        controller1.titleinitialize();
        primaryStage.setTitle("Electrisim");
        primaryStage.setScene(scene);
        primaryStage.setMaximized(true);
        primaryStage.show();

        primaryStage.setOnCloseRequest( e -> {
            if(CalculatingGrid.thread1 != null){
                if(CalculatingGrid.thread1.isAlive()){
                    CalculatingGrid.thread1.interrupt();
                    System.out.println("thread 1 closed");
                }
            } if(CalculatingGrid.thread2 != null){
                if(CalculatingGrid.thread2.isAlive()){
                    CalculatingGrid.thread2.interrupt();
                    System.out.println("thread 2 closed");
                }
            }
            saveGame();
            Logger.close();
            System.exit(0);
        });
    }

    public StackPane getMainContainer() {
        return mainContainer;
    }

    public HelloController MainController() {
        return controller4;
    }

    public static HelloController statMainController() {
        return controllerx;
    }

    //Method to set minimum of a screen
    public void setMin(){
        primaryStage.setMinHeight((Math.round((float) screenHeight /3)));
        primaryStage.setMinWidth((Math.round((float) screenWidth /3)));
    }

    //Title screen switching methods
    public Node switchToTitle(){
        setMin();
        return scene1;
    }
    public HelloController TitleController(){return controller1;}

    //Level select switching methods
    public Node switchToLevelSelect(){
        setMin();
        return scene2;
    }
    public HelloController LevelSelectController(){return controller2;}


    //Achievements switching methods
    public HelloController AchievementsController(){
        return controller3;
    }
    public Node switchToAchievements(){
        setMin();
        return scene3;
    }

    //MainScreen switching methods
    public Node switchToMainScreen(){
        primaryStage.setMinHeight(402);
        primaryStage.setMinWidth(743);
        return scene4;
    }

    //Settings
    public Node settings(){return scene5;}
    public HelloController settingsController(){return controller5;}

    //Other stuff
    public int getScreenWidth(){
        return screenWidth;
    }
    public int getScreenHeight(){
        return screenHeight;
    }

}