package com.javaredo.controllers;

import com.javaredo.SceneManager;
import com.javaredo.util.Save;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import com.javaredo.AppContext;

public class SaveController {
    

    @FXML
    private VBox root;

    
    AppContext ctx;
    SceneManager sceneManager;
    
    Save[] saves;
    
    public SaveController(AppContext ctx, SceneManager sceneManager){
        this.ctx = ctx;
        this.sceneManager = sceneManager;    
    }

    @FXML
    public void initialize(){
        this.saves = ctx.getSaveManager().getSaves();

        setSaveUI(saves);
    }

    private void setSaveUI(Save[] saves) {

        for(Save save : saves){
            
            //container
            HBox saveContainer = new HBox();
            saveContainer.setSpacing(20);
            String dateLabel = "";
            
            if(save.getDate() == null){
                dateLabel += "NO DATA";
            }
            else{
                dateLabel += save.getDate().toString();
            }
            
            Label dateOfSave = new Label(dateLabel);
            Label savePosition = new Label(String.valueOf(save.getSavePosition() + 1));
            Button saveButton = new Button("Save");

            saveButton.setOnAction(e ->{
                ctx.getSaveManager().save(ctx.getGameModel(),save.getSavePosition());
                sceneManager.showPrevScene();              
            });
            
            saveContainer.getStyleClass().add("save-container");
            dateOfSave.getStyleClass().add("date-of-save");
            savePosition.getStyleClass().add("save-position");
            saveButton.getStyleClass().add("save-button");
            
            saveContainer.getChildren().addAll(
                savePosition,
                dateOfSave,
                saveButton);

            root.getChildren().addAll(saveContainer);
        }
        
        Button back = new Button("back");

        back.setOnAction(e->{
            sceneManager.showGame();
        });

        root.getChildren().add(
            back
        );
    }


}
