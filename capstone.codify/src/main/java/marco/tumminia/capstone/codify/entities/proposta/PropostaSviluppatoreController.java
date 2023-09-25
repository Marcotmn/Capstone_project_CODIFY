package marco.tumminia.capstone.codify.entities.proposta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import marco.tumminia.capstone.codify.entities.annuncio.Annuncio;
import marco.tumminia.capstone.codify.entities.annuncio.AnnuncioService;
import marco.tumminia.capstone.codify.entities.azienda.Azienda;
import marco.tumminia.capstone.codify.entities.privato.Privato;
import marco.tumminia.capstone.codify.entities.sviluppatore.Sviluppatore;
import marco.tumminia.capstone.codify.entities.sviluppatore.SviluppatoreService;
import marco.tumminia.capstone.codify.entities.utente.Utente;
import marco.tumminia.capstone.codify.exceptions.NotFoundException;

@RestController
@RequestMapping("/proposte")
public class PropostaSviluppatoreController {

    @Autowired
    private PropostaSviluppatoreService propostaService;

    @Autowired
    private SviluppatoreService sviluppatoreService;

    @Autowired
    private AnnuncioService annuncioService;

  //ENDPOINT PER RISPONDERE AD UN ANNUNCIO SPECIFICO(ID) CON UNA PROPOSTA
    @PostMapping("/pubblicaProposta/{annuncioId}")
    public ResponseEntity<Map<String, Object>> creaProposta(
        @PathVariable UUID annuncioId,
        @RequestBody PropostaSviluppatorePayload payload,
        @AuthenticationPrincipal Utente currentUser
    ) {
        if (!(currentUser instanceof Sviluppatore)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        Sviluppatore sviluppatore = (Sviluppatore) currentUser;

        //METODO PER RECUPERARE L'ANNUNCIO TRAMITE L'ID
        Annuncio annuncio = annuncioService.findAnnuncioById(annuncioId);

        if (annuncio == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        PropostaSviluppatore proposta = propostaService.createProposta(sviluppatore, annuncio, payload);

        // Recupera ulteriori informazioni necessarie
        Utente utentePubblicatore = annuncio.getUtente();

        // Crea un oggetto JSON personalizzato
        Map<String, Object> response = new HashMap<>();
        response.put("Stato proposta", "La proposta è stata inviata correttamente ed è in stato di attesa.");
        response.put("idProposta", proposta.getId());
        response.put("Nome annuncio", annuncio.getTitolo());

        if (utentePubblicatore instanceof Azienda) {
            response.put("Annuncio pubblicato da", ((Azienda) utentePubblicatore).getNomeAzienda());
        } else if (utentePubblicatore instanceof Privato) {
            response.put("Annuncio pubblicato da", ((Privato) utentePubblicatore).getNome() + " " + ((Privato) utentePubblicatore).getCognome());
        }

        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/{id}")
    public PropostaSviluppatore getProposta(@PathVariable UUID id) {
        return propostaService.findById(id).orElseThrow(() -> new NotFoundException("Proposta non trovata"));
    }

    @PostMapping("/accettaProposta/{idProposta}")
    public ResponseEntity<?> accettaProposta(@PathVariable UUID idProposta, @AuthenticationPrincipal Utente currentUser) {
        if (!(currentUser instanceof Azienda || currentUser instanceof Privato)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        propostaService.accettaProposta(idProposta);
        return ResponseEntity.ok("Proposta accettata con successo");
    }

    @PostMapping("/rifiutaProposta/{idProposta}")
    public ResponseEntity<?> rifiutaProposta(@PathVariable UUID idProposta, @AuthenticationPrincipal Utente currentUser) {
        if (!(currentUser instanceof Azienda || currentUser instanceof Privato)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        propostaService.rifiutaProposta(idProposta);
        return ResponseEntity.ok("Proposta rifiutata con successo");
    }

    
    @GetMapping("/mieProposte")
    public ResponseEntity<List<Map<String, Object>>> getMieProposte(@AuthenticationPrincipal Utente currentUser) {
        if (!(currentUser instanceof Sviluppatore)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        Sviluppatore sviluppatore = (Sviluppatore) currentUser;
        List<PropostaSviluppatore> proposte = propostaService.findBySviluppatore(sviluppatore);

        // Creare una lista di mappe con le informazioni richieste
        List<Map<String, Object>> response = new ArrayList<>();
        for (PropostaSviluppatore proposta : proposte) {
            Map<String, Object> propostaInfo = new HashMap<>();
            propostaInfo.put("idProposta", proposta.getId());
            propostaInfo.put("nomeAnnuncio", proposta.getAnnuncio().getTitolo());

            // Determina il nome dell'azienda o del privato che ha pubblicato l'annuncio
            Utente utenteAnnuncio = proposta.getAnnuncio().getUtente();
            if (utenteAnnuncio instanceof Azienda) {
                propostaInfo.put("nomePubblicante", ((Azienda) utenteAnnuncio).getNomeAzienda());
            } else if (utenteAnnuncio instanceof Privato) {
                propostaInfo.put("nomePubblicante", ((Privato) utenteAnnuncio).getNome());
            }

            response.add(propostaInfo);
        }

        return ResponseEntity.ok(response);
    }


    @SuppressWarnings("unused")
	private PropostaSviluppatoreResponse mapPropostaToResponse(PropostaSviluppatore proposta) {
        PropostaSviluppatoreResponse response = new PropostaSviluppatoreResponse();
        response.setId(proposta.getId());
        response.setDescrizione(proposta.getDescrizione());
        response.setImportoProposto(proposta.getImportoProposto());
        response.setStatoProposta(proposta.getStatoProposta());
        response.setDataProposta(proposta.getDataProposta());
        response.setAnnuncio(proposta.getAnnuncio());
        response.setSviluppatore(proposta.getSviluppatore());
        return response;
    }

    @GetMapping("/sviluppatore/{idSviluppatore}")
    public List<PropostaSviluppatore> getProposteBySviluppatore(@PathVariable UUID idSviluppatore) {
        Sviluppatore sviluppatore = sviluppatoreService.findById(idSviluppatore);
        if(sviluppatore == null) {
            throw new NotFoundException("Sviluppatore non trovato");
        }
        return propostaService.findBySviluppatore(sviluppatore);
    }

    @DeleteMapping("/{id}")
    public void deleteProposta(@PathVariable UUID id) {
        propostaService.deleteById(id);
    }
}
    

