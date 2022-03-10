package es.resly.app.backend.usuarios.controllers;

import com.google.common.io.BaseEncoding;
import com.google.firebase.auth.*;
import es.resly.app.backend.auth.services.SecurityService;
import es.resly.app.backend.commons.helper.Correo;
import es.resly.app.backend.commons.helper.ResponseMessage;
import es.resly.app.backend.commons.models.Fichero;
import es.resly.app.backend.commons.repository.FirebaseStorageRepository;
import es.resly.app.backend.commons.models.Usuario;
import es.resly.app.backend.usuarios.services.UsuarioServiceImpl;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("usuario")
public class UsuarioController {

    private static final Logger logger = LogManager.getLogger(UsuarioController.class);

    @Autowired
    private UsuarioServiceImpl services;

    @Autowired
    private SecurityService securityService;

    private FirebaseStorageRepository storage = new FirebaseStorageRepository();

    private final ResponseMessage response = new ResponseMessage();

    private Correo correo;

    @GetMapping("/")
    public ResponseEntity<?> consultarUsuarios(){
        Map<String, Object> rsp;
        logger.info("Obteniendo lista de usuarios");
        List<Usuario> usuarios;
        try {
            usuarios = services.findAll();
            logger.info("Usuarios obtenidos exitosamente");
            rsp = response.messageOk(HttpStatus.OK.value());
            rsp.put("usuarios",usuarios);
        }catch (Exception e){
            logger.error("Ocurrio un error al listar los usuarios", e);
            rsp = response.messageException(HttpStatus.INTERNAL_SERVER_ERROR.value(),e);
        }
        logger.info("Enviando respuesta");
        return response.messageResponse(rsp);
    }

    @GetMapping("usuario/")
    public ResponseEntity<?> consultarUsuario(@RequestParam(name = "uid") String uid){
        Map<String, Object> rsp;
        Usuario usuario;
        try {
            usuario = services.findById(uid);
            rsp = response.messageOk(HttpStatus.OK.value());
            rsp.put("usuario",usuario);
        }catch (Exception e){
            logger.error("Ocurrio un error al obtener usuario", e);
            rsp = response.messageException(HttpStatus.INTERNAL_SERVER_ERROR.value(),e);
        }
        return response.messageResponse(rsp);
    }

    @PostMapping("crear")
    public ResponseEntity<?> crearUsuario(@Validated @RequestBody Usuario usuario){
        Map<String, Object> rsp;
        logger.info("Creando usuario " + usuario.getNombre());

        logger.info("Creando usuario en firebase");
        try {
            if(!services.existUsuarioFirebase(usuario.getCorreo())) {
                logger.info("Si no exite con correo se crea el usuario");
                usuario = services.crearUsuarioFirebase(usuario);
            }else {
                logger.info("Si exite con correo se obtiene el id de usuario");
                usuario.setId(services.getUserByEmailFirebase(usuario.getCorreo()).getId());
            }
        } catch (Exception e) {
            logger.error("Ocurrio un error al crear usuario", e);
            rsp = response.messageException(HttpStatus.BAD_REQUEST.value(),e);
            return response.messageResponse(rsp);
        }

        logger.info("Creando usuario en BD");
        try {
            services.save(usuario,usuario.getId());
            logger.info("Usuario Creado exitosamente");

            //Respuesta
            logger.info("Generando respuesta");
            rsp = response.messageOk(HttpStatus.OK.value());
            rsp.put("uid",usuario.getId());
        } catch (Exception e) {
            logger.error("Ocurrio un error al crear usuario", e);
            rsp = response.messageException(HttpStatus.BAD_REQUEST.value(),e);

            //Si en tal caso da error al crear usuario en BD se elimina de firebase
            logger.error("ELiminado usuario de firebase");
            services.eliminarUsuarioFirebase(usuario.getId());
        }
        logger.info("Respuesta: " + rsp.get("uid"));
        return response.messageResponse(rsp);
    }

