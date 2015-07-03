package com.chat.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by alexey.ivlev on 03.07.15.
 */
public class TCPClient implements Runnable{

    String[] args = null;

    public TCPClient(String[] args) {
        this.args = args;
    }

    @Override
    public void run() {
        Integer port = null;
        Socket socket = null;
        BufferedReader brSocket = null;
        PrintWriter pwSocket = null;

        try {
            port = Integer.valueOf(args[2]);
        } catch (NumberFormatException e) {
                System.out.println("Port" + args[2] + " must be an integer.");
                System.exit(1);
        }
        try {
            socket = new Socket(args[1], port);
            brSocket = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            pwSocket = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Welcome to TCP Client");
        System.out.println("Write your message");
        BufferedReader brSystem = new BufferedReader(new InputStreamReader(System.in));

        String clientMessage;
        String serverMessage;

        try {
            while ((clientMessage = brSystem.readLine()) != null) {
                if (clientMessage.equalsIgnoreCase("close") || clientMessage.equalsIgnoreCase("exit"))
                    break;
                pwSocket.println(clientMessage);
                serverMessage = brSocket.readLine();
                System.out.println("From Server ::: " + serverMessage);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        pwSocket.close();
        try {
            brSocket.close();
            brSystem.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
