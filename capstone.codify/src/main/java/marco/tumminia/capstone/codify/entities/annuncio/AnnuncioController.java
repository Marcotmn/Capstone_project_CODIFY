package marco.tumminia.capstone.codify.entities.annuncio;

import java.util.UUID;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import marco.tumminia.capstone.codify.exceptions.BadRequestException;

@RestController
@RequestMapping("/annunci")
public class AnnuncioController {

    @Autowired
    private AnnuncioService annuncioService;
    
    @PostMapping("/nuovoAnnuncio")
    public ResponseEntity<?> createAnnuncio(@RequestBody AnnuncioPayload payload) {
        try {
            Annuncio createdAnnuncio = annuncioService.createAnnuncio(payload);
            //MESSAGGIO DI CONFERMA PUBBLICAZIONE ANNUNCIO SU POSTMAN
            return ResponseEntity.status(HttpStatus.CREATED).body("L'annuncio è stato pubblicato con successo.");
        } catch (BadRequestException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Si è verificato un errore durante la pubblicazione dell'annuncio.");
        }
    }


    @GetMapping("/{idAnnuncio}")
    public Annuncio getAnnuncioById(@PathVariable UUID id) {
        return annuncioService.findById(id);
    }

    @GetMapping("/titolo/{titoloAnnuncio}")
    public List<Annuncio> getAnnunciByTitolo(@PathVariable String titoloAnnuncio) {
        return annuncioService.findByTitolo(titoloAnnuncio);
    }

    @GetMapping("/categoria/{categoria}")
    public List<Annuncio> getAnnunciByCategoria(@PathVariable CategoriaAnnuncio categoria) {
        return annuncioService.findByCategoria(categoria);
    }

}