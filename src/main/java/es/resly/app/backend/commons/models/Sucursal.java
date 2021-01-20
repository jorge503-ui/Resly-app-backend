package es.resly.app.backend.commons.models;

import lombok.Data;

import java.util.List;

@Data
public class Sucursal {
    private String id;
    private String cod_sucursal;
    private String nombre;
    private Direccion direccion;
    private String encargado;
    private List<ValoracionComentario> valoraciones_comentarios;
    private List<Fichero> ficheros;
    private String descripcion;
    private int alertas;
    private String estado;
    private List<Horario> horarios;
    private List<Contacto> contactos;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCod_sucursal() {
        return cod_sucursal;
    }

    public void setCod_sucursal(String cod_sucursal) {
        this.cod_sucursal = cod_sucursal;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    public String getEncargado() {
        return encargado;
    }

    public void setEncargado(String encargado) {
        this.encargado = encargado;
    }

    public List <ValoracionComentario> getValoraciones_comentarios() {
        return valoraciones_comentarios;
    }

    public void setValoraciones_comentarios(List <ValoracionComentario> valoraciones_comentarios) {
        this.valoraciones_comentarios = valoraciones_comentarios;
    }

    public List <Fichero> getFicheros() {
        return ficheros;
    }

    public void setFicheros(List <Fichero> ficheros) {
        this.ficheros = ficheros;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getAlertas() {
        return alertas;
    }

    public void setAlertas(int alertas) {
        this.alertas = alertas;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public List <Horario> getHorarios() {
        return horarios;
    }

    public void setHorarios(List <Horario> horarios) {
        this.horarios = horarios;
    }

    public List <Contacto> getContactos() {
        return contactos;
    }

    public void setContactos(List <Contacto> contactos) {
        this.contactos = contactos;
    }
}
