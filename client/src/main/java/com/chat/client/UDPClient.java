package com.chat.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

/**
 * Created by alexey.ivlev on 03.07.15.
 */
public class UDPClient implements Client{
    private String host;
    private Integer port;

    public UDPClient(String host, Integer port) {
        this.host = host;
        this.port = port;
    }

    @Override
    public void run() {
        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
        DatagramSocket clientSocket = null;
        try {
            clientSocket = new DatagramSocket();
        } catch (SocketException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
        InetAddress IPAddress = null;
        try {
            IPAddress = InetAddress.getByName(host);
        } catch (UnknownHostException e) {
            System.out.println("Unknown host - " + host);
            System.exit(1);
        }
        System.out.println("Welcome to UDP Client");
        System.out.println("Write your message");
        byte[] sendData = new byte[1024];
        byte[] receiveData = new byte[1024];
        String sentence;
        try {
            while((sentence = inFromUser.readLine()) != null){
                sendData = sentence.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
                clientSocket.send(sendPacket);
                if (sentence.equalsIgnoreCase("close") || sentence.equalsIgnoreCase("exit")){
                    break;
                }
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                clientSocket.receive(receivePacket);
                String serverMessage = new String(receivePacket.getData(), 0, receivePacket.getLength());
                System.out.println("From Server ::: " + serverMessage);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        } finally {
            clientSocket.close();
        }

    }
}
