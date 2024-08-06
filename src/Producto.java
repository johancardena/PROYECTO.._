import org.bson.Document;

import java.util.ResourceBundle;

public class Producto {
    private static ResourceBundle productoDoc;
    private String id;
    private String nombre;
    private double precio;
    private int stock;


    public Producto(String string, String id, String nombre, double precio, int stock, String imagen) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;

    }

    public String getId() {
        return id;
    }
    public String getNombre() {
        return nombre;
    }
    public double getPrecio() {
        return precio;
    }
    public int getStock() {
        return stock;
    }
    public void setStock(int stock) {
        this.stock = stock;
    }

    public Document toDocument() {
        return new Document("id", id)
                .append("nombre", nombre)
                .append("precio", precio)
                .append("stock", stock);
    }

    public static Producto fromDocument(Document doc) {
        return new Producto(
                productoDoc.getString("id"), doc.getString("id"),
                doc.getString("nombre"),
                doc.getDouble("precio"),
                doc.getInteger("stock"),
                productoDoc.getString("imagen"));
    }
}

