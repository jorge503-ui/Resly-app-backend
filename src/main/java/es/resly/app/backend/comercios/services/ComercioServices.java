package es.resly.app.backend.comercios.services;

import es.resly.app.backend.commons.models.Comercio;
import es.resly.app.backend.commons.services.CommonServices;

public interface ComercioServices extends CommonServices <Comercio> {

    public void agregarComercioUsuario(Comercio comercio,String uidUsuario);

    public void eliminarComercioUsuario(Comercio comercio,String uidUsuario);
}
