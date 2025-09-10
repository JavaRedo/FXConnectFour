package com.javaredo.model;

import java.io.Serializable;

import com.javaredo.model.GameStates.GameState;
import com.javaredo.model.GameStates.PlayingState;

public class GameModel implements Serializable {

    private int[][] board;
    private int[] nextAvailableRow;
    private int nRows;
    private int nCols;
    private int currentPlayer;
    private GameState state;

    public GameModel(int nRows,int nCols,int currentPlayer){
        this.nRows = nRows;
        this.nCols = nCols;
        this.board = new int[nRows][nCols];
        this.nextAvailableRow = new int[nCols];
        this.currentPlayer = currentPlayer;

    }

    public void initialize(){
        //2d array of board entries are either 0 or 1
        //0 -> empty
        //1 -> first player
        //2 -> second player

        resetBoard();

        resetNARds();

        this.state = PlayingState.PLAYER_1_TURN;
    }

    private void resetNARds() {
        for (int j = 0; j < nextAvailableRow.length; j++) {
            nextAvailableRow[j] = nRows-1;    
        }
    }

    private void resetBoard() {
        for (int i = 0; i < this.nRows; i++) {
            int[] col = board[i];
            for (int j = 0; j < this.nCols; j++) {
                col[j] = 0;
            }
        }
    }

    public int insertToken(int moveCol){
        int moveRow = nextAvailableRow[moveCol];

        if(moveRow < 0){
            return -1;
        }
        
        board[moveRow][moveCol] =  currentPlayer;

        winCheck(this.board,moveCol,moveRow);

        nextAvailableRow[moveCol] -=1;
        
        if(state.isGameOver()){
            return moveRow;
        }
        //for debugging
        //printModelBoard();
        return moveRow;
    }

    private void winCheck(int[][] gameboard,int moveCol, int moveRow){
        boolean isWin = GameUtil.checkDiagonalWin(gameboard,moveCol,moveRow,this.currentPlayer) ||
                GameUtil.checkVerticalWin(gameboard,moveCol,moveRow,this.currentPlayer) || 
                GameUtil.checkHorizontalWin(gameboard,moveCol,moveRow,this.currentPlayer);
        if(isWin){
            this.state = state.getGameOver();
        }
        }

    public void updateActivePlayer() {
        if(this.currentPlayer == 1){
            this.currentPlayer = 2;
        }
        else{
            this.currentPlayer = 1;
        }
    }

    public int getActivePlayer() {
        return this.currentPlayer;    
    }

    // private void printModelBoard(){

    //     for (int i = 0; i < nRows; i++) {
    //         for (int j = 0; j < nCols; j++) {
    //             int element = this.board[i][j];
    //             System.out.print(element + " ");
    //         }
    //         System.out.println("");
    //     }
    // }

    public GameState getGameState(){
        return state;
    }

    public int getRowsLen(){
        return this.nRows;
    }
    public int getColsLen(){
        return this.nCols;
    }

    public int getTokenAt(int row,int col) {

        return this.board[row][col];
    }

}
