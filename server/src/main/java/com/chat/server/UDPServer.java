package com.chat.server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

/**
 * Created by alexey.ivlev on 03.07.15.
 */
public class UDPServer implements Runnable{
    private String[] args;

    public UDPServer(String[] args) {
        this.args = args;
    }

    @Override
    public void run() {
        Integer serverPort = null;
        try {
            serverPort = Integer.valueOf(args[1]);
        } catch (NumberFormatException e) {
            System.out.println("Port" + args[1] + " must be an integer.");
            System.exit(1);
        }
        DatagramSocket serverSocket = null;
        try {
            serverSocket = new DatagramSocket(serverPort);
        } catch (SocketException e) {
            e.printStackTrace();
        }
        System.out.println("Welcome to UDP Server");
        System.out.println("Waiting for a client...");
        byte[] receiveData = new byte[1024];
        byte[] sendData = new byte[1024];
        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
        try {
            while(true){
                serverSocket.receive(receivePacket);
                String clientMessage = new String(receivePacket.getData(), 0, receivePacket.getLength());
                if (clientMessage.equalsIgnoreCase("close") || clientMessage.equalsIgnoreCase("exit"))
                    break;
                System.out.println("Client Msg ::: " + clientMessage);
                InetAddress IPAddress = receivePacket.getAddress();
                int port = receivePacket.getPort();
                String capitalizedSentence = clientMessage.toUpperCase();
                sendData = capitalizedSentence.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
                serverSocket.send(sendPacket);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            serverSocket.close();
        }
    }
}
