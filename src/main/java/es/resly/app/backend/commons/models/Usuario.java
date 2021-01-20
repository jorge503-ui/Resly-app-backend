package es.resly.app.backend.commons.models;
import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Usuario implements Serializable {

    private static final long serialVersionUID = 4408418647685225829L;
    private String id;
    private String cod_usuario;
    private String nombre;
    private String apellido;
    private String tipo_usuario;
    private String rol_id;
    @JsonIgnore
    private Rol rol;
    private String correo;
    private String contrasea;
    private String foto_perfil;
    private String fecha_nacimiento;
    private String edad;
    private String estado;
    private String num_telefono;
    private Double puntuacion_ranking;
    private List<Comercio> comercios;
    private Direccion direccion;
    private List<Plan> planes;
    private List<Categoria> categoria_favoritos;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCod_usuario() {
        return cod_usuario;
    }

    public void setCod_usuario(String cod_usuario) {
        this.cod_usuario = cod_usuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTipo_usuario() {
        return tipo_usuario;
    }

    public void setTipo_usuario(String tipo_usuario) {
        this.tipo_usuario = tipo_usuario;
    }

    public String getRol_id() {
        return rol_id;
    }

    public void setRol_id(String rol_id) {
        this.rol_id = rol_id;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasea() {
        return contrasea;
    }

    public void setContrasea(String contrasea) {
        this.contrasea = contrasea;
    }

    public String getFoto_perfil() {
        return foto_perfil;
    }

    public void setFoto_perfil(String foto_perfil) {
        this.foto_perfil = foto_perfil;
    }

    public String getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(String fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getNum_telefono() {
        return num_telefono;
    }

    public void setNum_telefono(String num_telefono) {
        this.num_telefono = num_telefono;
    }

    public Double getPuntuacion_ranking() {
        return puntuacion_ranking;
    }

    public void setPuntuacion_ranking(Double puntuacion_ranking) {
        this.puntuacion_ranking = puntuacion_ranking;
    }

    public List <Comercio> getComercios() {
        return comercios;
    }

    public void setComercios(List <Comercio> comercios) {
        this.comercios = comercios;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    public List <Plan> getPlanes() {
        return planes;
    }

    public void setPlanes(List <Plan> planes) {
        this.planes = planes;
    }

    public List <Categoria> getCategoria_favoritos() {
        return categoria_favoritos;
    }

    public void setCategoria_favoritos(List <Categoria> categoria_favoritos) {
        this.categoria_favoritos = categoria_favoritos;
    }
}