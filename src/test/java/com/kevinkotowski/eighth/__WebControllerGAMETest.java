package com.kevinkotowski.eighth;

import com.eighthlight.kkotowski.ttt.TttApi;
import com.kevinkotowski.server.HttpRequest;
import com.kevinkotowski.server.IHController;
import com.kevinkotowski.server.IHResponse;
import com.kevinkotowski.server.MockSocket;
import org.junit.Test;

import java.util.List;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by kevinkotowski on 6/15/16.
 */
public class __WebControllerGAMETest {
    @Test
    public void beginGameMenuPlay() throws Exception {
        TttApi gameApi = new TttApi();
        WebGame game = new WebGame(gameApi);
        IHController controller = new WebControllerGAME(game);

        HttpRequest request = new HttpRequest(new MockSocket());
        request.setContent("menu=p");
        String correctHeader = "Location: /board.html";

        IHResponse response = controller.execute(request);

        assertTrue( game.isActive() );
        assertEquals( "302", response.getResponseCode() );
        assertTrue(this.inHeaders(response.getHeaders(), correctHeader) );
    }

    @Test
    public void beginGameMoveValid() throws Exception {
        TttApi gameApi = new TttApi();
        WebGame game = new WebGame(gameApi);

        game.start();
        IHController controller = new WebControllerGAME(game);

        HttpRequest request = new HttpRequest(new MockSocket());
        request.setContent("move=4");

        IHResponse response = controller.execute(request);

        String[] content = game.getBoard();
        assertEquals("X", game.getSquare("4"));
        assertEquals("Joshua", game.getTurnPlayerName());
//        assertTrue( content.contains("turn=PLAYER2") );
//        assertTrue( content.contains("turn_player_name=Joshua") );
        assertTrue(this.inHeaders(response.getHeaders(), "board") );
    }

    @Test
    public void beginGameMoveButNoGame() throws Exception {
        TttApi gameApi = new TttApi();
        WebGame game = new WebGame(gameApi);
        IHController controller = new WebControllerGAME(game);

        HttpRequest request = new HttpRequest(new MockSocket());
        request.setContent("move=4");

        IHResponse response = controller.execute(request);

        assertFalse( game.isActive() );
    }

    @Test
    public void quitGame() throws Exception {
        TttApi gameApi = new TttApi();
        WebGame game = new WebGame(gameApi);

        game.start();
        game.move("4");
        IHController controller = new WebControllerGAME(game);

        HttpRequest request = new HttpRequest(new MockSocket());
        request.setContent("menu=q");
        String correctHeader = "Location: http://8thlight.com/principles";

        IHResponse response = controller.execute(request);

        assertFalse( game.isActive() );
        assertTrue(this.inHeaders(response.getHeaders(), correctHeader) );
    }

    public boolean inHeaders(List<String> headers, String match) {
        boolean foundIt = false;
        for (String header: headers) {
            if (header.contains(match)) {
                foundIt = true;
                break;
            }
        }
        return foundIt;
    }
}
