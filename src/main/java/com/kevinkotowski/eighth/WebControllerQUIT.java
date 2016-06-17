package com.kevinkotowski.eighth;

import com.kevinkotowski.server.HttpResponse;
import com.kevinkotowski.server.IHController;
import com.kevinkotowski.server.IHRequest;
import com.kevinkotowski.server.IHResponse;
import java.io.IOException;

/**
 * Created by kevinkotowski on 6/15/16.
 */
public class WebControllerQUIT implements IHController {
    private WebGame game;

    WebControllerQUIT(WebGame game) {
        this.game = game;
    }

    public IHResponse execute(IHRequest request) throws IOException {
        IHResponse response = new HttpResponse(request.getSocket());

        String[] tokens = WebParseOperation.parse(request.getContent());
        String method = tokens[0];
        String value = tokens[1];

        // set default so it only needs to be done once
        response.setResponseCode("302");

        if (method.equals("quit")) {
            this.game.restart();
            response.addHeader("Location: /board.html");
        }
        return response;
    }
}
