import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class CellTechHub extends JFrame {
    private JTextField txtId;
    private JTextField txtNombre;
    private JTextField txtPrecio;
    private JTextField txtStock;
    private JTextField txtImagenUrl;
    private JTextArea txtAreaStock;
    private JTextField txtTransaccionId;
    private JTextField txtTransaccionCantidad;
    private JTextField txtCajeroNombre;
    private JTextField txtCajeroContrasena;
    private JTextField txtCajeroId;
    private Stock stock;
    private Usuario usuario;

    public CellTechHub(Usuario usuario) {
        this.usuario = usuario;
        stock = new Stock();

        setTitle("CellTechHub - Gestión de Tienda de Accesorios");
        setSize(800, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        if (usuario.getRol().equals("administrador")) {
            initAdministradorPanel();
        } else if (usuario.getRol().equals("cajero")) {
            initCajeroPanel();
        }

        mostrarStock();
    }

    private void initAdministradorPanel() {
        // Panel de Producto
        JPanel productPanel = new JPanel();
        productPanel.setBounds(10, 10, 760, 150);
        productPanel.setLayout(null);

        JLabel lblId = new JLabel("ID:");
        lblId.setBounds(10, 10, 80, 25);
        productPanel.add(lblId);

        txtId = new JTextField();
        txtId.setBounds(100, 10, 160, 25);
        productPanel.add(txtId);

        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(10, 40, 80, 25);
        productPanel.add(lblNombre);

        txtNombre = new JTextField();
        txtNombre.setBounds(100, 40, 160, 25);
        productPanel.add(txtNombre);

        JLabel lblPrecio = new JLabel("Precio:");
        lblPrecio.setBounds(10, 70, 80, 25);
        productPanel.add(lblPrecio);

        txtPrecio = new JTextField();
        txtPrecio.setBounds(100, 70, 160, 25);
        productPanel.add(txtPrecio);

        JLabel lblStock = new JLabel("Stock:");
        lblStock.setBounds(10, 100, 80, 25);
        productPanel.add(lblStock);

        txtStock = new JTextField();
        txtStock.setBounds(100, 100, 160, 25);
        productPanel.add(txtStock);

        JLabel lblImagenUrl = new JLabel("Imagen URL:");
        lblImagenUrl.setBounds(270, 10, 80, 25);
        productPanel.add(lblImagenUrl);

        txtImagenUrl = new JTextField();
        txtImagenUrl.setBounds(360, 10, 160, 25);
        productPanel.add(txtImagenUrl);

        JButton btnAgregar = new JButton("Agregar Producto");
        btnAgregar.setBounds(270, 40, 250, 25);
        productPanel.add(btnAgregar);

        add(productPanel);

        // Panel de Cajero
        JPanel cajeroPanel = new JPanel();
        cajeroPanel.setBounds(10, 170, 760, 150);
        cajeroPanel.setLayout(null);

        JLabel lblCajeroId = new JLabel("ID Cajero:");
        lblCajeroId.setBounds(10, 10, 80, 25);
        cajeroPanel.add(lblCajeroId);

        txtCajeroId = new JTextField();
        txtCajeroId.setBounds(100, 10, 160, 25);
        cajeroPanel.add(txtCajeroId);

        JLabel lblCajeroNombre = new JLabel("Nombre:");
        lblCajeroNombre.setBounds(10, 40, 80, 25);
        cajeroPanel.add(lblCajeroNombre);

        txtCajeroNombre = new JTextField();
        txtCajeroNombre.setBounds(100, 40, 160, 25);
        cajeroPanel.add(txtCajeroNombre);

        JLabel lblCajeroContrasena = new JLabel("Contraseña:");
        lblCajeroContrasena.setBounds(10, 70, 80, 25);
        cajeroPanel.add(lblCajeroContrasena);

        txtCajeroContrasena = new JTextField();
        txtCajeroContrasena.setBounds(100, 70, 160, 25);
        cajeroPanel.add(txtCajeroContrasena);

        JButton btnAgregarCajero = new JButton("Agregar Cajero");
        btnAgregarCajero.setBounds(270, 40, 250, 25);
        cajeroPanel.add(btnAgregarCajero);

        add(cajeroPanel);

        btnAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarProducto();
                mostrarStock();
            }
        });

        btnAgregarCajero.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarCajero();
            }
        });
    }

    private void initCajeroPanel() {
        // Panel de Transacción
        JPanel transaccionPanel = new JPanel();
        transaccionPanel.setBounds(10, 10, 760, 150);
        transaccionPanel.setLayout(null);

        JLabel lblTransaccionId = new JLabel("Producto ID:");
        lblTransaccionId.setBounds(10, 10, 80, 25);
        transaccionPanel.add(lblTransaccionId);

        txtTransaccionId = new JTextField();
        txtTransaccionId.setBounds(100, 10, 160, 25);
        transaccionPanel.add(txtTransaccionId);

        JLabel lblTransaccionCantidad = new JLabel("Cantidad:");
        lblTransaccionCantidad.setBounds(10, 40, 80, 25);
        transaccionPanel.add(lblTransaccionCantidad);

        txtTransaccionCantidad = new JTextField();
        txtTransaccionCantidad.setBounds(100, 40, 160, 25);
        transaccionPanel.add(txtTransaccionCantidad);

        JButton btnRegistrarTransaccion = new JButton("Registrar Transacción");
        btnRegistrarTransaccion.setBounds(270, 40, 250, 25);
        transaccionPanel.add(btnRegistrarTransaccion);

        add(transaccionPanel);

        btnRegistrarTransaccion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registrarTransaccion();
                mostrarStock();
            }
        });
    }

    private void agregarProducto() {
        String id = txtId.getText();
        String nombre = txtNombre.getText();
        double precio = Double.parseDouble(txtPrecio.getText());
        int stockCantidad = Integer.parseInt(txtStock.getText());
        String imagenUrl = txtImagenUrl.getText();

        Producto producto = new Producto(id, nombre, precio, stockCantidad, imagenUrl);
        stock.agregarProducto(producto);
    }

    private void agregarCajero() {
        String id = txtCajeroId.getText();
        String nombre = txtCajeroNombre.getText();
        String contrasena = txtCajeroContrasena.getText();

        Usuario cajero = new Usuario(id, nombre, contrasena, "cajero");
        // Aquí deberías agregar el cajero a una lista de usuarios o una base de datos
    }

    private void registrarTransaccion() {
        String transaccionId = txtTransaccionId.getText();
        int cantidad = Integer.parseInt(txtTransaccionCantidad.getText());

        Producto producto = stock.getProducto(transaccionId);
        if (producto != null && producto.getStock() >= cantidad) {
            Transaccion transaccion = new Transaccion(transaccionId, producto, cantidad, usuario.getId());
            producto.setStock(producto.getStock() - cantidad);

            Notadeventa notaDeVenta = new Notadeventa(transaccion);
            notaDeVenta.generarNotadeventa();
        } else {
            JOptionPane.showMessageDialog(this, "Stock insuficiente o producto no encontrado.");
        }
    }

    private void mostrarStock() {
        if (txtAreaStock == null) {
            txtAreaStock = new JTextArea();
            txtAreaStock.setBounds(10, 330, 760, 300);
            add(txtAreaStock);
        }

        List<Producto> productos = stock.getProductos();
        StringBuilder sb = new StringBuilder();
        for (Producto producto : productos) {
            sb.append("ID: ").append(producto.getId()).append(", Nombre: ").append(producto.getNombre())
                    .append(", Precio: ").append(producto.getPrecio()).append(", Stock: ").append(producto.getStock())
                    .append(", Imagen URL: ").append(producto.getImagenUrl()).append("\n");
        }
        txtAreaStock.setText(sb.toString());
    }

}
