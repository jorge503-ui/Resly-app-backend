package es.resly.app.backend.commons.models;

import lombok.Data;

import java.util.List;

@Data
public class Departamento {
    private String id;
    private String codigo;
    private String nombre;
    private String estado;
    private List<Municipio> municipios;

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public List <Municipio> getMunicipios() {
        return municipios;
    }

    public void setMunicipios(List <Municipio> municipios) {
        this.municipios = municipios;
    }
}
