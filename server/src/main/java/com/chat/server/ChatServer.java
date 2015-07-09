package com.chat.server;

import java.io.IOException;

/**
 * Created by alexey.ivlev on 19.06.15.
 */
public class ChatServer {

    public static void main(String[] args) throws IOException {
        Integer port = null;
        if(args.length != 2){
            try{
                throw new IllegalArgumentException("Wrong number of args. Required: Type[UPD | TCP], port");
            } catch (IllegalArgumentException e){
                System.out.println(e.getMessage());
                System.exit(1);
            }
        }

        try {
            port = Integer.valueOf(args[1]);
        } catch (NumberFormatException e) {
            System.out.println("Port" + args[1] + " must be an integer.");
            System.exit(1);
        }

        ServerAdapter serverAdapter = new ServerAdapter(args[0], port);
        Thread serverThread = new Thread(serverAdapter.getServer());
        serverThread.start();
    }
}
