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

    WebControllerMOVE(WebGame game) {
        this.game = game;
    }

    public IHResponse execute(IHRequest request) throws IOException {
        IHResponse response = new HttpResponse(request.getSocket());

        String[] tokens = WebParseOperation.parse(request.getContent());
        String method = tokens[0];
        String value = "";
        if (tokens.length > 1)  {
            value = tokens[1];
        }

        // set default so it only needs to be done once
        response.setResponseCode("302");

        if (method.equals("move")) {
            if (value.equals("q")) {
                this.game.quit();
                response.addHeader("Location: /menu.html");
            } else {
                response = this.move(response, value);
            }
        }
        return response;
    }

    private IHResponse move(IHResponse response, String value) {
        if ( this.game.isActive() &&
                value.matches("[1-9]") &&
                this.game.isSquareAvailable(value) ) {
            this.game.move(value);
            String reco = this.game.getMoveReco();
            if (reco != null && reco.matches("[1-9]")) {
                this.game.move(reco);
            }
            response.addHeader("Location: /board.html?last=" + value);
        } else {
            response.addHeader("Location: /board.html?bad=" + value);
        }
        return response;
    }
}
