package com.chat.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

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
        try(Socket socket = new Socket(host, port);
            BufferedReader brSocket = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter pwSocket = new PrintWriter(socket.getOutputStream(), true)) {

            System.out.println("Welcome to TCP Client");
            System.out.println("Write your message");
            BufferedReader brSystem = new BufferedReader(new InputStreamReader(System.in));

            String clientMessage;
            String serverMessage;

            while ((clientMessage = brSystem.readLine()) != null) {
                if (clientMessage.equalsIgnoreCase("close") || clientMessage.equalsIgnoreCase("exit"))
                    break;
                pwSocket.println(clientMessage);
                serverMessage = brSocket.readLine();
                System.out.println("From Server ::: " + serverMessage);
            }
        } catch (UnknownHostException ex) {
            System.out.println("Unknown host - " + host);
            System.exit(1);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }
}
