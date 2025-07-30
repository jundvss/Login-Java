package gui;

import javax.swing.*;
import model.UsuarioManager;

public class LoginFrame extends JFrame {
    private final JTextField txtUsuario = new JTextField(15);
    private final JPasswordField txtContrasena = new JPasswordField(15);
    private final JButton btnLogin = new JButton("Iniciar Sesión");
    private final JButton btnRegistro = new JButton("Registrarse");

    public LoginFrame() {
        setTitle("Iniciar Sesión");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        panel.add(new JLabel("Usuario:"));
        panel.add(txtUsuario);
        panel.add(Box.createVerticalStrut(10));

        panel.add(new JLabel("Contraseña:"));
        panel.add(txtContrasena);
        panel.add(Box.createVerticalStrut(10));

        panel.add(btnLogin);
        panel.add(Box.createVerticalStrut(5));
        panel.add(btnRegistro);

        add(panel);
        pack();

        btnLogin.addActionListener(e -> iniciarSesion());
        btnRegistro.addActionListener(e -> {
            dispose();
            new RegisterFrame().setVisible(true);
        });
    }

    private void iniciarSesion() {
        String usuario = txtUsuario.getText();
        String clave = new String(txtContrasena.getPassword());

        if (usuario.isEmpty() || clave.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe ingresar usuario y contraseña, si no está registrado debe registrarse.");
            return;
        }

        if (UsuarioManager.getInstancia().login(usuario, clave)) {
            JOptionPane.showMessageDialog(this, "Bienvenido " + usuario);
            dispose();
            new MainAppFrame().setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Usuario o contraseña incorrectos.");
        }
    }
}
