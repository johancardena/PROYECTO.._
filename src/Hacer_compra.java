import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class Hacer_compra extends JFrame {
    private JTextField txtIdProducto;
    private JPanel panel1;
    private JTextField txtCantidad;
    private JButton COMPRARButton;
    private Usuario usuario;

    public Hacer_compra(Usuario usuario) {
        this.usuario = usuario;
        setTitle("Hacer Compra");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel lblIdProducto = new JLabel("ID PRODUCTO:");
        lblIdProducto.setBounds(20, 20, 100, 25);
        add(lblIdProducto);

        txtIdProducto = new JTextField();
        txtIdProducto.setBounds(130, 20, 130, 25);
        add(txtIdProducto);

        JLabel lblCantidad = new JLabel("CANTIDAD:");
        lblCantidad.setBounds(20, 60, 100, 25);
        add(lblCantidad);

        txtCantidad = new JTextField();
        txtCantidad.setBounds(130, 60, 130, 25);
        add(txtCantidad);

        JButton btnComprar = new JButton("COMPRAR");
        btnComprar.setBounds(90, 100, 120, 25);
        add(btnComprar);

        btnComprar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                hacerCompra();
            }
        });
    }

    private void hacerCompra() {
        String idProducto = txtIdProducto.getText();
        int cantidad = Integer.parseInt(txtCantidad.getText());

        Conexion.connect();
        MongoCollection<Document> productCollection = Conexion.getDatabase().getCollection("productos");
        MongoCollection<Document> transaccionCollection = Conexion.getDatabase().getCollection("transacciones");

        Document query = new Document("id", idProducto);
        Document producto = productCollection.find(query).first();

        if (producto != null) {
            int stock = producto.getInteger("stock");
            if (stock >= cantidad) {
                double precio = producto.getDouble("precio");
                double total = precio * cantidad;

                // Reducir stock
                productCollection.updateOne(query, new Document("$set", new Document("stock", stock - cantidad)));

                // Crear transacción
                Document transaccion = new Document("idProducto", idProducto)
                        .append("cantidad", cantidad)
                        .append("total", total)
                        .append("cajero", usuario.getUsername())
                        .append("idCajero", usuario.getId());
                transaccionCollection.insertOne(transaccion);

                JOptionPane.showMessageDialog(this, "Compra realizada exitosamente.");

                // Generar PDF
                generarFacturaPDF(idProducto, producto.getString("nombre"), cantidad, precio, total, usuario.getUsername());

            } else {
                JOptionPane.showMessageDialog(this, "Stock insuficiente.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Producto no encontrado.");
        }

        Conexion.close();
    }

    private void generarFacturaPDF(String idProducto, String nombreProducto, int cantidad, double precio, double total, String cajero) {
        com.itextpdf.text.Document document = new com.itextpdf.text.Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream("factura_" + idProducto + ".pdf"));
            document.open();
            document.add(new Paragraph("Factura de Compra"));
            document.add(new Paragraph("ID Producto: " + idProducto));
            document.add(new Paragraph("Nombre Producto: " + nombreProducto));
            document.add(new Paragraph("Cantidad: " + cantidad));
            document.add(new Paragraph("Precio Unitario: " + precio));
            document.add(new Paragraph("Total: " + total));
            document.add(new Paragraph("Cajero: " + cajero));
            document.close();
        } catch (DocumentException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
