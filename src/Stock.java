import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class Stock {
    private MongoCollection<Document> collection;

    public Stock() {
        MongoDatabase database = Conexion.getDatabase();
        collection = database.getCollection("productos");
    }

    public void agregarProducto(Producto producto) {
        collection.insertOne(producto.toDocument());
    }

    public void actualizarStock(String productoId, int cantidad) {
        Document query = new Document("id", productoId);
        Document update = new Document("$inc", new Document("stock", cantidad));
        collection.updateOne(query, update);
    }

    public List<Producto> obtenerTodosLosProductos() {
        List<Producto> productos = new ArrayList<>();
        for (Document doc : collection.find()) {
            productos.add(Producto.fromDocument(doc));
        }
        return productos;
    }

    public Producto obtenerProducto(String id) {
        Document doc = collection.find(new Document("id", id)).first();
        return doc != null ? Producto.fromDocument(doc) : null;
    }
}
