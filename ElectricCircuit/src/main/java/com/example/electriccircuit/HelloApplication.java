package com.example.electriccircuit;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {

    private Stage primaryStage;
    private Scene scene;
    private Node scene1,scene2,scene3,scene4;
    private FXMLLoader fxmlLoader1,fxmlLoader2,fxmlLoader3,fxmlLoader4;
    private HelloController controller1,controller2,controller3,controller4;

    @Override
    public void start(Stage primaryStage) throws IOException {
        this.primaryStage = primaryStage;

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("title.fxml"));
        scene = new Scene(fxmlLoader.load());

        FXMLLoader fxmlLoader1 = new FXMLLoader(HelloApplication.class.getResource("title.fxml"));
        scene1 = fxmlLoader1.load();

        FXMLLoader fxmlLoader2 = new FXMLLoader(HelloApplication.class.getResource("LevelSelection.fxml"));
        scene2 = fxmlLoader2.load();

        FXMLLoader fxmlLoader3 = new FXMLLoader(HelloApplication.class.getResource("Achievements screen.fxml"));
        scene3 = fxmlLoader3.load();
        controller3 = fxmlLoader3.getController();

        FXMLLoader fxmlLoader4 = new FXMLLoader(HelloApplication.class.getResource("MainScreen.fxml"));
        scene4 = fxmlLoader4.load();

        HelloController controller = fxmlLoader.getController();
        controller.setMain(this);
        controller.titleinitialize();

        primaryStage.setTitle("Hello!");
        primaryStage.setScene(scene);
        primaryStage.setMaximized(true);
        primaryStage.show();

    }

    public HelloController AchievementsController(){
        return controller3;
    }
    public Node switchToAchievements(){
        return scene3;
    }

    public Node switchToMainScreen(){
        return scene4;
    }

}