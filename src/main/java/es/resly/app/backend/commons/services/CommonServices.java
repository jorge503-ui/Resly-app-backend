package es.resly.app.backend.commons.services;


import java.util.List;

public interface CommonServices<E> {

    public List<E> findAll();

    public E findById(Long id);

    public E save(E entity, String id);

    public E update(E entity);

    public void deleteById(Long id);
}
