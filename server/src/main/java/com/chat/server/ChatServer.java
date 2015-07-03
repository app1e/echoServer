package com.chat.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;

/**
 * Created by alexey.ivlev on 19.06.15.
 */
public class ChatServer {

    public static void main(String[] args) throws IOException {

        if(args.length == 2){
            switch (args[0].toLowerCase()){
                case "udp" : startUPDServer(args);
                    break;
                case "tcp" : startTCPServer(args);
                    break;
            }
        } else {
            try{
                throw new IllegalArgumentException("Wrong number of args. Required: Type[UPD | TCP], port");
            } catch (IllegalArgumentException e){
                System.out.println(e.getMessage());
                System.exit(1);
            }
        }
    }

    public static void startUPDServer(String[] args){
        UDPServer server = new UDPServer(args);
        Thread serverThread = new Thread(server);
        serverThread.start();
    }

    public static void startTCPServer(String[] args){
        TCPServer server = new TCPServer(args);
        Thread serverThread = new Thread(server);
        serverThread.start();
    }
}
