package com.javaredo.controllers;

import javax.swing.GroupLayout.Alignment;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class GameController {
    @FXML 
    private HBox gameboard;
    
    public GameController(){}

    @FXML
    public void initialize(){
        int rowLength = 7;
        int colLength = 6;

        for (int i = 0; i < rowLength; i++) {
            StackPane columnPane = new StackPane();
            columnPane.setId("Col0");
            VBox column = new VBox(10);

         for (int j = 0; j < colLength; j++) {
            Circle slot = new Circle(30);
            slot.setStyle("-fx-stroke-width:2; -fx-stroke: black; -fx-fill: white;");
    
            StackPane cell = new StackPane(slot);
            cell.setStyle("-fx-padding: 10");
    
            column.getChildren().add(cell);
         }

            columnPane.getChildren().add(column);

            columnPane.setOnMouseEntered(event->{
                columnPane.setStyle("-fx-background-color: lightyellow");

            });

            columnPane.setOnMouseExited(event->{
                columnPane.setStyle("-fx-background-color: lightblue");

            });

            gameboard.getChildren().add(columnPane);
        }

    }

}