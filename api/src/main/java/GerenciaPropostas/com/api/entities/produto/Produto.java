package GerenciaPropostas.com.api.entities.produto;

import java.math.BigDecimal;

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
	private long idUsuario;
	private String descricao;
	
	private String unidade;
	private BigDecimal valorUnitarioTabela;
	
	public Produto() {
		
	}

	public Produto(DadosCadastroProduto dados) {
		this.idUsuario = dados.idUsuario();
		this.descricao = dados.descricao();
		this.unidade = dados.unidade();
		this.valorUnitarioTabela = dados.valorUnitarioTabela();
	}
	
	public long getId() {
		return id;
	}

	public long getIdUsuario() {
		return idUsuario;
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
	
	
	
}
