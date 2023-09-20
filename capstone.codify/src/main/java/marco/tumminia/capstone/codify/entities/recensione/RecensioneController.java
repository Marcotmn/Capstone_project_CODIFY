package marco.tumminia.capstone.codify.entities.recensione;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import marco.tumminia.capstone.codify.entities.azienda.Azienda;
import marco.tumminia.capstone.codify.entities.privato.Privato;
import marco.tumminia.capstone.codify.entities.sviluppatore.Sviluppatore;
import marco.tumminia.capstone.codify.entities.sviluppatore.SviluppatoreService;
import marco.tumminia.capstone.codify.entities.utente.Utente;
import marco.tumminia.capstone.codify.exceptions.NotFoundException;
import marco.tumminia.capstone.codify.exceptions.UnauthorizedException;
import marco.tumminia.capstone.codify.security.AuthenticationFacade;

import java.util.UUID;

@RestController
@RequestMapping("/recensioni")
public class RecensioneController {

    @Autowired
    private RecensioneService recensioneService;

    @Autowired
    private SviluppatoreService sviluppatoreService;
    
    @Autowired
    private AuthenticationFacade authenticationFacade;

    @PostMapping("/scriviRecensione/{idSviluppatore}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<NuovaRecensioneResponsePayload> scriviRecensionePerSviluppatore(
            @PathVariable UUID idSviluppatore,
            @RequestBody NuovaRecensionePayload payload
    ) {
        // Verifica se l'ID dello sviluppatore esiste
        Sviluppatore sviluppatore = sviluppatoreService.findById(idSviluppatore);

        if (sviluppatore == null) {
            throw new NotFoundException("Sviluppatore non trovato");
        }

        // Ottieni l'utente autenticato (recensore)
        Utente recensore = authenticationFacade.getAuthenticatedUser();

        if (recensore == null || !(recensore instanceof Azienda || recensore instanceof Privato)) {
            throw new UnauthorizedException("Solo aziende o privati possono lasciare recensioni.");
        }

        // Crea una nuova recensione
        Recensione nuovaRecensione = new Recensione(payload.getPunteggio(), payload.getTestoRecensione());
        nuovaRecensione.setSviluppatore(sviluppatore);
        nuovaRecensione.setRecensore(recensore); // Imposta il recensore sulla recensione

        // Salva la recensione
        Recensione recensioneSalvata = recensioneService.saveRecensione(nuovaRecensione);

        // Ottieni l'username dello sviluppatore recensito
        String usernameSviluppatoreRecensito = sviluppatore.getUsername();

        // Crea una risposta
        NuovaRecensioneResponsePayload responsePayload = new NuovaRecensioneResponsePayload();
        responsePayload.setIdRecensione(recensioneSalvata.getId());
        responsePayload.setMessaggio("La recensione è stata salvata con successo");

        return ResponseEntity.ok(responsePayload);
    }


    @GetMapping("/{id}")
    public Recensione getRecensioneById(@PathVariable UUID id) {
        return recensioneService.findById(id).orElse(null);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteRecensione(@PathVariable UUID id) {
        recensioneService.deleteById(id);
    }
}
