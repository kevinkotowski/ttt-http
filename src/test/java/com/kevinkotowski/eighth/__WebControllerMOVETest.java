package com.kevinkotowski.eighth;

import com.eighthlight.kkotowski.ttt.TttApi;
import com.kevinkotowski.server.HttpRequest;
import com.kevinkotowski.server.IHController;
import com.kevinkotowski.server.IHResponse;
import com.kevinkotowski.server.MockSocket;
import org.junit.Test;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by kevinkotowski on 6/16/16.
 */
public class __WebControllerMOVETest {
    @Test
    public void beginGameMoveButNoGame() throws Exception {
        TttApi gameApi = new TttApi();
        WebGame game = new WebGame(gameApi);
        IHController controller = new WebControllerMOVE(game);

        HttpRequest request = new HttpRequest(new MockSocket());
        request.setContent("move=4");

        IHResponse response = controller.execute(request);

        assertFalse( game.isActive() );
    }

    @Test
    public void beginGameMoveValid() throws Exception {
        TttApi gameApi = new TttApi();
        WebGame game = new WebGame(gameApi);

        game.start();
        IHController controller = new WebControllerMOVE(game);

        HttpRequest request = new HttpRequest(new MockSocket());
        request.setContent("move=4");

        IHResponse response = controller.execute(request);

        assertEquals("X", game.getSquare("4"));
        assertTrue(TestInHeaders.check(response.getHeaders(), "board") );
    }


    @Test
    public void verifyAutoComputerMove() throws Exception {
        TttApi gameApi = new TttApi();
        WebGame game = new WebGame(gameApi);

        game.start();
        IHController controller = new WebControllerMOVE(game);

        HttpRequest request = new HttpRequest(new MockSocket());
        request.setContent("move=1");

        IHResponse response = controller.execute(request);

        assertEquals( "X", game.getSquare("1") );
        assertEquals( "O", game.getSquare("5") );

        // If computer doesn't go automatically, Joshua would be player
        assertEquals( "Homer", game.getTurnPlayerName() );
        assertTrue(TestInHeaders.check(response.getHeaders(), "board") );
    }

    @Test
    public void beginGameMoveINValidX() throws Exception {
        TttApi gameApi = new TttApi();
        WebGame game = new WebGame(gameApi);

        game.start();
        IHController controller = new WebControllerMOVE(game);

        HttpRequest request = new HttpRequest(new MockSocket());
        request.setContent("move=X");

        IHResponse response = controller.execute(request);

        assertEquals("Homer", game.getTurnPlayerName());
        assertTrue(TestInHeaders.check(response.getHeaders(), "bad=X") );
    }

    @Test
    public void beginGameMoveINValidBlank() throws Exception {
        TttApi gameApi = new TttApi();
        WebGame game = new WebGame(gameApi);

        game.start();
        IHController controller = new WebControllerMOVE(game);

        HttpRequest request = new HttpRequest(new MockSocket());
        request.setContent("move=");

        IHResponse response = controller.execute(request);

        assertEquals("Homer", game.getTurnPlayerName());
        assertTrue(TestInHeaders.check(response.getHeaders(), "bad=") );
    }

    @Test
    public void beginGameMoveQuit() throws Exception {
        TttApi gameApi = new TttApi();
        WebGame game = new WebGame(gameApi);

        game.start();
        game.move("5");
        IHController controller = new WebControllerMOVE(game);

        HttpRequest request = new HttpRequest(new MockSocket());
        request.setContent("move=q");

        IHResponse response = controller.execute(request);

        assertFalse( game.isActive() );
        String correctHeader = "Location: /menu.html";
        assertTrue(TestInHeaders.check(response.getHeaders(), correctHeader) );
    }
}
