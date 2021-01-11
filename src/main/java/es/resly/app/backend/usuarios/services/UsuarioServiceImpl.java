package es.resly.app.backend.usuarios.services;

import es.resly.app.backend.usuarios.models.Usuario;
import es.resly.app.backend.usuarios.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    protected UsuarioRepository repository;

    public UsuarioServiceImpl() {
        repository = new UsuarioRepository();
    }

    @Override
    public List<Usuario> findAll() {
        return (List<Usuario>) repository.findAll();
    }

    @Override
    public Usuario findById(Long id) {
        return null;
    }

    @Override
    public Usuario save(Usuario entity, String id) {
        repository.save(entity,id);
        return entity;
    }

    @Override
    public Usuario update(Usuario entity) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }
}
