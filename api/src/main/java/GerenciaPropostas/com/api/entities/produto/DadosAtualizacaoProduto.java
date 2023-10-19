package GerenciaPropostas.com.api.entities.produto;

import java.math.BigDecimal;

public record DadosAtualizacaoProduto(
		Long id,
		String descricao,
		String unidade,
		BigDecimal valorUnitarioTabela) {

}
