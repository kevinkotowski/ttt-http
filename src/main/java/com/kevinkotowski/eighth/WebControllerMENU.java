package com.kevinkotowski.eighth;

import com.kevinkotowski.server.HttpResponse;
import com.kevinkotowski.server.IHController;
import com.kevinkotowski.server.IHRequest;
import com.kevinkotowski.server.IHResponse;

import java.io.IOException;

/**
 * Created by kevinkotowski on 6/14/16.
 */
public class WebControllerMENU implements IHController {
    public IHResponse execute(IHRequest request) throws IOException {
        IHResponse response = new HttpResponse(request.getSocket());

        String content = request.getContent();
        String menu = WebScan.get(content, "menu");

        if (menu.equals("p")) {
            response.setResponseCode("302");
            response.addHeader("Location: /board.html");
        } else {
            response.setResponseCode("302");
            response.addHeader("Location: http://8thlight.com/principles");
        }
        return response;
    }
}
