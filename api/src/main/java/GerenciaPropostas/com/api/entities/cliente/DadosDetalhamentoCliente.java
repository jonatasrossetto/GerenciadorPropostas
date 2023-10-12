package GerenciaPropostas.com.api.entities.cliente;

public record DadosDetalhamentoCliente(
		Long id, 
		String nome, 
		String email, 
		String telefone, 
		String empresa) {
	
	public DadosDetalhamentoCliente(Cliente cliente) {
		this(cliente.getId(),cliente.getNome(),cliente.getEmail(),cliente.getTelefone(),cliente.getEmpresa());
	}

}
