package com.javaredo.controllers;

import com.javaredo.model.GameModel;
import com.javaredo.model.GameState;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
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
    
    public GameController(){}

    @FXML
    public void initialize(){
        int rowLength = 7;
        int colLength = 6;
        this.model = new GameModel(colLength, rowLength, 1);
        this.model.initialize();
        
        this.slots = new Circle[colLength][rowLength];

        setGameBoardUI(rowLength, colLength);
        
        //Centre the Game over overlay content 
        StackPane.setMargin(overlayPaneContent, new Insets(100,0,0,450));
        StackPane.setMargin(gameboard, new Insets(60,0,0,325));

    }

    private void setGameBoardUI(int rowLength, int colLength) {

        for (int i = 0; i < rowLength; i++) {

            StackPane columnPane = new StackPane(); 

            //each column holds its column value needed for
            //updating the board
            columnPane.setUserData(i);

            VBox column = new VBox(10);

         for (int j = 0; j < colLength; j++) {
            //create slot and style
            Circle slot = new Circle(30);
            slot.setStyle("-fx-stroke-width:2; -fx-stroke: black; -fx-fill: white;");

            //create cell and style 
            StackPane cell = new StackPane(slot);
            cell.setStyle("-fx-padding: 10");

            //scene graph for a column
            //clumnPane stack pane -> column VBox -> cell stack pane -> slot Circle

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
     * Mouse entered event
     * Mouse exited event
     * Mouse clicked event 
     * 
     * for the column pane input component
     * @param columnPane
     */
    private void setUpColumnEvents(StackPane columnPane) {
        columnPane.setOnMouseEntered(event->{
            columnPane.setStyle("-fx-background-color: lightyellow");

        });

        columnPane.setOnMouseExited(event->{
            columnPane.setStyle("-fx-background-color: lightblue");
            
        });

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
                    slot.setStyle("-fx-stroke-width:2; -fx-stroke: black; -fx-fill: red;");

                }
                else{
                    slot.setStyle("-fx-stroke-width:2; -fx-stroke: black; -fx-fill: yellow;");
                }
                
                model.updateActivePlayer();
                if(model.getGameState() == GameState.GAMEOVER){
                    // gameboard.getChildren().clear();
                    // Label gameEndLabel = new Label("Player " + model.getActivePlayer() + " wins");
                    // gameEndLabel.setStyle("-fx-font-size: 32");
                    // gameboard.getChildren().add(gameEndLabel);

                    // gameboard.getChildren()

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
        initialize();
    }

}