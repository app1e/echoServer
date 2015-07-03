package com.chat.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

/**
 * Created by alexey.ivlev on 03.07.15.
 */
public class UPDClient implements Runnable{
    String[] args;

    public UPDClient(String[] args) {
        this.args = args;
    }

    @Override
    public void run() {
        Integer port = null;

        try {
            port = Integer.valueOf(args[2]);
        } catch (NumberFormatException e) {
            System.out.println("Port" + args[2] + " must be an integer.");
            System.exit(1);
        }

        System.out.println("Welcome to UDP Client");
        System.out.println("Write your message");
        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
        DatagramSocket clientSocket = null;
        try {
            clientSocket = new DatagramSocket();
        } catch (SocketException e) {
            e.printStackTrace();
        }
        InetAddress IPAddress = null;
        try {
            IPAddress = InetAddress.getByName(args[1]);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
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
            e.printStackTrace();
        } finally {
            clientSocket.close();
        }

    }
}
