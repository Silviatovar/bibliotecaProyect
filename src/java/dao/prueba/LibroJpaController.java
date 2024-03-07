/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao.prueba;

import dao.prueba.exceptions.NonexistentEntityException;
import dao.prueba.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import modelo.entidades.Autor;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import modelo.entidades.Genero;
import modelo.entidades.Libro;
import modelo.entidades.Prestamo;

/**
 *
 * @author Silvia Tovar
 */
public class LibroJpaController implements Serializable {

    public LibroJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Libro libro) throws PreexistingEntityException, Exception {
        if (libro.getAutores() == null) {
            libro.setAutores(new ArrayList<Autor>());
        }
        if (libro.getGeneros() == null) {
            libro.setGeneros(new ArrayList<Genero>());
        }
        if (libro.getPrestamos() == null) {
            libro.setPrestamos(new ArrayList<Prestamo>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Autor> attachedAutores = new ArrayList<Autor>();
            for (Autor autoresAutorToAttach : libro.getAutores()) {
                autoresAutorToAttach = em.getReference(autoresAutorToAttach.getClass(), autoresAutorToAttach.getId());
                attachedAutores.add(autoresAutorToAttach);
            }
            libro.setAutores(attachedAutores);
            List<Genero> attachedGeneros = new ArrayList<Genero>();
            for (Genero generosGeneroToAttach : libro.getGeneros()) {
                generosGeneroToAttach = em.getReference(generosGeneroToAttach.getClass(), generosGeneroToAttach.getId());
                attachedGeneros.add(generosGeneroToAttach);
            }
            libro.setGeneros(attachedGeneros);
            List<Prestamo> attachedPrestamos = new ArrayList<Prestamo>();
            for (Prestamo prestamosPrestamoToAttach : libro.getPrestamos()) {
                prestamosPrestamoToAttach = em.getReference(prestamosPrestamoToAttach.getClass(), prestamosPrestamoToAttach.getId());
                attachedPrestamos.add(prestamosPrestamoToAttach);
            }
            libro.setPrestamos(attachedPrestamos);
            em.persist(libro);
            for (Autor autoresAutor : libro.getAutores()) {
                autoresAutor.getLibros().add(libro);
                autoresAutor = em.merge(autoresAutor);
            }
            for (Genero generosGenero : libro.getGeneros()) {
                generosGenero.getLibros().add(libro);
                generosGenero = em.merge(generosGenero);
            }
            for (Prestamo prestamosPrestamo : libro.getPrestamos()) {
                Libro oldLibroOfPrestamosPrestamo = prestamosPrestamo.getLibro();
                prestamosPrestamo.setLibro(libro);
                prestamosPrestamo = em.merge(prestamosPrestamo);
                if (oldLibroOfPrestamosPrestamo != null) {
                    oldLibroOfPrestamosPrestamo.getPrestamos().remove(prestamosPrestamo);
                    oldLibroOfPrestamosPrestamo = em.merge(oldLibroOfPrestamosPrestamo);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findLibro(libro.getIsbn()) != null) {
                throw new PreexistingEntityException("Libro " + libro + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Libro libro) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Libro persistentLibro = em.find(Libro.class, libro.getIsbn());
            List<Autor> autoresOld = persistentLibro.getAutores();
            List<Autor> autoresNew = libro.getAutores();
            List<Genero> generosOld = persistentLibro.getGeneros();
            List<Genero> generosNew = libro.getGeneros();
            List<Prestamo> prestamosOld = persistentLibro.getPrestamos();
            List<Prestamo> prestamosNew = libro.getPrestamos();
            List<Autor> attachedAutoresNew = new ArrayList<Autor>();
            for (Autor autoresNewAutorToAttach : autoresNew) {
                autoresNewAutorToAttach = em.getReference(autoresNewAutorToAttach.getClass(), autoresNewAutorToAttach.getId());
                attachedAutoresNew.add(autoresNewAutorToAttach);
            }
            autoresNew = attachedAutoresNew;
            libro.setAutores(autoresNew);
            List<Genero> attachedGenerosNew = new ArrayList<Genero>();
            for (Genero generosNewGeneroToAttach : generosNew) {
                generosNewGeneroToAttach = em.getReference(generosNewGeneroToAttach.getClass(), generosNewGeneroToAttach.getId());
                attachedGenerosNew.add(generosNewGeneroToAttach);
            }
            generosNew = attachedGenerosNew;
            libro.setGeneros(generosNew);
            List<Prestamo> attachedPrestamosNew = new ArrayList<Prestamo>();
            for (Prestamo prestamosNewPrestamoToAttach : prestamosNew) {
                prestamosNewPrestamoToAttach = em.getReference(prestamosNewPrestamoToAttach.getClass(), prestamosNewPrestamoToAttach.getId());
                attachedPrestamosNew.add(prestamosNewPrestamoToAttach);
            }
            prestamosNew = attachedPrestamosNew;
            libro.setPrestamos(prestamosNew);
            libro = em.merge(libro);
            for (Autor autoresOldAutor : autoresOld) {
                if (!autoresNew.contains(autoresOldAutor)) {
                    autoresOldAutor.getLibros().remove(libro);
                    autoresOldAutor = em.merge(autoresOldAutor);
                }
            }
            for (Autor autoresNewAutor : autoresNew) {
                if (!autoresOld.contains(autoresNewAutor)) {
                    autoresNewAutor.getLibros().add(libro);
                    autoresNewAutor = em.merge(autoresNewAutor);
                }
            }
            for (Genero generosOldGenero : generosOld) {
                if (!generosNew.contains(generosOldGenero)) {
                    generosOldGenero.getLibros().remove(libro);
                    generosOldGenero = em.merge(generosOldGenero);
                }
            }
            for (Genero generosNewGenero : generosNew) {
                if (!generosOld.contains(generosNewGenero)) {
                    generosNewGenero.getLibros().add(libro);
                    generosNewGenero = em.merge(generosNewGenero);
                }
            }
            for (Prestamo prestamosOldPrestamo : prestamosOld) {
                if (!prestamosNew.contains(prestamosOldPrestamo)) {
                    prestamosOldPrestamo.setLibro(null);
                    prestamosOldPrestamo = em.merge(prestamosOldPrestamo);
                }
            }
            for (Prestamo prestamosNewPrestamo : prestamosNew) {
                if (!prestamosOld.contains(prestamosNewPrestamo)) {
                    Libro oldLibroOfPrestamosNewPrestamo = prestamosNewPrestamo.getLibro();
                    prestamosNewPrestamo.setLibro(libro);
                    prestamosNewPrestamo = em.merge(prestamosNewPrestamo);
                    if (oldLibroOfPrestamosNewPrestamo != null && !oldLibroOfPrestamosNewPrestamo.equals(libro)) {
                        oldLibroOfPrestamosNewPrestamo.getPrestamos().remove(prestamosNewPrestamo);
                        oldLibroOfPrestamosNewPrestamo = em.merge(oldLibroOfPrestamosNewPrestamo);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = libro.getIsbn();
                if (findLibro(id) == null) {
                    throw new NonexistentEntityException("The libro with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Libro libro;
            try {
                libro = em.getReference(Libro.class, id);
                libro.getIsbn();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The libro with id " + id + " no longer exists.", enfe);
            }
            List<Autor> autores = libro.getAutores();
            for (Autor autoresAutor : autores) {
                autoresAutor.getLibros().remove(libro);
                autoresAutor = em.merge(autoresAutor);
            }
            List<Genero> generos = libro.getGeneros();
            for (Genero generosGenero : generos) {
                generosGenero.getLibros().remove(libro);
                generosGenero = em.merge(generosGenero);
            }
            List<Prestamo> prestamos = libro.getPrestamos();
            for (Prestamo prestamosPrestamo : prestamos) {
                prestamosPrestamo.setLibro(null);
                prestamosPrestamo = em.merge(prestamosPrestamo);
            }
            em.remove(libro);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Libro> findLibroEntities() {
        return findLibroEntities(true, -1, -1);
    }

    public List<Libro> findLibroEntities(int maxResults, int firstResult) {
        return findLibroEntities(false, maxResults, firstResult);
    }

    private List<Libro> findLibroEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Libro.class));
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

    public Libro findLibro(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Libro.class, id);
        } finally {
            em.close();
        }
    }

    public int getLibroCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Libro> rt = cq.from(Libro.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
