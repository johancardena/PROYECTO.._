import com.mongodb.client.MongoCollection;
import org.bson.Document;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class login extends JFrame {
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin;
    private JButton btnCancel;
    private JPanel mainPanel;

    public login() {
        setTitle("Login ");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel lblUsername = new JLabel("USERNAME:");
        lblUsername.setBounds(20, 20, 80, 25);
        add(lblUsername);

        txtUsername = new JTextField();
        txtUsername.setBounds(100, 20, 160, 25);
        add(txtUsername);

        JLabel lblPassword = new JLabel("PASSWORD:");
        lblPassword.setBounds(20, 60, 80, 25);
        add(lblPassword);

        txtPassword = new JPasswordField();
        txtPassword.setBounds(100, 60, 160, 25);
        add(txtPassword);

        btnLogin = new JButton("ENTER");
        btnLogin.setBounds(40, 100, 100, 25);
        add(btnLogin);

        btnCancel = new JButton("CANCEL");
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
            main_menu mainMenu = new main_menu(user);
            mainMenu.setVisible(true);
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Usuario o contraseña incorrectos.");
        }
    }
}



