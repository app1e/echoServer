package com.chat.server;

/**
 * Created by alexey.ivlev on 06.07.15.
 */
public class ServerAdapter {
    private Server server;

    public Server getServer() {
        return server;
    }

    public ServerAdapter(String type, Integer port){
        if(type.equalsIgnoreCase("tcp")){
            server = new TCPServer(port);
        } else {
            server = new UDPServer(port);
        }
    }


}
