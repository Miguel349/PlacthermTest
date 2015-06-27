// Server Side
import java.net.*;
import java.io.*;

public class ServerP { 
  public void run() {
	try {
		int serverPort = 30006;
		ServerSocket serverSocket = new ServerSocket(serverPort);
		System.out.println("Waiting for client on port " + serverSocket.getLocalPort() + "..."); 
		Socket server = serverSocket.accept();
		PrintWriter toClient = new PrintWriter(server.getOutputStream(),true);
		BufferedReader fromClient =new BufferedReader(new InputStreamReader(server.getInputStream()));
		
		//serverSocket.setSoTimeout(10000); 
		while(true) {
		System.out.println("Just connected to " + server.getRemoteSocketAddress()); 
		String line = fromClient.readLine();
		System.out.println("Server received: " + line); 
		toClient.println("ok"); 
		}
	}
	catch(UnknownHostException ex) {
		ex.printStackTrace();
	}
	catch(IOException e){
		e.printStackTrace();
	}
  }
	
  public static void main(String[] args) {
		ServerP srv = new ServerP();
		srv.run();
  }
}
