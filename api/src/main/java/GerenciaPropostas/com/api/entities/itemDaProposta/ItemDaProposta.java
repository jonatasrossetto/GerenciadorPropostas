package GerenciaPropostas.com.api.entities.itemDaProposta;

import java.math.BigDecimal;
import java.sql.Date;

import GerenciaPropostas.com.api.entities.proposta.Proposta;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Table(name="itens_da_proposta")
@Entity(name="ItemDaProposta")
@Getter
@AllArgsConstructor
@EqualsAndHashCode(of="id")
public class ItemDaProposta {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(name="id_proposta")
	private long proposta;
	@Column(name="id_produto")
	private long produto;
	@Column(name="quantidade")
	private long quantidade;
	@Column(name="valor_unitario")
	private BigDecimal valorUnitario;
	@Column(name="prazo_entrega")
	private long prazoEntrega;
	@Column(name="data_criacao")
	private Date dataCriacao;
	
	public ItemDaProposta() {
	}
	
	public ItemDaProposta(DadosCadastroItemDaProposta dados) {
		this.proposta = dados.proposta();
		this.produto=dados.produto();
		this.quantidade=dados.quantidade();
		this.valorUnitario=dados.valorUnitario();
		this.prazoEntrega=dados.prazoEntrega();
		this.dataCriacao=dados.dataCriacao();
	}
	
	public void atualizarInformacoes(DadosAtualizacaoItemDaProposta dados) {
		if (dados.produto() != this.produto ) {
			this.produto = dados.produto();
		}
		if (dados.quantidade() != this.quantidade ) {
			this.quantidade = dados.quantidade();
		}
		if (dados.valorUnitario() != this.valorUnitario ) {
			this.valorUnitario = dados.valorUnitario();
		}
		if (dados.prazoEntrega() != this.prazoEntrega ) {
			this.prazoEntrega = dados.prazoEntrega();
		}
		if (dados.dataCriacao() != this.dataCriacao ) {
			this.dataCriacao = dados.dataCriacao();
		}
	}
	
}
