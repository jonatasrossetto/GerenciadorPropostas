package GerenciaPropostas.com.api.entities.produto;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosListagemProduto(
		Long id,
		Long usuario,
		String descricao,
		String unidade,
		BigDecimal valorUnitarioTabela) {
	
	public DadosListagemProduto(Produto produto) {
		this(produto.getId(),produto.getUsuario(),produto.getDescricao(),produto.getUnidade(),produto.getValorUnitarioTabela());
	}
	
}