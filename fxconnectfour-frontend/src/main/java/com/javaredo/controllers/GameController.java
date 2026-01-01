package com.javaredo.controllers;

import com.javaredo.SceneManager;
import com.javaredo.model.GameModel;


import javafx.fxml.FXML;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;

public class GameController {
    @FXML 
    private HBox gameboard;

    @FXML
    private StackPane overlayPane;

    @FXML
    private VBox overlayPaneContent;
    //slots[row][column]
    private Circle[][] slots;

    private GameModel model;
    private SceneManager sceneManager;
    
    public GameController(SceneManager sceneManager, GameModel model){
        this.model = model;
        this.sceneManager = sceneManager;
    }
    
    @FXML
    public void initialize(){
        //settings and model object needed 

        // model.nRows = col length
        // model.nCols = row length

        this.slots = new Circle[model.getRowsLen()][model.getColsLen()];

        this.setGameBoardUI(model.getColsLen(), model.getRowsLen());

    }

    private void setGameBoardUI(int rowLength, int colLength) {

        for (int i = 0; i < rowLength; i++) {

            StackPane columnPane = new StackPane(); 

            //each column holds its column value needed for
            //updating the board
            columnPane.setUserData(i);
            columnPane.getStyleClass().add("columnPane");

            VBox column = new VBox(10);

         for (int j = 0; j < colLength; j++) {
            //create slot and style
            Circle slot = new Circle(30);
            StackPane cell = new StackPane(slot);
            
            //create cell and style 
            slot.getStyleClass().add("slot");
            
            int currentToken = model.getTokenAt(j,i);
            if(currentToken == 1){
                slot.getStyleClass().add("player1token");
            }
            else if(currentToken == 2){
                slot.getStyleClass().add("player2token");                
            }

            cell.getStyleClass().add("cell");

            //scene graph for a column
            //columnPane stack pane -> column VBox -> cell stack pane -> slot Circle

            column.getChildren().add(cell);

            //add slot to 2d array for lookup when modifying scene graph
            slots[j][i] = slot;

         }

            columnPane.getChildren().add(column);

            setUpColumnEvents(columnPane);

            gameboard.getChildren().add(columnPane);
        }
    }

    /**
     * Sets: 
     * 
     * Mouse clicked event 
     * 
     * for the column pane input component
     * @param columnPane
     */
    private void setUpColumnEvents(StackPane columnPane) {

        columnPane.setOnMouseClicked(event->{
            int col = (int) columnPane.getUserData();
            System.out.println(col);
            int selectedRow = model.insertToken(col);
            
            if(selectedRow == -1){
                System.out.println("Column is full chose empty slot");
            }
            else{
                Circle slot = slots[selectedRow][col];

                if(model.getActivePlayer() == 1){
                    slot.getStyleClass().add("player1token");
                }
                else{
                    slot.getStyleClass().add("player2token");
                }
                
                model.updateActivePlayer();
                
                if(model.getGameState().isGameOver()){
                    overlayPane.setVisible(true);
                    overlayPane.setMouseTransparent(false);

                }
            }
            
        });
    }

    @FXML    
    private void handleRestartButton(){
        gameboard.getChildren().clear();
        overlayPane.setVisible(false);
        overlayPane.setMouseTransparent(true);
        this.model.initialize();
        initialize();
    }

    @FXML
    private void handleQuitGame(){
        javafx.application.Platform.exit();
    };
    
    @FXML
    private void handleSaveGame(){
        sceneManager.showSave();
    };
    
    @FXML
    private void handleLoadGame(){
        sceneManager.showLoad();
    };

}