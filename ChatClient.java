

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ChatClient {
	
	public static void main(String[] args) throws IOException {
		Socket socket = null;

	    System.out.println("Welcome to Client");

	    socket = new Socket(args[0], 8888);
	    BufferedReader brSocket = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	    PrintWriter pwSocket = new PrintWriter(socket.getOutputStream(), true);
	    BufferedReader brSystem = new BufferedReader(new InputStreamReader(System.in));
	    
	    String client;
	    String server;

	    while ((client = brSystem.readLine()) != null) {
	    	pwSocket.println(client);
	      server = brSocket.readLine();
	      System.out.println(server);
	      if (client.equalsIgnoreCase("close") || client.equalsIgnoreCase("exit")) 
	    	  break;
	    }

	    pwSocket.close();
	    brSocket.close();
	    brSystem.close();
	    socket.close();
	  }

}
