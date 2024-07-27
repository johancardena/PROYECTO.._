import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class Conexion {
    private static MongoClient mongoClient;
    private static MongoDatabase database;

    public static void connect() {
        mongoClient = MongoClients.create("mongodb://localhost:27017");
        database = mongoClient.getDatabase("CellTechHubDB");
    }

    public static MongoDatabase getDatabase() {
        return database;
    }

    public static void close() {
        if (mongoClient != null) {
            mongoClient.close();
        }
    }
}
