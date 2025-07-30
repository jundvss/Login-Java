package gui;

import java.awt.*;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import model.Usuario;
import model.UsuarioManager;

public class MainAppFrame extends JFrame {
    private JTable tabla;
    private DefaultTableModel modelo;
    private final JButton btnEliminar = new JButton("Eliminar");
    private final JButton btnActualizar = new JButton("Actualizar");
    private final JButton btnCerrarSesion = new JButton("Cerrar sesión");

    public MainAppFrame() {
        setTitle("Usuarios Registrados");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        modelo = new DefaultTableModel(new Object[]{"Username", "Nombre", "Apellido", "Teléfono", "Correo"}, 0);
        tabla = new JTable(modelo);
        cargarUsuarios();

        JPanel panelBotones = new JPanel();
        panelBotones.add(btnActualizar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnCerrarSesion);

        add(new JScrollPane(tabla), BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);

        btnActualizar.addActionListener(e -> actualizarUsuario());
        btnEliminar.addActionListener(e -> eliminarUsuario());
        btnCerrarSesion.addActionListener(e -> {
            dispose();
            new LoginFrame().setVisible(true);
        });

        setSize(700, 300);
        setLocationRelativeTo(null);
    }

    private void cargarUsuarios() {
        modelo.setRowCount(0);
        List<Usuario> lista = UsuarioManager.getInstancia().listarUsuarios();
        for (Usuario u : lista) {
            modelo.addRow(new Object[]{u.getUsername(), u.getNombre(), u.getApellido(), u.getTelefono(), u.getCorreo()});
        }
    }

    private void eliminarUsuario() {
        int fila = tabla.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un usuario para eliminar.");
            return;
        }
        String username = (String) modelo.getValueAt(fila, 0);
        if (UsuarioManager.getInstancia().eliminar(username)) {
            JOptionPane.showMessageDialog(this, "Usuario eliminado.");
            cargarUsuarios();
        } else {
            JOptionPane.showMessageDialog(this, "No se pudo eliminar el usuario.");
        }
    }

    private void actualizarUsuario() {
        int fila = tabla.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un usuario para actualizar.");
            return;
        }

        String username = (String) modelo.getValueAt(fila, 0);
        String nombre = JOptionPane.showInputDialog("Nuevo nombre:");
        String apellido = JOptionPane.showInputDialog("Nuevo apellido:");
        String telefono = JOptionPane.showInputDialog("Nuevo teléfono:");
        String correo = JOptionPane.showInputDialog("Nuevo correo:");
        String contrasena = JOptionPane.showInputDialog("Nueva contraseña:");

        Usuario actualizado = new Usuario(username, nombre, apellido, telefono, correo, contrasena);
        if (UsuarioManager.getInstancia().actualizar(actualizado)) {
            JOptionPane.showMessageDialog(this, "Usuario actualizado.");
            cargarUsuarios();
        } else {
            JOptionPane.showMessageDialog(this, "No se pudo actualizar el usuario.");
        }
    }
}