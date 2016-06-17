package com.kevinkotowski.eighth;

import com.eighthlight.kkotowski.ttt.TttApi;
import com.kevinkotowski.server.*;

import java.io.IOException;

public class Web implements IHHttp {
    private IHServer server;

    public static void main( String[] args ) throws IOException {
        String workingDir = System.getProperty("user.dir");
        String testDir = "/src/public";

        int portNumber = 5000;
        String docRoot = workingDir + testDir;

        IOServerSocket serverSocket = new HttpServerSocket(portNumber);
        IHNetwork network = new HttpNetwork(serverSocket,
                new HttpRequestParser());
        IOFile logFile = new HttpFile(docRoot + "/logs");
        IHLogger accessLogger = new HttpLogger(logFile);

        TttApi gameApi = new TttApi();
        WebGame game = new WebGame(gameApi);

        IHMiddleware middleware = new HttpMiddleware();
        middleware.registerTransformer(new HttpTransformREDIRECT (
                "/", "/index.html"));
        middleware.registerTransformer(new WebTransformINACTIVE( game ) );
        middleware.registerTransformer(new WebTransformENDGAME( game ) );
        middleware.registerTransformer(new WebTransformTAGS( game ) );


        IHRouter router = new HttpRouter(docRoot, accessLogger);
        router.registerRoute(new HttpRoute (
                "/menu",
                HttpMethod.POST, new WebControllerMENU( game ) ));
        router.registerRoute(new HttpRoute (
                "/move",
                HttpMethod.POST, new WebControllerMOVE( game ) ));
        router.registerRoute(new HttpRoute (
                "/quit",
                HttpMethod.POST, new WebControllerQUIT( game ) ));

        IHServer httpServer = new HttpServer( network,
                middleware,
                router );

        httpServer.listen();
    }

    public Web(IHServer server) throws IOException {
        this.server = server;
        this.server.listen();
    }
}