    @PostMapping("actualizar")
    public ResponseEntity<?> actualizarUsuario(@Validated @RequestBody Usuario usuario){
        Map<String, Object> rsp;
        logger.info("Actualizando usuario " + usuario.getId());

        logger.info("Actualizando usuario en firebase");
        try {
            services.actualizarUsuarioFirebase(usuario);
            logger.info("Actualizado correctamente en firebase");
        } catch (Exception e) {
            logger.error("Ocurrio un error al actualizar usuario", e);
            rsp = response.messageException(HttpStatus.BAD_REQUEST.value(),e);
            return response.messageResponse(rsp);
        }

        logger.info("Actualizando usuario en BD");
        try {
            services.update(usuario);
            logger.info("Actualizado correctamente");

            //Respuesta
            rsp = response.messageOk(HttpStatus.OK.value());
        }catch (Exception e){
            logger.error("Ocurrio un error al actualizar usuario", e);
            rsp = response.messageException(HttpStatus.BAD_REQUEST.value(),e);
        }
        logger.info("Enviando respuesta");
        return response.messageResponse(rsp);
    }

    @PostMapping("eliminar/")
    public ResponseEntity<?> eliminarUsuario(@RequestParam(name = "uid") String uid){
        Map<String, Object> rsp;
        logger.info("Eliminando usuario " +uid);

        logger.info("Eliminando usuario en firebase");
        try {
            services.eliminarUsuarioFirebase(uid);
            logger.info("Eliminado correctamente en firebase");
        } catch (Exception e) {
            logger.error("Ocurrio un error al eliminar usuario", e);
            rsp = response.messageException(HttpStatus.BAD_REQUEST.value(),e);
            //Si ocurre un error al eliminar usuario de firebase no se permite eliminar usuario de BD
            return response.messageResponse(rsp);
        }

        logger.info("Eliminando usuario en BD");
        try {
            services.deleteById(uid);
            logger.info("Eliminado correctamente en BD");
            //Respuesta
            rsp = response.messageOk(HttpStatus.OK.value());
        }catch (Exception e){
            logger.error("Ocurrio un error al eliminar usuario", e);
            rsp = response.messageException(HttpStatus.BAD_REQUEST.value(),e);
        }
        logger.info("Enviando respuesta");
        return response.messageResponse(rsp);
    }

    @PostMapping("eliminar/lista")
    public ResponseEntity<?> eliminarListaUsuarios(@Validated @RequestBody List<Usuario> usuarios){
        Map<String, Object> rsp;
        logger.info("Eliminando usuarios ");

        logger.info("Eliminando listado de usuarios en firebase");
        try {
            //Eliminamos los usuario estrayendo los id's de usuarios de RequestBody
            DeleteUsersResult result = services.eliminarListaUsuariosFirebase(usuarios);
            logger.info("Eliminado listado de usuarios en firebase correctamente");
            //Respuesta
            if (result.getFailureCount()==0){
                logger.info("Eliminacion sin ningun problema");
                rsp = response.messageOk(HttpStatus.OK.value());
            }else {
                logger.info("Existen problemas al eliminar usuarios");
                rsp = response.messageProblem(HttpStatus.CONFLICT.value());
                HashMap<String,Object> errors = new HashMap<>();
                for (ErrorInfo error : result.getErrors()) {
                    logger.info("error #" + error.getIndex() + ", reason: " + error.getReason());
                    errors.put("error", "error #" + error.getIndex() + ", reason: " + error.getReason());
                }
                rsp.put("lista_errors",errors);
            }
        } catch (Exception e){
            logger.error("Ocurrio un error al eliminar usuario", e);
            rsp = response.messageException(HttpStatus.BAD_REQUEST.value(),e);
        }

        logger.info("Eliminando usuarios en BD");
        List<Usuario> errors = new ArrayList <>();
        usuarios.forEach(u -> {
            if (!services.existUsuarioFirebase(u.getId())) {
                services.deleteById(u.getId());
            } else {
                errors.add(u);
            }
        });
        if (!errors.isEmpty()) rsp.put("usuarios_errors",errors);
        logger.info("Rutina de eliminacion ejecutada correctamente");

        logger.info("Enviando respuesta");
        return response.messageResponse(rsp);
    }

