package com.kevinkotowski.eighth;

import com.eighthlight.kkotowski.ttt.TttApi;
import com.kevinkotowski.server.*;
import com.kevinkotowski.server.MockSocket;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by kevinkotowski on 6/16/16.
 */
public class __WebTransformENDGAME {
    @Test
    public void redirectEndGame() throws Exception {
        TttApi gameApi = new TttApi();
        WebGame game = new WebGame(gameApi);

        game.start();
        game.move("1");
        game.move("3");
        game.move("4");
        game.move("6");
        game.move("7");

        IHTransformer xformENDGAME = new WebTransformENDGAME(game);

        IHRequest request = new HttpRequest(new MockSocket());
        request.setMethod("GET");
        request.setPath("/board.html");
        request = xformENDGAME.transformRequest(request);

        assertEquals("/endgame.html", request.getPath());

//        IHResponse response = new HttpResponse(new MockSocket());
//        response.setBody(this.content);
//        response = xformTAGS.transformResponse(response);

//        assertTrue(response.getBody().contains("Joshua"));
//        assertTrue(response.getBody().contains("lazy X"));
//        assertTrue(response.getBody().contains("info text"));
    }
}
