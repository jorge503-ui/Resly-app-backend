package es.resly.app.backend.commons.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Comercio {

    private String id;
    private String cod_comercio;
    private String nom_comercio;
    private String identifcacion_tributaria;
    private boolean verificado;
    private Date fecha_verificacion;
    private List<Fichero> ficheros;
    private String tipo_comercio;
    private String marca_id;
    @JsonIgnore
    private Marca marca;
    private String estado;
    private String correo;
    private List<Categoria> categorias;
    private List<Sucursal> sucursales;
    private List<DiaFeriado> dias_feriados;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCod_comercio() {
        return cod_comercio;
    }

    public void setCod_comercio(String cod_comercio) {
        this.cod_comercio = cod_comercio;
    }

    public String getNom_comercio() {
        return nom_comercio;
    }

    public void setNom_comercio(String nom_comercio) {
        this.nom_comercio = nom_comercio;
    }

    public String getIdentifcacion_tributaria() {
        return identifcacion_tributaria;
    }

    public void setIdentifcacion_tributaria(String identifcacion_tributaria) {
        this.identifcacion_tributaria = identifcacion_tributaria;
    }

    public boolean isVerificado() {
        return verificado;
    }

    public void setVerificado(boolean verificado) {
        this.verificado = verificado;
    }

    public String getTipo_comercio() {
        return tipo_comercio;
    }

    public void setTipo_comercio(String tipo_comercio) {
        this.tipo_comercio = tipo_comercio;
    }

    public String getMarca_id() {
        return marca_id;
    }

    public void setMarca_id(String marca_id) {
        this.marca_id = marca_id;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Date getFecha_verificacion() {
        return fecha_verificacion;
    }

    public void setFecha_verificacion(Date fecha_verificacion) {
        this.fecha_verificacion = fecha_verificacion;
    }

    public List <Fichero> getFicheros() {
        return ficheros;
    }

    public void setFicheros(List <Fichero> ficheros) {
        this.ficheros = ficheros;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    public List <Categoria> getCategorias() {
        return categorias;
    }

    public void setCategorias(List <Categoria> categorias) {
        this.categorias = categorias;
    }

    public List <Sucursal> getSucursales() {
        return sucursales;
    }

    public void setSucursales(List <Sucursal> sucursales) {
        this.sucursales = sucursales;
    }

    public List <DiaFeriado> getDias_feriados() {
        return dias_feriados;
    }

    public void setDias_feriados(List <DiaFeriado> dias_feriados) {
        this.dias_feriados = dias_feriados;
    }
}
