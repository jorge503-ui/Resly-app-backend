package es.resly.app.backend.commons.models;

import lombok.Data;

@Data
public class PuntuacionRanking {
    private String id;
    private String tipo_punutacion;
    private Double puntaje;
    private String estado;
    private String descripcion;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTipo_punutacion() {
        return tipo_punutacion;
    }

    public void setTipo_punutacion(String tipo_punutacion) {
        this.tipo_punutacion = tipo_punutacion;
    }

    public Double getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(Double puntaje) {
        this.puntaje = puntaje;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
