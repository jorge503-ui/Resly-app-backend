package es.resly.app.backend.usuarios.repository;

import es.resly.app.backend.commons.repository.FirebaseCrudRepository;
import es.resly.app.backend.usuarios.controllers.UsuarioController;
import es.resly.app.backend.usuarios.models.Usuario;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

@Repository
public class UsuarioRepository extends FirebaseCrudRepository<Usuario, String> {

    private static final Logger logger = LogManager.getLogger(UsuarioController.class);

    public UsuarioRepository(){
        setCollection("usuarios");

    }

}
