package com.chat.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by alexey.ivlev on 22.06.15.
 */
public class MessageListener implements Runnable{

    Socket socket = null;
    public MessageListener(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run() {
        BufferedReader br = null;
        PrintWriter pw = null;
        try{
            br  = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            pw = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        String clientMessage;

        System.out.println("Waiting for messages");
        try {
            while ((clientMessage = br.readLine()) != null) {
                if (clientMessage.equalsIgnoreCase("exit") || clientMessage.equalsIgnoreCase("close")) break;
                System.out.println("Client Msg ::: " + clientMessage);
                pw.println(clientMessage);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                pw.close();
                br.close();
                socket.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }


    }
}
