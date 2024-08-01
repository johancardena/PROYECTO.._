import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

public class Hacercompra extends JFrame {
    private JTextField txtIdProducto;
    private JTextField txtCantidad;
    private Usuario usuario;

    public Hacercompra(Usuario usuario) {
        this.usuario = usuario;
        setTitle("Hacer Compra - CellTechHub");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);


        JLabel lblIdProducto = new JLabel("ID Producto:");
        lblIdProducto.setBounds(20, 20, 100, 25);
        add(lblIdProducto);

        txtIdProducto = new JTextField();
        txtIdProducto.setBounds(130, 20, 130, 25);
        add(txtIdProducto);

        JLabel lblCantidad = new JLabel("Cantidad:");
        lblCantidad.setBounds(20, 60, 100, 25);
        add(lblCantidad);

        txtCantidad = new JTextField();
        txtCantidad.setBounds(130, 60, 130, 25);
        add(txtCantidad);

        JButton btnComprar = new JButton("Comprar");
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
            } else {
                JOptionPane.showMessageDialog(this, "Stock insuficiente.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Producto no encontrado.");
        }

        Conexion.close();
    }
}
