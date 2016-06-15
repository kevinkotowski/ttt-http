package com.kevinkotowski.eighth;

import com.kevinkotowski.server.*;
import com.kevinkotowski.server.MockLogger;
import com.kevinkotowski.server.MockSocket;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by kevinkotowski on 6/14/16.
 */
public class __WebControllerMENUTest {
    @Test
    public void executeControllerMENUPlay() throws Exception {
        HttpRequest request = new HttpRequest(new MockSocket());
        request.setContent("menu=p");

        IHController controller = new WebControllerMENU();
        IHResponse response = controller.execute(request);

        List<String> headers = response.getHeaders();
        String correctHeader =
                "Location: /board.html";

        assertEquals( "302", response.getResponseCode() );
        assertEquals( correctHeader, headers.get(0) );
    }

    @Test
    public void executeControllerMENUQuit() throws Exception {
        HttpRequest request = new HttpRequest(new MockSocket());
        request.setContent("menu=q");

        IHController controller = new WebControllerMENU();
        IHResponse response = controller.execute(request);

        List<String> headers = response.getHeaders();
        String correctHeader =
                "Location: http://8thlight.com/principles";

        assertEquals( "302", response.getResponseCode() );
        assertEquals( correctHeader, headers.get(0) );
    }
}