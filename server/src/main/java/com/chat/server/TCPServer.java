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
        try(ServerSocket serverSocket = new ServerSocket(port)) {

            System.out.println("Welcome to TCP Server");
            System.out.println("Waiting for a client...");
            MessageListener ml = new MessageListener(serverSocket.accept());
            Thread mlThread = new Thread(ml);
            mlThread.start();
            System.out.println("Client connected");
        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }
}
