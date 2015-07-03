package com.chat.server;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * Created by alexey.ivlev on 03.07.15.
 */
public class TCPServer implements Runnable{
    private String[] args;

    public TCPServer(String[] args) {
        this.args = args;
    }

    @Override
    public void run() {
        Integer port = null;
        ServerSocket serverSocket = null;
        try {
                port = Integer.valueOf(args[1]);
        } catch (NumberFormatException e) {
            System.out.println("Port must be an integer.");
            System.exit(1);
        }

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
