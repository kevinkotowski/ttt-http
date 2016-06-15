package com.kevinkotowski.eighth;

import com.kevinkotowski.server.HttpRequest;
import com.kevinkotowski.server.IHController;
import com.kevinkotowski.server.IHResponse;
import com.kevinkotowski.server.MockSocket;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by kevinkotowski on 6/14/16.
 */
public class __WebControllerMOVETest {
    @Test
    public void executeControllerMOVESquare() throws Exception {
        HttpRequest request = new HttpRequest(new MockSocket());
        request.setContent("move=4");

        IHController controller = new WebControllerMOVE();
        IHResponse response = controller.execute(request);

        List<String> headers = response.getHeaders();
        String correctHeader =
                "Location: /board.html";

        assertEquals( "302", response.getResponseCode() );
        assertEquals( correctHeader, headers.get(0) );
    }

    @Test
    public void executeControllerMOVEQuit() throws Exception {
        HttpRequest request = new HttpRequest(new MockSocket());
        request.setContent("move=q");

        IHController controller = new WebControllerMOVE();
        IHResponse response = controller.execute(request);

        List<String> headers = response.getHeaders();
        String correctHeader =
                "Location: /menu.html";

        assertEquals( "302", response.getResponseCode() );
        assertEquals( correctHeader, headers.get(0) );
    }

    @Test
    public void executeControllerMOVEErrorBad() throws Exception {
        HttpRequest request = new HttpRequest(new MockSocket());
        request.setContent("move=0");

        IHController controller = new WebControllerMOVE();
        IHResponse response = controller.execute(request);

        List<String> headers = response.getHeaders();
        String correctHeader =
                "Location: /board.html";

        assertEquals( "404", response.getResponseCode() );
        assertEquals( correctHeader, headers.get(0) );
    }

    @Test
    public void executeControllerMOVEErrorNull() throws Exception {
        HttpRequest request = new HttpRequest(new MockSocket());
        request.setContent("move=");

        IHController controller = new WebControllerMOVE();
        IHResponse response = controller.execute(request);

        List<String> headers = response.getHeaders();
        String correctHeader =
                "Location: /board.html";

        assertEquals( "404", response.getResponseCode() );
        assertEquals( correctHeader, headers.get(0) );
    }
}
