package GerenciaPropostas.com.api.entities.proposta;

import java.sql.Date;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoProposta(

		@NotNull
		Long id, 
		long usuario,
		long cliente, 
		String titulo, 
		String situacao,
		Date dataCriacao) 
 {
	
}
