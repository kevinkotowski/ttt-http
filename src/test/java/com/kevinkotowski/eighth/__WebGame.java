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

        assertTrue(game.isSquareAvailable("9"));
        game.move("9");
        assertFalse(game.isSquareAvailable("9"));
    }

    @Test
    public void getWinnerName() throws Exception {
        TttApi gameApi = new TttApi();
        WebGame game = new WebGame(gameApi);

        game.start();
        game.move("1");
        game.move("7");
        game.move("5");
        game.move("2");
        game.move("9");

        String winner = game.getWinnerName();
        assertEquals( "Homer", winner );
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

        assertFalse( game.isActive() );
        assertTrue( game.isEndgame() );
    }

    @Test
    public void quitGameIsNotEndgame() throws Exception {
        TttApi gameApi = new TttApi();
        WebGame game = new WebGame(gameApi);

        game.start();
        assertTrue( game.isActive() );
        assertFalse( game.isEndgame() );

        game.quit();
        assertFalse( game.isActive() );
        assertFalse( game.isEndgame() );
    }

    @Test
    public void getReco() throws Exception {
        TttApi gameApi = new TttApi();
        WebGame game = new WebGame(gameApi);

        game.start();
        game.move("1");

        String reco = game.getMoveReco();

        assertEquals( "5", reco );
    }

    @Test
    public void getNextTurnPlayerName() throws Exception {
        TttApi gameApi = new TttApi();
        WebGame game = new WebGame(gameApi);

        game.start();
        game.move("1");

        String player = game.getTurnPlayerName();

        assertEquals( "Joshua", player );
    }

    @Test
    public void restartGame() throws Exception {
        TttApi gameApi = new TttApi();
        WebGame game = new WebGame(gameApi);

        game.start();
        game.move("1");

        game.restart();
        // this would throw a Position is not available if not for the restart
        game.move("1");
        assertTrue( game.isActive() );
        assertFalse( game.isEndgame() );
    }
}
