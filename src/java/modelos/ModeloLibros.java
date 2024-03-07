package modelos;

import dao.prueba.LibroJpaController;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.RollbackException;
import modelo.entidades.Autor;
import modelo.entidades.Genero;
import modelo.entidades.Libro;

public class ModeloLibros {
    public static final String PU = "BibliotecaPU";

    public static List<Libro> getLibros() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(PU);
        LibroJpaController ljc = new LibroJpaController(emf);
        List<Libro> libros = ljc.findLibroEntities();
        emf.close();
        return libros;
    }

    public static String crearLibro(String isbn, String titulo, String fechaEdicion, List<Autor> autores, 
            List<Genero> generos, String imagen, Long cantidad) throws Exception {
        String error = null;

        EntityManagerFactory emf = Persistence.createEntityManagerFactory(PU);
        LibroJpaController ljc = new LibroJpaController(emf);
        Libro libro = new Libro();
        libro.setIsbn(isbn);
        libro.setTitulo(titulo);
        libro.setFechaEdicion(fechaEdicion);
        libro.setAutores(autores);
        libro.setGeneros(generos);
        libro.setImagen(imagen);
        libro.setCantidad(cantidad);

        try {
            ljc.create(libro);
        } catch (RollbackException ex) {
            if (ex.getMessage().contains("Duplicate")) {
                error = "Ya existe un libro con ISBN " + isbn;
            } else {
                error = "Se ha producido un error al crear el libro: " + ex.getMessage();
                ex.printStackTrace();
            }
        } finally {
            emf.close();
        }

        return error;
    }

    
    public static String actualizarLibro(String isbn, String titulo, String fechaEdicion, List<Autor> autores, 
            List<Genero> generos, String imagen, Long cantidad) throws Exception {
        String error = null;

        EntityManagerFactory emf = Persistence.createEntityManagerFactory(PU);
        LibroJpaController ljc = new LibroJpaController(emf);
        Libro libro = ljc.findLibro(isbn);

        if (libro != null) {
            libro.setTitulo(titulo);
            libro.setFechaEdicion(fechaEdicion);
            libro.setAutores(autores);
            libro.setGeneros(generos);
            libro.setImagen(imagen);
            libro.setCantidad(cantidad);

            try {
                ljc.edit(libro);
            } catch (RollbackException ex) {
                error = "Se ha producido un error al actualizar el libro: " + ex.getMessage();
                ex.printStackTrace();
            }
        } else {
            error = "No se encontró un libro con ISBN " + isbn;
        }

        emf.close();
        return error;
    }

    public static String eliminarLibro(String isbn) {
        String error = null;

        EntityManagerFactory emf = Persistence.createEntityManagerFactory(PU);
        LibroJpaController ljc = new LibroJpaController(emf);
        Libro libro = ljc.findLibro(isbn);

        if (libro != null) {
            try {
                ljc.destroy(isbn);
            } catch (Exception ex) {
                error = "Se ha producido un error al eliminar el libro: " + ex.getMessage();
                ex.printStackTrace();
            }
        } else {
            error = "No se encontró un libro con ISBN " + isbn;
        }

        emf.close();
        return error;
    }

    public static Libro consultarLibro(String isbn) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(PU);
        LibroJpaController ljc = new LibroJpaController(emf);
        Libro libro = ljc.findLibro(isbn);
        emf.close();
        return libro;
    }
}
