import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Notadeventa {
    private Transaccion transaccion;

    public Notadeventa(Transaccion transaccion) {
        this.transaccion = transaccion;
    }

    public void generarNotadeventa() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("nota_de_venta_" + transaccion.getId() + ".txt"))) {
            writer.write("Nota de Venta\n");
            writer.write("ID Transacción: " + transaccion.getId() + "\n");
            writer.write("Producto: " + transaccion.getProducto().getNombre() + "\n");
            writer.write("Cantidad: " + transaccion.getCantidad() + "\n");
            writer.write("Precio Unitario: " + transaccion.getProducto().getPrecio() + "\n");
            writer.write("Total: " + (transaccion.getProducto().getPrecio() * transaccion.getCantidad()) + "\n");
            writer.write("Cajero ID: " + transaccion.getCajeroId() + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
