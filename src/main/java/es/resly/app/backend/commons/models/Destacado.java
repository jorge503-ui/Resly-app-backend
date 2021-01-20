package es.resly.app.backend.commons.models;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Destacado {
    private String id;
    private Date fecha_ini;
    private Date fecha_fin;
    private Double monto;
    private String estado;
    private List<Comercio> comercios;
    private List<Sucursal> sucursales;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getFecha_ini() {
        return fecha_ini;
    }

    public void setFecha_ini(Date fecha_ini) {
        this.fecha_ini = fecha_ini;
    }

    public Date getFecha_fin() {
        return fecha_fin;
    }

    public void setFecha_fin(Date fecha_fin) {
        this.fecha_fin = fecha_fin;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public List <Comercio> getComercios() {
        return comercios;
    }

    public void setComercios(List <Comercio> comercios) {
        this.comercios = comercios;
    }

    public List <Sucursal> getSucursales() {
        return sucursales;
    }

    public void setSucursales(List <Sucursal> sucursales) {
        this.sucursales = sucursales;
    }
}
