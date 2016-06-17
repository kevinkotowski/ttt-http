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
public class __WebControllerMENUTest {
    @Test
    public void beginGameMenuPlay() throws Exception {
        TttApi gameApi = new TttApi();
        WebGame game = new WebGame(gameApi);
        IHController controller = new WebControllerMENU(game);

        HttpRequest request = new HttpRequest(new MockSocket());
        request.setContent("menu=p");

        IHResponse response = controller.execute(request);

        assertTrue( game.isActive() );
        assertEquals( "302", response.getResponseCode() );
        String correctHeader = "Location: /board.html";
        assertTrue(TestInHeaders.check(response.getHeaders(), correctHeader) );
    }

    @Test
    public void quitGameViaMenu() throws Exception {
        TttApi gameApi = new TttApi();
        WebGame game = new WebGame(gameApi);

        game.start();
        game.move("4");
        IHController controller = new WebControllerMENU(game);

        HttpRequest request = new HttpRequest(new MockSocket());
        request.setContent("menu=q");
        String correctHeader = "Location: http://8thlight.com/principles";

        IHResponse response = controller.execute(request);

        assertFalse( game.isActive() );
        assertTrue(TestInHeaders.check(response.getHeaders(), correctHeader) );
    }

}
