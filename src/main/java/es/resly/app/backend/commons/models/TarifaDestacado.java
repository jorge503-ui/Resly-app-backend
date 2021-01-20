package es.resly.app.backend.commons.models;

import lombok.Data;

@Data
public class TarifaDestacado {
    private String id;
    private String tipo_tarifa;
    private String nom_tarifa;
    private Double precio;
    private String estado;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTipo_tarifa() {
        return tipo_tarifa;
    }

    public void setTipo_tarifa(String tipo_tarifa) {
        this.tipo_tarifa = tipo_tarifa;
    }

    public String getNom_tarifa() {
        return nom_tarifa;
    }

    public void setNom_tarifa(String nom_tarifa) {
        this.nom_tarifa = nom_tarifa;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
