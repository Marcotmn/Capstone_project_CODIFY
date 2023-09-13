package marco.tumminia.capstone.codify.entities.proposta;

import java.time.LocalDate;
import java.util.List;
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
        

    @GetMapping("/{id}")
    public PropostaSviluppatore getProposta(@PathVariable UUID id) {
        return propostaService.findById(id).orElseThrow(() -> new NotFoundException("Proposta non trovata"));
    }
    
    @PostMapping("/crea")
    public ResponseEntity<PropostaSviluppatore> creaProposta(@RequestBody PropostaSviluppatorePayload payload, @AuthenticationPrincipal Utente currentUser) {

        if (!(currentUser instanceof Sviluppatore)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        Sviluppatore sviluppatore = (Sviluppatore) currentUser;

        Annuncio annuncio = annuncioService.findById(payload.getIdAnnuncio());
            

        PropostaSviluppatore proposta = new PropostaSviluppatore(sviluppatore, annuncio, payload.getDescrizione(), payload.getImportoProposto(), StatoProposta.IN_ATTESA, LocalDate.now());
        PropostaSviluppatore propostaSalvata = propostaService.saveProposta(proposta);

        return new ResponseEntity<>(propostaSalvata, HttpStatus.CREATED);
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

    // Potrai anche avere endpoint per aggiornare una proposta ecc.
}
    

