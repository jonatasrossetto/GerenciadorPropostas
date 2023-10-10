package GerenciaPropostas.com.api.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Example;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import GerenciaPropostas.com.api.entities.cliente.Cliente;
import GerenciaPropostas.com.api.entities.cliente.ClienteRepository;
import GerenciaPropostas.com.api.entities.cliente.DadosCadastroCliente;
import GerenciaPropostas.com.api.entities.cliente.DadosListagemCliente;
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
	public List<DadosListagemCliente> listar() {
		return repository.findAll().stream().map(DadosListagemCliente::new).toList();
	}
	
}
