import java.io.*;
	 import java.net.*;
import java.util.Random;
	 public class ClientP extends Thread {
	   private int udp_port;
	   
	   //Constructor para los mensajes de control por UDP
	   public ClientP(int udp_port){
		   this.udp_port=udp_port;
	   }
	public void run() {
	 	try {
	 		int serverPort = 30006;
	 		String host = "54.171.190.69";
	 		System.out.println("Connecting to server"+host+ "on port " + serverPort); 

	 		Socket socket = new Socket(host,serverPort); 
	 		//Socket socket = new Socket("127.0.0.1", serverPort);
	 		System.out.println("Just connected to " + socket.getRemoteSocketAddress()); 
	 		PrintWriter toServer = 
	 			new PrintWriter(socket.getOutputStream(),true);
	 		BufferedReader fromServer = 
	 			new BufferedReader(
	 					new InputStreamReader(socket.getInputStream()));
	 		toServer.println("" + socket.getLocalSocketAddress()); 
	 		String line = fromServer.readLine();
	 		System.out.println("Client received: " + line + " from Server");
	 		//toServer.close();
	 		//fromServer.close();
	 		//socket.close();
	 	}
	 	catch(UnknownHostException ex) {
	 		ex.printStackTrace();
	 	}
	 	catch(IOException e){
	 		e.printStackTrace();
	 	}
	   }
	
	 }
	 