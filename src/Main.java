import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        Conexion.connect();

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new login().setVisible(true);
            }
        });
    }
}
