package es.resly.app.backend.commons.models;

import com.google.cloud.Date;
import lombok.Data;

import java.util.List;

@Data
public class Plan {
    private String id;
    private String nombre;
    private String descripcion;
    private Double precio;
    private Double precio_oferta;
    private String estado;
    private Date fecha_ini;
    private Date fecha_fin;
    private List<TarifaDestacado> tarifas_destacado;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Double getPrecio_oferta() {
        return precio_oferta;
    }

    public void setPrecio_oferta(Double precio_oferta) {
        this.precio_oferta = precio_oferta;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
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

    public List <TarifaDestacado> getTarifas_destacado() {
        return tarifas_destacado;
    }

    public void setTarifas_destacado(List <TarifaDestacado> tarifas_destacado) {
        this.tarifas_destacado = tarifas_destacado;
    }
}
