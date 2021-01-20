package es.resly.app.backend.commons.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Categoria {
    private String id;
    private String nom_categoria;
    private String imagen_id;
    @JsonIgnore
    private Fichero imagen;
    private String estado;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNom_categoria() {
        return nom_categoria;
    }

    public void setNom_categoria(String nom_categoria) {
        this.nom_categoria = nom_categoria;
    }

    public String getImagen_id() {
        return imagen_id;
    }

    public void setImagen_id(String imagen_id) {
        this.imagen_id = imagen_id;
    }

    public Fichero getImagen() {
        return imagen;
    }

    public void setImagen(Fichero imagen) {
        this.imagen = imagen;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
