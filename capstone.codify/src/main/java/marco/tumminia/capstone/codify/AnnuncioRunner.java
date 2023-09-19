package marco.tumminia.capstone.codify;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import marco.tumminia.capstone.codify.entities.annuncio.Annuncio;
import marco.tumminia.capstone.codify.entities.annuncio.AnnuncioPayload;
import marco.tumminia.capstone.codify.entities.annuncio.AnnuncioService;
import marco.tumminia.capstone.codify.entities.annuncio.CategoriaAnnuncio;
import marco.tumminia.capstone.codify.entities.annuncio.StatoAnnuncio;

import java.time.LocalDate;

@Component
public class AnnuncioRunner implements CommandLineRunner {

    @Autowired
    private AnnuncioService annuncioService;

    @Override
    public void run(String... args) throws Exception {
        // Creare un esempio di payload per l'annuncio
        AnnuncioPayload payload = new AnnuncioPayload(
                "Annuncio di esempio",
                "Descrizione di esempio",
                CategoriaAnnuncio.SVILUPPO_WEB,
                StatoAnnuncio.IN_CORSO,
                1000.0,
                LocalDate.now(),
                LocalDate.now().plusDays(30)
        );

        // Chiamare il servizio per creare un nuovo annuncio
// Annuncio nuovoAnnuncio = annuncioService.createAnnuncio(payload);

        // Stampare l'annuncio appena creato

}}
