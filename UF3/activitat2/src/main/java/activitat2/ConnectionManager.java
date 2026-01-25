package activitat2;

import org.bson.Document;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoException;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class ConnectionManager {

    private static final String CONNECTION_STRING = "mongodb+srv://ghizlane:1qazZAQ!@cluster0.iatampw.mongodb.net/?appName=Cluster0";
    private static final String DB_NAME = "Cotxes"; 

    private static MongoClient mongoClient = null;

    
    public static MongoDatabase getConnection() {
        if (mongoClient == null) {
            try {
                ServerApi serverApi = ServerApi.builder()
                        .version(ServerApiVersion.V1)
                        .build();

                MongoClientSettings settings = MongoClientSettings.builder()
                        .applyConnectionString(new ConnectionString(CONNECTION_STRING))
                        .serverApi(serverApi)
                        .build();

                mongoClient = MongoClients.create(settings);

                MongoDatabase database = mongoClient.getDatabase(DB_NAME);
                database.runCommand(new Document("ping", 1));
                System.out.println("Ping a la BBDD exitós! Connectat correctament a MongoDB.");
            } catch (MongoException e) {
                System.err.println("Error connectant a MongoDB: " + e.getMessage());
                e.printStackTrace();
            }
        }
        return mongoClient.getDatabase(DB_NAME);
    }

    public static void closeConnection() {
        if (mongoClient != null) {
            mongoClient.close();
            mongoClient = null;
            System.out.println("Connexió MongoDB tancada.");
        }
    }
}
