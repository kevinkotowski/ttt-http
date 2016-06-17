package com.kevinkotowski.eighth;

import com.kevinkotowski.server.HttpMethod;
import com.kevinkotowski.server.IHRequest;
import com.kevinkotowski.server.IHResponse;
import com.kevinkotowski.server.IHTransformer;

/**
 * Created by kevinkotowski on 6/16/16.
 */
public class WebTransformINACTIVE implements IHTransformer {
    private WebGame game;

    public WebTransformINACTIVE(WebGame game) {
        this.game = game;
    }

    public IHRequest transformRequest(IHRequest request) {
        if (request.getMethod() == HttpMethod.GET &&
                !request.getPath().equals("/menu.html") &&
                !this.game.isActive() &&
                !this.game.isEndgame()) {
            request.setPath("/index.html");
        }
        return request;
    }

    public IHResponse transformResponse(IHResponse response) {
        return response;
    }
}
