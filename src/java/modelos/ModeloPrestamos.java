package modelos;

import dao.prueba.PrestamoJpaController;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import modelo.entidades.Libro;
import modelo.entidades.Prestamo;
import modelo.entidades.Usuario;

public class ModeloPrestamos {

    public static final String PU = "BibliotecaPU";

    public static List<Prestamo> getPrestamos() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(PU);
        PrestamoJpaController pjc = new PrestamoJpaController(emf);
        List<Prestamo> prestamos = pjc.findPrestamoEntities();
        emf.close();
        return prestamos;
    }

    public static String crearPrestamo(Usuario usuario, Libro libro, Date fecha_devolucion, Date fecha_prestamo) {
        String error = null;

        EntityManagerFactory emf = Persistence.createEntityManagerFactory(PU);
        PrestamoJpaController pjc = new PrestamoJpaController(emf);
        Prestamo prestamo = new Prestamo();
        prestamo.setUsuario(usuario);
        prestamo.setLibro(libro);
        prestamo.setFecha_prestamo(fecha_prestamo);
        prestamo.setFecha_devolucion(fecha_devolucion);

        try {
            pjc.create(prestamo);
        } catch (Exception ex) {
            error = "Se ha producido un error al crear el préstamo: " + ex.getMessage();
            ex.printStackTrace();
        } finally {
            emf.close();
        }

        return error;
    }

    public static Prestamo consultarPrestamo(long id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(PU);
        PrestamoJpaController pjc = new PrestamoJpaController(emf);
        Prestamo prestamo = pjc.findPrestamo(id);
        emf.close();

        return prestamo;
    }

    public static String actualizarPrestamo(Prestamo prestamo, Usuario usuario, Libro libro, Date fecha_devolucion, Date fecha_prestamo) {
        String error = null;

        EntityManagerFactory emf = Persistence.createEntityManagerFactory(PU);
        PrestamoJpaController pjc = new PrestamoJpaController(emf);

        prestamo.setUsuario(usuario);
        prestamo.setLibro(libro);
        prestamo.setFecha_prestamo(fecha_prestamo);
        prestamo.setFecha_devolucion(fecha_devolucion);

        try {
            pjc.edit(prestamo);
        } catch (Exception ex) {
            error = "Se ha producido un error al actualizar el préstamo: " + ex.getMessage();
            ex.printStackTrace();
        } finally {
            emf.close();
        }

        return error;
    }

    public static String eliminarPrestamo(Prestamo prestamo) {
        String error = null;
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(PU);
        PrestamoJpaController pjc = new PrestamoJpaController(emf);

        try {
            pjc.destroy(prestamo.getId());
        } catch (Exception ex) {
            error = "Se ha producido un error al eliminar el préstamo " + prestamo;
            ex.printStackTrace();
        } finally {
            emf.close();
        }

        return error;
    }
}
