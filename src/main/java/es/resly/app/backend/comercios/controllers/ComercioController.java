package es.resly.app.backend.comercios.controllers;

import es.resly.app.backend.auth.services.SecurityService;
import es.resly.app.backend.comercios.services.ComercioServicesImpl;
import es.resly.app.backend.commons.helper.ResponseMessage;
import es.resly.app.backend.commons.models.Comercio;
import es.resly.app.backend.commons.repository.FirebaseStorageRepository;
import es.resly.app.backend.usuarios.controllers.UsuarioController;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("comercio")
public class ComercioController {

    private static final Logger logger = LogManager.getLogger(UsuarioController.class);

    @Autowired
    private ComercioServicesImpl services;

    @Autowired
    private SecurityService securityService;

    private FirebaseStorageRepository storage = new FirebaseStorageRepository();

    private final ResponseMessage response = new ResponseMessage();

    @GetMapping("/")
    public ResponseEntity <?> consultarComercios(){
        Map <String, Object> rsp;
        logger.info("Obteniendo lista de comercios");
        List <Comercio> comercios = new ArrayList <>();
        try {
            comercios = services.findAll();
            logger.info("Comercios obtenidos exitosamente");
            rsp = response.messageOk(HttpStatus.OK.value());
            rsp.put("comercios",comercios);
        }catch (Exception e){
            logger.error("Ocurrio un error al listar los comercios", e);
            rsp = response.messageException(HttpStatus.INTERNAL_SERVER_ERROR.value(),e);
        }
        logger.info("Enviando respuesta");
        return response.messageResponseListObjetcOk(Collections.singletonList(comercios));
    }

    @GetMapping("comercio/")
    public ResponseEntity<?> consultarComercio(@RequestParam(name = "uid") String uid){
        Map<String, Object> rsp;
        logger.info("Obtiendo comerio: " + uid );
        Comercio comercio;
        try {
            comercio = services.findById(uid);
            logger.info("Comercio obtenido exitosamente");
            rsp = response.messageOk(HttpStatus.OK.value());
            rsp.put("comercio",comercio);
        }catch (Exception e){
            logger.error("Ocurrio un error al obtener usuario", e);
            rsp = response.messageException(HttpStatus.INTERNAL_SERVER_ERROR.value(),e);
        }
        logger.info("Enviando respuesta");
        return response.messageResponse(rsp);
    }

    @PostMapping("crear")
    public ResponseEntity<?> crearComercio(@Validated @RequestBody Comercio comercio, @RequestParam String uid){
        Map<String, Object> rsp;
        logger.info("Creando comercio " + comercio.getNom_comercio());

        try {
            comercio = services.save(comercio);
            logger.info("Comercio creado exitosamente, id: "+comercio.getId());

            //logger.info("Agregando comercio al usuario: " + securityService.getUser().getUid());
            logger.info("Agregando comercio al usuario: " + uid);
            services.agregarComercioUsuario(comercio, uid);
            logger.info("Comercio agregado al usuario correctamente");

            //Respuesta
            logger.info("Generando respuesta");
            rsp = response.messageOk(HttpStatus.OK.value());
            rsp.put("comercio_id",comercio.getId());
        } catch (Exception e) {
            logger.error("Ocurrio un error al crear usuario", e);
            rsp = response.messageException(HttpStatus.BAD_REQUEST.value(),e);
        }
        logger.info("Enviando respuesta");
        return response.messageResponse(rsp);
    }

    @PostMapping("actualizar")
    public ResponseEntity<?> actualizarComercio(@Validated @RequestBody Comercio comercio){
        Map<String, Object> rsp;
        logger.info("Actualizando comercio " + comercio.getId());

        try {
            services.update(comercio);
            logger.info("Actualizado correctamente");

            //Respuesta
            rsp = response.messageOk(HttpStatus.OK.value());
        }catch (Exception e){
            logger.error("Ocurrio un error al actualizar comercio", e);
            rsp = response.messageException(HttpStatus.BAD_REQUEST.value(),e);
        }
        logger.info("Enviando respuesta");
        return response.messageResponse(rsp);
    }

    @PostMapping("eliminar/")
    public ResponseEntity<?> eliminarComercio(@RequestParam(name = "uid") String uid){
        Map<String, Object> rsp;
        logger.info("Eliminando comercio " +uid);
        try {
            services.deleteById(uid);
            logger.info("Eliminado correctamente en BD");

            logger.info("Eliminando Comercio de usuario");
            //services.eliminarComercioUsuario();

            //Respuesta
            rsp = response.messageOk(HttpStatus.OK.value());
        }catch (Exception e){
            logger.error("Ocurrio un error al eliminar comercio", e);
            rsp = response.messageException(HttpStatus.BAD_REQUEST.value(),e);
        }
        logger.info("Enviando respuesta");
        return response.messageResponse(rsp);
    }

    @PostMapping("eliminar/lista")
    public ResponseEntity<?> eliminarListaComercio(@Validated @RequestBody List<Comercio> comercios){
        Map<String, Object> rsp;
        logger.info("Eliminando comercios");

        List<Comercio> errors = new ArrayList <>();
        comercios.forEach(u -> {
            try {
                services.deleteById(u.getId());
            } catch (Exception e){
                logger.info("Ocurrio un problema al eliminar comercio: "+u.getId(),e);
                errors.add(u);
            }
        });
        //Respuesta
        rsp = response.messageOk(HttpStatus.OK.value());
        if (!errors.isEmpty()) rsp.put("errors",errors);
        logger.info("Rutina de eliminacion ejecutada correctamente");

        logger.info("Enviando respuesta");
        return response.messageResponse(rsp);
    }
}
