import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

public class MostrarProducto extends JFrame {
    private JTextField txtId;
    private JTextArea txtAreaDetalles;

    public MostrarProducto() {
        setTitle("Mostrar Producto - CellTechHub");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel lblId = new JLabel("ID:");
        lblId.setBounds(20, 20, 80, 25);
        add(lblId);

        txtId = new JTextField();
        txtId.setBounds(100, 20, 160, 25);
        add(txtId);

        JButton btnBuscar = new JButton("Buscar");
        btnBuscar.setBounds(270, 20, 100, 25);
        add(btnBuscar);

        txtAreaDetalles = new JTextArea();
        txtAreaDetalles.setBounds(20, 60, 350, 200);
        add(txtAreaDetalles);

        btnBuscar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mostrarProducto();
            }
        });
    }

    private void mostrarProducto() {
        String id = txtId.getText();

        Conexion.connect();
        MongoCollection<Document> productCollection = Conexion.getDatabase().getCollection("productos");

        Document query = new Document("id", id);
        Document producto = productCollection.find(query).first();

        Conexion.close();

        if (producto != null) {
            String detalles = "ID: " + producto.getString("id") +
                    "\nNombre: " + producto.getString("nombre") +
                    "\nDescripción: " + producto.getString("descripcion") +
                    "\nPrecio: " + producto.getDouble("precio") +
                    "\nStock: " + producto.getInteger("stock");
            txtAreaDetalles.setText(detalles);
        } else {
            JOptionPane.showMessageDialog(this, "Producto no encontrado.");
        }
    }
}
