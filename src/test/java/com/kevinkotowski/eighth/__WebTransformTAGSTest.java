package com.kevinkotowski.eighth;

import com.eighthlight.kkotowski.ttt.TttApi;
import com.kevinkotowski.server.*;
import com.kevinkotowski.server.MockSocket;

import org.junit.Test;
import static org.junit.Assert.assertTrue;

/**
 * Created by kevinkotowski on 6/14/16.
 */
public class __WebTransformTAGSTest {
    @Test
    public void htmlTagsToValues() throws Exception {
        TttApi gameApi = new TttApi();
        WebGame game = new WebGame(gameApi);

        game.start();
        game.move("3");

        IHTransformer xformTAGS = new WebTransformTAGS(game);

        IHResponse response = new HttpResponse(new MockSocket());
        response.setBody(this.content);
        response = xformTAGS.transformResponse(response);

        System.out.println("...test: \n" + response.getBody());
        assertTrue(response.getBody().contains("Homer"));
        assertTrue(response.getBody().contains("lazy X"));
        assertTrue(response.getBody().contains("info text"));
    }

    String content =
            "The quick brown {{turn_player_name}} jumps over\n" +
                    "the lazy {{square::3}}. However, there was a\n" +
                    "strange {{message}} to read.";
}
