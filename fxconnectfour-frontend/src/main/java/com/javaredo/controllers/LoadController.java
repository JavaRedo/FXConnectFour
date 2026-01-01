package com.javaredo.controllers;

import java.time.LocalDateTime;

import com.javaredo.AppContext;
import com.javaredo.SceneManager;
import com.javaredo.model.GameModel;
import com.javaredo.util.Save;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class LoadController {
    
      @FXML
    private VBox root;

    
    AppContext ctx;
    SceneManager sceneManager;
    
    Save[] saves;
    public LoadController(AppContext ctx, SceneManager sceneManager){
        this.ctx = ctx;
        this.sceneManager = sceneManager;    
    }

    @FXML
    public void initialize(){
        this.saves = ctx.getSaveManager().getSaves();

        setLoadUI(saves);
    }

    private void setLoadUI(Save[] saves) {

        for(Save save : saves){
                    
            //container
            HBox loadContainer = new HBox();
            loadContainer.setSpacing(20);
            String dateLabel = "DATE: ";
            String timeLabel = "TIME: ";
            
            if(save.getDate() == null){
                dateLabel += "NO DATA";
                timeLabel += "NO DATA";
            }
            else{
                LocalDateTime date = save.getDate();
                String hour = String.valueOf(date.getHour());
                String minutes = String.valueOf(date.getMinute());
                String month = String.valueOf(date.getMonthValue());
                String day = String.valueOf(date.getMonthValue());
                String year = String.valueOf(date.getYear());

                dateLabel += day + "-" + month + "-" + year;
                timeLabel += hour + ":" + minutes;

            }
            
            Label dateOfSave = new Label(dateLabel);
            Label timeOfSave = new Label(timeLabel);
            Label savePosition = new Label(String.valueOf(save.getSavePosition() + 1));
            Button loadButton = new Button("Load");
            Button deleteButton = new Button("Delete");

            HBox buttonRegion = new HBox();
            buttonRegion.setSpacing(20);
            HBox.setHgrow(buttonRegion, Priority.ALWAYS);
            buttonRegion.setAlignment(Pos.CENTER_RIGHT); 
            buttonRegion.getChildren().addAll(loadButton,deleteButton);

            loadButton.setOnAction(e ->{
                GameModel savedModel = ctx.getSaveManager().load(save.getSavePosition());
                ctx.setGameModel(savedModel);
                sceneManager.showGame();                
            });

            deleteButton.setOnAction(e->{
                ctx.getSaveManager().deleteSave(save.getSavePosition());
                //reset ui, can change the
                root.getChildren().clear();
                initialize();
            });
            
            loadContainer.getStyleClass().add("load-container");
            dateOfSave.getStyleClass().add("date-of-save");
            timeOfSave.getStyleClass().add("time-of-save");
            savePosition.getStyleClass().add("save-position");
            loadButton.getStyleClass().add("load-button");
            deleteButton.getStyleClass().add("delete-button");
            buttonRegion.getStyleClass().add("button-region");
            
            loadContainer.getChildren().addAll(
                savePosition,
                dateOfSave,
                timeOfSave,
                buttonRegion);

            root.getChildren().addAll(loadContainer);
        }
        Button back = new Button("back");

        back.setOnAction(e->{
            sceneManager.showPrevScene();
        });

        root.getChildren().add(
            back
        );



    }
}
