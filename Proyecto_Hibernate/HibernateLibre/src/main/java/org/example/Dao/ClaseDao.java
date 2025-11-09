package org.example.Dao;

import jakarta.persistence.*;
import org.example.model.Clase;

import java.util.List;

public class ClaseDao {
    private final EntityManager em;

    public ClaseDao(EntityManager em) {
        this.em = em;
    }

    public Clase insertar(Clase clase) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(clase);
            tx.commit();
            return clase;
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            throw e;
        }
    }

    public List<Clase> listar() {
        TypedQuery<Clase> q = em.createQuery("SELECT c FROM Clase c", Clase.class);
        return q.getResultList();
    }

    public Clase buscarPorId(Long id) { return em.find(Clase.class, id); }
}

