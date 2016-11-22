package src;

import java.io.*;
import java.net.*;

public class TchatClientThread extends Thread {
		
		private Socket clientSocket;
		private int clientId = 0;
		
		TchatClientThread(Socket s, int nb) {
			this.clientSocket = s;
			clientId = nb;
		}

	 	/**
	  	* receives a request from client then sends an echo to the client
	  	* @param clientSocket the client socket
	  	**/
		public void run() {
	    	  try {
	    		BufferedReader socIn = null;
	    		socIn = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
	    		//PrintStream socOut = new PrintStream(clientSocket.getOutputStream());
	    		while (true) {
	    			
	    				String line = socIn.readLine();
	    				for(int i = 0; i < TchatServer.clientList.size(); i++) {
	    					PrintStream socOutI = new PrintStream(TchatServer.clientList.get(i).clientSocket.getOutputStream());
	  	    		  		socOutI.println(line);
	  	    		  	
	  	    		  		System.out.println("To Client " + TchatServer.clientList.get(i).clientId + ": " + line);
	    				}
	    			
	    			//String line = socIn.readLine();
  	    		  	//socOut.println(line);
  	    		  	
  	    		  	//System.out.println("Client " + clientId + ": " + line);
	    		}
	    	  } catch (Exception e) {
	        	System.err.println("Error in EchoServer:" + e); 
	    }
	    }
}
