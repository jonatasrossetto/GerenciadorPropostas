package GerenciaPropostas.com.api.entities.proposta;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public record DadosCadastroProposta(
		long cliente, 
		String titulo, 
		String situacao,
		Date dataCriacao) {

}
