package com.kevinkotowski.eighth;

import com.eighthlight.kkotowski.ttt.TttApi;
import org.junit.Test;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by kevinkotowski on 6/15/16.
 */
public class __WebGame {
    @Test
    public void getGameData() throws Exception {
        TttApi gameApi = new TttApi();
        WebGame game = new WebGame(gameApi);
        game.start();

        assertTrue(game.isActive());
//        WebGameBoard board = new WebGameBoard(game);
//        assertEquals( "PLAYER1", board.getTurn() );
//        assertEquals( "Homer", board.getTurnPlayerName() );
//        assertEquals( "4", board.getMoveReco() );
//        assertEquals( "0,0,0,0,0,0,0,0,0", board.getMoveReco() );
//        assertTrue( board.isAvailable("0") );
    }

    @Test
    public void getGameBoard() throws Exception {
        TttApi gameApi = new TttApi();
        WebGame game = new WebGame(gameApi);
        game.start();

        String[] board = game.getBoard();
        int count = 0;
        for (String square : board) {
            count++;
            assertTrue(square.equals("0"));
        }
        assertEquals( 9,count );
    }

    @Test
    public void getGameSquare() throws Exception {
        TttApi gameApi = new TttApi();
        WebGame game = new WebGame(gameApi);
        game.start();

        game.move("4");
        String square = game.getSquare("4");
        assertEquals( "X", square );

        game.move("8");
        square = game.getSquare("8");
        assertEquals( "O", square );
    }

    @Test
    public void moveAndCheckSquareAvailability() throws Exception {
        TttApi gameApi = new TttApi();
        WebGame game = new WebGame(gameApi);
        game.start();

        assertTrue(game.isAvailable("9"));
        game.move("9");
        assertFalse(game.isAvailable("9"));
    }

    @Test
    public void playToEndgame() throws Exception {
        TttApi gameApi = new TttApi();
        WebGame game = new WebGame(gameApi);

        game.start();
        game.move("1");
        game.move("7");
        game.move("5");
        game.move("2");
        game.move("9");

        System.out.println(gameApi.getStatus());
        assertFalse( game.isActive() );
        assertTrue( game.isEndgame() );

        System.out.println(game.getBoard() );
    }

    @Test
    public void quitGameIsEndgame() throws Exception {
        TttApi gameApi = new TttApi();
        WebGame game = new WebGame(gameApi);

        game.start();
        assertTrue( game.isActive() );
        assertFalse( game.isEndgame() );

        game.quit();
        System.out.println(gameApi.getStatus());
        assertFalse( game.isActive() );
        assertFalse( game.isEndgame() );
    }
}
