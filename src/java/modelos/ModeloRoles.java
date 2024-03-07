/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;



import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import modelo.entidades.Rol;

public class ModeloRoles {

    public static Rol obtenerRolPorNombre(String nombre) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(ModeloUsuarios.PU);
        EntityManager em = emf.createEntityManager();

        try {
            Query query = em.createQuery("SELECT r FROM Rol r WHERE r.nombre = :nombre");
            query.setParameter("nombre", nombre);
            return (Rol) query.getResultList().stream().findFirst().orElse(null);
        } finally {
            em.close();
            emf.close();
        }
    }

   
}
