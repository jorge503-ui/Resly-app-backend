package es.resly.app.backend.commons.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.List;

@Data
public class Marca {
    private String id;
    private String codigo;
    private String nombre;
    private String descripcion;
    private String logo_id;
    @JsonIgnore
    private Fichero logo;
    private String banner_id;
    @JsonIgnore
    private Fichero banner;
    private String sito_web;
    private String estado;
    private List<RedSocial> redes_sociales;

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

    public Fichero getLogo() {
        return logo;
    }

    public void setLogo(Fichero logo) {
        this.logo = logo;
    }

    public Fichero getBanner() {
        return banner;
    }

    public void setBanner(Fichero banner) {
        this.banner = banner;
    }

    public String getSito_web() {
        return sito_web;
    }

    public void setSito_web(String sito_web) {
        this.sito_web = sito_web;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public List <RedSocial> getRedes_sociales() {
        return redes_sociales;
    }

    public void setRedes_sociales(List <RedSocial> redes_sociales) {
        this.redes_sociales = redes_sociales;
    }

    public String getLogo_id() {
        return logo_id;
    }

    public void setLogo_id(String logo_id) {
        this.logo_id = logo_id;
    }

    public String getBanner_id() {
        return banner_id;
    }

    public void setBanner_id(String banner_id) {
        this.banner_id = banner_id;
    }
}
