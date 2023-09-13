package marco.tumminia.capstone.codify;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import marco.tumminia.capstone.codify.entities.annuncio.Annuncio;
import marco.tumminia.capstone.codify.entities.annuncio.AnnuncioService;
import marco.tumminia.capstone.codify.entities.annuncio.CategoriaAnnuncio;
import marco.tumminia.capstone.codify.entities.annuncio.StatoAnnuncio;
import marco.tumminia.capstone.codify.entities.azienda.Azienda;
import marco.tumminia.capstone.codify.entities.azienda.AziendaService;
import marco.tumminia.capstone.codify.entities.privato.Privato;
import marco.tumminia.capstone.codify.entities.privato.PrivatoService;
import marco.tumminia.capstone.codify.entities.utente.Utente;
import marco.tumminia.capstone.codify.exceptions.NotFoundException;

import java.time.LocalDate;
import java.util.UUID;

@Component
public class AnnuncioRunner implements CommandLineRunner {

    @Autowired
    private AnnuncioService annuncioService;

    @Autowired
    private PrivatoService privatoService;

    @Autowired
    private AziendaService aziendaService;

        @Override
        public void run(String... args) throws Exception {
            // Suppongo che tu abbia un metodo per cercare per ID nelle tue classi di servizio
            Utente privato = privatoService.findById(UUID.fromString("bd66ed7a-2366-41f9-a2be-fdc3f01ebab6"));
            Utente azienda = aziendaService.findById(UUID.fromString("fcae8ba4-2391-4395-8a0d-299199daad73"));

            // Crea un annuncio per il privato
            Annuncio annuncioPrivato = new Annuncio(
                    privato, 
                    "Titolo per Privato", 
                    "Descrizione per Privato", 
                    CategoriaAnnuncio.SVILUPPO_WEB, 
                    StatoAnnuncio.APERTO, 
                    1000.0, 
                    LocalDate.now(), 
                    LocalDate.now().plusDays(30)
            );
            annuncioService.save(annuncioPrivato);

            // Crea un annuncio per l'azienda
            Annuncio annuncioAzienda = new Annuncio(
                    azienda, 
                    "Titolo per Azienda", 
                    "Descrizione per Azienda", 
                    CategoriaAnnuncio.CONSULENZA, 
                    StatoAnnuncio.APERTO, 
                    2000.0, 
                    LocalDate.now(), 
                    LocalDate.now().plusDays(30)
            );
            annuncioService.save(annuncioAzienda);
        }
    }
