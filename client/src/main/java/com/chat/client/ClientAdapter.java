package com.chat.client;

/**
 * Created by alexey.ivlev on 09.07.2015.
 */
public class ClientAdapter {
    private Client client;

    public Client getClient() {
        return client;
    }

    public ClientAdapter(String type, String host, Integer port) {
        if(type.equalsIgnoreCase("tcp")){
            client = new TCPClient(host, port);
        } else {
            client = new UDPClient(host, port);
        }
    }
}
