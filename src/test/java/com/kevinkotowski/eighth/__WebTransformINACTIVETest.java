package com.kevinkotowski.eighth;

import com.eighthlight.kkotowski.ttt.TttApi;
import com.kevinkotowski.server.*;
import com.kevinkotowski.server.MockSocket;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by kevinkotowski on 6/16/16.
 */
public class __WebTransformINACTIVETest {
    @Test
    public void redirectInactive() throws Exception {
        TttApi gameApi = new TttApi();
        WebGame game = new WebGame(gameApi);

        IHTransformer xformINACTIVE = new WebTransformINACTIVE(game);

        IHRequest request = new HttpRequest(new MockSocket());
        request.setMethod("GET");
        request.setPath("/board.html");
        request = xformINACTIVE.transformRequest(request);

        assertEquals("/index.html", request.getPath());
    }

    @Test
    public void redirectInactiveExceptMenu() throws Exception {
        TttApi gameApi = new TttApi();
        WebGame game = new WebGame(gameApi);

        IHTransformer xformINACTIVE = new WebTransformINACTIVE(game);

        IHRequest request = new HttpRequest(new MockSocket());
        request.setMethod("GET");
        request.setPath("/menu.html");
        request = xformINACTIVE.transformRequest(request);

        assertEquals("/menu.html", request.getPath());
    }
}
