package es.resly.app.backend;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ReslyAppBackendApplication {

    static final Logger logger = Logger.getLogger(ReslyAppBackendApplication.class);

    public static void main(String[] args) {
		logger.info("Iniciando aplicacion");
		SpringApplication.run(ReslyAppBackendApplication.class, args);
	}

}
