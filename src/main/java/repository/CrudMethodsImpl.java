package repository;

import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import DAO.JpaUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.TypedQuery;

public abstract class CrudMethodsImpl<T, ID> implements CrudMethods<T, ID> {
    private static final Logger LOGGER = Logger.getLogger(CrudMethodsImpl.class.getName());
    private final Class<T> entityClass;

    public CrudMethodsImpl(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    @Override
    public T save(T entity) {
        EntityManager em = JpaUtil.getEntityManager();
        EntityTransaction tx = null;
        try {
            tx = em.getTransaction();
            tx.begin();
            T saved = em.merge(entity);
            tx.commit();
            return saved;
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            LOGGER.log(Level.SEVERE, "Save failed", e);
            throw new PersistenceException("Save failed", e);
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

    @Override
    public T update(T entity) {
        EntityManager em = JpaUtil.getEntityManager();
        EntityTransaction tx = null;
        try {
            tx = em.getTransaction();
            tx.begin();
            T merged = em.merge(entity);
            tx.commit();
            return merged;
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            LOGGER.log(Level.SEVERE, "Update failed", e);
            throw new PersistenceException("Update failed", e);
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

    @Override
    public T delete(ID id) {
        EntityManager em = JpaUtil.getEntityManager();
        EntityTransaction tx = null;
        try {
            tx = em.getTransaction();
            tx.begin();
            T managed = em.find(entityClass, id);
            if (managed != null) {
                em.remove(managed);
                tx.commit();
                return managed;
            }
            return null;
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            LOGGER.log(Level.SEVERE, "Delete failed", e);
            throw new PersistenceException("Delete failed", e);
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

    @Override
    public Optional<T> findById(ID id) {
        EntityManager em = JpaUtil.getEntityManager();
        EntityTransaction tx = null;
        try {
            tx = em.getTransaction();
            tx.begin();
            T entity = em.find(entityClass, id);
            tx.commit();
            return Optional.ofNullable(entity);
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            LOGGER.log(Level.SEVERE, "Find by ID failed", e);
            throw new PersistenceException("Find by ID failed", e);
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

    @Override
    public List<T> findAll() {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            TypedQuery<T> query = em.createQuery(
                "SELECT t FROM " + entityClass.getSimpleName() + " t", 
                entityClass
            );
            return query.getResultList();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Find all failed", e);
            throw new PersistenceException("Find all failed", e);
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }
}
