package gui;

import java.awt.*;
import javax.swing.*;
import model.Usuario;
import model.UsuarioManager;

public class RegisterFrame extends JFrame {
    private final JTextField txtUsername = new JTextField(15);
    private final JTextField txtNombre = new JTextField(15);
    private final JTextField txtApellido = new JTextField(15);
    private final JTextField txtTelefono = new JTextField(15);
    private final JTextField txtCorreo = new JTextField(15);
    private final JPasswordField txtPassword = new JPasswordField(15);
    private final JPasswordField txtConfirm = new JPasswordField(15);
    private final JButton btnRegistrar = new JButton("Registrar");

    public RegisterFrame() {
        setTitle("Registro de Usuario");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(8, 2));

        add(new JLabel("Usuario:")); add(txtUsername);
        add(new JLabel("Nombre:")); add(txtNombre);
        add(new JLabel("Apellido:")); add(txtApellido);
        add(new JLabel("Teléfono:")); add(txtTelefono);
        add(new JLabel("Correo:")); add(txtCorreo);
        add(new JLabel("Contraseña:")); add(txtPassword);
        add(new JLabel("Confirmar Contraseña:")); add(txtConfirm);
        add(new JLabel("")); add(btnRegistrar);

        btnRegistrar.addActionListener(e -> registrarUsuario());

        pack();
        setLocationRelativeTo(null);
    }

    private void registrarUsuario() {
        String username = txtUsername.getText();
        String nombre = txtNombre.getText();
        String apellido = txtApellido.getText();
        String telefono = txtTelefono.getText();
        String correo = txtCorreo.getText();
        String pass = new String(txtPassword.getPassword());
        String confirm = new String(txtConfirm.getPassword());

        if (username.isEmpty() || nombre.isEmpty() || apellido.isEmpty() || telefono.isEmpty() || correo.isEmpty() || pass.isEmpty() || confirm.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios.");
            return;
        }

        if (!pass.equals(confirm)) {
            JOptionPane.showMessageDialog(this, "Las contraseñas no coinciden.");
            return;
        }

        Usuario u = new Usuario(username, nombre, apellido, telefono, correo, pass);
        if (UsuarioManager.getInstancia().registrar(u)) {
            JOptionPane.showMessageDialog(this, "Usuario registrado exitosamente.");
            dispose();
            new LoginFrame().setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Error: el nombre de usuario ya existe.");
        }
    }
}