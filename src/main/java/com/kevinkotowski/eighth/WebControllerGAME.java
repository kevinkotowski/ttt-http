package com.kevinkotowski.eighth;

import com.eighthlight.kkotowski.ttt.TttApi;
import com.kevinkotowski.server.HttpResponse;
import com.kevinkotowski.server.IHController;
import com.kevinkotowski.server.IHRequest;
import com.kevinkotowski.server.IHResponse;

import java.io.IOException;
import java.util.Scanner;

/**
 * Created by kevinkotowski on 6/15/16.
 */
public class WebControllerGAME implements IHController {
    private WebGame game;
    private String method;
    private String value;

    WebControllerGAME(WebGame game) {
        this.game = game;
    }

    public IHResponse execute(IHRequest request) throws IOException {
        IHResponse response = new HttpResponse(request.getSocket());

        this.method = "";
        this.value = "";
        this.parseOperation(request.getContent());

        // set default so it only needs to be done once
        response.setResponseCode("302");

        switch (this.method) {
            case "menu":
                response = this.controlMENU(response);
                break;
            case "move":
                response = this.controlMOVE(response);
                break;
            default:
                response.addHeader("Location: /index.html?error=no_method");
                break;
        }
        return response;
    }

    private void parseOperation(String content) {
        if (content != null) {
            Scanner scanner = new Scanner(content);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] tokens;
                tokens = line.split("=");
                if (tokens.length == 2) {
                    this.method = tokens[0];
                    this.value = tokens[1];
                } else {
                    this.method = tokens[0];
                }
            }
            scanner.close();
        }
    }

    private IHResponse controlMENU(IHResponse response) {
        if (this.value.equals("p")) {
            this.game.start();
            response.addHeader("Location: /board.html");
        } else { // all other values will "quit"
            this.game.quit();
            response.addHeader("Location: " +
                    "http://8thlight.com/principles");
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
