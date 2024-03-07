package dao.prueba;

import dao.prueba.exceptions.NonexistentEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import modelo.entidades.Libro;
import modelo.entidades.Prestamo;
import modelo.entidades.Usuario;

/**
 * Controlador JPA para la entidad Prestamo.
 * Permite realizar operaciones CRUD en la base de datos.
 */
public class PrestamoJpaController implements Serializable {

    private EntityManagerFactory emf = null;

    public PrestamoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Prestamo prestamo) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(prestamo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Prestamo prestamo) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            prestamo = em.merge(prestamo);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = prestamo.getId();
                if (findPrestamo(id) == null) {
                    throw new NonexistentEntityException("The prestamo with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Prestamo prestamo;
            try {
                prestamo = em.getReference(Prestamo.class, id);
                prestamo.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The prestamo with id " + id + " no longer exists.", enfe);
            }
            em.remove(prestamo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Prestamo> findPrestamoEntities() {
        return findPrestamoEntities(true, -1, -1);
    }

    public List<Prestamo> findPrestamoEntities(int maxResults, int firstResult) {
        return findPrestamoEntities(false, maxResults, firstResult);
    }

    private List<Prestamo> findPrestamoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Prestamo.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Prestamo findPrestamo(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Prestamo.class, id);
        } finally {
            em.close();
        }
    }

    public int getPrestamoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Prestamo> rt = cq.from(Prestamo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    /**
     * Obtener la lista de préstamos para un usuario específico.
     *
     * @param usuario Usuario para el cual se obtendrán los préstamos.
     * @return Lista de préstamos para el usuario.
     */
    public List<Prestamo> findPrestamosByUsuario(Usuario usuario) {
        EntityManager em = getEntityManager();
        try {
            Query query = em.createQuery("SELECT p FROM Prestamo p WHERE p.usuario = :usuario");
            query.setParameter("usuario", usuario);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    /**
     * Obtener la lista de préstamos para un libro específico.
     *
     * @param libro Libro para el cual se obtendrán los préstamos.
     * @return Lista de préstamos para el libro.
     */
    public List<Prestamo> findPrestamosByLibro(Libro libro) {
        EntityManager em = getEntityManager();
        try {
            Query query = em.createQuery("SELECT p FROM Prestamo p WHERE p.libro = :libro");
            query.setParameter("libro", libro);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
    public List<Prestamo> findPrestamosActivos() {
    EntityManager em = getEntityManager();
    try {
        Query query = em.createQuery("SELECT p FROM Prestamo p WHERE p.fecha_devolucion IS NULL");
        return query.getResultList();
    } finally {
        em.close();
    }
}
}
