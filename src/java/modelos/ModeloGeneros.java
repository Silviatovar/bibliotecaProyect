package modelos;

import dao.prueba.GeneroJpaController;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import modelo.entidades.Genero;

public class ModeloGeneros {
    public static final String PU = "BibliotecaPU";

    public static List<Genero> getGeneros() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(PU);
        GeneroJpaController gjc = new GeneroJpaController(emf);
        List<Genero> generos = gjc.findGeneroEntities();
        emf.close();
        return generos;
    }

    public static String crearGenero(String nombre) {
        String error = null;

        EntityManagerFactory emf = Persistence.createEntityManagerFactory(PU);
        GeneroJpaController gjc = new GeneroJpaController(emf);
        Genero genero = new Genero();
        genero.setNombre(nombre);

        try {
            gjc.create(genero);
        } catch (Exception ex) {
            error = "Se ha producido un error al crear el género: " + ex.getMessage();
            ex.printStackTrace();
        } finally {
            emf.close();
        }

        return error;
    }

    public static Genero consultarGenero(long id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(PU);
        GeneroJpaController gjc = new GeneroJpaController(emf);
        Genero genero = gjc.findGenero(id);
        emf.close();

        if (genero == null) {
            throw new IllegalArgumentException("El género con ID " + id + " no existe");
        }

        return genero;
    }

    public static String actualizarGenero(String genero, String nombre) {
      String error = null;
      Genero g=  ModeloGeneros.consultarGenero(Long.parseLong(genero));
      g.setNombre(nombre);
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(PU);
        GeneroJpaController gjc = new GeneroJpaController(emf);
        try {
            gjc.edit(g);
        } catch (Exception ex) {
            error = "Se ha producido un error al actualizar el género: " + ex.getMessage();
            ex.printStackTrace();
        } finally {
            emf.close();
        }

        return error;
    }

    public static String eliminarGenero(Genero genero) {
        String error = null;
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(PU);
        GeneroJpaController gjc = new GeneroJpaController(emf);

        try {
            gjc.destroy(genero.getId());
        } catch (Exception ex) {
            error = "Se ha producido un error al eliminar el género " + genero;
            ex.printStackTrace();
        } finally {
            emf.close();
        }

        return error;
    }
}
