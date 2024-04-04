package com.example.electriccircuit;

import com.example.electriccircuit.Logic.CalculatingGrid;
import com.example.electriccircuit.Logic.Unlocks;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;

import static com.example.electriccircuit.Logic.Debug.Logger;
import static com.example.electriccircuit.Logic.SaveFiles.saveGame;

public class HelloApplication extends Application {

    private Stage primaryStage;
    private Unlocks unlocked = new Unlocks();
    private Scene scene;
    private Node scene1,scene2,scene3,scene4;
    private FXMLLoader fxmlLoader1,fxmlLoader2,fxmlLoader3,fxmlLoader4;
    private HelloController controller1,controller2,controller3,controller4;

    // Add mainContainer
    private StackPane mainContainer = new StackPane();

    @Override
    public void start(Stage primaryStage) throws IOException {
        this.primaryStage = primaryStage;
        primaryStage.setMinHeight(350);
        primaryStage.setMinWidth(675);

        // Initialize mainContainer
        mainContainer = new StackPane();

        scene = new Scene(mainContainer);

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


        //set the main for every screen
        controller1.setMain(this);
        controller2.setMain(this);
        controller3.setMain(this);
        controller4.setMain(this);

        //set unlocked for the level select screen and achievements screen
        controller2.setUnlocks(this.unlocked);
        controller3.setUnlocks(this.unlocked);

        controller1.titleinitialize();

        primaryStage.setTitle("Hello!");
        primaryStage.setScene(scene);
        primaryStage.setMaximized(true);
        primaryStage.show();

        primaryStage.setOnCloseRequest( e -> {
            saveGame();
            Logger.close();
        });
    }

    public StackPane getMainContainer() {
        return mainContainer;
    }

    //Title screen switching methods
    public Node switchToTitle(){
        return scene1;
    }
    public HelloController TitleController(){return controller1;}

    //Level select switching methods
    public Node switchToLevelSelect(){
        return scene2;
    }
    public HelloController LevelSelectController(){return controller2;}


    //Achievements switching methods
    public HelloController AchievementsController(){
        return controller3;
    }
    public Node switchToAchievements(){
        return scene3;
    }

    //MainScreen switching methods
    public Node switchToMainScreen(){
        return scene4;
    }

}