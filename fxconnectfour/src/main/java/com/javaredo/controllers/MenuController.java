package com.javaredo.controllers;

import com.javaredo.SceneManager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MenuController {

    @FXML
    private Button NewGameButton;

    @FXML
    private Button QuitGameButton;

    @FXML
    public void initialize() {
        // Initialization logic here
        //set default settings
    }

    @FXML
    private void handleNewGame() {
        System.out.println("Start Game button clicked!");
        SceneManager.getSceneManager().switchScene("/views/GameView.fxml");

    }
    @FXML
    private void handleQuitGame() {
        System.out.println("Quit Game button clicked!");
        javafx.application.Platform.exit();
    }

    @FXML
    private void hadnleQuitGameButton() {
        System.out.println("Quit Game button clicked!");
        javafx.application.Platform.exit();
    }

    @FXML
    private void handleBackToMenuFunction() {
        System.out.println("Quit Game button clicked!");
        javafx.application.Platform.exit();
    }

}