package com.chat.server;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * Created by alexey.ivlev on 03.07.15.
 */
public class TCPServer implements Server{
    private Integer port;

    public TCPServer(Integer port) {
        this.port = port;
    }

    @Override
    public void run() {
        ServerSocket serverSocket = null;

        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            System.out.println("Couldn't listen to port " + port);
            System.exit(1);
        }

        try {
            System.out.println("Welcome to TCP Server");
            System.out.println("Waiting for a client...");
            MessageListener ml = new MessageListener(serverSocket.accept());
            Thread mlThread = new Thread(ml);
            mlThread.start();
            System.out.println("Client connected");
        } catch (IOException e) {
            System.out.println("Can't accept");
            System.exit(1);
        }
    }
}
