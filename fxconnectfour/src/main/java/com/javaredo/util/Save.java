package com.javaredo.util;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.javaredo.model.GameModel;

public class Save implements ISave,Serializable{

    private int savePosition;
    private LocalDateTime saveDate; 
    private GameModel model;

    public Save(int savePos){
        this.savePosition = savePos;
        this.model = null;
        this.saveDate = null;

    }
    @Override
    public int getSavePosition() {return this.savePosition;}

    @Override
    public void setSavePosition(int position) {
        this.savePosition = position;
    }

    @Override
    public void setDate(LocalDateTime dateTime) {
        this.saveDate = dateTime;
    }

    @Override
    public LocalDateTime getDate() {
        return this.saveDate;
    }

    @Override
    public void setModel(GameModel model) {
        this.model = model;
    }
    @Override
    public GameModel getModel() {
        return this.model;
    }

    
    
}
