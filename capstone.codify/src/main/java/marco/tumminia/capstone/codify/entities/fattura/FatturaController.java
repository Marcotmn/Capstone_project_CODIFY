package marco.tumminia.capstone.codify.entities.fattura;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import marco.tumminia.capstone.codify.entities.utente.Utente;
import marco.tumminia.capstone.codify.security.AuthenticationFacade;

@RestController
@RequestMapping("/fatture")
public class FatturaController {

    @Autowired
    private FatturaService fatturaService;

    @Autowired
    private AuthenticationFacade authenticationFacade;

    // Recupera una fattura specifica tramite l'ID
    @GetMapping("/{id}")
    public ResponseEntity<Fattura> getFattura(@PathVariable UUID id) {
        Fattura fattura = fatturaService.findFatturaById(id);
        return ResponseEntity.ok(fattura);
    }

    // Recupera tutte le fatture dell'utente autenticato
    @GetMapping
    public ResponseEntity<List<Fattura>> getFattureUtente() {
        Utente utenteAutenticato = authenticationFacade.getAuthenticatedUser();
        List<Fattura> fatture = fatturaService.findFattureByUtente(utenteAutenticato);
        return ResponseEntity.ok(fatture);
    }
}
