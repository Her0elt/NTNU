package no.ntnu.idata2001.contacts.repositories;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

public abstract class AbstractRepository<T,ID> implements Repository<T,ID> {

    protected Class<T> entityClass;

    @PersistenceContext
    protected EntityManager entityManager;

    public AbstractRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    protected void setEntityClass(Class<T> classTypeToSet) {
        entityClass = classTypeToSet;
    }


    @Override
    public Optional<T> save(T entity) {
        try {
            persist(entity);
            return Optional.ofNullable(entity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Optional<T> update(T entity) {
        try {
            merge(entity);
            return Optional.ofNullable(entity);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    @Override
    public Optional<T> findById(ID id) {
        T entity = entityManager.find(entityClass, id);
        return Optional.ofNullable(entity);
    }

    @Override
    public List<T> findAll() {
        return entityManager.createQuery("from " + entityClass.getName())
                .getResultList();
    }

    @Override
    public void delete(T entity) {
        try {
            remove(entity);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteById(ID id) {
        Optional<T> entity = findById(id);
        entity.ifPresent(this::delete);
    }

    private void persist(T entity) {
        entityManager.getTransaction().begin();
        entityManager.persist(entity);
        entityManager.getTransaction().commit();
    }
    private void merge(T entity) {
        entityManager.getTransaction().begin();
        entityManager.merge(entity);
        entityManager.getTransaction().commit();
    }

    private void remove(T entity) {
        entityManager.getTransaction().begin();
        entityManager.remove(entity);
        entityManager.getTransaction().commit();
    }
}
