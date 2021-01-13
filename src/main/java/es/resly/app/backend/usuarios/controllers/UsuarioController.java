package es.resly.app.backend.usuarios.controllers;

import com.google.common.io.BaseEncoding;
import com.google.firebase.auth.*;
import es.resly.app.backend.commons.helper.ResponseMessage;
import es.resly.app.backend.commons.models.Fichero;
import es.resly.app.backend.commons.repository.FirebaseStorageRepository;
import es.resly.app.backend.usuarios.models.Usuario;
import es.resly.app.backend.usuarios.services.UsuarioServiceImpl;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("usuario")
public class UsuarioController {

    private static final Logger logger = LogManager.getLogger(UsuarioController.class);

    @Autowired
    private UsuarioServiceImpl services;

    private FirebaseStorageRepository storage;

    private final ResponseMessage response = new ResponseMessage();

    @GetMapping("all")
    public ResponseEntity<?> consultarUsuarios(){
        Map<String, Object> rsp;
        List<Usuario> usuarios;
        try {
            usuarios = services.findAll();
            rsp = response.messageOk(HttpStatus.OK.value());
            rsp.put("usuarios",usuarios);
        }catch (Exception e){
            logger.error("Ocurrio un error al listar los usuarios", e);
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
            services.crearUsuarioFirebase(usuario);
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
            rsp = response.messageOk(HttpStatus.CREATED.value());
            rsp.put("uid",usuario.getId());
        } catch (Exception e) {
            logger.error("Ocurrio un error al crear usuario", e);
            rsp = response.messageException(HttpStatus.BAD_REQUEST.value(),e);

            //Si en tal caso da error al crear usuario en BD se elimina de firebase
            logger.error("ELiminado usuario de firebase");
            services.eliminarUsuarioFirebase(usuario.getId());
        }
        return response.messageResponse(rsp);
    }

    @PostMapping("actualizar")
    public ResponseEntity<?> actualizarUsuario(@Validated @RequestBody Usuario usuario){
        Map<String, Object> rsp;
        logger.info("Actualizando usuario " + usuario.getId());

        logger.info("Actualizando usuario en firebase");
        try {
            services.actualizarUsuarioFirebase(usuario);

            //Respuesta
            rsp = response.messageOk(HttpStatus.CREATED.value());
            rsp.put("uid",usuario.getId());
        } catch (Exception e) {
            logger.error("Ocurrio un error al actualizar usuario", e);
            rsp = response.messageException(HttpStatus.BAD_REQUEST.value(),e);
        }

        logger.info("Actualizando usuario en BD");

        return response.messageResponse(rsp);
    }

    @PostMapping("eliminar/{uid}")
    public ResponseEntity<?> eliminarUsuario(@PathVariable String uid){
        Map<String, Object> rsp;
        logger.info("Eliminando usuario " +uid);

        logger.info("Eliminando usuario en firebase");
        try {
            services.eliminarUsuarioFirebase(uid);
            //Respuesta
            rsp = response.messageOk(HttpStatus.OK.value());
        } catch (Exception e) {
            logger.error("Ocurrio un error al eliminar usuario", e);
            rsp = response.messageException(HttpStatus.BAD_REQUEST.value(),e);
        }

        logger.info("Eliminando usuario en BD");

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

            //Respuesta
            if (result.getFailureCount()==0){
                rsp = response.messageOk(HttpStatus.OK.value());
            }else {
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

        return response.messageResponse(rsp);
    }

    @PostMapping("image/perfil")
    public ResponseEntity<?> upload(@Validated @RequestBody Fichero fichero){
        Map<String, Object> rsp;
        logger.info("Agregando Fichero");

        try {
            String ruta = storage.uploadObject(fichero.getNombreArchivo(), BaseEncoding.base64().decode(fichero.getBase64()));
            fichero.setRuta(ruta);
            //Respuesta
            rsp = response.messageOk(HttpStatus.OK.value());
            rsp.put("fichero",fichero);
        } catch (IOException e) {
            logger.error("Ocurrio un error al subir imagen", e);
            rsp = response.messageException(HttpStatus.BAD_REQUEST.value(),e);
        }

        return response.messageResponse(rsp);
    }
}