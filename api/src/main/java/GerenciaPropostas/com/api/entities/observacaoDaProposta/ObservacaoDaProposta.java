package GerenciaPropostas.com.api.entities.observacaoDaProposta;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name="observacoes_da_proposta")
@Entity(name="ObservacaoDaProposta")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="id")
public class ObservacaoDaProposta {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name="id_proposta")
	private Long idproposta;
	private String observacao;
	@Column(name="data_criacao")
	private Date dataCriacao;
	
	public ObservacaoDaProposta() {
	}
	
	
}
