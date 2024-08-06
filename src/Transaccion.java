import com.itextpdf.text.Document;

public class Transaccion {
    private String id;
    private Producto producto;
    private int cantidad;
    private Usuario cajero;

    public Transaccion(String id, Producto producto, int cantidad, Usuario cajero) {
        this.id = id;
        this.producto = producto;
        this.cantidad = cantidad;
        this.cajero = cajero;
    }



    public void generarFactura() {
        Factura.generarFactura(id, producto.getNombre(), cantidad, producto.getPrecio(), cajero.getUsername());
    }

    // Getters y setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Usuario getCajero() {
        return cajero;
    }

    public void setCajero(Usuario cajero) {
        this.cajero = cajero;
    }
}
