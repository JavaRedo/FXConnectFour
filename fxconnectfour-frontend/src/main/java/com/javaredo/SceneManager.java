package com.javaredo;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.function.Function;

import com.javaredo.controllers.GameController;
import com.javaredo.controllers.LoadController;
import com.javaredo.controllers.LoginController;
import com.javaredo.controllers.MenuController;
import com.javaredo.controllers.SaveController;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Pair;

/**
 * The Scene Manager class handles the 
 * scene switching and management for the 
 * Connect Four game application.
 *    
 * @author Arshad Hamza
 * @version 1.0
 * @since 2023-10-01
 */
public class SceneManager {
    
    private Stage primaryStage;
    private AppContext ctx;
    private Deque<Pair<String,Function<Class<?>, Object>>> prevSceneDeque;
    private Pair<String,Function<Class<?>, Object>> prevScene;
    
    public SceneManager(Stage primaryStage,AppContext ctx) {
        this.primaryStage = primaryStage;
        this.ctx = ctx;
        this.prevSceneDeque = new ArrayDeque<>(1);
    }

    //Note: abatract the navigation by exposing a single method that 
    // takes in an enum which maps to the fxml file and dedicated controller
    
    //Note: each controller only concerns itself in using prvided
    // app context
    public void showMenu(){
        load("/views/menuView/MenuView.fxml", type -> {
                return new MenuController(this);
        });
    }

    public void showGame(){
        load("/views/gameView/GameView.fxml", type -> {
                return new GameController(this,this.ctx.getGameModel());
        });
    }
    
    public void showSave(){
        load("/views/saveView/SaveView.fxml", type -> {
                return new SaveController(this.ctx,this);
        });
    }

    public void showLoad(){
        load("/views/loadView/LoadView.fxml", type -> {
                return new LoadController(this.ctx,this);
        });
    }
    public void showLogIn(){
        load("/views/loginView/LoginView.fxml", type -> {
                return new LoginController(this.ctx,this);
        });
    }

    public void showPrevScene() {
        Function<Class<?>,Object> factory = prevScene.getValue();
        String fxmlpath = prevScene.getKey();

        load(fxmlpath, factory);
    }
    
    public void load(String fxmlpath,Function<Class<?>, Object> factory){
    
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlpath));
            loader.setControllerFactory(factory::apply);
            Parent root = loader.load();
            System.out.println("loaded fxml");

            
            
            Scene newScene = new Scene(root);

            //update prev scene field with top of queue
            if(prevSceneDeque.size() == 1){
                this.prevScene = prevSceneDeque.pop();    
            }

            //if current scene the same as prev scene no need to add it to queue
            // if(prevScene != null && prevScene.getKey().equals(fxmlpath)){
            //     primaryStage.setScene(newScene);
            //     primaryStage.show();
            //     return;
            // }

            prevSceneDeque.add(new Pair<String,Function<Class<?>, Object>>(fxmlpath,factory));
            
            primaryStage.setScene(newScene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    

}