package com.chat.server;

/**
 * Created by alexey.ivlev on 19.06.15.
 */
public class ChatServer {

    public static void main(String[] args) {

        CommandLineValues values = new CommandLineValues(args);

        ServerAdapter serverAdapter = new ServerAdapter(values.getType(), values.getPort());
        Thread serverThread = new Thread(serverAdapter.getServer());
        serverThread.start();
    }
}
