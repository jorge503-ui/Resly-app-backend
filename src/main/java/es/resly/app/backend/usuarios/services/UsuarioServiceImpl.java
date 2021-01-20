package es.resly.app.backend.usuarios.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.firebase.auth.DeleteUsersResult;
import com.google.firebase.auth.UserRecord;
import es.resly.app.backend.commons.models.Comercio;
import es.resly.app.backend.commons.models.Usuario;
import es.resly.app.backend.usuarios.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

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
    public Usuario findById(String id) throws ExecutionException, InterruptedException {
        ObjectMapper mapper = new ObjectMapper();
        Object t = repository.findById(id);
        return mapper.convertValue(t,Usuario.class);
    }

    @Override
    public Usuario save(Usuario entity, String id) {
        entity.setId(id);
        repository.save(entity,id);
        return entity;
    }

    @Override
    public Usuario save(Usuario entity) {
        String id;
        id=UUID.randomUUID().toString();
        entity.setId(id);
        repository.save(entity,id);
        return entity;
    }

    @Override
    public Usuario update(Usuario entity) {
        return repository.update(entity,entity.getId());
    }

    @Override
    public void deleteById(String id) {
        repository.deleteById(id);
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

    @Override
    public boolean existUsuarioFirebase(String uid) {
        return repository.existUserByIdFirebase(uid);
    }

    @Override
    public Usuario getUserByEmailFirebase(String email) throws ExecutionException, InterruptedException {
        UserRecord ur = repository.getUserByEmailFirebase(email);
        Usuario u = new Usuario();
        u.setId(ur.getUid());
        u.setNum_telefono(ur.getPhoneNumber());
        u.setNombre(ur.getDisplayName());
        u.setFoto_perfil(ur.getPhotoUrl());
        return u;
    }

    @Override
    public void agregarComercioUsuario(Comercio comercio, String uidUsuario) {
        repository.agregarComercioUsuario(comercio, uidUsuario);
    }

    @Override
    public void eliminarComercioUsuario(Comercio comercio, String uidUsuario) {

    }
}
