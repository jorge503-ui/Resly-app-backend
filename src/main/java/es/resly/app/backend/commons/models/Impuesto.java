package es.resly.app.backend.commons.models;

import lombok.Data;

@Data
public class Impuesto {
    private String tip_impuesto;
    private Double porcentaje;
    private String estado;

    public String getTip_impuesto() {
        return tip_impuesto;
    }

    public void setTip_impuesto(String tip_impuesto) {
        this.tip_impuesto = tip_impuesto;
    }

    public Double getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(Double porcentaje) {
        this.porcentaje = porcentaje;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
