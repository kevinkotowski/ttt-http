package com.kevinkotowski.eighth;

import com.kevinkotowski.server.IHRequest;
import com.kevinkotowski.server.IHResponse;
import com.kevinkotowski.server.IHTransformer;

import java.util.List;

/**
 * Created by kevinkotowski on 6/14/16.
 */
public class WebTransformTAGS implements IHTransformer {
    private WebGame game;

    public WebTransformTAGS(WebGame game ) {
        this.game = game;
    }

    public IHRequest transformRequest(IHRequest request) {
        return request;
    }

    public IHResponse transformResponse(IHResponse response) {
        if (game.isActive() || game.isEndgame()) {
            String body = response.getBody();
            List<String> tags = WebReplaceTags.get(body);

            for (String tag : tags) {
                System.out.println("...xformTAGS: " + tag);
                body = this.replaceTag(body, tag);
                response.setBody( body );
            }
        }
        return response;
    }

    private String replaceTag(String body, String tag) {
        String replacement;
        String name;
        String value = "";
        String[] nameValue = tag.split("::");
        name = nameValue[0];
        if (nameValue.length > 1) {
            value = nameValue[1];
        }

        switch (name) {
            case "turn_player_name":
                replacement = this.game.getTurnPlayerName();
                break;
            case "square":
                replacement = this.game.getSquare(value);
                break;
            case "message":
                replacement = "info text";
                break;
            case "reco":
                replacement = this.game.getMoveReco();
                break;
            case "winner_player_name":
                replacement = this.game.getWinnerName();
                break;
            default:
                replacement = tag;
                break;
        }
        if (replacement == null) replacement = "";
        body = body.replaceAll("\\{\\{" + tag + "\\}\\}", replacement);
        return body;
    }
}
