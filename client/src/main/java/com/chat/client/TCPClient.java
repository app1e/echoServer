package com.chat.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by alexey.ivlev on 03.07.15.
 */
public class TCPClient implements Client{

    private String host;
    private Integer port;

    public TCPClient(String host, Integer port) {
        this.host = host;
        this.port = port;
    }

    @Override
    public void run() {
        Socket socket = null;
        BufferedReader brSocket = null;
        PrintWriter pwSocket = null;
        try {
            socket = new Socket(host, port);
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
