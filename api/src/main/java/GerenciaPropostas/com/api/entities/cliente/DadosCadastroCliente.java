package GerenciaPropostas.com.api.entities.cliente;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record DadosCadastroCliente(
		
		@NotBlank(message="Nome é obrigatório")
		String nome,
		
		@NotBlank(message="E-mail é obrigatório")
		@Email(message="Formato do e-mail é inválido")
		String email, 
		
		@NotBlank(message="Telefone é obrigatório")
		String telefone, 
		
		@NotBlank(message="Empresa é obrigatório")
		String empresa
		
		) {
	
}
