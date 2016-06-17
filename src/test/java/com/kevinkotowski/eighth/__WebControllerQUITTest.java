package com.kevinkotowski.eighth;

import com.eighthlight.kkotowski.ttt.TttApi;
import com.kevinkotowski.server.HttpRequest;
import com.kevinkotowski.server.IHController;
import com.kevinkotowski.server.IHResponse;
import com.kevinkotowski.server.MockSocket;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
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
        String correctHeader = "Location: /board.html";

        IHResponse response = controller.execute(request);

        // game restarts after a quit
        assertTrue( game.isActive() );
        String[] board = game.getBoard();
        int count = 0;
        for (String square : board) {
            count++;
            assertTrue(square.equals("0"));
        }
        assertEquals( 9,count );
        assertTrue(TestInHeaders.check(response.getHeaders(), correctHeader) );
    }
}
