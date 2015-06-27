import java.util.Random;
/** 
 * StartClients is the Class in charge of instantiating N (number_clients) Tiles (clients) in parallel
 * Inputs: None, we could have included the N of clients but it wasn´t part of the problem (extra points) *
 * Methods: genId generates a random ID number for each tile upon creation, already divided into 2 8 bytes
 * pieces
 */


public class StartClients {
	
	//We create N number of clients
	static int number_clients=1;
	//udp_port is used for additional implementations.
	static int udp_port=10000;
	static int  server_port=30018;
	static String host="localhost";
	//String host="54.171.190.69";
	
	   public static void main(String[] args) {
		   Tile j[]=new Tile[number_clients];
	 		for(int i=0; i<number_clients; i++){
	 			udp_port=udp_port+10;
	 			
	 		j[i]=new Tile(genId(),genId(),udp_port,server_port,host);
	 		new Thread(j[i]).start();
	 		server_port=server_port+5;
	 		}
	   }
	   
	   private static long genId(){
			Random rGen = new Random();
			long data1=rGen.nextInt(50000);
			return data1;
		}	
}


