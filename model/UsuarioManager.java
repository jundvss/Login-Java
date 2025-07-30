package model;

import java.util.ArrayList;
import java.util.List;

public class UsuarioManager {
    private static UsuarioManager instancia;
    private final List<Usuario> listaUsuarios;

    private UsuarioManager() {
        listaUsuarios = new ArrayList<>();
        
        // Usuario de prueba para hacer login
        listaUsuarios.add(new Usuario("admin", "Admin", "General", "8090000000", "admin@email.com", "1234"));
    }

    public static UsuarioManager getInstancia() {
        if (instancia == null) {
            instancia = new UsuarioManager();
        }
        return instancia;
    }

    public boolean registrar(Usuario nuevo) {
        for (Usuario u : listaUsuarios) {
            if (u.getUsername().equalsIgnoreCase(nuevo.getUsername())) {
                return false; // ya existe
            }
        }
        listaUsuarios.add(nuevo);
        return true;
    }

    public boolean login(String username, String contrasena) {
        for (Usuario u : listaUsuarios) {
            if (u.getUsername().equalsIgnoreCase(username) && u.getContrasena().equals(contrasena)) {
                return true;
            }
        }
        return false;
    }

    public List<Usuario> listarUsuarios() {
        return new ArrayList<>(listaUsuarios);
    }

    public boolean eliminar(String username) {
        return listaUsuarios.removeIf(u -> u.getUsername().equalsIgnoreCase(username));
    }

    public boolean actualizar(Usuario actualizado) {
        for (int i = 0; i < listaUsuarios.size(); i++) {
            Usuario u = listaUsuarios.get(i);
            if (u.getUsername().equalsIgnoreCase(actualizado.getUsername())) {
                listaUsuarios.set(i, actualizado);
                return true;
            }
        }
        return false;
    }
}
