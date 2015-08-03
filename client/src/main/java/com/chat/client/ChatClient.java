package com.chat.client;

/**
 * Created by alexey.ivlev on 19.06.15.
 */
public class ChatClient {

    public static void main(String[] args) {

        CommandLineValues values = new CommandLineValues(args);

        ClientAdapter clientAdapter = new ClientAdapter(values.getType(), values.getHost(), values.getPort());
        Thread clientThread = new Thread(clientAdapter.getClient());
        clientThread.start();
    }

}
