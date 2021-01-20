package es.resly.app.backend.commons.models;

import lombok.Data;

import java.util.List;

@Data
public class Rol {
    private String id;
    private String codigo;
    private String nombre;
    private String descripcion;
    private String estado;
    private List<Modulo> modulos;

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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public List <Modulo> getModulos() {
        return modulos;
    }

    public void setModulos(List <Modulo> modulos) {
        this.modulos = modulos;
    }
}
