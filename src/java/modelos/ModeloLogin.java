package modelos;

import dao.prueba.UsuarioJpaController;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import modelo.entidades.Usuario;
import modelo.entidades.Rol;

public class ModeloLogin {

    public static Usuario validarUsuario(String email, String password) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("BibliotecaPU");
        UsuarioJpaController ujc = new UsuarioJpaController(emf);
        Usuario usuario = null;
        // Obtener la lista de usuarios
        List<Usuario> usuarios = ujc.findUsuarioEntities();
        boolean encontrado = false;
        for (int i = 0; i < usuarios.size() && !encontrado; i++) {
            Usuario actual = usuarios.get(i);
            if(actual.getEmail().equals(email)) {
                encontrado = true;
                if(actual.getContraseña().equals(password)) {
                    usuario = actual;
                }
            }
        }
       emf.close();
       return usuario;
    }

}