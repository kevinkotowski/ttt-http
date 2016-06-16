package com.kevinkotowski.eighth;

import com.kevinkotowski.server.HttpResponse;
import com.kevinkotowski.server.IHController;
import com.kevinkotowski.server.IHRequest;
import com.kevinkotowski.server.IHResponse;
import java.io.IOException;

/**
 * Created by kevinkotowski on 6/15/16.
 */
public class WebControllerMOVE implements IHController {
    private WebGame game;
    private String method;
    private String value;

    WebControllerMOVE(WebGame game) {
        this.game = game;
    }

    public IHResponse execute(IHRequest request) throws IOException {
        IHResponse response = new HttpResponse(request.getSocket());

        String[] tokens = WebParseOperation.parse(request.getContent());
        this.method = tokens[0];
        this.value = tokens[1];

        // set default so it only needs to be done once
        response.setResponseCode("302");

        if (this.method.equals("move")) {
            response = this.controlMOVE(response);
        }
        return response;
    }

    private IHResponse controlMOVE(IHResponse response) {
        if (this.game.isActive()) {
            if ( this.game.isAvailable(this.value) ) {
                this.game.move(this.value);
                response.addHeader("Location: /board.html?last=" +
                        this.value);
            } else {
                // TODO: add setMessage interface to game?
                response.addHeader("Location: /board.html?bad=" +
                        this.value);
            }
        }
        return response;
    }
}
