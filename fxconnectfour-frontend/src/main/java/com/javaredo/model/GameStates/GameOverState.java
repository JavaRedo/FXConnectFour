package com.javaredo.model.GameStates;

public enum GameOverState implements GameState{
    PLAYER_1_WIN,
    PLAYER_2_WIN,
    DRAW;

    @Override
    public boolean isGameOver() {
        return true;
    }

    @Override
    public GameOverState getGameOver() {
        return this;
    }
}
