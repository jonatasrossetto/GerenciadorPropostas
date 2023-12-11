package GerenciaPropostas.com.api.entities.itemDaProposta;

import java.math.BigDecimal;
import java.sql.Date;

public record DadosAtualizacaoItemDaProposta(
		long id,
		long proposta,
		long produto,
		long quantidade,
		BigDecimal valorUnitario,
		long prazoEntrega,
		Date dataCriacao
) {

}
