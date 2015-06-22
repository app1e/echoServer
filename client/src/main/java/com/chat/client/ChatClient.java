package com.chat.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by alexey.ivlev on 19.06.15.
 */
public class ChatClient {

    public static void main(String[] args) throws IOException {

        Integer port = null;

        //gets port number from args
        if (args.length == 2){
            try {
                port = Integer.valueOf(args[1]);
            } catch (NumberFormatException e) {
                System.out.println("Port" + args[1] + " must be an integer.");
                System.exit(1);
            }
        } else {
            try {
                throw new IllegalArgumentException("Wrong number of args. Required (host, port)");
            } catch (IllegalArgumentException e){
                System.out.println(e.getMessage());
                System.exit(1);
            }
        }

        Socket socket = null;

        System.out.println("Welcome to Client");
        socket = new Socket(args[0], port);
        BufferedReader brSocket = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter pwSocket = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader brSystem = new BufferedReader(new InputStreamReader(System.in));

        String clientMsg;
        String serverMsg;

        while ((clientMsg = brSystem.readLine()) != null) {
            pwSocket.println(clientMsg);
            serverMsg = brSocket.readLine();
            System.out.println(serverMsg);
            if (clientMsg.equalsIgnoreCase("close") || clientMsg.equalsIgnoreCase("exit"))
                break;
        }

        pwSocket.close();
        brSocket.close();
        brSystem.close();
        socket.close();
    }
}
