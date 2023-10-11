package GerenciaPropostas.com.api.entities.cliente;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoCliente(
		
		@NotNull
		Long id, 
		String nome, 
		String email, 
		String telefone, 
		String empresa) {

}
