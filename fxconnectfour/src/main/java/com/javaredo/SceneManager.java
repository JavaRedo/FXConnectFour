package com.javaredo;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * The Scene Manager sigleton class handles the 
 * scene switching and management for the 
 * Connect Four game application.
 *    
 * @author Arshad Hamza
 * @version 1.0
 * @since 2023-10-01
 */
public class SceneManager {
    
    private static SceneManager instance;
    private Stage primaryStage;
    
    
    private SceneManager(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    // static methods

    public static void initialize(Stage primaryStage) {
        instance = new SceneManager(primaryStage);
    }

    public static SceneManager getSceneManager(){
        return instance;
    }

    //non static methods

    public void switchScene(String fxmlpath){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlpath));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
