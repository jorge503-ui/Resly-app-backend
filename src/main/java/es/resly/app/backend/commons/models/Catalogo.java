package es.resly.app.backend.commons.models;

import lombok.Data;

import java.util.List;

@Data
public class Catalogo {
    private String id;
    private String nom_catalogo;
    private List<ValorCatalogo> valores_catalogo;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNom_catalogo() {
        return nom_catalogo;
    }

    public void setNom_catalogo(String nom_catalogo) {
        this.nom_catalogo = nom_catalogo;
    }

    public List <ValorCatalogo> getValores_catalogo() {
        return valores_catalogo;
    }

    public void setValores_catalogo(List <ValorCatalogo> valores_catalogo) {
        this.valores_catalogo = valores_catalogo;
    }
}
