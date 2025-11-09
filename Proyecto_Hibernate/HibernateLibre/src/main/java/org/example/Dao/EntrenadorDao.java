package org.example.Dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import org.example.model.Entrenador;

import java.util.List;

public class EntrenadorDao {

    private final EntityManager em;

    public EntrenadorDao(EntityManager em) {
        this.em = em;
    }

    //Create
    public Entrenador crearEntrenador(Entrenador entrenador) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(entrenador);
            tx.commit();

        }catch (Exception e) {
            System.err.println(e.getMessage());
            if (tx.isActive()) {
                tx.rollback();
            }
        }
        return entrenador;
    }

    //LeerTodos
    public List<Entrenador> listarEntrenadores() {
        TypedQuery<Entrenador>  query = em.createQuery("SELECT e FROM Entrenador e", Entrenador.class);
        return query.getResultList();
    }

    //LeerUno
    public Entrenador buscarEntrenadorPorId(Long id) {
        return em.find(Entrenador.class, id);
    }

    //Update
    public Entrenador actualizarEntrenador(Entrenador entrenador) {
        EntityTransaction tx = em.getTransaction();
        Entrenador entrenadorActualizado = null;
        try {
            tx.begin();
            entrenadorActualizado = em.merge(entrenador);
            tx.commit();
        }catch (Exception e) {
            System.err.println(e.getMessage());
            if (tx.isActive()) {
                tx.rollback();
            }
        }
        return entrenadorActualizado;
    }

    //Delete
    public void borrarEntrenador(Long id) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Entrenador entrenador = em.find(Entrenador.class, id);
            if(entrenador != null){
                em.remove(entrenador);
            }
            tx.commit();
        }catch (Exception e) {
            System.err.println(e.getMessage());
            if (tx.isActive()) {
                tx.rollback();
            }
        }
    }
}
