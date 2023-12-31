package GerenciaPropostas.com.api.entities.produto;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroProduto(
		
		@NotBlank
		String descricao,
		@NotBlank
		String unidade,
		@NotNull
		BigDecimal valorUnitarioTabela) {

}
