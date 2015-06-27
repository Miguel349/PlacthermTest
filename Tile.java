import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Serializable;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Random;
/** 
 * Class create for clarity and in order to be able to send, in case of implementation in the future
 * all the data that the tiles contains at once, by means of serializing the object
 */
public class Tile extends Thread {
	
	int udp_port;
	char control=0;
	
	//We create the data object with the specified parameters. The reason we do this is in case in 
	//Future implementations we wish to send the whole object fields as a datagram (Object Input/Output Stream).
	
	Datagram data;
	byte initial_temperature1=20;
	byte initial_temperature2=5;
	
	public Tile(long sub_id1, long sub_id2, int udp_port){
		data= new Datagram(sub_id1, sub_id2, initial_temperature1, initial_temperature2);
		this.udp_port=udp_port;
	}
	
	public void run() {
	 	try {
	 		int serverPort = 30006;
	 		String host = "54.171.190.69";
	 		System.out.println("Client "+data.sub_id1+" "+data.sub_id2+" connecting to server "+host+ " on port " + serverPort); 

	 		Socket socket = new Socket(host,serverPort); 
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
	 		toServer.println(data.temperature);
	 		
	 		String line = fromServer.readLine();

	 		if(line!="ok"){
	 		System.out.println("Client "+data.sub_id1+" "+data.sub_id2+" received: " + line + " from Server");
		 		
	 		}
	 		else{
	 			System.out.println("Client "+data.sub_id1+" "+data.sub_id2+" received: " + line + " from Server");
			 }
	 		
	 		try {
	 		//System.out.println("Sleeping...");
	 		Random rGen = new Random();
			int change=rGen.nextInt(10000);
			Tile.sleep(change);
			//System.out.println("Awake...");
			} catch (InterruptedException e) {
			System.out.println("Error...");
				// TODO Auto-generated catch block
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
