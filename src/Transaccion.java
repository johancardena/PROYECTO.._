public class Transaccion {
    private String id;
    private Producto producto;
    private int cantidad;
    private String cajeroId;

    public Transaccion(String id, Producto producto, int cantidad, String cajeroId) {
        this.id = id;
        this.producto = producto;
        this.cantidad = cantidad;
        this.cajeroId = cajeroId;
    }

    public String getId() {
        return id;
    }

    public Producto getProducto() {
        return producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public String getCajeroId() {
        return cajeroId;
    }
}
