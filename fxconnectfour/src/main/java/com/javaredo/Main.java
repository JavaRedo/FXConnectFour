package com.javaredo;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends javafx.application.Application {
    
    @Override
    public void start(Stage primaryStage){

        SceneManager.initialize(primaryStage);

        primaryStage.setResizable(false);
        primaryStage.setHeight(720);
        primaryStage.setWidth(1280);

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/MenuView.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            primaryStage.setScene(scene);
            primaryStage.setTitle("FXConnectFour");
            primaryStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    } 
    public static void main(String[] args) {
        launch(args);
    }


}