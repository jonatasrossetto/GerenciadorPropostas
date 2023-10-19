package GerenciaPropostas.com.api.entities.produto;

import java.math.BigDecimal;

import GerenciaPropostas.com.api.entities.cliente.DadosAtualizacaoCliente;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Digits;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name="produtos")
@Entity(name="Produto")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="id")
public class Produto {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name="id_usuario")
	private long usuario;
	
	private String descricao;
	
	private String unidade;
	private BigDecimal valorUnitarioTabela;
	
	public Produto() {
		
	}

	public Produto(DadosCadastroProduto dados) {
		this.usuario = 0;
		this.descricao = dados.descricao();
		this.unidade = dados.unidade();
		this.valorUnitarioTabela = dados.valorUnitarioTabela();
	}
	
	public void setUsuario(long idUsuario) {
		this.usuario = idUsuario;
	}

	public long getId() {
		return id;
	}

	public long getUsuario() {
		return usuario;
	}

	public String getDescricao() {
		return descricao;
	}

	public String getUnidade() {
		return unidade;
	}

	public BigDecimal getValorUnitarioTabela() {
		return valorUnitarioTabela;
	}
	
	public void atualizarInformacoes(DadosAtualizacaoProduto dados) {
		if (dados.descricao() != null ) {
			this.descricao = dados.descricao();
		}
		if(dados.unidade() !=null) {
			this.unidade=dados.unidade();
		}
		if(dados.valorUnitarioTabela() !=null) {
			this.valorUnitarioTabela=dados.valorUnitarioTabela();
		}
	}
	
}
