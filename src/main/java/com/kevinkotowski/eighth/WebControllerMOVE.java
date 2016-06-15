package com.kevinkotowski.eighth;

import com.kevinkotowski.server.HttpResponse;
import com.kevinkotowski.server.IHController;
import com.kevinkotowski.server.IHRequest;
import com.kevinkotowski.server.IHResponse;

import java.io.IOException;

/**
 * Created by kevinkotowski on 6/14/16.
 */
public class WebControllerMOVE implements IHController {
    public IHResponse execute(IHRequest request) throws IOException {
        IHResponse response = new HttpResponse(request.getSocket());

        String content = request.getContent();
        String move = WebScan.get(content, "move");
        boolean valid = false;

        if (move != null) {
            if (move.matches("[1-9]")) {
                valid = true;
                // TODO: post move-1 to game
                response.setResponseCode("302");
                response.addHeader("Location: /board.html");
            } else if (move.equals("q")){
                valid = true;
                // TODO: post quit to game
                response.setResponseCode("302");
                response.addHeader("Location: /menu.html");
            }
        }

        if (!valid) {
            // TODO: post message to game
            response.setResponseCode("404");
            response.addHeader("Location: /board.html");
        }
        return response;
    }
}
