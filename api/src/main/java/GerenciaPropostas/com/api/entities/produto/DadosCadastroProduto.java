package GerenciaPropostas.com.api.entities.produto;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;

public record DadosCadastroProduto(
		@NotBlank
		long idUsuario,
		@NotBlank
		String descricao,
		@NotBlank
		String unidade,
		@NotBlank
		BigDecimal valorUnitarioTabela) {

}
