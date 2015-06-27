import java.io.Serializable;
import java.util.Random;

/** 
 * Class create for clarity and in order to be able to send, in case of implementation in the future
 * all the data that the tiles contains at once, by means of serializing the object
 * Inputs: Data to send.
 * Methods: Change temperature, a random algorithm for generating a valid variation of the temperature
 * during time.
 * Outputs: None
 */
public class Datagram implements Serializable {
	//We use longs to store our id since they have a fixed 8 byte size.
	long sub_id1;
	long sub_id2;
	//We use two bytes to indicate the temperature, first one is the full value, second, after the coma.
	byte[] temperature=new byte[2];
	
	
	public Datagram(long sub_id1, long sub_id2, byte temp1,byte temp2){
		this.sub_id1=sub_id1;
		this.sub_id2=sub_id2;
		this.temperature[0]=temp1;
		this.temperature[1]=temp2;
	}
		
	public void change_Temperature(){
		Random rGen = new Random();
		char change=(char)rGen.nextInt(10);
		if(rGen.nextInt(50)%2==0){
			if(temperature[1]+change>9){
				temperature[1]+=change-10;
				temperature[0]+=1;				
			}
			else{
				temperature[1]+=change;
			}
		}	
		else{
			if(temperature[1]-change<0){
				temperature[1]+=10;
				temperature[1]-=change;
				temperature[0]-=1;
			}
			else{
				temperature[1]-=change;
			}	
		}
	}
}
			