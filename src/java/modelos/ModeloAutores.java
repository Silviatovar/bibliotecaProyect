package modelos;

import dao.prueba.AutorJpaController;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import modelo.entidades.Autor;

public class ModeloAutores {
    public static final String PU = "BibliotecaPU";

    public static List<Autor> getAutores() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(PU);
        AutorJpaController ajc = new AutorJpaController(emf);
        List<Autor> autores = ajc.findAutorEntities();
        emf.close();
        return autores;
    }

    public static String crearAutor(String nombre, String apellido) {
        String error = null;

        EntityManagerFactory emf = Persistence.createEntityManagerFactory(PU);
        AutorJpaController ajc = new AutorJpaController(emf);
        Autor autor = new Autor();
        autor.setNombre(nombre);
        autor.setApellidos(apellido);

        try {
            ajc.create(autor);
        } catch (Exception ex) {
            error = "Se ha producido un error al crear el autor: " + ex.getMessage();
            ex.printStackTrace();
        } finally {
            emf.close();
        }

        return error;
    }

    public static Autor consultarAutor(long id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(PU);
        AutorJpaController ajc = new AutorJpaController(emf);
        Autor autor = ajc.findAutor(id);
        emf.close();

        if (autor == null) {
            throw new IllegalArgumentException("El autor con ID " + id + " no existe");
        }

        return autor;
    }

    public static String actualizarAutor(Autor autor, String nombre, String apellido) {
        String error = null;
        autor.setNombre(nombre);
        autor.setApellidos(apellido);

        EntityManagerFactory emf = Persistence.createEntityManagerFactory(PU);
        AutorJpaController ajc = new AutorJpaController(emf);

        try {
            ajc.edit(autor);
        } catch (Exception ex) {
            error = "Se ha producido un error al actualizar el autor: " + ex.getMessage();
            ex.printStackTrace();
        } finally {
            emf.close();
        }

        return error;
    }

    public static String eliminarAutor(Autor autor) {
        String error = null;
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(PU);
        AutorJpaController ajc = new AutorJpaController(emf);

        try {
            ajc.destroy(autor.getId());
        } catch (Exception ex) {
            error = "Se ha producido un error al eliminar el autor " + autor;
            ex.printStackTrace();
        } finally {
            emf.close();
        }

        return error;
    }
}
