package marco.tumminia.capstone.codify.entities.proposta;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import marco.tumminia.capstone.codify.entities.sviluppatore.Sviluppatore;

@Entity
@Table(name = "proposte_sviluppatori")
@Data
@NoArgsConstructor


public class PropostaSviluppatore {
	
	@Id
	@GeneratedValue
	private UUID idProposta;
	
	@ManyToOne
	@JoinColumn(name = "id_sviluppatore")
	private Sviluppatore sviluppatore;
	
	


}
