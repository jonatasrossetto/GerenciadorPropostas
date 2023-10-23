package GerenciaPropostas.com.api.entities.observacaoDaProposta;

import java.sql.Date;

public record DadosDetalhamentoObservacaoDaProposta(
		Long id,
		Long idproposta,
		String observacao,
		Date dataCriacao) {

	public DadosDetalhamentoObservacaoDaProposta(ObservacaoDaProposta observacao) {
		this(observacao.getId(),observacao.getIdproposta(),observacao.getObservacao(),observacao.getDataCriacao());
	}

}
