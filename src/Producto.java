import org.bson.Document;

public class Producto {
    private String id;
    private String nombre;
    private double precio;
    private int stock;
    private String imagenUrl;

    public Producto(String id, String nombre, double precio, int stock, String imagenUrl) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
        this.imagenUrl = imagenUrl;
    }

    public Document toDocument() {
        return new Document("id", id)
                .append("nombre", nombre)
                .append("precio", precio)
                .append("stock", stock)
                .append("imagenUrl", imagenUrl);
    }

    public static Producto fromDocument(Document doc) {
        return new Producto(
                doc.getString("id"),
                doc.getString("nombre"),
                doc.getDouble("precio"),
                doc.getInteger("stock"),
                doc.getString("imagenUrl")
        );
    }

    // Getters y Setters
    public String getId() { return id; }
    public String getNombre() { return nombre; }
    public double getPrecio() { return precio; }
    public int getStock() { return stock; }
    public String getImagenUrl() { return imagenUrl; }

    public void actualizarStock(int cantidad) {
        this.stock += cantidad;
    }
}
