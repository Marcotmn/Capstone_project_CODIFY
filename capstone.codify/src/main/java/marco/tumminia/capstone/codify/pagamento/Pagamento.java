package marco.tumminia.capstone.codify.pagamento;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "pagamenti")
public class Pagamento {

    @Id
    @GeneratedValue
    private UUID id;

    private UUID idAnnuncio;
    private String nomeAnnuncio;
    private double importo;
    private LocalDate dataPagamento;
    private String emailSviluppatore;
    private String partitaIvaSviluppatore;
    private String emailUtentePagante;


    public Pagamento(UUID idAnnuncio, String nomeAnnuncio, double importo, LocalDate dataPagamento, String emailSviluppatore, String partitaIvaSviluppatore, String emailUtentePagante) {
        this.idAnnuncio = idAnnuncio;
        this.nomeAnnuncio = nomeAnnuncio;
        this.importo = importo;
        this.dataPagamento = dataPagamento;
        this.emailSviluppatore = emailSviluppatore;
        this.partitaIvaSviluppatore = partitaIvaSviluppatore;
        this.emailUtentePagante = emailUtentePagante;
    } 
}
