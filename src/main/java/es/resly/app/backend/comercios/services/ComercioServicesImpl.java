package es.resly.app.backend.comercios.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import es.resly.app.backend.comercios.repository.ComercioRepository;
import es.resly.app.backend.commons.models.Comercio;
import es.resly.app.backend.usuarios.services.UsuarioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

@Service
public class ComercioServicesImpl implements ComercioServices{

    protected ComercioRepository repository;

    @Autowired
    private UsuarioServiceImpl servicesUsuario;

    public ComercioServicesImpl() {
        repository = new ComercioRepository();
    }

    @Override
    public List <Comercio> findAll() {
        return (List <Comercio>) repository.findAll();
    }

    @Override
    public Comercio findById(String id) throws ExecutionException, InterruptedException {
        ObjectMapper mapper = new ObjectMapper();
        Object t = repository.findById(id);
        return mapper.convertValue(t, Comercio.class);
    }

    @Override
    public Comercio save(Comercio entity, String id) {
        entity.setId(id);
        repository.save(entity,id);
        return entity;
    }

    @Override
    public Comercio save(Comercio entity) {
        String id;
        id=UUID.randomUUID().toString();
        entity.setId(id);
        repository.save(entity,id);
        return entity;
    }

    @Override
    public Comercio update(Comercio entity) {
        return repository.update(entity,entity.getId());
    }

    @Override
    public void deleteById(String id) {
        repository.deleteById(id);
    }

    @Override
    public void agregarComercioUsuario(Comercio comercio, String uidUsuario) {
        servicesUsuario.agregarComercioUsuario(comercio,uidUsuario);
    }

    @Override
    public void eliminarComercioUsuario(Comercio comercio, String uidUsuario) {
        //Falta agregar codigo
    }
}
