package es.resly.app.backend.commons.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.List;

@Data
public class SolicitudReserva {
    private String id;
    private String titulo;
    private String descripcion;
    private String usuario_id;
    @JsonIgnore
    private Usuario usuario;
    private String sucursal_id;
    @JsonIgnore
    private Sucursal sucursal;
    private List<Horario> Horario;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUsuario_id() {
        return usuario_id;
    }

    public void setUsuario_id(String usuario_id) {
        this.usuario_id = usuario_id;
    }

    public String getSucursal_id() {
        return sucursal_id;
    }

    public void setSucursal_id(String sucursal_id) {
        this.sucursal_id = sucursal_id;
    }

    public List <es.resly.app.backend.commons.models.Horario> getHorario() {
        return Horario;
    }

    public void setHorario(List <es.resly.app.backend.commons.models.Horario> horario) {
        Horario = horario;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Sucursal getSucursal() {
        return sucursal;
    }

    public void setSucursal(Sucursal sucursal) {
        this.sucursal = sucursal;
    }
}
