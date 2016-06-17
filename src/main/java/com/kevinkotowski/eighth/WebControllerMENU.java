package com.kevinkotowski.eighth;

import com.kevinkotowski.server.HttpResponse;
import com.kevinkotowski.server.IHController;
import com.kevinkotowski.server.IHRequest;
import com.kevinkotowski.server.IHResponse;
import java.io.IOException;

/**
 * Created by kevinkotowski on 6/15/16.
 */
public class WebControllerMENU implements IHController {
    private WebGame game;

    WebControllerMENU(WebGame game) {
        this.game = game;
    }

    public IHResponse execute(IHRequest request) throws IOException {
        IHResponse response = new HttpResponse(request.getSocket());

        String[] tokens = WebParseOperation.parse(request.getContent());
        String method = tokens[0];
        String value = tokens[1];

        // set default so it only needs to be done once
        response.setResponseCode("302");

        if (method.equals("menu")) {
            response = this.controlMENU(response, value);
        }
        return response;
    }

    private IHResponse controlMENU(IHResponse response, String value) {
        if (value.equals("p")) {
            this.game.start();
            response.addHeader("Location: /board.html");
        } else { // all other values will "quit"
            this.game.quit();
            response.addHeader("Location: " +
                    "http://8thlight.com/principles");
        }
        return response;
    }
}
