package marco.tumminia.capstone.codify.entities.recensione;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import marco.tumminia.capstone.codify.entities.utente.Utente;
import marco.tumminia.capstone.codify.entities.utente.UtenteService;
import marco.tumminia.capstone.codify.exceptions.NotFoundException;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/recensioni")
public class RecensioneController {

    @Autowired
    private RecensioneService recensioneService;
    
    @Autowired
    private UtenteService utenteService;
    
    @Autowired
    private RecensioneRepository recensioneRepository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Recensione creaRecensione(@RequestBody Recensione recensione) {
        return recensioneService.saveRecensione(recensione);
    }

   // @GetMapping
   // public List<Recensione> getTutteLeRecensioni() {
   //     return recensioneService.findAll();
   // }
    
    @GetMapping("/recensore/{utenteId}")
    public List<Recensione> getRecensioniByUtenteRecensore(@PathVariable UUID utenteId) {
        Utente utenteRecensore = utenteService.findById(utenteId);
        if(utenteRecensore == null) {
            throw new NotFoundException("Utente non trovato");
        }
        return recensioneService.findByUtenteRecensore(utenteRecensore);
    }


    @GetMapping("/recensito/{utenteId}")
    public List<Recensione> getRecensioniByUtenteRecensito(@PathVariable UUID utenteId) {
        Utente utenteRecensito = utenteService.findById(utenteId);
        if(utenteRecensito == null) {
            throw new NotFoundException("Utente non trovato");
        }
        return recensioneService.findByUtenteRecensito(utenteRecensito);
    }

    @GetMapping("/recensito/{utenteId}")
    public List<Recensione> getRecensioniByUtenteRecensito(
            @PathVariable UUID utenteId,
            @RequestParam(required = false) PunteggioRecensione punteggio) {
        return recensioneService.findByUtenteRecensitoAndPunteggio(utenteId, punteggio);
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
