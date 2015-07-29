package com.chat.client;

import java.io.IOException;

/**
 * Created by alexey.ivlev on 19.06.15.
 */
public class ChatClient {

    public static void main(String[] args) throws IOException {
        Integer port = null;

        if(args.length != 3){
            try{
                throw new IllegalArgumentException("Wrong number of args. Required: Type[UPD | TCP], host, port");
            } catch (IllegalArgumentException e){
                System.out.println(e.getMessage());
                System.exit(1);
            }
        }

        try {
            port = Integer.valueOf(args[2]);
        } catch (NumberFormatException e) {
            System.out.println("Port" + args[2] + " must be an integer.");
            System.exit(1);
        }

        ClientAdapter clientAdapter = new ClientAdapter(args[0], args[1], port);
        Thread clientThread = new Thread(clientAdapter.getClient());
        clientThread.start();
    }

}
