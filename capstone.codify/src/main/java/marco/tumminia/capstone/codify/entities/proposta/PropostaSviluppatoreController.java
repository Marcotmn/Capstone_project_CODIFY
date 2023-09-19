package marco.tumminia.capstone.codify.entities.proposta;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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

    // Endpoint per inviare una proposta associata a un annuncio specifico
    @PostMapping("/pubblicaProposta/{annuncioId}")
    public ResponseEntity<PropostaSviluppatore> creaProposta(
        @PathVariable UUID annuncioId,
        @RequestBody PropostaSviluppatorePayload payload,
        @AuthenticationPrincipal Utente currentUser
    ) {
        if (!(currentUser instanceof Sviluppatore)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        Sviluppatore sviluppatore = (Sviluppatore) currentUser;

        // Recupera l'annuncio in base all'ID fornito
        Annuncio annuncio = annuncioService.findById(annuncioId);

        if (annuncio == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        PropostaSviluppatore proposta = propostaService.createProposta(sviluppatore, annuncio, payload);

        return new ResponseEntity<>(proposta, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public PropostaSviluppatore getProposta(@PathVariable UUID id) {
        return propostaService.findById(id).orElseThrow(() -> new NotFoundException("Proposta non trovata"));
    }
    
    @GetMapping("/proposte/inAttesa")
    public ResponseEntity<List<PropostaSviluppatore>> getProposteInAttesa(@AuthenticationPrincipal Utente currentUser) {
        if (!(currentUser instanceof Azienda || currentUser instanceof Privato)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        // Per recupera le proposte in stato "IN_ATTESA" relative all'azienda o al privato
        List<PropostaSviluppatore> proposteInAttesa = propostaService.getProposteInAttesa(currentUser);

        return ResponseEntity.ok(proposteInAttesa);
    }

    @PostMapping("/accettaProposta/{idProposta}")
    public ResponseEntity<?> accettaProposta(@PathVariable UUID idProposta) {
        propostaService.accettaProposta(idProposta);
        return ResponseEntity.ok("Proposta accettata con successo");
    }

    @PostMapping("/rifiutaProposta/{idProposta}")
    public ResponseEntity<?> rifiutaProposta(@PathVariable UUID idProposta) {
        propostaService.rifiutaProposta(idProposta);
        return ResponseEntity.ok("Proposta rifiutata con successo");
    }
    
    @GetMapping("/mieProposte")
    public ResponseEntity<List<PropostaSviluppatoreResponse>> getMieProposte(@AuthenticationPrincipal Utente currentUser) {
        if (!(currentUser instanceof Sviluppatore)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        Sviluppatore sviluppatore = (Sviluppatore) currentUser;
        List<PropostaSviluppatore> proposte = propostaService.findBySviluppatore(sviluppatore);

        // Converti le proposte in una lista di DTO (PropostaSviluppatoreResponse)
        List<PropostaSviluppatoreResponse> response = proposte.stream()
                .map(this::mapPropostaToResponse)
                .collect(Collectors.toList());

        return ResponseEntity.ok(response);
    }

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
    

