package com.kevinkotowski.eighth;

import com.kevinkotowski.server.IHServer;

public class App
{
    private IHServer server;

    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
    }

    public void App(IHServer server) {
        this.server = server;
    }
}
