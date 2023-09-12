package marco.tumminia.capstone.codify.entities.annuncio;

import java.util.UUID;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/annunci")
public class AnnuncioController {

    @Autowired
    private AnnuncioService annuncioService;

    @GetMapping("/{idAnnuncio}")
    public Annuncio getAnnuncioById(@PathVariable UUID idAnnuncio) {
        return annuncioService.findById(idAnnuncio);
    }

    @GetMapping("/titolo/{titoloAnnuncio}")
    public List<Annuncio> getAnnunciByTitolo(@PathVariable String titoloAnnuncio) {
        return annuncioService.findByTitolo(titoloAnnuncio);
    }

    @GetMapping("/privato/{idPrivato}")
    public List<Annuncio> getAnnunciByPrivato(@PathVariable UUID idPrivato) {
        return annuncioService.findByPrivato(idPrivato);
    }

    @GetMapping("/azienda/{idAzienda}")
    public List<Annuncio> getAnnunciByAzienda(@PathVariable UUID idAzienda) {
        return annuncioService.findByAzienda(idAzienda);
    }

    @GetMapping("/categoria/{categoria}")
    public List<Annuncio> getAnnunciByCategoria(@PathVariable CategoriaAnnuncio categoria) {
        return annuncioService.findByCategoria(categoria);
    }

}