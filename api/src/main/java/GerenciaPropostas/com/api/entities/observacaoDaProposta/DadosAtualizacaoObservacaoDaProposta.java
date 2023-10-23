package GerenciaPropostas.com.api.entities.observacaoDaProposta;

import java.sql.Date;

public record DadosAtualizacaoObservacaoDaProposta(
		Long id,
		Long idproposta,
		String observacao,
		Date dataCriacao) {

}
