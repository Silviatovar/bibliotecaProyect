/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao.prueba;

import dao.prueba.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import modelo.entidades.Libro;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import modelo.entidades.Genero;

/**
 *
 * @author Silvia Tovar
 */
public class GeneroJpaController implements Serializable {

    public GeneroJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Genero genero) {
        if (genero.getLibros() == null) {
            genero.setLibros(new ArrayList<Libro>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Libro> attachedLibros = new ArrayList<Libro>();
            for (Libro librosLibroToAttach : genero.getLibros()) {
                librosLibroToAttach = em.getReference(librosLibroToAttach.getClass(), librosLibroToAttach.getIsbn());
                attachedLibros.add(librosLibroToAttach);
            }
            genero.setLibros(attachedLibros);
            em.persist(genero);
            for (Libro librosLibro : genero.getLibros()) {
                librosLibro.getGeneros().add(genero);
                librosLibro = em.merge(librosLibro);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Genero genero) throws NonexistentEntityException, Exception {
    EntityManager em = null;
    try {
        em = getEntityManager();
        em.getTransaction().begin();

        // Find the existing genero in the database
        Genero persistentGenero = em.find(Genero.class, genero.getId());

        // Retrieve the list of libros (books) associated with the existing genero
        List<Libro> librosOld = persistentGenero.getLibros();

        // Retrieve the updated list of libros from the provided genero
        List<Libro> librosNew = genero.getLibros();

        // Attach new libros to the persistence context
        List<Libro> attachedLibrosNew = new ArrayList<>();
        for (Libro librosNewLibroToAttach : librosNew) {
            librosNewLibroToAttach = em.getReference(librosNewLibroToAttach.getClass(), librosNewLibroToAttach.getIsbn());
            attachedLibrosNew.add(librosNewLibroToAttach);
        }
        librosNew = attachedLibrosNew;

        // Set the updated list of libros to the genero
        genero.setLibros(librosNew);

        // Merge the updated genero into the persistence context
        genero = em.merge(genero);

        // Remove libros that are no longer associated with the genero
        for (Libro librosOldLibro : librosOld) {
            if (!librosNew.contains(librosOldLibro)) {
                librosOldLibro.getGeneros().remove(genero);
                librosOldLibro = em.merge(librosOldLibro);
            }
        }

        // Add libros that are newly associated with the genero
        for (Libro librosNewLibro : librosNew) {
            if (!librosOld.contains(librosNewLibro)) {
                librosNewLibro.getGeneros().add(genero);
                librosNewLibro = em.merge(librosNewLibro);
            }
        }

        // Commit the transaction
        em.getTransaction().commit();
    } catch (Exception ex) {
        String msg = ex.getLocalizedMessage();
        if (msg == null || msg.length() == 0) {
            Long id = genero.getId();
            if (findGenero(id) == null) {
                throw new NonexistentEntityException("The genero with id " + id + " no longer exists.");
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
            Genero genero;
            try {
                genero = em.getReference(Genero.class, id);
                genero.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The genero with id " + id + " no longer exists.", enfe);
            }
            List<Libro> libros = genero.getLibros();
            for (Libro librosLibro : libros) {
                librosLibro.getGeneros().remove(genero);
                librosLibro = em.merge(librosLibro);
            }
            em.remove(genero);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Genero> findGeneroEntities() {
        return findGeneroEntities(true, -1, -1);
    }

    public List<Genero> findGeneroEntities(int maxResults, int firstResult) {
        return findGeneroEntities(false, maxResults, firstResult);
    }

    private List<Genero> findGeneroEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Genero.class));
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

    public Genero findGenero(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Genero.class, id);
        } finally {
            em.close();
        }
    }

    public int getGeneroCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Genero> rt = cq.from(Genero.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
