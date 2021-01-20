package es.resly.app.backend.comercios.repository;

import es.resly.app.backend.commons.models.Comercio;
import es.resly.app.backend.commons.repository.FirebaseCrudRepository;
import es.resly.app.backend.usuarios.controllers.UsuarioController;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

@Repository
public class ComercioRepository extends FirebaseCrudRepository <Comercio, String> {

    private static final Logger logger = LogManager.getLogger(UsuarioController.class);

    public ComercioRepository() {
        setCollection("comercios");
    }
}
