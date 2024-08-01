
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

public class Ingresarproducto extends JFrame {
    private JTextField txtId;
    private JTextField txtNombre;
    private JTextField txtDescripcion;
    private JTextField txtPrecio;
    private JTextField txtStock;

    public Ingresarproducto() {
        setTitle("Ingresar Producto - CellTechHub");
        setSize(300, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel lblId = new JLabel("ID:");
        lblId.setBounds(20, 20, 80, 25);
        add(lblId);

        txtId = new JTextField();
        txtId.setBounds(100, 20, 160, 25);
        add(txtId);

        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(20, 60, 80, 25);
        add(lblNombre);

        txtNombre = new JTextField();
        txtNombre.setBounds(100, 60, 160, 25);
        add(txtNombre);

        JLabel lblDescripcion = new JLabel("Descripción:");
        lblDescripcion.setBounds(20, 100, 80, 25);
        add(lblDescripcion);

        txtDescripcion = new JTextField();
        txtDescripcion.setBounds(100, 100, 160, 25);
        add(txtDescripcion);

        JLabel lblPrecio = new JLabel("Precio:");
        lblPrecio.setBounds(20, 140, 80, 25);
        add(lblPrecio);

        txtPrecio = new JTextField();
        txtPrecio.setBounds(100, 140, 160, 25);
        add(txtPrecio);

        JLabel lblStock = new JLabel("Stock:");
        lblStock.setBounds(20, 180, 80, 25);
        add(lblStock);

        txtStock = new JTextField();
        txtStock.setBounds(100, 180, 160, 25);
        add(txtStock);

        JButton btnIngresar = new JButton("Ingresar");
        btnIngresar.setBounds(100, 220, 160, 25);
        add(btnIngresar);

        btnIngresar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ingresarProducto();
            }
        });
    }

    private void ingresarProducto() {
        String id = txtId.getText();
        String nombre = txtNombre.getText();
        String descripcion = txtDescripcion.getText();
        double precio = Double.parseDouble(txtPrecio.getText());
        int stock = Integer.parseInt(txtStock.getText());

        Conexion.connect();
        MongoCollection<Document> productCollection = Conexion.getDatabase().getCollection("productos");

        Document producto = new Document("id", id)
                .append("nombre", nombre)
                .append("descripcion", descripcion)
                .append("precio", precio)
                .append("stock", stock);

        productCollection.insertOne(producto);

        Conexion.close();

        JOptionPane.showMessageDialog(this, "Producto ingresado exitosamente.");
    }
}
