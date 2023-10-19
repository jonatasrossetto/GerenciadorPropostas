package GerenciaPropostas.com.api.entities.proposta;

import java.sql.Date;

public record DadosDetalhamentoProposta(
		long id,
		long usuario,
		long cliente, 
		String titulo, 
		String situacao,
		Date dataCriacao) 
 {
	
	public DadosDetalhamentoProposta(Proposta dados) {
		this(dados.getId(),dados.getUsuario(),dados.getCliente(),dados.getTitulo(),dados.getSituacao(),dados.getDataCriacao());
	}

}
