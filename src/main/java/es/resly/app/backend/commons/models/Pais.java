package es.resly.app.backend.commons.models;

import lombok.Data;

import java.util.List;

@Data
public class Pais {
    private String id;
    private String codigo;
    private String moneda;
    private String cod_llamada;
    private String estado;
    private List<Departamento> departamentos;
    private List<Impuesto> impuestos;

    public List <Impuesto> getImpuestos() {
        return impuestos;
    }

    public void setImpuestos(List <Impuesto> impuestos) {
        this.impuestos = impuestos;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public String getCod_llamada() {
        return cod_llamada;
    }

    public void setCod_llamada(String cod_llamada) {
        this.cod_llamada = cod_llamada;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public List <Departamento> getDepartamentos() {
        return departamentos;
    }

    public void setDepartamentos(List <Departamento> departamentos) {
        this.departamentos = departamentos;
    }
}
