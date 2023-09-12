package marco.tumminia.capstone.codify.entities.sviluppatore;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import marco.tumminia.capstone.codify.entities.proposta.PropostaSviluppatore;

@RestController
@RequestMapping("/sviluppatori")
public class SviluppatoreController {

    @Autowired
    SviluppatoreService sviluppatoreService;

    @GetMapping("/{idSviluppatore}/proposte")
    public List<PropostaSviluppatore> getPropostePerSviluppatore(@PathVariable UUID idSviluppatore) {
        return sviluppatoreService.getPropostePerSviluppatore(idSviluppatore);
    }

    // Aggiungi eventuali altri endpoint specifici...
}
