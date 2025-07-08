package com.javaredo.model;

public class GameUtil {
    
    public static boolean checkDiagonalWin(int[][] gameboard, int moveCol, int moveRow, int currentPlayer) {
        return checkMainDiagonal(gameboard,moveRow,moveCol,currentPlayer) || 
               checkAntiDiagonal(gameboard, moveRow, moveCol, currentPlayer); 

    
    }

    

    private static boolean checkMainDiagonal(int[][] gameboard,int moveRow,int moveCol,int currentPlayer) {
        int[] upperRightVector ={-1,1};
        int[] bottomLeftVector ={1,-1};

        int count = 0; 

        count = countInDirection(gameboard,currentPlayer, upperRightVector, moveRow,moveCol,count); 
        count = countInDirection(gameboard,currentPlayer, bottomLeftVector,moveRow,moveCol,count); 
        count +=1;

        boolean isWin = count == 4;

        return isWin;

    }



    private static int countInDirection(int[][] gameboard,int currentPlayer, int[] direction,int moveRow,int moveCol,int count) {
        int nCols = gameboard[0].length;
        int nRows = gameboard.length;
        
        int tempRow = moveRow;
        int tempCol = moveCol;

        tempRow += direction[0];
        tempCol += direction[1];

        while(tempRow >= 0 && tempRow < nRows && tempCol >= 0 && tempCol < nCols && count<3)
        {
            if(gameboard[tempRow][tempCol] == currentPlayer){
                count+=1;
            }
            else{
                break;
            }

            tempRow += direction[0];
            tempCol += direction[1];
            
        }
        return count;
    }

    private static boolean checkAntiDiagonal(int[][] gameboard,int moveRow,int moveCol,int currentPlayer){
        
        int[] upperLeftVector ={-1,-1};
        int[] bottomRightVector ={1,1};

        int count = 0; 

        count =  countInDirection(gameboard,currentPlayer, upperLeftVector, moveRow,moveCol,count);
        count = countInDirection(gameboard,currentPlayer, bottomRightVector,moveRow,moveCol,count);
        
        //count current move
        count+= 1;

        boolean isWin = count == 4;

        return isWin;
    }


    public static boolean checkVerticalWin(int[][] gameboard, int moveCol, int moveRow, int currentPlayer) {
        int[] left ={0,-1};
        int[] right ={0,1};

        int count = 0; 

        count = countInDirection(gameboard,currentPlayer, left, moveRow,moveCol,count); 
        count = countInDirection(gameboard,currentPlayer, right,moveRow,moveCol,count);
        
        count+=1;

        boolean isWin = count == 4;

        return isWin;
    }

    public static boolean checkHorizontalWin(int[][] gameboard, int moveCol, int moveRow, int currentPlayer) {
        int[] up ={-1,0};
        int[] down ={1,0};

        int count = 0; 

        count = countInDirection(gameboard,currentPlayer, up, moveRow,moveCol,count); 
        count = countInDirection(gameboard,currentPlayer, down,moveRow,moveCol,count);
        
        count+=1;

        boolean isWin = count == 4;

        return isWin;
    }
}
