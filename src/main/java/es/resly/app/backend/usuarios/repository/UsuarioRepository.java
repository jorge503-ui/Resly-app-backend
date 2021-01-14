package es.resly.app.backend.usuarios.repository;

import com.google.common.base.Strings;
import com.google.firebase.auth.*;
import es.resly.app.backend.commons.repository.FirebaseCrudRepository;
import es.resly.app.backend.usuarios.controllers.UsuarioController;
import es.resly.app.backend.usuarios.models.Usuario;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Repository
public class UsuarioRepository extends FirebaseCrudRepository <Usuario, String> {

    private static final Logger logger = LogManager.getLogger(UsuarioController.class);

    public UsuarioRepository() {
        setCollection("usuarios");
    }

    public Usuario getUsuarioByIdFirebase(String id) {
        Usuario usuario = new Usuario();
        try {
            UserRecord userRecord = FirebaseAuth.getInstance().getUser(id);
            usuario.setId(userRecord.getUid());
            usuario.setCorreo(userRecord.getEmail());
            usuario.setFotoPerfil(userRecord.getPhotoUrl());
            usuario.setNombre(userRecord.getDisplayName());
            usuario.setNumTelefono(userRecord.getPhoneNumber());
        } catch (FirebaseAuthException e) {
            logger.error("Ocurrio un error al crear usuario", e);
        }
        return usuario;
    }

    public List<Usuario> getUsuariosFirebase(String pageToken,int maxResult){
        ListUsersPage page = null;
        List<Usuario> usuarios = null;
        try {
            page = FirebaseAuth.getInstance().listUsers(pageToken,maxResult);
            while (page != null) {
                for (ExportedUserRecord user : page.getValues()) {
                    Usuario usuario = new Usuario();
                    usuario.setId(user.getUid());
                    usuario.setCorreo(user.getEmail());
                    usuario.setFotoPerfil(user.getPhotoUrl());
                    usuario.setNombre(user.getDisplayName());
                    usuario.setNumTelefono(user.getPhoneNumber());

                    usuarios.add(usuario);
                }
                page = page.getNextPage();
            }
        } catch (FirebaseAuthException e) {
            logger.error("Ocurrio un error al crear usuario", e);
        }
        return usuarios;
    }

    public Usuario crearUsuarioFirebase(Usuario usuario) {
        try {
            UserRecord.CreateRequest request = new UserRecord.CreateRequest()
                    .setEmail(usuario.getCorreo())
                    .setEmailVerified(false)
                    .setPassword(usuario.getContrasea())
                    .setPhoneNumber(usuario.getNumTelefono())
                    .setDisplayName(usuario.getNombre() + usuario.getApellido())
                    .setPhotoUrl(usuario.getFotoPerfil())
                    .setDisabled(false);

            UserRecord userRecord = FirebaseAuth.getInstance().createUser(request);
            logger.info("Usuario creado exitosamente en firebase: " + userRecord.getUid());
            usuario.setId(userRecord.getUid());

        } catch (FirebaseAuthException e) {
            logger.error("Ocurrio un error al crear usuario", e);
        }
        return usuario;
    }

    public void eliminarUsuarioFirebase(String uid){
        try {
            FirebaseAuth.getInstance().deleteUser(uid);
        } catch (Exception e) {
            logger.error("Ocurrio un error al eliminar usuario", e);
        }
    }

    public void actualizarUsuarioFirebase(Usuario usuario){
        try {
            UserRecord.UpdateRequest request = new UserRecord.UpdateRequest(usuario.getId())
                    .setEmail(usuario.getCorreo())
                    .setPassword(usuario.getContrasea())
                    .setPhoneNumber(usuario.getNumTelefono())
                    .setDisplayName(usuario.getNombre() + usuario.getApellido())
                    .setPhotoUrl(usuario.getFotoPerfil());

            UserRecord userRecord = FirebaseAuth.getInstance().updateUser(request);

        } catch (Exception e) {
            logger.error("Ocurrio un error al actualizar usuario", e);
        }
    }

    public DeleteUsersResult eliminarListaUsuariosFirebase(List<Usuario> usuarioList){
        DeleteUsersResult result = null;
        try {
            //Eliminamos los usuario estrayendo los id's de usuarios de RequestBody
            result = FirebaseAuth.getInstance().deleteUsersAsync(
                    usuarioList.stream().map(Usuario::getId
                    ).collect(Collectors.toList())).get();
        } catch (Exception e){
            logger.error("Ocurrio un error al eliminar usuario", e);
        }
        return result;
    }

    public UserRecord getUserByEmailFirebase(String email) throws ExecutionException, InterruptedException {
        UserRecord userRecord = null;
        try {
            userRecord = FirebaseAuth.getInstance().getUserByEmail(email);
            
        } catch (FirebaseAuthException e) {
            logger.error("Ocurrio un error al crear usuario", e);
        }
        return userRecord;
    }

    public boolean existUserByIdFirebase(String id) {
        UserRecord userRecord;
        try {
            userRecord = FirebaseAuth.getInstance().getUser(id);
        } catch (FirebaseAuthException e) {
            logger.error("Ocurrio un error al crear usuario", e);
            return false;
        }
        if (Strings.isNullOrEmpty(userRecord.getEmail())) {
            return false;
        }else {
            return true;
        }


    }
}
