package marco.tumminia.capstone.codify.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import marco.tumminia.capstone.codify.entities.utente.Utente;

@Component
public class AuthenticationFacade {

    public Utente getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof Utente) {
            return (Utente) authentication.getPrincipal();
        }
        return null;
    }
}
