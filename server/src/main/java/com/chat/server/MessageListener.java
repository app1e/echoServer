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
        try(BufferedReader br  = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter pw = new PrintWriter(socket.getOutputStream(), true)){

            String clientMessage;
            System.out.println("Waiting for messages");
            while ((clientMessage = br.readLine()) != null) {
                if (clientMessage.equalsIgnoreCase("exit") || clientMessage.equalsIgnoreCase("close")) break;
                System.out.println("Client Msg ::: " + clientMessage);
                pw.println(clientMessage);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
