import gui.LoginFrame;
import javax.swing.SwingUtilities;

public class App {
    public static void main(String[] args) {
        // Ejecutar en el hilo de eventos de Swing para evitar problemas de concurrencia
        SwingUtilities.invokeLater(() -> {
            new LoginFrame().setVisible(true);
        });
    }
}
