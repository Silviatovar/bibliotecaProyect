package modelos;

import dao.prueba.UsuarioJpaController;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.RollbackException;
import modelo.entidades.Rol;
import modelo.entidades.Usuario;

/**
 * Clase ModeloUsuarios.
 * Contiene métodos estáticos para implementar la lógica del modelo de Usuario.
 */
public class ModeloUsuarios {
    public static final String PU = "BibliotecaPU";
    
    public static List<Usuario> getUsuarios() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(PU);
        UsuarioJpaController ujc = new UsuarioJpaController(emf);
        List<Usuario> usuarios = ujc.findUsuarioEntities();
        emf.close();
        return usuarios;
    }
    
    public static List<Usuario> getUsuarios(String filtro) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(PU);
        UsuarioJpaController ujc = new UsuarioJpaController(emf);
        List<Usuario> usuarios = ujc.findUsuarioEntities();
        emf.close();
        List<Usuario> usuariosFiltrados = new ArrayList<>();
        
        for (Usuario u : usuarios) {
            if (u.getNombre().contains(filtro) || u.getApellidos().contains(filtro)) {
                usuariosFiltrados.add(u);
            }
        }
        
        return usuariosFiltrados;
    }
    public static String crearUsuarioConRol(String nombre, String apellidos, String email, String domicilio,
        String password, Rol rol) {
    String error = null;

    EntityManagerFactory emf = Persistence.createEntityManagerFactory(PU);
    UsuarioJpaController ujc = new UsuarioJpaController(emf);
    Usuario u = new Usuario();
    u.setNombre(nombre);
    u.setApellidos(apellidos);
    u.setEmail(email);
    u.setDomicilio(domicilio);
    u.setContraseña(password);
    u.setRol(rol);

    try {
        ujc.create(u);
    } catch (RollbackException ex) {
        if (ex.getMessage().contains("Duplicate")) {
            error = "Ya existe un usuario con e-mail " + email;
        } else {
            error = "Se ha producido un error al crear el Usuario: " + ex.getMessage();
            ex.printStackTrace();
        }
    } finally {
        emf.close();
    }

    return error;
}

    public static String crearUsuario(String nombre, String apellidos, String email, String domicilio,
            String password) {
        String error = null;

        EntityManagerFactory emf = Persistence.createEntityManagerFactory(PU);
        UsuarioJpaController ujc = new UsuarioJpaController(emf);
        Usuario u = new Usuario();
        u.setNombre(nombre);
        u.setApellidos(apellidos);
        u.setEmail(email);  
        u.setDomicilio(domicilio);
        u.setContraseña(password);
            u.setRol(Rol.SOCIO);
   

        try {
            ujc.create(u);
       } catch (RollbackException ex) {
    if (ex.getMessage().contains("Duplicate")) {
        error = "Ya existe un usuario con e-mail " + email;
    } else {
        error = "Se ha producido un error al crear el Usuario: " + ex.getMessage();
        ex.printStackTrace();  
    }
} finally {
    emf.close();
}

        return error;
    }

    public static Usuario consultarUsuario(Long id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(PU);
        UsuarioJpaController ujc = new UsuarioJpaController(emf);
        Usuario u = ujc.findUsuario(id);
        emf.close();

        if (u == null) {
            throw new IllegalArgumentException("El usuario con ID " + id + " no existe");
        }

        return u;
    }
        
    public static String actualizarUsuario(Usuario u, String nombre, 
        String apellidos, String email, String password, String domicilio) {
    String error = null;
    u.setNombre(nombre);
    u.setApellidos(apellidos);
    u.setEmail(email);
    
    if (password != null && !password.isEmpty()) {
        u.setContraseña(password);
    }

    u.setDomicilio(domicilio);

    EntityManagerFactory emf = Persistence.createEntityManagerFactory(PU);
    UsuarioJpaController ujc = new UsuarioJpaController(emf);

    try {
        ujc.edit(u);
    } catch (dao.prueba.exceptions.NonexistentEntityException ex) {
        error = "El usuario ha sido eliminado";
    } catch (Exception ex) {
        if (ex.getMessage().contains("Duplicate")) {
            error = "Ya existe un usuario con e-mail " + email;
        } else {
            error = "Se ha producido un error al actualizar el usuario " + u;
        }
    } finally {
        emf.close();
    }

    return error;
}

public static String eliminarUsuario(Long usuarioID) {
    String error = null;
    EntityManagerFactory emf = Persistence.createEntityManagerFactory(PU);
    UsuarioJpaController ujc = new UsuarioJpaController(emf);

    try {
        ujc.destroy(usuarioID);
    } catch (Exception ex) {
        error = "Se ha producido un error al eliminar el usuario con ID " + usuarioID;
    } finally {
        emf.close();
    }

    return error;
}
}