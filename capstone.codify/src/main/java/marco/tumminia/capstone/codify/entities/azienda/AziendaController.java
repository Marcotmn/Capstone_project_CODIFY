package marco.tumminia.capstone.codify.entities.azienda;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

import marco.tumminia.capstone.codify.entities.annuncio.Annuncio;

@RestController
@RequestMapping("/azienda")
public class AziendaController {

    @Autowired
    private AziendaService aziendaService;
    
    
    @PostMapping("/register")
    public ResponseEntity<Azienda> registerAzienda(@RequestBody AziendaPayload payload) {
        Azienda createdAzienda = aziendaService.save(payload);
        return new ResponseEntity<>(createdAzienda, HttpStatus.CREATED);
    }

    @GetMapping("/{idAzienda}")
    public Azienda getAziendaById(@PathVariable UUID idAzienda) {
        return aziendaService.findById(idAzienda);
    }

    @GetMapping("/nome/{nomeAzienda}")
    public List<Azienda> getAziendaByNome(@PathVariable String nomeAzienda) {
        return aziendaService.findByNomeAzienda(nomeAzienda);
    }
    
    @PutMapping("/{idAzienda}")
    public Azienda updateAzienda(@PathVariable UUID idAzienda, @RequestBody Azienda updatedData) {
        return aziendaService.updateAzienda(idAzienda, updatedData);
    }

    @DeleteMapping("/{idAzienda}")
    public ResponseEntity<Void> deleteAzienda(@PathVariable UUID idAzienda) {
        aziendaService.deleteAzienda(idAzienda);
        return ResponseEntity.noContent().build();
    }
}
