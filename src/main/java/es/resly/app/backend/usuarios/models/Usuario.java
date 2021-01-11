package es.resly.app.backend.usuarios.models;
import java.io.Serializable;

import lombok.Data;

@Data
public class Usuario implements Serializable {

    private static final long serialVersionUID = 4408418647685225829L;
    private String id;
    private String codUsuario;
    private String nombre;
    private String apellido;
    private String tipoUsuario;
    private String rolId;
    private String correo;
    private String contrasea;
    private String fotoPerfil;
    private String fechaNacimiento;
    private String edad;
    private String estado;
    private String numTelefono;
    private Double puntuacionRanking;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCodUsuario() {
        return codUsuario;
    }

    public void setCodUsuario(String codUsuario) {
        this.codUsuario = codUsuario;
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

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public String getRolId() {
        return rolId;
    }

    public void setRolId(String rolId) {
        this.rolId = rolId;
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

    public String getFotoPerfil() {
        return fotoPerfil;
    }

    public void setFotoPerfil(String fotoPerfil) {
        this.fotoPerfil = fotoPerfil;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
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

    public String getNumTelefono() {
        return numTelefono;
    }

    public void setNumTelefono(String numTelefono) {
        this.numTelefono = numTelefono;
    }

    public Double getPuntuacionRanking() {
        return puntuacionRanking;
    }

    public void setPuntuacionRanking(Double puntuacionRanking) {
        this.puntuacionRanking = puntuacionRanking;
    }
}