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
import modelo.entidades.Autor;

/**
 *
 * @author Silvia Tovar
 */
public class AutorJpaController implements Serializable {

    public AutorJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Autor autor) {
        if (autor.getLibros() == null) {
            autor.setLibros(new ArrayList<Libro>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Libro> attachedLibros = new ArrayList<Libro>();
            for (Libro librosLibroToAttach : autor.getLibros()) {
                librosLibroToAttach = em.getReference(librosLibroToAttach.getClass(), librosLibroToAttach.getIsbn());
                attachedLibros.add(librosLibroToAttach);
            }
            autor.setLibros(attachedLibros);
            em.persist(autor);
            for (Libro librosLibro : autor.getLibros()) {
                librosLibro.getAutores().add(autor);
                librosLibro = em.merge(librosLibro);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Autor autor) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Autor persistentAutor = em.find(Autor.class, autor.getId());
            List<Libro> librosOld = persistentAutor.getLibros();
            List<Libro> librosNew = autor.getLibros();
            List<Libro> attachedLibrosNew = new ArrayList<Libro>();
            for (Libro librosNewLibroToAttach : librosNew) {
                librosNewLibroToAttach = em.getReference(librosNewLibroToAttach.getClass(), librosNewLibroToAttach.getIsbn());
                attachedLibrosNew.add(librosNewLibroToAttach);
            }
            librosNew = attachedLibrosNew;
            autor.setLibros(librosNew);
            autor = em.merge(autor);
            for (Libro librosOldLibro : librosOld) {
                if (!librosNew.contains(librosOldLibro)) {
                    librosOldLibro.getAutores().remove(autor);
                    librosOldLibro = em.merge(librosOldLibro);
                }
            }
            for (Libro librosNewLibro : librosNew) {
                if (!librosOld.contains(librosNewLibro)) {
                    librosNewLibro.getAutores().add(autor);
                    librosNewLibro = em.merge(librosNewLibro);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = autor.getId();
                if (findAutor(id) == null) {
                    throw new NonexistentEntityException("The autor with id " + id + " no longer exists.");
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
            Autor autor;
            try {
                autor = em.getReference(Autor.class, id);
                autor.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The autor with id " + id + " no longer exists.", enfe);
            }
            List<Libro> libros = autor.getLibros();
            for (Libro librosLibro : libros) {
                librosLibro.getAutores().remove(autor);
                librosLibro = em.merge(librosLibro);
            }
            em.remove(autor);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Autor> findAutorEntities() {
        return findAutorEntities(true, -1, -1);
    }

    public List<Autor> findAutorEntities(int maxResults, int firstResult) {
        return findAutorEntities(false, maxResults, firstResult);
    }

    private List<Autor> findAutorEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Autor.class));
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

    public Autor findAutor(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Autor.class, id);
        } finally {
            em.close();
        }
    }

    public int getAutorCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Autor> rt = cq.from(Autor.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
