import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        // Conectar a la base de datos
        Conexion.connect();

        // Lanzar la interfaz de login
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Login1().setVisible(true);
            }
        });
    }
}


