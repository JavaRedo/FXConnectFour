package com.javaredo.model.GameStates;

public interface GameState {
    boolean isGameOver();
    GameOverState getGameOver();
}

