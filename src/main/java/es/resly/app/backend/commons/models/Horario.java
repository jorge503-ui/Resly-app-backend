package es.resly.app.backend.commons.models;

import com.google.cloud.Timestamp;
import lombok.Data;

@Data
public class Horario {
    private String id;
    private String dia;
    private Timestamp hora_ini;
    private Timestamp hora_fin;
    private boolean cierre_mediodia;
    private Timestamp hora_ini_cierre;
    private Timestamp hora_fin_cierre;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public Timestamp getHora_ini() {
        return hora_ini;
    }

    public void setHora_ini(Timestamp hora_ini) {
        this.hora_ini = hora_ini;
    }

    public Timestamp getHora_fin() {
        return hora_fin;
    }

    public void setHora_fin(Timestamp hora_fin) {
        this.hora_fin = hora_fin;
    }

    public boolean isCierre_mediodia() {
        return cierre_mediodia;
    }

    public void setCierre_mediodia(boolean cierre_mediodia) {
        this.cierre_mediodia = cierre_mediodia;
    }

    public Timestamp getHora_ini_cierre() {
        return hora_ini_cierre;
    }

    public void setHora_ini_cierre(Timestamp hora_ini_cierre) {
        this.hora_ini_cierre = hora_ini_cierre;
    }

    public Timestamp getHora_fin_cierre() {
        return hora_fin_cierre;
    }

    public void setHora_fin_cierre(Timestamp hora_fin_cierre) {
        this.hora_fin_cierre = hora_fin_cierre;
    }
}
