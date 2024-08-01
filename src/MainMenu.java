import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends JFrame {
    private Usuario usuario;

    public MainMenu(Usuario usuario) {
        this.usuario = usuario;
        setTitle("Menú Principal - CellTechHub");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel lblMenu = new JLabel("Menú Principal");
        lblMenu.setBounds(150, 20, 100, 25);
        add(lblMenu);

        JButton btnIngresarProducto = new JButton("Ingresar Producto");
        btnIngresarProducto.setBounds(120, 60, 160, 25);
        add(btnIngresarProducto);

        JButton btnMostrarProducto = new JButton("Mostrar Producto");
        btnMostrarProducto.setBounds(120, 100, 160, 25);
        add(btnMostrarProducto);

        JButton btnHacerCompra = new JButton("Hacer Compra");
        btnHacerCompra.setBounds(120, 140, 160, 25);
        add(btnHacerCompra);

        btnIngresarProducto.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Ingresarproducto().setVisible(true);
            }
        });

        btnMostrarProducto.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new MostrarProducto().setVisible(true);
            }
        });

        btnHacerCompra.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Hacercompra(usuario).setVisible(true);
            }
        });
    }
}
