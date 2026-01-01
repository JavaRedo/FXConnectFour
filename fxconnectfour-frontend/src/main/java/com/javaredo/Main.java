package com.javaredo;

import com.javaredo.model.GameModel;
import com.javaredo.util.FontLoader;
import com.javaredo.util.SaveManager;

import javafx.stage.Stage;

public class Main extends javafx.application.Application {
    
    @Override
    public void start(Stage primaryStage){
        
        AppContext ctx = new AppContext(
            new GameModel(6, 7, 1),
            new SaveManager()
            );
            
        FontLoader.load("/fonts/fredoka/Static/Fredoka-Medium.ttf", 8*5);
        
        configStage(primaryStage);

        SceneManager sm = new SceneManager(primaryStage,ctx);
        
        sm.showMenu();

    }
    private void configStage(Stage primaryStage) {
        primaryStage.setResizable(false);
        primaryStage.setHeight(720);
        primaryStage.setWidth(1280);
    } 
    public static void main(String[] args) {
        launch(args);
    }


}