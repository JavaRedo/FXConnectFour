package com.javaredo.model.GameStates;


public enum PlayingState implements GameState{
    PLAYER_1_TURN{
        @Override
        public boolean isGameOver() {
            return false;
        }

        @Override
        public GameOverState getGameOver() {
            return GameOverState.PLAYER_1_WIN;
        }

        
    },
    PLAYER_2_TURN{
        @Override
        public boolean isGameOver() {
            return false;
        }

        @Override
        public GameOverState getGameOver() {
            return GameOverState.PLAYER_2_WIN;
        }
    }
}
