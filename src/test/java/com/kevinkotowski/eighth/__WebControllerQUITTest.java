package com.kevinkotowski.eighth;

import com.eighthlight.kkotowski.ttt.TttApi;
import com.kevinkotowski.server.HttpRequest;
import com.kevinkotowski.server.IHController;
import com.kevinkotowski.server.IHResponse;
import com.kevinkotowski.server.MockSocket;
import org.junit.Test;
import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by kevinkotowski on 6/15/16.
 */
public class __WebControllerQUITTest {
    @Test
    public void quitGameViaEndgame() throws Exception {
        TttApi gameApi = new TttApi();
        WebGame game = new WebGame(gameApi);

        game.start();
        game.move("1");
        game.move("7");
        game.move("2");
        game.move("8");
        game.move("3");
        IHController controller = new WebControllerQUIT(game);

        HttpRequest request = new HttpRequest(new MockSocket());
        request.setContent("quit=true");
        String correctHeader = "Location: /";

        IHResponse response = controller.execute(request);

        assertFalse( game.isActive() );
        assertTrue(TestInHeaders.check(response.getHeaders(), correctHeader) );
    }
}
