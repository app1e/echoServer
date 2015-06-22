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
            e.printStackTrace();
        }

        String input;

        System.out.println("Waiting for messages");
        try {
            while ((input = br.readLine()) != null) {
                if (input.equalsIgnoreCase("exit") || input.equalsIgnoreCase("close")) break;
                pw.println("S ::: " + input);
                System.out.println(input);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            pw.close();
            br.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
