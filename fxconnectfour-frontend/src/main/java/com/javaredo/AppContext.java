package com.javaredo;

import com.javaredo.model.GameModel;
import com.javaredo.util.SaveManager;

import javafx.util.Pair;

public class AppContext {
    
    private  GameModel model;
    private  SaveManager saveManager;
    private Pair<String,String> authenticatedCredentials;

    public AppContext(
        GameModel model,
        SaveManager saveManager){
        
        this.model = model;
        this.saveManager = saveManager;

        model.initialize();
        saveManager.initialize();
    }


    public GameModel getGameModel(){
        return this.model;
    }

    public SaveManager getSaveManager(){
        return this.saveManager;
    }


    public void setGameModel(GameModel gameModel) {
        this.model = gameModel;
    }


    public void setAuthenticatedCredentials(String username, String password) {
        this.authenticatedCredentials = new Pair<String,String>(username, password);
    }



    
}
