package es.resly.app.backend.auth.controllers;

import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;

import es.resly.app.backend.auth.models.Credentials;
import es.resly.app.backend.auth.models.SecurityProperties;
import es.resly.app.backend.auth.models.User;
import es.resly.app.backend.auth.services.CookieService;
import es.resly.app.backend.auth.services.SecurityService;
import es.resly.app.backend.usuarios.repository.UsuarioRepository;
import es.resly.app.backend.usuarios.services.UsuarioServiceImpl;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.SessionCookieOptions;

@RestController
@RequestMapping("session")
public class SessionController {

    private static final Logger logger = LogManager.getLogger(SessionController.class);

    @Autowired
    private SecurityService securityService;

    @Autowired
    private CookieService cookieUtils;

    @Autowired
    private SecurityProperties secProps;

    @PostMapping("login")
    public void sessionLogin(HttpServletRequest request) {
        logger.info("Creando session");
        String idToken = securityService.getBearerToken(request);
        int sessionExpiryDays = secProps.getFirebaseProps().getSessionExpiryInDays();
        long expiresIn = TimeUnit.DAYS.toMillis(sessionExpiryDays);
        SessionCookieOptions options = SessionCookieOptions.builder().setExpiresIn(expiresIn).build();
        try {
            String sessionCookieValue = FirebaseAuth.getInstance().createSessionCookie(idToken, options);
            cookieUtils.setSecureCookie("session", sessionCookieValue, sessionExpiryDays);
            cookieUtils.setCookie("authenticated", Boolean.toString(true), sessionExpiryDays);
            logger.info("Session creada exitosamente");
        } catch (FirebaseAuthException e) {
            logger.info("Ha ocurrido un problema",e);
        }
    }

    @PostMapping("logout")
    public void sessionLogout() {
        logger.info("Cerrando session");
        if (securityService.getCredentials().getType() == Credentials.CredentialType.SESSION
                && secProps.getFirebaseProps().isEnableLogoutEverywhere()) {
            try {
                FirebaseAuth.getInstance().revokeRefreshTokens(securityService.getUser().getUid());
            } catch (FirebaseAuthException e) {
                logger.info("Ha ocurrido un problema",e);
            }
        }
        cookieUtils.deleteSecureCookie("session");
        cookieUtils.deleteCookie("authenticated");
        logger.info("Session cerrada");
    }

    @PostMapping("me")
    public User getUser() {
        return securityService.getUser();
    }

    @GetMapping("create/token")
    public String getCustomToken() throws FirebaseAuthException {
        return FirebaseAuth.getInstance().createCustomToken(String.valueOf(securityService.getUser().getUid()));
    }

}
