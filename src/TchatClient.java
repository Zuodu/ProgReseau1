package src;

import java.io.*;
import java.net.*;

public class TchatClient {
	public static void main(String[] args) throws IOException {

	Socket tchatSocket = null;
    PrintStream socketOut = null;
    BufferedReader stdInput = null;

    if (args.length != 2) {
      System.out.println("Usage: java EchoClient <EchoServer host> <EchoServer port>");
      System.exit(1);
    }

    /* Allocation du socket cote client */
    try {
    	tchatSocket = new Socket(args[0],new Integer(args[1]).intValue());
    	socketOut = new PrintStream(tchatSocket.getOutputStream());
    	stdInput = new BufferedReader(new InputStreamReader(System.in));
    } catch (UnknownHostException e) {
        System.err.println("Don't know about host:" + args[0]);
        System.exit(1);
    } catch (IOException e) {
        System.err.println("Couldn't get I/O for " + "the connection to:"+ args[0]);
        System.exit(1);
    }
    
    
    String line;
    TchatClientPrintThread th = new TchatClientPrintThread(tchatSocket);
	th.start();
    while (true) {
    	line = stdInput.readLine();
    	if (line.equals(".")) break;
    	socketOut.println(line);
    	
    	//System.out.println("echo: " + socIn.readLine());
    }
    
    socketOut.close();
    stdInput.close();
    tchatSocket.close();
	}

}
