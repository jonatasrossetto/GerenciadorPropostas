package GerenciaPropostas.com.api.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import GerenciaPropostas.com.api.entities.cliente.Cliente;
import GerenciaPropostas.com.api.entities.cliente.ClienteRepository;
import GerenciaPropostas.com.api.entities.cliente.DadosAtualizacaoCliente;
import GerenciaPropostas.com.api.entities.cliente.DadosCadastroCliente;
import GerenciaPropostas.com.api.entities.cliente.DadosListagemCliente;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
	
	@Autowired
	private ClienteRepository repository;
	
	@PostMapping
	public void cadastrar(@RequestBody @Valid DadosCadastroCliente json) {
		System.out.println(json.nome());
		System.out.println(json.email());
		System.out.println(json.telefone());
		System.out.println(json.empresa());
		repository.save(new Cliente(json));
	}
	
	@GetMapping
	public Page<DadosListagemCliente> listar(@PageableDefault(size = 10,
			sort = {"nome"}) Pageable paginacao) {
		return repository.findAll(paginacao).map(DadosListagemCliente::new);
//		exemplo de query: http://localhost:8080/clientes?size=2 ?sort=crm,desc&size=2&page=1
//		exemplo de query: http://localhost:8080/clientes?sort=nome,desc&size=2&page=1
	}
	
	@PutMapping
	@Transactional
	public void atualizar(@RequestBody @Valid DadosAtualizacaoCliente dados) {
		var cliente = repository.getReferenceById(dados.id());
		cliente.atualizarInformacoes(dados);
	}
	
	
}
