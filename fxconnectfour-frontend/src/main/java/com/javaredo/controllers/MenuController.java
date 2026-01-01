package com.javaredo.controllers;

import com.javaredo.SceneManager;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MenuController {

    @FXML
    private Button NewGameButton;

    @FXML
    private Button QuitGameButton;
    
    SceneManager sceneManager;
 
    public MenuController(SceneManager sceneManager){
        this.sceneManager = sceneManager;
    }

    @FXML
    public void initialize() {
        // Initialization logic here
        //set default settings
    }

    @FXML
    private void handleNewGame() {
        System.out.println("Start Game button clicked!");
        sceneManager.showGame();

    }

    @FXML
    private void handleLogIn() {
        System.out.println("Log in clicked!");
        sceneManager.showLogIn();

    }
    @FXML
    private void handleQuitGame() {
        System.out.println("Quit Game button clicked!");
        javafx.application.Platform.exit();
    }

        @FXML
    private void handleLoadGame() {
        sceneManager.showLoad();
    }


    @FXML
    private void handleBackToMenuFunction() {
        System.out.println("Quit Game button clicked!");
        javafx.application.Platform.exit();
    }

}