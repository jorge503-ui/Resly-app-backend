package es.resly.app.backend.commons.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.List;

@Data
public class Ranking {
    private String id;
    private String nombre;
    private String descripcion;
    private String imagen_id;
    @JsonIgnore
    private Fichero imagen;
    private int puntaje_ini;
    private int puntaje_fin;
    private String estado;
    private List<PuntuacionRanking> puntuacionRankings;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public int getPuntaje_ini() {
        return puntaje_ini;
    }

    public void setPuntaje_ini(int puntaje_ini) {
        this.puntaje_ini = puntaje_ini;
    }

    public int getPuntaje_fin() {
        return puntaje_fin;
    }

    public void setPuntaje_fin(int puntaje_fin) {
        this.puntaje_fin = puntaje_fin;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public List <PuntuacionRanking> getPuntuacionRankings() {
        return puntuacionRankings;
    }

    public void setPuntuacionRankings(List <PuntuacionRanking> puntuacionRankings) {
        this.puntuacionRankings = puntuacionRankings;
    }
}
