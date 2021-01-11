package es.resly.app.backend.commons.helper;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Data
public class ResponseMessage {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp;

    public ResponseMessage(){
        timestamp = LocalDateTime.now();
    }

    public ResponseEntity<?> messageResponse(Map<String, Object> respuesta){
        return ResponseEntity.status(Integer.parseInt(respuesta.get("status").toString())).body(respuesta);
    }

    public Map<String, Object> messageException(int status, Throwable e){
        timestamp = LocalDateTime.now();
        Map<String, Object> hasmap = new HashMap<>();
        hasmap.put("status", status);
        hasmap.put("timestamp",timestamp);
        hasmap.put("message", e.getMessage());
        hasmap.put("details", e.getLocalizedMessage());
        return hasmap;
    }

    public Map<String, Object> messageOk(int status){
        timestamp = LocalDateTime.now();
        Map<String, Object> hasmap = new HashMap<>();
        hasmap.put("status", status);
        hasmap.put("timestamp",timestamp);
        hasmap.put("message", "Proceso finalizado exitosamente");
        return hasmap;
    }

    public Map<String, Object> messageProblem(int status){
        timestamp = LocalDateTime.now();
        Map<String, Object> hasmap = new HashMap<>();
        hasmap.put("status", status);
        hasmap.put("timestamp",timestamp);
        hasmap.put("message", "Ha ocurrido un problema");
        return hasmap;
    }

    public Map<String, Object> messageGeneric(int status, String message){
        timestamp = LocalDateTime.now();
        Map<String, Object> hasmap = new HashMap<>();
        hasmap.put("status", status);
        hasmap.put("timestamp",timestamp);
        hasmap.put("message", message);
        return hasmap;
    }
}
