package es.resly.app.backend.commons.models;

import lombok.Data;

import java.util.List;

@Data
public class Direccion {
    private String id;
    private String cod_pais;
    private String cod_departamento;
    private String cod_municipio;
    private List<String> direcciones;
    private String referencia;
    private String estado;
    private List<Indicacion> indicaciones;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCod_pais() {
        return cod_pais;
    }

    public void setCod_pais(String cod_pais) {
        this.cod_pais = cod_pais;
    }

    public String getCod_departamento() {
        return cod_departamento;
    }

    public void setCod_departamento(String cod_departamento) {
        this.cod_departamento = cod_departamento;
    }

    public String getCod_municipio() {
        return cod_municipio;
    }

    public void setCod_municipio(String cod_municipio) {
        this.cod_municipio = cod_municipio;
    }

    public List <String> getDirecciones() {
        return direcciones;
    }

    public void setDirecciones(List <String> direcciones) {
        this.direcciones = direcciones;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public List <Indicacion> getIndicaciones() {
        return indicaciones;
    }

    public void setIndicaciones(List <Indicacion> indicaciones) {
        this.indicaciones = indicaciones;
    }
}
