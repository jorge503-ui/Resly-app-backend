package es.resly.app.backend.commons.models;

import lombok.Data;

import java.util.List;

@Data
public class DetalleVenta {
    private String id;
    private List<Plan> planes;
    private List<Destacado> destacados;
    private Double monto;
    private Double descuento;
    private Double costo;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List <Plan> getPlanes() {
        return planes;
    }

    public void setPlanes(List <Plan> planes) {
        this.planes = planes;
    }

    public List <Destacado> getDestacados() {
        return destacados;
    }

    public void setDestacados(List <Destacado> destacados) {
        this.destacados = destacados;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public Double getDescuento() {
        return descuento;
    }

    public void setDescuento(Double descuento) {
        this.descuento = descuento;
    }

    public Double getCosto() {
        return costo;
    }

    public void setCosto(Double costo) {
        this.costo = costo;
    }
}
