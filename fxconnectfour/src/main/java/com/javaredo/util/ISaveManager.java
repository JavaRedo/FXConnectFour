package com.javaredo.util;

import com.javaredo.model.GameModel;

public interface ISaveManager {
    
    public boolean save(GameModel model,int savePos);
    public GameModel load(int savePos);
}