    @PostMapping("imagen/perfil")
    public ResponseEntity<?> uploadImagePerfil(@Validated @RequestBody Fichero fichero){
        Map<String, Object> rsp;
        logger.info("Agregando Fichero");

        try {
            logger.info("Generando nombre de archivo");
            String nombre = fichero.getNombreArchivo() + "-" + UUID.randomUUID().toString() + fichero.getExtencion();
            logger.info("Nombre fichero: "+ nombre);

            String ruta = storage.uploadObject(nombre, BaseEncoding.base64().decode(fichero.getBase64()));
            logger.info("Imagen subido exitosamente");

            logger.info("Actualizando firebase");
            Usuario usuario = services.findById(securityService.getUser().getUid());
            usuario.setFoto_perfil(ruta);
            services.actualizarUsuarioFirebase(usuario);
            logger.info("Firebase actualizado correctamente");

            logger.info("Actualizando DB");
            services.update(usuario);
            logger.info("DB actualizado correctamente");

            //Respuesta
            rsp = response.messageOk(HttpStatus.OK.value());
            rsp.put("ruta",ruta);
        } catch (ExecutionException e) {
            logger.error("Ocurrio un error al subir imagen", e);
            rsp = response.messageException(HttpStatus.BAD_REQUEST.value(),e);
        } catch (InterruptedException e) {
            logger.error("Ocurrio un error al subir imagen", e);
            rsp = response.messageException(HttpStatus.BAD_REQUEST.value(),e);
        } catch (IOException e) {
            logger.error("Ocurrio un error al subir imagen", e);
            rsp = response.messageException(HttpStatus.BAD_REQUEST.value(),e);
        }

        logger.info("Enviando respuesta");
        return response.messageResponse(rsp);
    }

    @PostMapping("recuperar")
    public ResponseEntity<?> recuperarPassword(@RequestParam String email){
        Map<String, Object> rsp;
        try {
            logger.info("Buscando cliente por email");
            Usuario usuario = services.getUserByEmailFirebase(email);
            if (usuario !=null) {
                logger.info("Cliente encontrado, generando link de recuperacion");
            String link = FirebaseAuth.getInstance().generatePasswordResetLink(
                    email, ActionCodeSettings.builder().build());

                // Construct email verification template, embed the link and send
                // using custom SMTP server.
                logger.info("Enviando correo: " + link);
                correo.enviarCorreo(email, securityService.getUser().getName(), link);
                logger.info("Correo enviado exitosamente");
                rsp = response.messageOk(HttpStatus.OK.value());
            }else {
                rsp = response.messageGeneric(HttpStatus.NO_CONTENT.value(),"Usuario no encontrado con email");
            }
        } catch (FirebaseAuthException e) {
            logger.error("Ocurrio un error al subir imagen", e);
            rsp = response.messageException(HttpStatus.BAD_REQUEST.value(),e);
        }catch (ExecutionException e) {
            logger.error("Ocurrio un error al subir imagen", e);
            rsp = response.messageException(HttpStatus.BAD_REQUEST.value(),e);
        } catch (InterruptedException e) {
            logger.error("Ocurrio un error al subir imagen", e);
            rsp = response.messageException(HttpStatus.BAD_REQUEST.value(),e);
        }
        logger.info("Enviando respuesta");
        return response.messageResponse(rsp);
    }

    @PostMapping("validarEmail")
    public ResponseEntity<?> validarEmail(@RequestParam String email){
        Map<String, Object> rsp;
        try {
            logger.info("Buscando cliente por email");
            Usuario usuario = services.getUserByEmailFirebase(email);
            if (usuario !=null) {
                logger.info("Cliente encontrado, enviando link de validacion");
                String link = FirebaseAuth.getInstance().generateEmailVerificationLink(
                        email, ActionCodeSettings.builder().build());

                // Construct email verification template, embed the link and send
                // using custom SMTP server.
                logger.info("Enviando correo: " + link);
                correo.enviarCorreo(email, securityService.getUser().getName(), link);
                logger.info("Correo enviado exitosamente");
                rsp = response.messageOk(HttpStatus.OK.value());
            }else {
                rsp = response.messageGeneric(HttpStatus.NO_CONTENT.value(),"Usuario no encontrado con email");
            }
        } catch (FirebaseAuthException e) {
            logger.error("Ocurrio un error al subir imagen", e);
            rsp = response.messageException(HttpStatus.BAD_REQUEST.value(),e);
        }catch (ExecutionException e) {
            logger.error("Ocurrio un error al subir imagen", e);
            rsp = response.messageException(HttpStatus.BAD_REQUEST.value(),e);
        } catch (InterruptedException e) {
            logger.error("Ocurrio un error al subir imagen", e);
            rsp = response.messageException(HttpStatus.BAD_REQUEST.value(),e);
        }
        logger.info("Enviando respuesta");
        return response.messageResponse(rsp);
    }
}