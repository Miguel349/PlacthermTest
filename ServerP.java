// Server Side
import java.net.*;
import java.io.*;
/** 
 * Class that creates a number of server instances listening at inital port + 5 for every N.  
 * Inputs: 
 * Outputs: Ports for the Server instances
 * @author Miguel Pag�n Murphy
 */
public class ServerP { 
	public static int num_servers=1;
	public static int server_port=30018;
	
	public static void main(String[] args) {
		ServerInstance j[]=new ServerInstance[num_servers];
		for(int i=0; i<num_servers; i++){
			j[i]=new ServerInstance(server_port);
			new Thread(j[i]).start();
			server_port=server_port+5;
		}
	}		 
}

