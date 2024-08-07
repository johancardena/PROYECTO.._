import com.mongodb.client.MongoCollection;
import org.bson.Document;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URL;

public class Mostrar_producto extends JFrame {
    private JTextField txtId;
    private JTextArea txtAreaDetalles;
    private JLabel lblImagenProducto;
    private JButton btnBuscar;

    public Mostrar_producto() {
        setTitle("Mostrar Producto - CellTechHub");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel lblId = new JLabel("ID:");
        lblId.setBounds(20, 20, 80, 25);
        add(lblId);

        txtId = new JTextField();
        txtId.setBounds(100, 20, 160, 25);
        add(txtId);

        btnBuscar = new JButton("Buscar");
        btnBuscar.setBounds(270, 20, 100, 25);
        add(btnBuscar);

        txtAreaDetalles = new JTextArea();
        txtAreaDetalles.setBounds(20, 60, 350, 200);
        add(txtAreaDetalles);

        lblImagenProducto = new JLabel();
        lblImagenProducto.setBounds(20, 270, 350, 200);
        add(lblImagenProducto);

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

            String imageUrl = producto.getString("imagenUrl");
            if (imageUrl != null && !imageUrl.isEmpty()) {
                try {
                    URL url = new URL(imageUrl);
                    ImageIcon imageIcon = new ImageIcon(new ImageIcon(url).getImage().getScaledInstance(350, 200, java.awt.Image.SCALE_SMOOTH));
                    lblImagenProducto.setIcon(imageIcon);
                    lblImagenProducto.setText("");
                } catch (MalformedURLException e) {
                    lblImagenProducto.setIcon(null);
                    lblImagenProducto.setText("URL de imagen inválida");
                }
            } else {
                lblImagenProducto.setIcon(null);
                lblImagenProducto.setText("Sin imagen");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Producto no encontrado.");
            txtAreaDetalles.setText("");
            lblImagenProducto.setIcon(null);
            lblImagenProducto.setText("");
        }
    }
}
