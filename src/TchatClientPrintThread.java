package src;

import java.io.*;
import java.net.*;

public class TchatClientPrintThread extends Thread {
	private Socket clientSocketIn;
	
	TchatClientPrintThread(Socket socIn) {
		clientSocketIn = socIn;
	}
	
	/**
  	* receives a request from client then sends an echo to the client
  	* @param clientSocket the client socket
  	**/
	public void run() {
    	  try {
    		  BufferedReader socIn = null;
	    		socIn = new BufferedReader(new InputStreamReader(clientSocketIn.getInputStream()));
	    		while (true) {
	    			System.out.println("echo: " + socIn.readLine());
	    		}
    		
    	  } catch (Exception e) {
        	System.err.println("Error in EchoServer:" + e); 
    	  }
    }
}
