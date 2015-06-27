import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.*;
 
/**
 * Class for inserting and extracting data from the Database.
 * @author Miguel Pagán Murphy
 *
 */
public class Datab {
    
	static MongoClient mongo;
	static MongoDatabase db;
	static String db_name="Placthermdb";
	static String table_name="Tiles";
	  
	public Datab(){
		mongo = createConexion();
		  if (mongo != null) {
          //Si no existe la base de datos la crea
			  db = mongo.getDatabase(db_name);
              System.out.println();
		  }
		  else {
          System.out.println("Error: Conexión no establecida");
		  }
	}
       
 
     /**
     * Creates Mongodb connection.
     * @return MongoClient
     */
    private static MongoClient createConexion() {
        MongoClient mongo = null;
        mongo = new MongoClient("localhost", 27017);
        return mongo;
    }

    /**
     * Checks that the entry exists, inserts it if not, and updates it if it does
     * @param db
     * @param tabla
     * @param id
     * @param temperatura
     * 
     */
    public void insert(String id, String temp, String temp2) {
        //Recoge datos de la tabla
    	MongoCollection<Document> collection = db.getCollection(table_name);
        //BasicDBObject searchById = new BasicDBObject();
        Document doc = new Document("id", id)
                .append("temp1", temp)
                .append("temp2", temp2);
  
    	collection.insertOne(doc);
    	System.out.println("Inserted temperature "+ temp +","+temp2+" into Database from Client: "+id);
        //searchById.append("iden", id);
        //if(searchById!=null){
        //	System.out.println("Entry exists... Updating..");
        //	updateEntry(id,temp,temp2);
        //}
        //Crea un objecto básico y le añade elementos
        //Document doc = new Document("iden", id)
          //      .append("type", "database")
            //    .append("temp1", temp)
              //  .append("temp2", temp2);
        /*BasicDBObject document = new BasicDBObject();
        document.put("Id", id);
        document.put("Temp1", temp1);
        document.put("Temp2", temp2);
        document.put("Fecha", new Date());
 		*/
        //Inserta la tabla
    }
 
    /**
     * 
     * @param db
     * @param tabla
     * @param id
     * @param nuevosAnyos
     */
    private static void updateEntry(String id, String temp, String temp2) {
        //Recoge datos de la tabla
    	MongoCollection<Document> collection = db.getCollection(table_name);
    	
        //Prepara para insertar un nuevo campo
        //Document doc = new Document("iden", id)
          //      .append("type", "database")
           //     .append("temp1", temp)
            //    .append("temp2", temp2);
        //Busca el/los registro/s con el nombre indicado
        //BasicDBObject searchById = new BasicDBObject();
        //searchById.append("iden", id);
    	//Document myDoc = collection.find(eq("i", 71)).first();
    	//Realiza la actualización
        //collection.updateOne(searchById, doc);
    }
}