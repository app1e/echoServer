package com.chat.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;

/**
 * Created by alexey.ivlev on 19.06.15.
 */
public class ChatClient {

    public static void main(String[] args) throws IOException {

        if(args.length == 3){
            switch (args[0].toLowerCase()){
                case "udp" : startUPDClient(args);
                    break;
                case "tcp" : startTCPClient(args);
            }
        } else {
            try{
                throw new IllegalArgumentException("Wrong number of args. Required: Type[UPD | TCP], host, port");
            } catch (IllegalArgumentException e){
                System.out.println(e.getMessage());
                System.exit(1);
            }
        }
    }

    public static void startUPDClient(String[] args){
        UPDClient client = new UPDClient(args);
        Thread clientThread = new Thread(client);
        clientThread.start();
    }

    public static void startTCPClient(String[] args){
        TCPClient client = new TCPClient(args);
        Thread clientThread = new Thread(client);
        clientThread.start();
    }

}
