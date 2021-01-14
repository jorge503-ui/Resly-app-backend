package es.resly.app.backend.commons.services;


import java.util.List;
import java.util.concurrent.ExecutionException;

public interface CommonServices<E> {

    public List<E> findAll();

    public E findById(String id) throws ExecutionException, InterruptedException;

    public E save(E entity, String id);

    public E update(E entity);

    public void deleteById(String id);
}
