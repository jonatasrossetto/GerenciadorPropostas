package GerenciaPropostas.com.api.entities.produto;

import java.math.BigDecimal;

public record DadosDetalhamentoProduto(
		Long id,
		Long usuario,
		String descricao,
		String unidade,
		BigDecimal valorUnitarioTabela) {
	
	public DadosDetalhamentoProduto(Produto produto) {
		this(produto.getId(),produto.getUsuario(),produto.getDescricao(),produto.getUnidade(),produto.getValorUnitarioTabela());
	}
	
}
