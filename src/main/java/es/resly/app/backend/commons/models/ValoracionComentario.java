package es.resly.app.backend.commons.models;

import com.google.cloud.Date;
import lombok.Data;

@Data
public class ValoracionComentario {
    private String id;
    private Double puntuacion;
    private String usuario_id;
    private String reservacion_id;
    private String comentario;
    private Date fecha_comentario;
    private String reconocimiento;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(Double puntuacion) {
        this.puntuacion = puntuacion;
    }

    public String getUsuario_id() {
        return usuario_id;
    }

    public void setUsuario_id(String usuario_id) {
        this.usuario_id = usuario_id;
    }

    public String getReservacion_id() {
        return reservacion_id;
    }

    public void setReservacion_id(String reservacion_id) {
        this.reservacion_id = reservacion_id;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Date getFecha_comentario() {
        return fecha_comentario;
    }

    public void setFecha_comentario(Date fecha_comentario) {
        this.fecha_comentario = fecha_comentario;
    }

    public String getReconocimiento() {
        return reconocimiento;
    }

    public void setReconocimiento(String reconocimiento) {
        this.reconocimiento = reconocimiento;
    }
}
