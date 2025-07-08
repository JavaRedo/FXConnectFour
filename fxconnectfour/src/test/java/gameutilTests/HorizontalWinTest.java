package gameutilTests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.javaredo.model.GameModel;
import com.javaredo.model.GameState;
import com.javaredo.model.GameUtil;

public class HorizontalWinTest {

    GameModel model = new GameModel(6, 7, 1);

    @BeforeEach
    void testsSetUp() {
        this.model = new GameModel(6, 7, 1);
        model.initialize();
    }

    @Test
    void test1() {

        int[] moves = { 2, 3, 3, 4, 4, 1, 1, 2, 1, 1, 3,
                5, 4, 6, 3, 4, 4, 4, 5, 5, 5, 5,
                2, 1, 0, 1, 2, 2, 3, 2, 0, 0, 2,
                1, 2, 3, 5, 6 };

        for (int i = 0; i < moves.length; i++) {
            this.model.insertToken(moves[i]);
        }

        System.out.println(model.getGameState());
        assertEquals(model.getGameState().toString(), "GAMEOVER");
        assertEquals(model.getActivePlayer(),1);
    }

    @Test
    void test2(){


        int[] moves = {1,0,5,5,4,3,3,3,1,1,2,3,2,5,4,5,1,2,0,4,0,5};

        for (int i = 0; i < moves.length; i++) {
            this.model.insertToken(moves[i]);
        }
        
        System.out.println(model.getGameState());

        assertEquals(model.getGameState().toString(),"GAMEOVER");
        assertEquals(model.getActivePlayer(),1);
    }


    @Test
    void interruptAndJoin(){
        //should detect win after joining a horizontal streak
        // by inserting in the middle

        int[] moves = {0,0,1,1,3,3,4,4,2};

        for (int i = 0; i < moves.length; i++) {
            this.model.insertToken(moves[i]);
        }
        
        System.out.println(model.getGameState());

        assertEquals(model.getGameState().toString(),"GAMEOVER");
        assertEquals(model.getActivePlayer(),1);
    }



}
