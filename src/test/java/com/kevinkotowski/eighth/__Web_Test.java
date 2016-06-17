package com.kevinkotowski.eighth;

import com.kevinkotowski.server.*;
import com.kevinkotowski.server.MockLogger;
import com.kevinkotowski.server.MockServer;
import com.kevinkotowski.server.MockSocket;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by kevinkotowski on 6/14/16.
 */
public class __Web_Test {
    @Test
    public void createServerAndListen() throws Exception {
        IHServer server = new MockServer();
        Web web = new Web(server);
        assertTrue(server.status().contains("listening"));
    }
}
