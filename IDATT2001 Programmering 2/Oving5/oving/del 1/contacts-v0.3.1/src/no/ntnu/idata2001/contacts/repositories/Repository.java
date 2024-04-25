package no.ntnu.idata2001.contacts.repositories;

import java.util.List;
import java.util.Optional;

public interface Repository <T, ID>{

    Optional<T> save(T entity);

    Optional<T> update(T entity);

    List<T> findAll();

    void delete(T entity);

    void deleteById(ID id);

    Optional<T> findById(ID id);
}
