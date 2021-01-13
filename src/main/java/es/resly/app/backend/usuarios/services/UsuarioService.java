package es.resly.app.backend.usuarios.services;

import com.google.firebase.auth.DeleteUsersResult;
import es.resly.app.backend.commons.services.CommonServices;
import es.resly.app.backend.usuarios.models.Usuario;

import java.util.List;

public interface UsuarioService extends CommonServices<Usuario> {

    public Usuario getUsuarioByIdFirebase(String id);

    public Usuario crearUsuarioFirebase(Usuario usuario);

    public void eliminarUsuarioFirebase(String uid);

    public void actualizarUsuarioFirebase(Usuario usuario);

    public DeleteUsersResult eliminarListaUsuariosFirebase(List <Usuario> usuarioList);

    public List<Usuario> getUsuariosFirebase(String pageToken,int maxResult);
}
