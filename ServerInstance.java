import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
/** 
 * Class Representing a Server Instance Socket (a socket waiting at a certain port). Receives the clients data
 and instantiates a DB object to insert the data into the MongoDB database.
 * Inputs: Port
 * Outputs: 
 * @author: Miguel Pagán Murphy
 */
public class ServerInstance extends Thread {
	
	int server_port;
	Datab db;
	public ServerInstance(int server_port){
		this.server_port=server_port;
		db=new Datab();
	}
	
	public void run(){
		ServerSocket serverSocket;
		try {
			serverSocket= new ServerSocket(server_port);	
	 		System.out.println("Waiting for client on port " + serverSocket.getLocalPort() + "..."); 
	 		Socket server = serverSocket.accept();
	 		PrintWriter toClient = new PrintWriter(server.getOutputStream(),true);
	 		BufferedReader fromClient =new BufferedReader(new InputStreamReader(server.getInputStream()));
	 		Datab db=new Datab();
		
	 		//serverSocket.setSoTimeout(10000); 
	 		while(true) {
	 			
	 			//int []chara=new int[5];
	 			//chara[0]=fromClient.read();
	 			//chara[1]=fromClient.read();
	 			//chara[2]=fromClient.read();
	 			//chara[3]=fromClient.read();
	 			//chara[4]=fromClient.read();
	 			//String id1=chara[0]+""+chara[1]+""+chara[2]+""+chara[3]+chara[4];
	 			//System.out.println("Read first long: " + id1);
	 			String line = fromClient.readLine();
	 			int length_id=line.length()-3;
	 			String id=line.substring(0, length_id);
	 			System.out.println("Server Received message from client:"+id);
	 			System.out.println("Server Whole message: " + line); 
	 			String temp=line.substring(length_id,length_id+2);
	 			String temp2=line.substring(length_id+2,length_id+3);
	 			System.out.println("Temperature is: " + temp+","+temp2); 
	 			toClient.println("ok");
	 			db.insert(id, temp, temp2);
	 			
	 		}
	 		//serverSocket.close();
	 	}
	 	catch(UnknownHostException ex) {
	 		ex.printStackTrace();
	 	}
	 	catch(IOException e){
	 		e.printStackTrace();
	 	}
	}
}