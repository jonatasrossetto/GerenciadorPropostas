package GerenciaPropostas.com.api.entities.observacaoDaProposta;

import java.sql.Date;

public record DadosCadastroObservacaoDaProposta(
		Long idproposta,
		String observacao,
		Date dataCriacao) {

}
