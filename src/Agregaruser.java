import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class Agregaruser {
    public static void main(String[] args) {
        // Conectar a la base de datos
        Conexion.connect();
        MongoDatabase database = Conexion.getDatabase();
        MongoCollection<Document> userCollection = database.getCollection("usuarios");

        // Crear usuario cajero/administrador
        Document admin = new Document("id", "1")
                .append("username", "cajero.local")
                .append("password", "cajero123")
                .append("role", "Juan_cajero");

        // Crear usuario cajero/administrador
        Document cashier = new Document("id", "2")
                .append("username", "cajero.inter")
                .append("password", "456cajero")
                .append("role", "Luis_cajero");

        // Insertar usuarios en la colección
        userCollection.insertOne(admin);
        userCollection.insertOne(cashier);

        // Cerrar la conexión a la base de datos
        Conexion.close();

        System.out.println("Usuarios agregados exitosamente.");
    }
}
