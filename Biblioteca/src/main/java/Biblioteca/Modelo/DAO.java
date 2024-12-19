package Biblioteca.Modelo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.util.List;

public class DAO<T, ID> {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("Biblioteca");
    EntityManager em = emf.createEntityManager();
    EntityTransaction tx = em.getTransaction();

    private Class<T> clase;
    private Class<ID> id;

    public DAO(Class<T> clase, Class<ID> id) {
        this.clase = clase;
        this.id = id;
    }

    //Insert
    public void insert(T t){
        tx.begin();
        em.persist(t);
        tx.commit();
    }

    //Update
    public void update(T t){
        tx.begin();
        em.merge(t);
        tx.commit();
    }

    //Delete
    public void delete(T t){
        tx.begin();
        em.remove(t);
        tx.commit();
    }

    //Select
    public T findById(ID id){
        return em.find(clase, id);
    }

    //Select all
    public List<T> findAll(){
        return em.createQuery("FROM " + clase.getSimpleName(), clase).getResultList();
    }

    public T findByUniqueValue(String column, String value){
        return em.createQuery("SELECT e FROM " +clase.getSimpleName()+ " e WHERE " +column+ " = \"" +value+ "\"", clase).getSingleResult();
    }

    /*public List<T> findPrestamos(int id){
        return em.createQuery("SELECT e.isbn, p.fechaInicio, p.fechaDevolucion FROM Prestamo p JOIN Ejemplar e ON " +
                "e.id = p.ejemplar_id JOIN Usuario u ON u.id = " +id, clase).getResultList();
    }*/
}
