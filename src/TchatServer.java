package src;

import java.io.*;
import java.net.*;
import java.util.*;

public class TchatServer {
	static ArrayList<TchatClientThread> clientList;
	
	/**
  	* main method
	* @param EchoServer port
  	* 
  	**/
    public static void main(String args[]){ 
    	ServerSocket listenSocket;
    	int clientCount = 1;
    	
    
        
    	if (args.length != 1) {
    		System.out.println("Usage: java EchoServer <EchoServer port>");
    		System.exit(1);
    	}
  	
    	try {
    		listenSocket = new ServerSocket(Integer.parseInt(args[0]));
    		System.out.println("Server ready...");
    		clientList = new ArrayList<TchatClientThread>();
		
    		//String echoLine;
    		while (true) {
    			Socket clientSocket = listenSocket.accept();
    			System.out.println("Connexion from : " + clientSocket.getInetAddress());
    			TchatClientThread ct = new TchatClientThread(clientSocket, clientCount);
			
    			clientList.add(ct);
    			ct.start();
    			clientCount++;
    			
    			System.out.println("Client List Size : " + clientList.size());
    		}
        } catch (Exception e) {
            System.err.println("Error in EchoServer:" + e);
        }
    }
}
