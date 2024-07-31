import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

public class Login1 extends JFrame {
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin;
    private JButton btnCancel;

    public Login1() {
        setTitle("Login - CellTechHub");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel lblUsername = new JLabel("Username:");
        lblUsername.setBounds(20, 20, 80, 25);
        add(lblUsername);

        txtUsername = new JTextField();
        txtUsername.setBounds(100, 20, 160, 25);
        add(txtUsername);

        JLabel lblPassword = new JLabel("Password:");
        lblPassword.setBounds(20, 60, 80, 25);
        add(lblPassword);

        txtPassword = new JPasswordField();
        txtPassword.setBounds(100, 60, 160, 25);
        add(txtPassword);

        btnLogin = new JButton("Login");
        btnLogin.setBounds(40, 100, 100, 25);
        add(btnLogin);

        btnCancel = new JButton("Cancel");
        btnCancel.setBounds(150, 100, 100, 25);
        add(btnCancel);

        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                authenticate();
            }
        });

        btnCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    private void authenticate() {
        String username = txtUsername.getText();
        String password = new String(txtPassword.getPassword());

        Conexion.connect();
        MongoCollection<Document> userCollection = Conexion.getDatabase().getCollection("usuarios");

        Document query = new Document("username", username).append("password", password);
        Document userDoc = userCollection.find(query).first();

        if (userDoc != null) {
            String role = userDoc.getString("role");
            Usuario user = new Usuario(
                    userDoc.getString("id"),
                    userDoc.getString("username"),
                    userDoc.getString("password"),
                    role
            );

            Conexion.close();

            if (role.equals("administrador")) {
                CellTechHub adminUI = new CellTechHub(user);
                adminUI.setVisible(true);
            } else if (role.equals("cajero")) {
                CellTechHub cashierUI = new CellTechHub(user);
                cashierUI.setVisible(true);
            }
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Usuario o contraseña incorrectos.");
        }
    }


}
