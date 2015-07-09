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
        if(type.equalsIgnoreCase("udp")){
            server = new UDPServer(port);
        } else {
            server = new TCPServer(port);
        }
    }


}
