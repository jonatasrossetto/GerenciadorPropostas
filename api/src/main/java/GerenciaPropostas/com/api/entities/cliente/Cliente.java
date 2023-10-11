package GerenciaPropostas.com.api.entities.cliente;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name="clientes")
@Entity(name="Cliente")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="id")
public class Cliente {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String email;
	private String telefone;
	private String empresa;
	
	public Cliente() {
		
	}
	
	public Cliente(DadosCadastroCliente dados) {
		this.nome = dados.nome();
		this.email = dados.email();
		this.telefone = dados.telefone();
		this.empresa = dados.empresa();
	}
	
	public void atualizarInformacoes(DadosAtualizacaoCliente dados) {
		if (dados.nome() != null ) {
			this.nome = dados.nome();
		}
		if(dados.email() !=null) {
			this.email=dados.email();
		}
		if(dados.telefone() !=null) {
			this.telefone=dados.telefone();
		}
		if(dados.empresa() !=null) {
			this.empresa=dados.empresa();
		}
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getEmail() {
		return email;
	}

	public String getTelefone() {
		return telefone;
	}

	public String getEmpresa() {
		return empresa;
	}
	
}
