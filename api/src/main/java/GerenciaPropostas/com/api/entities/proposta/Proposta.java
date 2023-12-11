package GerenciaPropostas.com.api.entities.proposta;

import java.sql.Date;
import java.util.Objects;

import GerenciaPropostas.com.api.entities.cliente.DadosAtualizacaoCliente;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name="propostas")
@Entity(name="Proposta")
@Getter
@AllArgsConstructor
@EqualsAndHashCode(of="id")
public class Proposta {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(name="id_usuario")
	private long usuario;
	@Column(name="id_cliente")
	private long cliente;
	private String titulo;
	private String situacao;
	@Column(name="data_criacao")
	private Date dataCriacao;
	
	public Proposta() {
		
	}
	
	public Proposta(DadosCadastroProposta dados) {
		this.cliente = dados.cliente();
		this.titulo = dados.titulo();
		this.situacao = dados.situacao();
		this.dataCriacao = dados.dataCriacao();
	}

	public long getUsuario() {
		return usuario;
	}

	public void setUsuario(long usuario) {
		this.usuario = usuario;
	}

	public long getId() {
		return id;
	}

	public long getCliente() {
		return cliente;
	}

	public String getTitulo() {
		return titulo;
	}

	public String getSituacao() {
		return situacao;
	}

	public Date getDataCriacao() {
		return dataCriacao;
	}
	
	
	public void atualizarInformacoes(DadosAtualizacaoProposta dados) {
	
		if (!Objects.isNull(dados.cliente())) {
			this.cliente = dados.cliente();
		}
		if (dados.titulo()!=null) {
			this.titulo = dados.titulo();
		}
		if (dados.situacao()!=null) {
			this.situacao=dados.situacao();
		}
		
	
	}
	
	
	
}
