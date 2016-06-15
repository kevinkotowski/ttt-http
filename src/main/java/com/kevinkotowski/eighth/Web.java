package com.kevinkotowski.eighth;

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
        IHServer httpServer = new HttpServer( network,
                getMiddleware(),
                getRouter(docRoot, accessLogger) );

        httpServer.listen();
    }

    public Web(IHServer server) throws IOException {
        this.server = server;
        server.listen();
    }

    public static IHMiddleware getMiddleware() {
        IHMiddleware middleware = new HttpMiddleware();

        middleware.registerTransformer(new HttpTransformREDIRECT (
                "/", "/index.html"));

        return middleware;
    }

    public static IHRouter getRouter(String docRoot, IHLogger accessLogger) {
        IHRouter router = new HttpRouter(docRoot, accessLogger);

        router.registerRoute(new HttpRoute (
                "/",
                HttpMethod.GET, new HttpControllerSTATIC() ));

        router.registerRoute(new HttpRoute (
                "/menu",
                HttpMethod.POST, new WebControllerMENU() ));

        router.registerRoute(new HttpRoute (
                "/move",
                HttpMethod.POST, new WebControllerMOVE() ));

        return router;
    }
}
