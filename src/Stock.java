import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class Stock {
    private MongoCollection<Document> productCollection;

    public Stock() {
        Conexion.connect();
        productCollection = Conexion.getDatabase().getCollection("productos");
    }

    public void agregarProducto(Producto producto) {
        productCollection.insertOne(producto.toDocument());
    }

    public Producto getProducto(String id) {
        Document query = new Document("id", id);
        Document doc = productCollection.find(query).first();
        return doc != null ? Producto.fromDocument(doc) : null;
    }

    public List<Producto> getProductos() {
        List<Producto> productos = new ArrayList<>();
        try (MongoCursor<Document> cursor = productCollection.find().iterator()) {
            while (cursor.hasNext()) {
                productos.add(Producto.fromDocument(cursor.next()));
            }
        }
        return productos;
    }

    public void close() {
        Conexion.close();
    }
}
