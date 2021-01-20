package es.resly.app.backend.commons.models;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class DiaFeriado {
    private String id;
    private Date fecha;
    private String estado;
    private List <Sucursal> sucursales;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public List <Sucursal> getSucursales() {
        return sucursales;
    }

    public void setSucursales(List <Sucursal> sucursales) {
        this.sucursales = sucursales;
    }
}
