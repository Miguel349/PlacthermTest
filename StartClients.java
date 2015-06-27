import java.util.Random;
/** 
 * StartClients is the Class in charge of instantiating N (number_clients) Tiles (clients) in parallel
 */
public class StartClients {
	
	//We create N number of clients
	static int number_clients=5;
	//udp_port is used for additional implementations.
	static int udp_port=10000;
	
	   public static void main(String[] args) {
		   Tile j[]=new Tile[number_clients];
	 		for(int i=0; i<number_clients; i++){
	 			udp_port=udp_port+10;	
	 		j[i]=new Tile(genid(),genid(),udp_port);
	 		new Thread(j[i]).start();
	 		}
	   }
	   
	   private static long genid(){
			Random rGen = new Random();
			long data1=rGen.nextInt(50000);
			return data1;
		}	
}


