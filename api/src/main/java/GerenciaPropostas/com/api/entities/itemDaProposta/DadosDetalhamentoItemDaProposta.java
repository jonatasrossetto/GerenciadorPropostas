package GerenciaPropostas.com.api.entities.itemDaProposta;

import java.math.BigDecimal;
import java.sql.Date;

public record DadosDetalhamentoItemDaProposta(
		long id,
		long proposta,
		long produto,
		long quantidade,
		BigDecimal valorUnitario,
		long prazoEntrega,
		Date dataCriacao
)  {
	
	public DadosDetalhamentoItemDaProposta(ItemDaProposta dados) {
		this(dados.getId(),dados.getProposta(),dados.getProduto(),dados.getQuantidade(),dados.getValorUnitario(),dados.getPrazoEntrega(),dados.getDataCriacao());
	}

}
