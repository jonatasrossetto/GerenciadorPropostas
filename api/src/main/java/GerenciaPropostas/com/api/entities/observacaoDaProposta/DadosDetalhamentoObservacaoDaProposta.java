package GerenciaPropostas.com.api.entities.observacaoDaProposta;

import java.sql.Date;

public record DadosDetalhamentoObservacaoDaProposta(
		Long id,
		Long idproposta,
		String observacao,
		Date dataCriacao) {

}
