import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Serializable;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Random;
/** 
 * Class representing a Tile (client) in an independant thread. It uses the socket 
 * connection to each of the different Socket Ports available. The amount of Tiles
 * created depends on the parameter specified in StartClients.java and must always
 * be equal or smaller to the number of ServerInstances.
 * The explanation for this is that, since the TCP connection is identified by port
 * and ip, the clients share the same one, and some of the packets are dropped 
 * therefore, each client connects into a different server port creating a different
 * socket.  
 * Inputs: 
 * 	Constructor Input: id, divided by bytes, port for udp control messages(extra 
 * 	implementation and server port
 * Outputs: Traffic towards the server. ID and temperature.
 * 
 */
public class Tile extends Thread {
	
	int udp_port;
	char control=0;
	
	//We create the data object with the specified parameters. The reason we do this is in case in 
	//Future implementations we wish to send the whole object fields as a datagram (Object Input/Output Stream).
	
	Datagram data;
	byte initial_temperature1=20;
	byte initial_temperature2=5;
	int server_port;
	String host;
	
	public Tile(long sub_id1, long sub_id2, int udp_port, int server_port, String host){
		data= new Datagram(sub_id1, sub_id2, initial_temperature1, initial_temperature2);
		this.udp_port=udp_port;
		this.server_port=server_port;
		this.host=host;
	}
	
	public void run() {
	 	try {
	 		
	 		System.out.println("Client "+data.sub_id1+" "+data.sub_id2+" connecting to server "+host+ " on port " + server_port); 

	 		Socket socket = new Socket(host,server_port); 
	 		//If we want it to run locally we change the address in socket to localhost.
	 		//Socket socket = new Socket("localhost", serverPort);
	 		
	 		System.out.println("Client " +data.sub_id1+" "+data.sub_id2+" connected to: " + socket.getRemoteSocketAddress());
	 		PrintWriter toServer = new PrintWriter(socket.getOutputStream(),true);
	 		BufferedReader fromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	 		
	 		while(control!=1){
	 			data.change_Temperature();
	 			System.out.println("Client "+data.sub_id1+" "+data.sub_id2+" with temperature: "+data.temperature[0]+","+data.temperature[1]+" sending...");
	 			toServer.print(data.sub_id1);
	 			toServer.print(data.sub_id2);
	 			toServer.print(data.temperature[0]);
	 			toServer.println(data.temperature[1]);
	 			String line = fromServer.readLine();
	 			
	 			//Redundant code, used for processing responses in the future
	 			if(line!="ok"){
	 				System.out.println("Client "+data.sub_id1+" "+data.sub_id2+" received: " + line + " from Server");
	 			}
	 			
	 			else{
	 				System.out.println("Client "+data.sub_id1+" "+data.sub_id2+" received: " + line + " from Server");
	 			}
	 			try {
	 				//System.out.println("Sleeping...");
	 				Random rGen = new Random();
	 				int change=rGen.nextInt(100000);
	 				Tile.sleep(change);
	 				//System.out.println("Awake...");
	 			} catch (InterruptedException e) {
	 				System.out.println("Error...");
	 				
	 				e.printStackTrace();
	 			}
	 		}
	 		
	 		toServer.close();
	 		fromServer.close();
	 		socket.close();
	 		}

	 	catch(UnknownHostException ex) {
	 		ex.printStackTrace();
	 	}
	 	catch(IOException e){
	 		e.printStackTrace();
	 	}
	}
}
