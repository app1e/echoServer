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
        System.out.println("Welcome to Server");
        BufferedReader br = null;
        PrintWriter pw = null;

        ServerSocket serverSocket = null;
        Socket socket = null;

        // create server socket
        try {
            serverSocket = new ServerSocket(8888);
        } catch (IOException e) {
            System.out.println("Couldn't listen to port 8888");
            System.exit(-1);
        }

        try {
            System.out.print("Waiting for a client...");
            socket = serverSocket.accept();
            System.out.println("Client connected");
        } catch (IOException e) {
            System.out.println("Can't accept");
            System.exit(-1);
        }

        br  = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        pw = new PrintWriter(socket.getOutputStream(), true);
        String input;

        System.out.println("Wait for messages");
        while ((input = br.readLine()) != null) {
            if (input.equalsIgnoreCase("exit") || input.equalsIgnoreCase("close")) break;
            pw.println("S ::: " + input);
            System.out.println(input);
        }
        pw.close();
        br.close();
        socket.close();
        serverSocket.close();
    }
}
