package es.resly.app.backend.usuarios.services;

import com.google.firebase.auth.DeleteUsersResult;
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

    @Override
    public Usuario getUsuarioByIdFirebase(String id) {
        return repository.getUsuarioByIdFirebase(id);
    }

    @Override
    public Usuario crearUsuarioFirebase(Usuario usuario) {
        return repository.crearUsuarioFirebase(usuario);
    }

    @Override
    public void eliminarUsuarioFirebase(String uid) {
        repository.eliminarUsuarioFirebase(uid);
    }

    @Override
    public void actualizarUsuarioFirebase(Usuario usuario) {
        repository.actualizarUsuarioFirebase(usuario);
    }

    @Override
    public DeleteUsersResult eliminarListaUsuariosFirebase(List <Usuario> usuarioList) {
        return repository.eliminarListaUsuariosFirebase(usuarioList);
    }

    @Override
    public List <Usuario> getUsuariosFirebase(String pageToken, int maxResult) {
        return repository.getUsuariosFirebase(pageToken,maxResult);
    }
}
