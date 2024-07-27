public class Notadeventa {
    private Transaccion transaccion;

    public Notadeventa(Transaccion transaccion) {
        this.transaccion = transaccion;
    }

    public void imprimirNota() {
        System.out.println("Nota de Venta:");
        System.out.println("Producto: " + transaccion.getProducto().getNombre());
        System.out.println("Cantidad: " + transaccion.getCantidad());
        System.out.println("Total: " + transaccion.getTotal());
    }
}
