package com.javaredo.util;
import java.time.LocalDateTime;

import com.javaredo.model.GameModel;

public interface ISave {

    int getSavePosition();
    void setSavePosition(int position);

    void setDate(LocalDateTime dateTime);
    LocalDateTime getDate();
    
    GameModel getModel();
    void setModel(GameModel model);
}
