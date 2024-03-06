package com.example.electriccircuit;

import com.example.electriccircuit.Logic.CalculatingGrid;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import com.example.electriccircuit.Logic.BuilderMatrix;

import java.io.IOException;

import static com.example.electriccircuit.Logic.SaveFiles.saveGame;

public class HelloApplication extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("MainScreen.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        primaryStage.setTitle("Hello!");
        primaryStage.setScene(scene);
        primaryStage.setMaximized(true);
        primaryStage.show();

        HelloController controller = fxmlLoader.getController();
        controller.titleinitialize();
        primaryStage.setOnCloseRequest( e -> {
            saveGame();
            CalculatingGrid calc = new CalculatingGrid();
        });

    }
}