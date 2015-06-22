package com.chat.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by alexey.ivlev on 19.06.15.
 */
public class ChatServer {

    public static void main(String[] args) throws IOException {
        Integer port = null;

        //gets port number from args
        if (args.length == 1){
            try {
                port = Integer.valueOf(args[0]);
            } catch (NumberFormatException e) {
                System.out.println("Port" + args[0] + " must be an integer.");
                System.exit(1);
            }
        } else {
            throw new IllegalArgumentException("Wrong number of args. Required (port)");
        }

        ServerSocket serverSocket = null;

//      create server socket
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            System.out.println("Couldn't listen to port " + port);
            System.exit(1);
        }

        try {
            System.out.println("Welcome to Server");
            System.out.print("Waiting for a client...");
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
