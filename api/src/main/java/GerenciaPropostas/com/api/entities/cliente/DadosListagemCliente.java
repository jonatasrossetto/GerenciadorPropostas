package GerenciaPropostas.com.api.entities.cliente;

public record DadosListagemCliente(Long id, String nome, String email, String telefone, String empresa) {
	
	public DadosListagemCliente(Cliente cliente) {
		this(cliente.getId(), cliente.getNome(),cliente.getEmail(),cliente.getTelefone(),cliente.getEmpresa());
	}

}
