public class Transaccion {
    private String id;
    private Producto producto;
    private int cantidad;
    private double total;

    public Transaccion(String id, Producto producto, int cantidad) {
        this.id = id;
        this.producto = producto;
        this.cantidad = cantidad;
        this.total = producto.getPrecio() * cantidad;
    }

    public void generarNotaDeVenta() {
        Notadeventa nota = new Notadeventa(this);
        nota.imprimirNota();
    }

    // Getters y Setters
    public Producto getProducto() { return producto; }
    public int getCantidad() { return cantidad; }
    public double getTotal() { return total; }
}
