package marco.tumminia.capstone.codify.entities.proposta;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import marco.tumminia.capstone.codify.entities.sviluppatore.Sviluppatore;
import marco.tumminia.capstone.codify.exceptions.NotFoundException;

@RestController
@RequestMapping("/api/proposte")
public class PropostaSviluppatoreController {

    @Autowired
    private PropostaSviluppatoreService propostaService;

    @PostMapping
    public PropostaSviluppatore createProposta(@RequestBody PropostaSviluppatore proposta) {
        return propostaService.saveProposta(proposta);
    }

    @GetMapping("/{id}")
    public PropostaSviluppatore getProposta(@PathVariable UUID id) {
        return propostaService.findById(id).orElseThrow(() -> new NotFoundException("Proposta non trovata"));
    }


    @GetMapping("/sviluppatore/{idSviluppatore}")
    public List<PropostaSviluppatore> getProposteBySviluppatore(@PathVariable UUID idSviluppatore) {
        Sviluppatore sviluppatore = sviluppatoreService.findById(idSviluppatore).orElseThrow(() -> new NotFoundException("Sviluppatore non trovato"));
        return propostaService.findBySviluppatore(sviluppatore);
    }


    @DeleteMapping("/{id}")
    public void deleteProposta(@PathVariable UUID id) {
        propostaService.deleteById(id);
    }

    // Potrai anche avere endpoint per aggiornare una proposta ecc.
}
